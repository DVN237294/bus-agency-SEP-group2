package travelAgencyGUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.scene.shape.Box;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import travelAgencyModel.Address;
import travelAgencyModel.Chauffeur;
import travelAgencyModel.TravelAgency;

public class AddChauffeurPanel extends JFrame
{
	private static final long serialVersionUID = 1L;
	private TravelAgency agency;
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField chauffeurIdField;
	private TextField phoneNumberField = new TextField(10);
	private TextField emailField = new TextField(10);
	private JLabel phoneNumberLabel = new JLabel("Phone number");
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel chauffeurIdLabel;
	private JLabel emaiLabel = new JLabel("Email");
	private JButton saveButton;
	
	private JTextField streetNameField;
	private JTextField streetHouseNumberField;
	private JTextField zipCodeField;
	private JTextField doorNumberField;
	private JTextField floorNumberField;

	//private JPanel addChauffeurNorthPanel = new JPanel();

	public AddChauffeurPanel(TravelAgency agency)
	{
		super("Add Chauffeur");
		setSize(450, 400);
		this.agency = agency;
		setLayout(new GridLayout(2, 1));
		firstNameField = new TextField(10);
		lastNameField = new TextField(10);
		chauffeurIdField = new TextField(5);
		firstNameLabel = new JLabel("First Name");
		lastNameLabel = new JLabel("Last Name");
		chauffeurIdLabel = new JLabel("Chauffeur ID");
		saveButton = new JButton("Save");
				
		/*addChauffeurNorthPanel.add(firstNameLabel);
		addChauffeurNorthPanel.add(firstNameField);
		addChauffeurNorthPanel.add(lastNameLabel);
		addChauffeurNorthPanel.add(lastNameField);
		addChauffeurNorthPanel.add(chauffeurIdLabel);
		addChauffeurNorthPanel.add(chauffeurIdField);
		addChauffeurNorthPanel.add(saveButton);*/
		saveButton.addActionListener(new SaveAction());

		streetNameField = new JTextField();
		zipCodeField = new JTextField();
		floorNumberField = new JTextField();
		doorNumberField = new JTextField();
		streetHouseNumberField = new JTextField();
		
		
		streetNameField.setBorder(BorderFactory.createTitledBorder("Street name"));
		streetHouseNumberField.setBorder(BorderFactory.createTitledBorder("Street number"));
		zipCodeField.setBorder(BorderFactory.createTitledBorder("Zipcode"));
		doorNumberField.setBorder(BorderFactory.createTitledBorder("Door number"));
		floorNumberField.setBorder(BorderFactory.createTitledBorder("Floor number"));

		JPanel addressPanel = new JPanel(new GridBagLayout());
		addressPanel.setBorder(BorderFactory.createTitledBorder("Address"));

		GridBagConstraints streetNameBagConstraints = new GridBagConstraints();
		streetNameBagConstraints.gridx = 0;
		streetNameBagConstraints.gridy = 0;
		streetNameBagConstraints.gridwidth = 4;
		streetNameBagConstraints.gridheight = 1;
		streetNameBagConstraints.weightx = 1;
		streetNameBagConstraints.weighty = 1;
		streetNameBagConstraints.fill = GridBagConstraints.HORIZONTAL;

		addressPanel.add(streetNameField, streetNameBagConstraints);

		GridBagConstraints theRestBagConstraints = new GridBagConstraints();
		theRestBagConstraints.gridx = 0;
		theRestBagConstraints.gridy = 1;
		theRestBagConstraints.gridwidth = 1;
		theRestBagConstraints.gridheight = 1;
		theRestBagConstraints.weightx = 1;
		theRestBagConstraints.weighty = 1;
		theRestBagConstraints.fill = GridBagConstraints.HORIZONTAL;

		addressPanel.add(streetHouseNumberField, theRestBagConstraints);
		theRestBagConstraints.gridx = 1;
		addressPanel.add(floorNumberField, theRestBagConstraints);
		theRestBagConstraints.gridx = 0;
		theRestBagConstraints.gridy = 2;
		addressPanel.add(doorNumberField, theRestBagConstraints);
		theRestBagConstraints.gridx = 1;
		addressPanel.add(zipCodeField, theRestBagConstraints);

		
		JPanel upperPanel = new JPanel(new GridLayout(3, 2));
		JPanel lowerPanel = new JPanel(new BorderLayout());
		
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.add(firstNameLabel);
		namePanel.add(firstNameField);
		
		JPanel lastNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lastNamePanel.add(lastNameLabel);
		lastNamePanel.add(lastNameField);
		
		JPanel idsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		idsPanel.add(chauffeurIdLabel);
		idsPanel.add(chauffeurIdField);
		
		JPanel phonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		phonePanel.add(phoneNumberLabel);
		phonePanel.add(phoneNumberField);
		
		JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		emailPanel.add(emaiLabel);
		emailPanel.add(emailField);
		
		upperPanel.add(namePanel);
		upperPanel.add(lastNamePanel);
		upperPanel.add(idsPanel);
		upperPanel.add(phonePanel);
		upperPanel.add(emailPanel);
		upperPanel.add(javax.swing.Box.createGlue());
		
		JPanel savePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		savePanel.add(saveButton);
		
		lowerPanel.add(addressPanel, BorderLayout.CENTER);
		lowerPanel.add(savePanel, BorderLayout.SOUTH);
		
		add(upperPanel);
		add(lowerPanel);
		
		setVisible(true);
	}

