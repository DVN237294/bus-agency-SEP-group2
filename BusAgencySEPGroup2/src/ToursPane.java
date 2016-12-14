import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ToursPane extends JPanel
{
	private static final long serialVersionUID = 1L;
	private TravelAgency agency;
	private JPanel toursNorthPanel;
	private JPanel toursCenterPanel;
	private JPanel toursSouthPanel;
	private JPanel southWestPanel;
	private JPanel southEastPanel;
	private JList<Tour> centerWestList;
	private JTextArea centerEastJTextArea;
	private JExtendedComboBox<String> destinationBox;
	private JExtendedComboBox<Chauffeur> chauffeurBox;
	private JExtendedComboBox<Bus> busBox;
	private JButton searchButton;
	private JButton resetButton;
	private JButton addTourButton;
	private JButton editTourButton;
	private JButton deleteTourButton;

	public ToursPane(TravelAgency agency)
	{
		super();
		this.agency = agency;
		// Tour pane
		toursNorthPanel = new JPanel();
		toursCenterPanel = new JPanel();
		toursSouthPanel = new JPanel();
		southEastPanel = new JPanel();
		southWestPanel = new JPanel();
		addTourButton = new JButton("Add Tour");
		editTourButton = new JButton("Edit Tour");
		deleteTourButton = new JButton("Delete Tour");
		deleteTourButton.addActionListener(new DeleteButtonAction());
		centerWestList = new JList<Tour>(new DefaultListModel<Tour>());
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
		chauffeurBox.setDefaultDisplayedItem(new Chauffeur("Chauffeur", null, Integer.MIN_VALUE)); // is
																									// this
																									// cheating?

		busBox = new JExtendedComboBox<>(agency.getAllBusses());
		busBox.setDefaultDisplayedItem(new Bus("Bus", null, null, Integer.MIN_VALUE));
		toursNorthPanel.add(destinationBox);
		toursNorthPanel.add(chauffeurBox);
		toursNorthPanel.add(busBox);
		toursNorthPanel.add(searchButton);
		toursNorthPanel.add(resetButton);

		toursSouthPanel.setLayout(new GridLayout(1, 2));
		toursSouthPanel.add(southWestPanel);
		toursSouthPanel.add(southEastPanel);

		southWestPanel.add(addTourButton);
		southWestPanel.add(editTourButton);
		southWestPanel.add(deleteTourButton);

		addTourButton.addActionListener(new AddTourAction());

		this.setLayout(new BorderLayout());
		this.add(toursNorthPanel, BorderLayout.NORTH);
		this.add(toursCenterPanel, BorderLayout.CENTER);
		this.add(toursSouthPanel, BorderLayout.SOUTH);
	}

	private class ResetAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub

			destinationBox.reset();
			chauffeurBox.reset();
			busBox.reset();

		}
	}
	
	private class DeleteButtonAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(centerWestList.getSelectedIndex() != -1)
			{
				int result = JOptionPane.showConfirmDialog(ToursPane.this, "Are you sure you wish to delete this Tour?");
				if(result == JOptionPane.YES_OPTION)
				{
					agency.removeTravel(centerWestList.getSelectedValue());
				}
			}
		}
		
	}

	private class SearchAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			centerWestList.setVisible(true);
			String destinationString = destinationBox.isDefaultItemSelected() ? null : destinationBox.getSelectedItem();
			Chauffeur chauffeur = chauffeurBox.isDefaultItemSelected() ? null : chauffeurBox.getSelectedItem();
			Bus bus = busBox.isDefaultItemSelected() ? null : busBox.getSelectedItem();
			Travel[] searchResult = agency.searchTravel(destinationString, chauffeur, bus);
			DefaultListModel<Tour> listModel = (DefaultListModel<Tour>) centerWestList.getModel();
			listModel.clear();
			for (Travel travel : searchResult)
			{
				if (travel instanceof Tour)
					listModel.addElement((Tour) travel);
			}

		}

	}

	private class AddTourAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			AddTourFrame frame = new AddTourFrame(agency);
			frame.addWindowListener(new WindowStateChangedHandler());
		}
	}

	private class WindowStateChangedHandler implements WindowListener
	{
		@Override
		public void windowActivated(WindowEvent e)
		{
		}

		@Override
		public void windowClosed(WindowEvent e)
		{
		}

		@Override
		public void windowClosing(WindowEvent e)
		{
			AddTourFrame frame = (AddTourFrame) e.getSource();
			Travel result = frame.getResultTour();
			agency.addTravel(result);
		}

		@Override
		public void windowDeactivated(WindowEvent e)
		{
		}

		@Override
		public void windowDeiconified(WindowEvent e)
		{
		}

		@Override
		public void windowIconified(WindowEvent e)
		{
		}

		@Override
		public void windowOpened(WindowEvent e)
		{
		}
	}
}
