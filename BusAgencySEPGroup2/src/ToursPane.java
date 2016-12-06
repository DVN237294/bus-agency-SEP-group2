import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ToursPane extends JPanel
{
	private TravelAgency agency;
	public ToursPane(TravelAgency agency)
	{
		super();
		this.agency = agency;
		// Tour pane
		JPanel toursNorthPanel = new JPanel();
		toursNorthPanel.setBorder(BorderFactory.createTitledBorder("Search"));
		toursNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		String[] destinations = agency.getAllDestinations();
		JExtendedComboBox<String> destiBox = new JExtendedComboBox<String>(destinations);
		destiBox.setPrototypeDisplayValue("Destination");
		destiBox.setDefaultDisplayedValue("Destination");
		JExtendedComboBox<Chauffeur> chauffeurBox = new JExtendedComboBox<Chauffeur>(agency.getAllChauffeurs());
		JExtendedComboBox<Bus> busBox = new JExtendedComboBox<>(agency.getAllBusses());
		toursNorthPanel.add(destiBox);
		toursNorthPanel.add(chauffeurBox);
		toursNorthPanel.add(busBox);

		this.setLayout(new BorderLayout());
		this.add(toursNorthPanel, BorderLayout.NORTH);
	}
}
