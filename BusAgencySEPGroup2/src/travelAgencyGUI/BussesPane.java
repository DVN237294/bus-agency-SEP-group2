package travelAgencyGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import travelAgencyModel.Bus;
import travelAgencyModel.Chauffeur;
import travelAgencyModel.TravelAgency;

public class BussesPane extends JPanel
{
	private static final long serialVersionUID = 1L;
	private TravelAgency agency;
	// private JExtendedComboBox<String> destiBox;
	private JExtendedComboBox<String> destiEditBox;
	private JExtendedComboBox<String> makeBox;
	private JExtendedComboBox<String> modelBox;
	private JExtendedComboBox<String> licensePlateBox;
	private JExtendedComboBox<String> maxCapacityBox;
	// private JExtendedComboBox<Chauffeur> chauffeurBox;
	private JExtendedComboBox<Chauffeur> chauffeurEditBox;
	private JButton searchButton;
	private JButton showAllBussesButton;
	private JList<Bus> busList;
	private JTextArea infoBusList;
	private JButton deleteButton;
	private JButton addBusFrameButton;
	private JButton editBusFrameButton;
	private JButton saveEditBus;
	private JButton cancelEditBus;
	private TextField makeField;
	private TextField modelField;
	private TextField licensePlateField;
	private TextField maxCapacityField;
	private JLabel makeLabel;
	private JLabel modelLabel;
	private JLabel licensePlateLabel;
	private JLabel maxCapacityLabel;

	JPanel bussesNorthPanel = new JPanel();
	JPanel bussesWestPanel = new JPanel();
	JPanel bussesEditBusPanel = new JPanel();
	JPanel bussesSouthPanel = new JPanel();

