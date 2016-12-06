import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import org.omg.CORBA.PRIVATE_MEMBER;

public class BussesPane extends JPanel
{
   private static final long serialVersionUID = 1L;
   private TravelAgency agency;
   private JExtendedComboBox<String> destiBox;
   private JExtendedComboBox<Bus> busBox;
   private JExtendedComboBox<Chauffeur> chauffeurBox;
   private JButton searchButton;
   private JList<Bus> busList;
   private JButton deleteButton;
   
   JPanel bussesNorthPanel = new JPanel();
   JPanel bussesWestPanel = new JPanel();
   JPanel bussesSouthPanel = new JPanel();
   
	public BussesPane(TravelAgency agency)
	{
		super();
		this.agency = agency;
		
	      bussesNorthPanel.setBorder(BorderFactory.createTitledBorder("Search"));
	      bussesNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	      bussesWestPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	      busBox = new JExtendedComboBox<>(agency.getAllBusses());
	      destiBox = new JExtendedComboBox<String>(agency.getAllDestinations());
	      destiBox.setPrototypeDisplayValue("Destination");
	      destiBox.setDefaultDisplayedItem("Destination");
	      chauffeurBox = new JExtendedComboBox<Chauffeur>(agency.getAllChauffeurs());
	      busList = new JList<Bus>(agency.getAllBusses());
	      busList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	      searchButton = new JButton("Search");
	      deleteButton = new JButton("Delete");
	      busList.setVisible(false);
	      deleteButton.setVisible(false);
	      
	      bussesNorthPanel.add(destiBox);   
	      bussesNorthPanel.add(busBox);
	      bussesNorthPanel.add(chauffeurBox);
	      bussesNorthPanel.add(searchButton);
	      bussesWestPanel.add(busList);
         bussesSouthPanel.add(deleteButton);
	      searchButton.addActionListener(new SearchAllListener());
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
         busList.setVisible(true);
         deleteButton.setVisible(true);
      }
	   
	}
}
