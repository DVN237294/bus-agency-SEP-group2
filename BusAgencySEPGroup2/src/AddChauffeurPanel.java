import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddChauffeurPanel extends JFrame
{
   private static final long serialVersionUID = 1L;
   private TravelAgency agency;
   private TextField firstNameField;
   private TextField lastNameField;
   private TextField chauffeurIdField;
   private JLabel firstNameLabel;
   private JLabel lastNameLabel;
   private JLabel chauffeurIdLabel;
   private JButton saveButton;

   private JPanel addChauffeurNorthPanel = new JPanel();

   public AddChauffeurPanel(TravelAgency agency)
   {
      super("Add Chauffeur");
      this.agency = agency;
      addChauffeurNorthPanel.setBorder(BorderFactory
            .createTitledBorder("Input Bus Information"));
      addChauffeurNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      firstNameField = new TextField(10);
      lastNameField = new TextField(10);
      chauffeurIdField = new TextField(5);
      firstNameLabel = new JLabel("First Name");
      lastNameLabel = new JLabel("Last Name");
      chauffeurIdLabel = new JLabel("Chauffeur ID");
      saveButton = new JButton("Save");
      addChauffeurNorthPanel.add(firstNameLabel);
      addChauffeurNorthPanel.add(firstNameField);
      addChauffeurNorthPanel.add(lastNameLabel);
      addChauffeurNorthPanel.add(lastNameField);
      addChauffeurNorthPanel.add(chauffeurIdLabel);
      addChauffeurNorthPanel.add(chauffeurIdField);
      addChauffeurNorthPanel.add(saveButton);
      saveButton.addActionListener(new SaveAction());

      setSize(960, 540);
      setVisible(true);

      this.setLayout(new BorderLayout());
      this.add(addChauffeurNorthPanel, BorderLayout.NORTH);
   }

   public class SaveAction implements ActionListener
   {

      @Override
      public void actionPerformed(ActionEvent e)
      {
         agency.addChauffeur(firstNameField.getText(), lastNameField.getText(),
               Integer.parseInt(chauffeurIdField.getText()));
         AddChauffeurPanel.this.dispose();
      }

   }
}
