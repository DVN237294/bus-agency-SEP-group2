import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BussesPane extends JPanel
{
	private TravelAgency agency;
	public BussesPane(TravelAgency agency)
	{
		super();
		this.agency = agency;
		
		JPanel bussesNorthPanel = new JPanel();
	      bussesNorthPanel.setBorder(BorderFactory.createTitledBorder("Reserve"));
	      bussesNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	      JExtendedComboBox<Bus> busBox = new JExtendedComboBox<>(agency.getAllBusses());
	      
	      JExtendedComboBox<String> destiBox = new JExtendedComboBox<String>(agency.getAllDestinations());
	      destiBox.setPrototypeDisplayValue("Destination");
	      destiBox.setDefaultDisplayedValue("Destination");
	      JExtendedComboBox<Chauffeur> chauffeurBox = new JExtendedComboBox<Chauffeur>(agency.getAllChauffeurs());
	      
	      bussesNorthPanel.add(destiBox);   
	      bussesNorthPanel.add(busBox);
	      bussesNorthPanel.add(chauffeurBox);
	      this.setLayout(new BorderLayout());
	      this.add(bussesNorthPanel, BorderLayout.NORTH);
	}
}
