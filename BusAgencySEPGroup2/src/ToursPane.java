import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ToursPane extends JPanel
{
	private TravelAgency agency;
	private JPanel toursNorthPanel;
	private JPanel toursCenterPanel;
	private JList<Tour> centerWestList;
	private JTextArea centerEastJTextArea;
	private JExtendedComboBox<String> destinationBox;
	private JExtendedComboBox<Chauffeur> chauffeurBox;
	private JExtendedComboBox<Bus> busBox;
	private JButton searchButton;
	private JButton resetButton;
	public ToursPane(TravelAgency agency)
	{
		super();
		this.agency = agency;
		// Tour pane
		toursNorthPanel = new JPanel();
		toursCenterPanel = new JPanel();
		centerWestList = new JList<Tour>();
		centerWestList.setVisible(false);
		centerEastJTextArea = new JTextArea();
		centerEastJTextArea.setVisible(false);
		toursCenterPanel.setLayout(new GridLayout(1, 2));
		toursCenterPanel.add(centerWestList);
		toursCenterPanel.add(centerEastJTextArea);
		searchButton = new JButton("Search");
		searchButton.addActionListener(new SearchAction());
		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ResetAction());
		toursNorthPanel.setBorder(BorderFactory.createTitledBorder("Search"));
		toursNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		destinationBox = new JExtendedComboBox<String>(agency.getAllDestinations());
		destinationBox.setPrototypeDisplayValue("Destination");
		destinationBox.setDefaultDisplayedItem("Destination");
		chauffeurBox = new JExtendedComboBox<Chauffeur>(agency.getAllChauffeurs());
		busBox = new JExtendedComboBox<>(agency.getAllBusses());
		
		toursNorthPanel.add(destinationBox);
		toursNorthPanel.add(chauffeurBox);
		toursNorthPanel.add(busBox);
		toursNorthPanel.add(searchButton);
		toursNorthPanel.add(resetButton);
		this.setLayout(new BorderLayout());
		this.add(toursNorthPanel, BorderLayout.NORTH);
		this.add(toursCenterPanel, BorderLayout.CENTER);
	}
	
	private class ResetAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	private class SearchAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			centerWestList.setVisible(true);
		}
		
	}
}