	public class SaveAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			int chauffeurId;
			try
			{
				chauffeurId = Integer.parseInt(chauffeurIdField.getText());
			} catch (NumberFormatException e2)
			{
				//error message
				JOptionPane.showMessageDialog(AddChauffeurPanel.this, "Chauffeur ID is invalid. Please only use whole numbers");
				return;
			}
			Chauffeur newChauffeur = new Chauffeur(firstNameField.getText(), lastNameField.getText(), chauffeurId);
			newChauffeur.setPhoneNumber(phoneNumberField.getText());
			
			
			int streetHouseNumber = 0;
			int zipCode = 0;
			int floorNumber = 0;
			int doorNumber = 0;
			if(streetHouseNumberField.getText() != null && !streetHouseNumberField.getText().trim().equals(""))
			try
			{
				streetHouseNumber = Integer.parseInt(streetHouseNumberField.getText());
			} catch (NumberFormatException e2)
			{
				JOptionPane.showMessageDialog(AddChauffeurPanel.this, "Street number is invalid. Please only use whole numbers");
				return;
			}
			if(zipCodeField.getText() != null && !zipCodeField.getText().trim().equals(""))
			try
			{
				zipCode = Integer.parseInt(zipCodeField.getText());				
			} catch (NumberFormatException e2)
			{
				JOptionPane.showMessageDialog(AddChauffeurPanel.this, "Zipcode is invalid. Please only use whole numbers");
				return;
			}
			if(floorNumberField.getText() != null && !floorNumberField.getText().trim().equals(""))
			try
			{
				floorNumber = Integer.parseInt(floorNumberField.getText());	
			} catch (NumberFormatException e2)
			{
				JOptionPane.showMessageDialog(AddChauffeurPanel.this, "Floor number is invalid. Please only use whole numbers");
				return;
			}
			if(doorNumberField.getText() != null && !doorNumberField.getText().trim().equals(""))
			try
			{
				doorNumber = Integer.parseInt(doorNumberField.getText());	
			} catch (NumberFormatException e2)
			{
				JOptionPane.showMessageDialog(AddChauffeurPanel.this, "Door number is invalid. Please only use whole numbers");
				return;
			}
			
			Address chauffeurAddress = new Address(streetNameField.getText(), streetHouseNumber, zipCode);
			chauffeurAddress.setDoorNumber(doorNumber);
			chauffeurAddress.setFloorNumber(floorNumber);
			
			newChauffeur.setAddress(chauffeurAddress);
			
			agency.addChauffeur(newChauffeur);
			
			AddChauffeurPanel.this.dispose();
		}

	}
}