	public BussesPane(TravelAgency agency)
	{
		super();
		this.agency = agency;
		Dimension comboBoxDimension = new Dimension(135, 25);
		Bus[] allBusses = agency.getAllBusses();
		String[] makeArray = new String[allBusses.length];
		String[] modelArray = new String[allBusses.length];
		String[] licensePlateArray = new String[allBusses.length];
		String[] maxCapacityArray = new String[allBusses.length];
		for (int i = 0; i < allBusses.length; i++)
		{
			makeArray[i] = allBusses[i].getMake();
			modelArray[i] = allBusses[i].getModel();
			licensePlateArray[i] = allBusses[i].getLicensePlate();
			maxCapacityArray[i] = Integer.toString(allBusses[i].getMaxCapacity());
		}
		bussesNorthPanel.setBorder(BorderFactory.createTitledBorder("Search"));
		bussesNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		bussesWestPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bussesEditBusPanel.setLayout(new GridLayout(6, 2));
		bussesEditBusPanel.setBorder(BorderFactory.createTitledBorder("Edit"));
		// destiBox = new
		// JExtendedComboBox<String>(agency.getAllDestinations());
		// destiBox.setPrototypeDisplayValue("Destination");
		// destiBox.setDefaultDisplayedItem("Destination");
		destiEditBox = new JExtendedComboBox<String>(agency.getAllDestinations());
		destiEditBox.setPrototypeDisplayValue("Destination");
		destiEditBox.setDefaultDisplayedItem("Destination");
		makeBox = new JExtendedComboBox<>(makeArray);
		makeBox.setPreferredSize(comboBoxDimension);
		makeBox.setPrototypeDisplayValue("Make");
		makeBox.setDefaultDisplayedItem("Make");
		modelBox = new JExtendedComboBox<>(modelArray);
		modelBox.setPreferredSize(comboBoxDimension);
		modelBox.setPrototypeDisplayValue("Model");
		modelBox.setDefaultDisplayedItem("Model");
		licensePlateBox = new JExtendedComboBox<>(licensePlateArray);
		licensePlateBox.setPrototypeDisplayValue("License Plate");
		licensePlateBox.setDefaultDisplayedItem("License Plate");
		licensePlateBox.setPreferredSize(comboBoxDimension);
		maxCapacityBox = new JExtendedComboBox<>(maxCapacityArray);
		maxCapacityBox.setPrototypeDisplayValue("Capacity");
		maxCapacityBox.setDefaultDisplayedItem("Capacity");
		maxCapacityBox.setPreferredSize(comboBoxDimension);
		// chauffeurBox = new
		// JExtendedComboBox<Chauffeur>(agency.getAllChauffeurs());
		chauffeurEditBox = new JExtendedComboBox<Chauffeur>(agency.getAllChauffeurs());
		searchButton = new JButton("Search");
		deleteButton = new JButton("Delete");
		addBusFrameButton = new JButton("Add Bus");
		editBusFrameButton = new JButton("Edit Bus");
		busList = new JList<Bus>(new DefaultListModel<Bus>());
		showAllBussesButton = new JButton("Show all Busses");
		infoBusList = new JTextArea();
		makeField = new TextField(10);
		modelField = new TextField(10);
		licensePlateField = new TextField(10);
		maxCapacityField = new TextField(10);
		makeLabel = new JLabel("Make");
		modelLabel = new JLabel("Model");
		licensePlateLabel = new JLabel("License Plate");
		maxCapacityLabel = new JLabel("Max Capacity");
		saveEditBus = new JButton("Save");
		cancelEditBus = new JButton("Cancel");
		cancelEditBus.setVisible(false);
		saveEditBus.setVisible(false);
		infoBusList.setVisible(false);
		busList.setVisible(false);
		deleteButton.setVisible(false);
		chauffeurEditBox.setVisible(false);
		editBusFrameButton.setVisible(false);
		bussesEditBusPanel.setVisible(false);
		destiEditBox.setVisible(false);
		addBusFrameButton.setVisible(true);
		makeLabel.setVisible(false);
		modelLabel.setVisible(false);
		licensePlateLabel.setVisible(false);
		maxCapacityLabel.setVisible(false);

		// bussesNorthPanel.add(destiBox);
		// bussesNorthPanel.add(chauffeurBox);
		bussesNorthPanel.add(makeBox);
		bussesNorthPanel.add(modelBox);
		bussesNorthPanel.add(licensePlateBox);
		bussesNorthPanel.add(maxCapacityBox);
		bussesWestPanel.add(busList);
		bussesNorthPanel.add(searchButton);
		bussesNorthPanel.add(showAllBussesButton);
		bussesSouthPanel.add(deleteButton);
		bussesSouthPanel.add(addBusFrameButton);
		bussesSouthPanel.add(editBusFrameButton);
		bussesWestPanel.add(infoBusList);
		bussesEditBusPanel.add(destiEditBox);
		bussesEditBusPanel.add(chauffeurEditBox);
		bussesEditBusPanel.add(makeLabel);
		bussesEditBusPanel.add(makeField);
		bussesEditBusPanel.add(modelLabel);
		bussesEditBusPanel.add(modelField);
		bussesEditBusPanel.add(licensePlateLabel);
		bussesEditBusPanel.add(licensePlateField);
		bussesEditBusPanel.add(maxCapacityLabel);
		bussesEditBusPanel.add(maxCapacityField);
		bussesEditBusPanel.add(saveEditBus);
		bussesEditBusPanel.add(cancelEditBus);
		searchButton.addActionListener(new SearchListener());
		busList.addListSelectionListener(new InformationListener());
		showAllBussesButton.addActionListener(new SearchAllListener());
		deleteButton.addActionListener(new DeleteItem());
		addBusFrameButton.addActionListener(new AddBusFrameAction());
		editBusFrameButton.addActionListener(new EditBusAction());
		saveEditBus.addActionListener(new SaveEditAction());
		cancelEditBus.addActionListener(new CancelEditAction());
		this.setLayout(new BorderLayout());
		this.add(bussesNorthPanel, BorderLayout.NORTH);
		this.add(bussesWestPanel, BorderLayout.WEST);
		this.add(bussesSouthPanel, BorderLayout.SOUTH);
		this.add(bussesEditBusPanel, BorderLayout.CENTER);
	}

