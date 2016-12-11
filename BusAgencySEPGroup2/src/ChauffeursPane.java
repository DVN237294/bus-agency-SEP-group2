import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ChauffeursPane extends JPanel
{
	private static final long serialVersionUID = 1L;
	private TravelAgency agency;
	private JExtendedComboBox<String> destiBox;
	private JExtendedComboBox<String> firstNameBox;
	private JExtendedComboBox<String> lastNameBox;
	private JExtendedComboBox<String> chauffeurIdBox;
	private JPanel chauffeurNorthPanel = new JPanel();

	public ChauffeursPane(TravelAgency agency)
	{
		super();
		this.agency = agency;

		chauffeurNorthPanel.setBorder(BorderFactory.createTitledBorder("Search"));
		chauffeurNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		destiBox = new JExtendedComboBox<String>(agency.getAllDestinations());
		firstNameBox = new JExtendedComboBox<String>(agency.getAllFirstNames());
		lastNameBox = new JExtendedComboBox<String>(agency.getAllLastNames());
		chauffeurIdBox = new JExtendedComboBox<String>(agency.getAllChauffeurIds());

		chauffeurNorthPanel.add(destiBox);
		chauffeurNorthPanel.add(firstNameBox);
		chauffeurNorthPanel.add(lastNameBox);
		chauffeurNorthPanel.add(chauffeurIdBox);

		setSize(960, 540);
		setVisible(true);

		this.setLayout(new BorderLayout());
		this.add(chauffeurNorthPanel, BorderLayout.NORTH);
	}
}
