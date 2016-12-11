import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChauffeursPane extends JPanel
{
   private static final long serialVersionUID = 1L;
   private TravelAgency agency;
   private JExtendedComboBox<String> destiBox;
   private JExtendedComboBox<String> firstNameBox;
   private JExtendedComboBox<String> lastNameBox;
   private JExtendedComboBox<String> chauffeurIdBox;
   private JList<Chauffeur> chauffeurList;
   private JButton searchButton;
   private JButton searchAllButton;
   private JTextArea infoChauffeurList;

   private JPanel chauffeurNorthPanel = new JPanel();
   private JPanel chauffeurWestPanel = new JPanel();

   public ChauffeursPane(TravelAgency agency)
   {
      super();
      this.agency = agency;

      chauffeurNorthPanel.setBorder(BorderFactory.createTitledBorder("Search"));
      chauffeurNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      chauffeurWestPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      destiBox = new JExtendedComboBox<String>(agency.getAllDestinations());
      firstNameBox = new JExtendedComboBox<String>(agency.getAllFirstNames());
      lastNameBox = new JExtendedComboBox<String>(agency.getAllLastNames());
      chauffeurIdBox = new JExtendedComboBox<String>(
            agency.getAllChauffeurIds());
      chauffeurList = new JList<Chauffeur>(new DefaultListModel<Chauffeur>());
      searchButton = new JButton("Search");
      searchAllButton = new JButton("Search All");
      infoChauffeurList = new JTextArea();
      chauffeurList.setVisible(false);
      infoChauffeurList.setVisible(false);

      chauffeurNorthPanel.add(destiBox);
      chauffeurNorthPanel.add(firstNameBox);
      chauffeurNorthPanel.add(lastNameBox);
      chauffeurNorthPanel.add(chauffeurIdBox);
      chauffeurNorthPanel.add(searchButton);
      chauffeurNorthPanel.add(searchAllButton);
      chauffeurWestPanel.add(chauffeurList);
      chauffeurWestPanel.add(infoChauffeurList);
      searchButton.addActionListener(new SearchAction());
      searchAllButton.addActionListener(new SearchAllAction());
      chauffeurList.addListSelectionListener(new InformationListener());

      setSize(960, 540);
      setVisible(true);

      this.setLayout(new BorderLayout());
      this.add(chauffeurNorthPanel, BorderLayout.NORTH);
      this.add(chauffeurWestPanel, BorderLayout.WEST);
   }

   public class SearchAction implements ActionListener
   {

      @Override
      public void actionPerformed(ActionEvent e)
      {
         DefaultListModel<Chauffeur> model = (DefaultListModel<Chauffeur>) chauffeurList
               .getModel();
         model.removeAllElements();
         model.addElement(agency.getChauffeur(firstNameBox.getSelectedItem(),
               lastNameBox.getSelectedItem(), Integer.parseInt(chauffeurIdBox.getSelectedItem())));
         chauffeurList.setVisible(true);
      }

   }
   
   public class SearchAllAction implements ActionListener
   {

      @Override
      public void actionPerformed(ActionEvent e)
      {
         DefaultListModel<Chauffeur> model = (DefaultListModel<Chauffeur>) chauffeurList
               .getModel();
         model.removeAllElements();
         for (Chauffeur chauffeur : agency.getAllChauffeurs())
         {
            model.addElement(chauffeur);
         }
         chauffeurList.setVisible(true);
      }
      
   }
   
   public class InformationListener implements ListSelectionListener
   {

      @Override
      public void valueChanged(ListSelectionEvent e)
      {
         if (!chauffeurList.isSelectionEmpty() && !chauffeurList.isSelectionEmpty())
         {
            infoChauffeurList.setText(chauffeurList.getSelectedValue().toString());
            infoChauffeurList.setEditable(false);
            infoChauffeurList.setVisible(true);
         }
      }
      
   }
}