	private class SearchListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			DefaultListModel<Bus> buses = (DefaultListModel<Bus>) busList.getModel();
			buses.removeAllElements();
			String make = makeBox.isDefaultItemSelected() ? null : makeBox.getSelectedItem();
			String model = modelBox.isDefaultItemSelected() ? null : modelBox.getSelectedItem();
			String license = licensePlateBox.isDefaultItemSelected() ? null : licensePlateBox.getSelectedItem();
			int capacity = maxCapacityBox.isDefaultItemSelected() ? 0 : Integer.parseInt(maxCapacityBox.getSelectedItem());
			for (Bus bus : agency.searchBus(make, model, license, capacity))
				buses.addElement(bus);
			busList.setVisible(true);
			deleteButton.setVisible(true);
			editBusFrameButton.setVisible(true);
			infoBusList.setVisible(false);
		}
	}

	private class DeleteItem implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			DefaultListModel<Bus> model = (DefaultListModel<Bus>) busList.getModel();
			Bus temp = busList.getSelectedValue();
			model.removeElement(temp);
			agency.deleteBus(temp);
			makeBox.removeItem(temp.getMake());
			modelBox.removeItem(temp.getModel());
			licensePlateBox.removeItem(temp.getLicensePlate());
			maxCapacityBox.removeItem(temp.getMaxCapacity());
			busList.setVisible(true);
			deleteButton.setVisible(true);
			infoBusList.setVisible(false);
			bussesEditBusPanel.setVisible(false);
			editBusFrameButton.setVisible(false);
			makeLabel.setVisible(false);
			saveEditBus.setVisible(false);
			modelLabel.setVisible(false);
			licensePlateLabel.setVisible(false);
			maxCapacityLabel.setVisible(false);
			bussesEditBusPanel.setVisible(false);
			chauffeurEditBox.setVisible(false);
			destiEditBox.setVisible(false);
		}
	}

	private class SearchAllListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			DefaultListModel<Bus> model = (DefaultListModel<Bus>) busList.getModel();
			model.removeAllElements();
			for (Bus bus : agency.getAllBusses())
			{
				model.addElement(bus);
			}
			deleteButton.setVisible(true);
			busList.setVisible(true);
			editBusFrameButton.setVisible(true);
			infoBusList.setVisible(false);
		}

	}

	private class InformationListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			if (!busList.isSelectionEmpty() && !busList.isSelectionEmpty())
			{
				infoBusList.setText(busList.getSelectedValue().toString());
				infoBusList.setEditable(false);
				infoBusList.setVisible(true);
			}
		}

	}

	private class AddBusFrameAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			AddBusFrame frame = new AddBusFrame(agency);
			frame.addWindowListener(new WindowListenerChangeHandler());
		}

	}

	private class EditBusAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			infoBusList.setVisible(false);
			makeLabel.setVisible(true);
			saveEditBus.setVisible(true);
			destiEditBox.setVisible(true);
			modelLabel.setVisible(true);
			cancelEditBus.setVisible(true);
			licensePlateLabel.setVisible(true);
			maxCapacityLabel.setVisible(true);
			bussesEditBusPanel.setVisible(true);
			chauffeurEditBox.setVisible(true);
		}

	}

	private class CancelEditAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			editBusFrameButton.setVisible(false);
			makeLabel.setVisible(false);
			saveEditBus.setVisible(false);
			modelLabel.setVisible(false);
			licensePlateLabel.setVisible(false);
			maxCapacityLabel.setVisible(false);
			bussesEditBusPanel.setVisible(false);
			chauffeurEditBox.setVisible(false);
			destiEditBox.setVisible(false);
		}

	}

	private class SaveEditAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				agency.addBus(makeField.getText(), modelField.getText(), licensePlateField.getText(), Integer.parseInt(maxCapacityField.getText()));// ,
																																					// chauffeurEditBox.getSelectedItem(),
																																					// destiBox.getSelectedItem());
				DefaultListModel<Bus> model = (DefaultListModel<Bus>) busList.getModel();
				Bus temp = busList.getSelectedValue();
				model.removeElement(temp);
				agency.deleteBus(temp);
			} catch (Exception a)
			{
				editBusFrameButton.setVisible(false);
				makeLabel.setVisible(false);
				saveEditBus.setVisible(false);
				modelLabel.setVisible(false);
				licensePlateLabel.setVisible(false);
				maxCapacityLabel.setVisible(false);
				bussesEditBusPanel.setVisible(false);
				chauffeurEditBox.setVisible(false);
				destiEditBox.setVisible(false);
				cancelEditBus.setVisible(false);
				System.out.println(a.getMessage() + "Please fill up all the empty fields");
				makeBox.setItems(agency.getAllBusMakes());
				modelBox.setItems(agency.getAllBusModels());
				licensePlateBox.setItems(agency.getAllBusLicensePlates());
				maxCapacityBox.setItems(agency.getAllBusMaxCapacitiesAsStrings());
			}
			editBusFrameButton.setVisible(false);
			makeLabel.setVisible(false);
			saveEditBus.setVisible(false);
			modelLabel.setVisible(false);
			licensePlateLabel.setVisible(false);
			maxCapacityLabel.setVisible(false);
			bussesEditBusPanel.setVisible(false);
			chauffeurEditBox.setVisible(false);
			destiEditBox.setVisible(false);
			cancelEditBus.setVisible(false);
			makeBox.setItems(agency.getAllBusMakes());
			modelBox.setItems(agency.getAllBusModels());
			licensePlateBox.setItems(agency.getAllBusLicensePlates());
			maxCapacityBox.setItems(agency.getAllBusMaxCapacitiesAsStrings());
		}
	}

	private class WindowListenerChangeHandler implements WindowListener
	{

		@Override
		public void windowActivated(WindowEvent e)
		{
			if (e.getID() == WindowEvent.WINDOW_CLOSED)
			{
				System.out.println("you closed the window");
			}
			System.out.println("something");
		}

		@Override
		public void windowClosed(WindowEvent e)
		{
			// TODO Auto-generated method stub

			System.out.println("closed");
			makeBox.setItems(agency.getAllBusMakes());
			modelBox.setItems(agency.getAllBusModels());
			licensePlateBox.setItems(agency.getAllBusLicensePlates());
			maxCapacityBox.setItems(agency.getAllBusMaxCapacitiesAsStrings());
		}

		@Override
		public void windowClosing(WindowEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent e)
		{
			// TODO Auto-generated method stub

		}

	}
}
