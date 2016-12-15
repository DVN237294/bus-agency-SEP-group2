package travelAgencyGUI;

import java.awt.BorderLayout;
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

import travelAgencyModel.Chauffeur;
import travelAgencyModel.TravelAgency;

public class ChauffeursPane extends JPanel
{
	private static final long serialVersionUID = 1L;
	private TravelAgency agency;
	// private JExtendedComboBox<String> destiBox;
	private JExtendedComboBox<String> firstNameBox;
	private JExtendedComboBox<String> lastNameBox;
	private JExtendedComboBox<String> chauffeurIdBox;
	private JList<Chauffeur> chauffeurList;
	private JButton searchButton;
	private JButton searchAllButton;
	private JTextArea infoChauffeurList;
	private JButton addChauffeurButton;
	private JButton deleteChauffeurButton;
	private JButton editChauffeurButton;
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField chauffeurIdField;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel chauffeurIdLabel;
	private JButton saveEditButton;
	private JButton cancelEditButton;
	private JButton checkScheduleButton;
	private JTextArea scheduleArea;

	private JPanel chauffeurNorthPanel = new JPanel();
	private JPanel chauffeurWestPanel = new JPanel();
	private JPanel chauffeurSouthPanel = new JPanel();
	private JPanel chauffeurAvailabilityJPanel = new JPanel();
	private JPanel chauffeurEditPanel = new JPanel();

	public ChauffeursPane(TravelAgency agency)
	{
		super();
		this.agency = agency;

		chauffeurNorthPanel.setBorder(BorderFactory.createTitledBorder("Search"));
		chauffeurNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		chauffeurWestPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		chauffeurEditPanel.setLayout(new GridLayout(6, 2));
		chauffeurEditPanel.setBorder(BorderFactory.createTitledBorder("Edit"));
		chauffeurAvailabilityJPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		chauffeurAvailabilityJPanel.setBorder(BorderFactory.createTitledBorder("Check Availability"));
		// destiBox = new
		// JExtendedComboBox<String>(agency.getAllDestinations());
		firstNameBox = new JExtendedComboBox<String>(agency.getAllFirstNames());
		firstNameBox.setDefaultDisplayedItem("First Name");
		firstNameBox.setPrototypeDisplayValue("First Name");
		lastNameBox = new JExtendedComboBox<String>(agency.getAllLastNames());
		lastNameBox.setDefaultDisplayedItem("Last Name");
		lastNameBox.setPrototypeDisplayValue("Last Name");
		chauffeurIdBox = new JExtendedComboBox<String>(agency.getAllChauffeurIds());

		chauffeurIdBox.setDefaultDisplayedItem("Chauffeur ID");
		chauffeurIdBox.setPrototypeDisplayValue("Chauffeur ID");
		chauffeurList = new JList<Chauffeur>(new DefaultListModel<Chauffeur>());
		searchButton = new JButton("Search");
		searchAllButton = new JButton("Search All");
		infoChauffeurList = new JTextArea();
		addChauffeurButton = new JButton("Add Chauffeur");
		deleteChauffeurButton = new JButton("Delete Chauffeur");
		editChauffeurButton = new JButton("Edit Chauffeur");
		firstNameField = new TextField(10);
		lastNameField = new TextField(10);
		chauffeurIdField = new TextField(5);
		firstNameLabel = new JLabel("First Name");
		lastNameLabel = new JLabel("Last Name");
		chauffeurIdLabel = new JLabel("Chauffeur ID");
		saveEditButton = new JButton("Save");
		cancelEditButton = new JButton("Cancel");
		checkScheduleButton = new JButton("Check Availability");
		// scheduleArea = new JTextArea("From:" +
		// agency.getReservationStartDate()
		// + "\n" + "Until:" + agency.getReservationEndDate());
		// scheduleArea.setVisible(false);
		checkScheduleButton.setVisible(false);
		chauffeurList.setVisible(false);
		chauffeurEditPanel.setVisible(false);
		chauffeurAvailabilityJPanel.setVisible(false);
		infoChauffeurList.setVisible(false);
		chauffeurIdField.setVisible(false);
		firstNameField.setVisible(false);
		lastNameField.setVisible(false);
		firstNameLabel.setVisible(false);
		lastNameLabel.setVisible(false);
		deleteChauffeurButton.setVisible(false);
		editChauffeurButton.setVisible(false);
		chauffeurIdLabel.setVisible(false);
		saveEditButton.setVisible(false);
		cancelEditButton.setVisible(false);

		// chauffeurNorthPanel.add(destiBox);
		chauffeurNorthPanel.add(firstNameBox);
		chauffeurNorthPanel.add(lastNameBox);
		chauffeurNorthPanel.add(chauffeurIdBox);
		chauffeurNorthPanel.add(searchButton);
		chauffeurNorthPanel.add(searchAllButton);
		chauffeurWestPanel.add(chauffeurList);
		chauffeurWestPanel.add(infoChauffeurList);
		chauffeurSouthPanel.add(addChauffeurButton);
		chauffeurSouthPanel.add(deleteChauffeurButton);
		chauffeurSouthPanel.add(checkScheduleButton);
		chauffeurSouthPanel.add(editChauffeurButton);
		chauffeurEditPanel.add(firstNameLabel);
		chauffeurEditPanel.add(firstNameField);
		chauffeurEditPanel.add(lastNameLabel);
		chauffeurEditPanel.add(lastNameField);
		chauffeurEditPanel.add(chauffeurIdLabel);
		chauffeurEditPanel.add(chauffeurIdField);
		chauffeurEditPanel.add(saveEditButton);
		chauffeurEditPanel.add(cancelEditButton);
		// chauffeurAvailabilityJPanel.add(scheduleArea);
		searchButton.addActionListener(new SearchAction());
		searchAllButton.addActionListener(new SearchAllAction());
		chauffeurList.addListSelectionListener(new InformationListener());
		addChauffeurButton.addActionListener(new AddChauffeurAction());
		deleteChauffeurButton.addActionListener(new DeleteAction());
		editChauffeurButton.addActionListener(new EditAction());
		saveEditButton.addActionListener(new SaveEditAction());
		cancelEditButton.addActionListener(new CancelAction());

		setSize(960, 540);
		setVisible(true);

		this.setLayout(new BorderLayout());
		this.add(chauffeurNorthPanel, BorderLayout.NORTH);
		this.add(chauffeurWestPanel, BorderLayout.WEST);
		this.add(chauffeurSouthPanel, BorderLayout.SOUTH);
		this.add(chauffeurEditPanel, BorderLayout.CENTER);
		this.add(chauffeurAvailabilityJPanel, BorderLayout.EAST);
	}

