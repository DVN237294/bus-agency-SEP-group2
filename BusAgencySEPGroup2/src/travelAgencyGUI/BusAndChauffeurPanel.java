package travelAgencyGUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import travelAgencyModel.Bus;
import travelAgencyModel.TravelAgency;



public class BusAndChauffeurPanel extends JPanel
{
   private static final long serialVersionUID = 1L;
   private TravelAgency agency;
   
   private JPanel busAndChauffeurNorthPanel = new JPanel();
   private JPanel busAndChauffeurWestPanel = new JPanel();
   private JPanel busAndChauffeurSouthPanel = new JPanel();
    
   private JExtendedComboBox<String> firstNameBox;
   private JExtendedComboBox<String> lastNameBox;
   private JExtendedComboBox<String> chauffeurIdBox;
   private JExtendedComboBox<String> makeBox;
   private JExtendedComboBox<String> modelBox;
   private JExtendedComboBox<String> licensePlateBox;
   private JExtendedComboBox<Integer> maxCapacityBox;
   
   public BusAndChauffeurPanel(TravelAgency agency)
   {
      super();
      this.agency = agency;
      
      Bus[] allBusses = agency.getAllBusses();

      String[] makeArray = new String[allBusses.length];
      String[] modelArray = new String[allBusses.length];
      String[] licensePlateArray = new String[allBusses.length];
      Integer[] maxCapacityArray = new Integer[allBusses.length];

      for (int i = 0; i < allBusses.length; i++)
      {
         makeArray[i] = allBusses[i].getMake();
         modelArray[i] = allBusses[i].getModel();
         licensePlateArray[i] = allBusses[i].getLicensePlate();
         maxCapacityArray[i] = allBusses[i].getMaxCapacity();
      }

      busAndChauffeurNorthPanel.setBorder(BorderFactory.createTitledBorder("Search"));
      busAndChauffeurNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      busAndChauffeurWestPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      
      firstNameBox = new JExtendedComboBox<String>(agency.getAllFirstNames());
      lastNameBox = new JExtendedComboBox<String>(agency.getAllLastNames());
      chauffeurIdBox = new JExtendedComboBox<String>(agency.getAllChauffeurIds());
      makeBox = new JExtendedComboBox<String>(makeArray);
      modelBox = new JExtendedComboBox<String>(modelArray);
      licensePlateBox = new JExtendedComboBox<String>(licensePlateArray);
      maxCapacityBox = new JExtendedComboBox<Integer>(maxCapacityArray);
      
      
      busAndChauffeurNorthPanel.add(firstNameBox); 
      busAndChauffeurNorthPanel.add(lastNameBox); 
      busAndChauffeurNorthPanel.add(chauffeurIdBox); 
      busAndChauffeurWestPanel.add(makeBox);
      busAndChauffeurWestPanel.add(modelBox);
      busAndChauffeurWestPanel.add(licensePlateBox);
      busAndChauffeurWestPanel.add(maxCapacityBox);
      
      
      setSize(960, 540);
      setVisible(true);

      this.setLayout(new BorderLayout());
      this.add(busAndChauffeurNorthPanel, BorderLayout.NORTH);
      this.add(busAndChauffeurWestPanel, BorderLayout.WEST);
      this.add(busAndChauffeurSouthPanel, BorderLayout.SOUTH);
   }
}
