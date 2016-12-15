package travelAgencyGUI;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FocusTraversalPolicy;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import travelAgencyModel.Bus;
import travelAgencyModel.Chauffeur;
import travelAgencyModel.Travel;
import travelAgencyModel.TravelAgency;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class ReservationsPane extends JPanel
{
	private static final long serialVersionUID = 1L;
	private TravelAgency agency;
	private JPanel toursNorthPanel;
	private JPanel toursCenterPanel;
	private JPanel toursSouthPanel;
	private JPanel southWestPanel;
	private JPanel southEastPanel;
	private JList<Travel> travelsList;
	private JTextArea centerEastJTextArea;
	private JScrollPane reservationDetailScrollPane;
	private JExtendedComboBox<String> destinationBox;
	private JExtendedComboBox<Chauffeur> chauffeurBox;
	private JExtendedComboBox<Bus> busBox;
	private JButton searchButton;
	private JButton resetButton;
	private JButton addTourButton;
	private JButton editTourButton;
	private JButton deleteTourButton;

	public ReservationsPane(TravelAgency agency)
	{
		super();
		this.agency = agency;
		// Tour pane
		toursNorthPanel = new JPanel();
		toursCenterPanel = new JPanel();
		toursSouthPanel = new JPanel();
		southEastPanel = new JPanel();
		southWestPanel = new JPanel();
		addTourButton = new JButton("Add Reservation");
		editTourButton = new JButton("Edit Reservation");
		deleteTourButton = new JButton("Delete Reservation");
		deleteTourButton.addActionListener(new DeleteButtonAction());
		travelsList = new JList<Travel>(new DefaultListModel<Travel>());
		travelsList.addListSelectionListener(new TravelsListSelectionChangedHandler());
		travelsList.setPreferredSize(new Dimension(200, 200));
		travelsList.setBorder(BorderFactory.createTitledBorder("Search results"));
		centerEastJTextArea = new JTextArea();
		centerEastJTextArea.setTabSize(4);
		centerEastJTextArea.setEditable(false);
		
		centerEastJTextArea.setBorder(BorderFactory.createTitledBorder("Reservation details"));

		reservationDetailScrollPane = new JScrollPane(centerEastJTextArea);
		reservationDetailScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		reservationDetailScrollPane.setPreferredSize(new Dimension(200, 200));
		toursCenterPanel.setLayout(new GridBagLayout());
		GridBagConstraints listBagConstraints = new GridBagConstraints();
		listBagConstraints.insets = new Insets(5, 5, 5, 5);
		listBagConstraints.weightx = 1;
		listBagConstraints.weighty = 1;
		listBagConstraints.fill = GridBagConstraints.BOTH;
		toursCenterPanel.add(travelsList, listBagConstraints);
		GridBagConstraints textAreaBagConstraints = new GridBagConstraints();
		textAreaBagConstraints.insets = new Insets(5, 5, 5, 5);
		textAreaBagConstraints.weightx = 2;
		textAreaBagConstraints.weighty = 2;
		textAreaBagConstraints.fill = GridBagConstraints.BOTH;

		toursCenterPanel.add(reservationDetailScrollPane, textAreaBagConstraints);
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
		
		
		// We don't want the default focus policy to mess with our focus events
		// on components..
		
	}

	private void performSearch()
	{
		String destinationString = destinationBox.isDefaultItemSelected() ? null : destinationBox.getSelectedItem();
		Chauffeur chauffeur = chauffeurBox.isDefaultItemSelected() ? null : chauffeurBox.getSelectedItem();
		Bus bus = busBox.isDefaultItemSelected() ? null : busBox.getSelectedItem();
		Travel[] searchResult = agency.searchTravel(destinationString, chauffeur, bus);
		DefaultListModel<Travel> listModel = (DefaultListModel<Travel>) travelsList.getModel();
		listModel.clear();
		for (Travel travel : searchResult)
		{
			listModel.addElement(travel);
		}
	}

	private class TravelsListSelectionChangedHandler implements ListSelectionListener
	{
		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			if (travelsList.getSelectedIndex() > -1)
			{
				Travel selected = travelsList.getSelectedValue();
				centerEastJTextArea.setText(selected.getText());
			}
		}
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
			if (travelsList.getSelectedIndex() != -1)
			{
				int result = JOptionPane.showConfirmDialog(ReservationsPane.this, "Are you sure you wish to delete this reservation?");
				if (result == JOptionPane.YES_OPTION)
				{
					agency.removeTravel(travelsList.getSelectedValue());
					performSearch();
					centerEastJTextArea.setText("");
				}
			}
		}

	}

	private class SearchAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			performSearch();
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
			Travel result = frame.getResultTravel();
			if (result != null)
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