	public class SearchAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			DefaultListModel<Chauffeur> model = (DefaultListModel<Chauffeur>) chauffeurList.getModel();
			model.removeAllElements();
			String firstName = firstNameBox.isDefaultItemSelected() ? null : firstNameBox.getSelectedItem();
			String lastName = lastNameBox.isDefaultItemSelected() ? null : lastNameBox.getSelectedItem();
			int chauffeurID = chauffeurIdBox.isDefaultItemSelected() ? 0 : Integer.parseInt(chauffeurIdBox.getSelectedItem());
			for (Chauffeur chauffeur : agency.searchChauffeur(firstName, lastName, chauffeurID))
				model.addElement(chauffeur);
			chauffeurList.setVisible(true);
			infoChauffeurList.setVisible(false);
		}

	}

	public class SearchAllAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			DefaultListModel<Chauffeur> model = (DefaultListModel<Chauffeur>) chauffeurList.getModel();
			model.removeAllElements();
			for (Chauffeur chauffeur : agency.getAllChauffeurs())
			{
				model.addElement(chauffeur);
			}
			chauffeurList.setVisible(true);
			infoChauffeurList.setVisible(false);
		}

	}

	public class DeleteAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			DefaultListModel<Chauffeur> model = (DefaultListModel<Chauffeur>) chauffeurList.getModel();
			Chauffeur temp = chauffeurList.getSelectedValue();
			model.removeElement(temp);
			agency.deleteChauffeur(temp);
			firstNameBox.removeItem(temp.getFirstName());
			lastNameBox.removeItem(temp.getLastName());
			chauffeurIdBox.removeItem(temp.getChauffeurID());
			infoChauffeurList.setVisible(false);
		}

	}

	public class EditAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			chauffeurEditPanel.setVisible(true);
			firstNameField.setVisible(true);
			lastNameField.setVisible(true);
			chauffeurIdField.setVisible(true);
			firstNameLabel.setVisible(true);
			lastNameLabel.setVisible(true);
			chauffeurIdLabel.setVisible(true);
			saveEditButton.setVisible(true);
			cancelEditButton.setVisible(true);
		}

	}

	public class InformationListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			if (!chauffeurList.isSelectionEmpty() && !chauffeurList.isSelectionEmpty())
			{
				infoChauffeurList.setText(chauffeurList.getSelectedValue().toString());
				infoChauffeurList.setEditable(false);
				editChauffeurButton.setVisible(true);
				deleteChauffeurButton.setVisible(true);
				checkScheduleButton.setVisible(true);
				infoChauffeurList.setVisible(true);
			}
		}

	}

	public class AddChauffeurAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			AddChauffeurPanel frame = new AddChauffeurPanel(agency);
			frame.addWindowListener(new WindowStateChangedHandler());
		}

	}

	public class SaveEditAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				agency.addChauffeur(firstNameField.getText(), lastNameField.getText(), Integer.parseInt(chauffeurIdField.getText()));
				DefaultListModel<Chauffeur> model = (DefaultListModel<Chauffeur>) chauffeurList.getModel();
				Chauffeur temp = chauffeurList.getSelectedValue();
				model.removeElement(temp);
				agency.deleteChauffeur(temp);
			} catch (Exception e2)
			{
				chauffeurEditPanel.setVisible(false);
				firstNameField.setVisible(false);
				lastNameField.setVisible(false);
				chauffeurIdField.setVisible(false);
				firstNameLabel.setVisible(false);
				lastNameLabel.setVisible(false);
				chauffeurIdLabel.setVisible(false);
				saveEditButton.setVisible(false);
				cancelEditButton.setVisible(false);
				firstNameBox.setItems(agency.getAllFirstNames());
				lastNameBox.setItems(agency.getAllLastNames());
				chauffeurIdBox.setItems(agency.getAllChauffeurIds());
			}
			chauffeurEditPanel.setVisible(false);
			firstNameField.setVisible(false);
			lastNameField.setVisible(false);
			chauffeurIdField.setVisible(false);
			firstNameLabel.setVisible(false);
			lastNameLabel.setVisible(false);
			chauffeurIdLabel.setVisible(false);
			saveEditButton.setVisible(false);
			cancelEditButton.setVisible(false);
			infoChauffeurList.setVisible(false);
			firstNameBox.setItems(agency.getAllFirstNames());
			lastNameBox.setItems(agency.getAllLastNames());
			chauffeurIdBox.setItems(agency.getAllChauffeurIds());
		}
	}

	public class CancelAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			chauffeurEditPanel.setVisible(false);
			firstNameField.setVisible(false);
			lastNameField.setVisible(false);
			chauffeurIdField.setVisible(false);
			firstNameLabel.setVisible(false);
			lastNameLabel.setVisible(false);
			chauffeurIdLabel.setVisible(false);
			saveEditButton.setVisible(false);
			cancelEditButton.setVisible(false);
			infoChauffeurList.setVisible(false);
		}

	}

	public class AvailabilityAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			chauffeurAvailabilityJPanel.setVisible(true);
			scheduleArea.setVisible(true);
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
			// TODO Auto-generated method stub

			firstNameBox.setItems(agency.getAllFirstNames());
			lastNameBox.setItems(agency.getAllLastNames());
			chauffeurIdBox.setItems(agency.getAllChauffeurIds());
		}

		@Override
		public void windowClosing(WindowEvent e)
		{
			// AddTourFrame frame = (AddTourFrame) e.getSource();
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
