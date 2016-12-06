import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BussesPane extends JPanel
{
   private static final long serialVersionUID = 1L;
   private TravelAgency agency;
   private JExtendedComboBox<String> destiBox;
   private JExtendedComboBox<String> makeBox;
   private JExtendedComboBox<String> modelBox;
   private JExtendedComboBox<String> licensePlateBox;
   private JExtendedComboBox<Chauffeur> chauffeurBox;
   private JButton searchButton;
   private JList<Bus> busList;
   private JTextArea infoBusList;
   private JButton deleteButton;

   JPanel bussesNorthPanel = new JPanel();
   JPanel bussesWestPanel = new JPanel();
   JPanel bussesSouthPanel = new JPanel();

   public BussesPane(TravelAgency agency)
   {
      super();
      this.agency = agency;
      Bus[] allBusses = agency.getAllBusses();

      String[] makeArray = new String[allBusses.length];
      String[] modelArray = new String[allBusses.length];
      String[] licensePlateArray = new String[allBusses.length];

      for (int i = 0; i < allBusses.length; i++)
      {
         makeArray[i] = allBusses[i].getMake();
         modelArray[i] = allBusses[i].getModel();
         licensePlateArray[i] = allBusses[i].getLicensePlate();
      }

      bussesNorthPanel.setBorder(BorderFactory.createTitledBorder("Search"));
      bussesNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      bussesWestPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
      destiBox = new JExtendedComboBox<String>(agency.getAllDestinations());
      destiBox.setPrototypeDisplayValue("Destination");
      destiBox.setDefaultDisplayedItem("Destination");
      makeBox = new JExtendedComboBox<>(makeArray);
      makeBox.setPrototypeDisplayValue("Make");
      makeBox.setDefaultDisplayedItem("Make");
      modelBox = new JExtendedComboBox<>(modelArray);
      modelBox.setPrototypeDisplayValue("Model");
      modelBox.setDefaultDisplayedItem("Model");
      licensePlateBox = new JExtendedComboBox<>(licensePlateArray);
      licensePlateBox.setPrototypeDisplayValue("License Plate");
      licensePlateBox.setDefaultDisplayedItem("License Plate");
      chauffeurBox = new JExtendedComboBox<Chauffeur>(agency.getAllChauffeurs());
      searchButton = new JButton("Search");
      deleteButton = new JButton("Delete");
      busList = new JList<Bus>();
      infoBusList = new JTextArea();
      infoBusList.setVisible(false);
      busList.setVisible(false);
      deleteButton.setVisible(false);

      bussesNorthPanel.add(destiBox);
      bussesNorthPanel.add(chauffeurBox);
      bussesNorthPanel.add(makeBox);
      bussesNorthPanel.add(modelBox);
      bussesNorthPanel.add(licensePlateBox);
      bussesWestPanel.add(busList);
      bussesNorthPanel.add(searchButton);
      bussesSouthPanel.add(deleteButton);
      bussesWestPanel.add(infoBusList);
      searchButton.addActionListener(new SearchAllListener());
      busList.addListSelectionListener(new InformationListener());
      this.setLayout(new BorderLayout());
      this.add(bussesNorthPanel, BorderLayout.NORTH);
      this.add(bussesWestPanel, BorderLayout.WEST);
      this.add(bussesSouthPanel, BorderLayout.SOUTH);
   }

   private class SearchAllListener implements ActionListener
   {

      @Override
      public void actionPerformed(ActionEvent e)
      {
         DefaultListModel<Bus> model = new DefaultListModel<>();
         model.addElement(agency.getBus(makeBox.getSelectedItem(),
               modelBox.getSelectedItem(), licensePlateBox.getSelectedItem()));
         busList.setModel(model);
         busList.setVisible(true);
         deleteButton.setVisible(true);
      }

   }

   private class InformationListener implements ListSelectionListener
   {

      @Override
      public void valueChanged(ListSelectionEvent e)
      {
         infoBusList.setText(busList.getSelectedValue().toString());
         infoBusList.setEditable(false);
         infoBusList.setVisible(true);
      }

   }
}
