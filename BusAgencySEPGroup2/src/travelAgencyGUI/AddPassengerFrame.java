package travelAgencyGUI;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

import travelAgencyModel.Address;
import travelAgencyModel.Customer;
import travelAgencyModel.Passenger;
import travelAgencyModel.TravelAgency;

public class AddPassengerFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Customer relatedCustomer;
	private JTextField nameField;
	private JTextField phoneNumberField;
	private JTextField streetNameField;
	private JTextField streetHouseNumberField;
	private JTextField zipCodeField;
	private JTextField doorNumberField;
	private JTextField floorNumberField;
	private JTextField ticketPriceField;
	private JButton submitFormButton;
	private DateJPanel birthdayPanel;

	public AddPassengerFrame(TravelAgency agency, Customer relatedCustomer, double tourPrice)
	{
		super("Add Passenger");
		this.relatedCustomer = relatedCustomer;
		setSize(400, 400);
		setLayout(new GridBagLayout());

		birthdayPanel = new DateJPanel(LocalDateTime.now().getYear() - 100, LocalDateTime.now().getYear());
		nameField = new JTextField();
		phoneNumberField = new JTextField();
		streetNameField = new JTextField();
		zipCodeField = new JTextField();
		floorNumberField = new JTextField();
		doorNumberField = new JTextField();
		streetHouseNumberField = new JTextField();
		ticketPriceField = new JTextField(10);
		ticketPriceField.setText(Double.toString(agency.getCustomerSuggestedPrice(tourPrice, relatedCustomer)));
		
		new MinimumInputHandler(new JTextField[] {
			nameField, phoneNumberField, ticketPriceField	
		});
		
		submitFormButton = new JButton("Submit");

		birthdayPanel.setBorder(BorderFactory.createTitledBorder("Birthday"));
		nameField.setBorder(BorderFactory.createTitledBorder("Name"));
		phoneNumberField.setBorder(BorderFactory.createTitledBorder("Phone"));

		streetNameField.setBorder(BorderFactory.createTitledBorder("Street name"));
		streetHouseNumberField.setBorder(BorderFactory.createTitledBorder("Street number"));
		zipCodeField.setBorder(BorderFactory.createTitledBorder("Zipcode"));
		doorNumberField.setBorder(BorderFactory.createTitledBorder("Door number"));
		floorNumberField.setBorder(BorderFactory.createTitledBorder("Floor number"));

		ticketPriceField.setBorder(BorderFactory.createTitledBorder("Ticket price"));

		submitFormButton.setEnabled(false);
		submitFormButton.addActionListener(new SubmitFormHandler());
		JPanel submitPanel = new JPanel();
		JPanel ticketPricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ticketPricePanel.add(ticketPriceField);
		submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.LINE_AXIS));
		submitPanel.add(ticketPricePanel);
		submitPanel.add(Box.createHorizontalGlue());
		submitPanel.add(submitFormButton);

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

		GridBagConstraints first = new GridBagConstraints();
		first.gridx = 0;
		first.gridy = 0;
		first.gridwidth = 1;
		first.gridheight = 1;
		first.weightx = 1;
		first.weighty = 1;
		first.insets = new Insets(5, 5, 5, 5);
		first.anchor = GridBagConstraints.FIRST_LINE_START;
		first.fill = GridBagConstraints.HORIZONTAL;

		GridBagConstraints second = new GridBagConstraints();
		second.gridx = 1;
		second.gridy = 0;
		second.gridwidth = 1;
		second.gridheight = 1;
		second.weightx = 1;
		second.weighty = 1;
		second.insets = new Insets(5, 5, 5, 5);
		second.anchor = GridBagConstraints.FIRST_LINE_START;
		second.fill = GridBagConstraints.HORIZONTAL;

		GridBagConstraints birthdayPanelBagConstraints = new GridBagConstraints();
		birthdayPanelBagConstraints.gridx = 0;
		birthdayPanelBagConstraints.gridy = 1;
		birthdayPanelBagConstraints.gridwidth = 2;
		birthdayPanelBagConstraints.gridheight = 1;
		birthdayPanelBagConstraints.weightx = 1;
		birthdayPanelBagConstraints.weighty = 1;
		birthdayPanelBagConstraints.insets = new Insets(5, 5, 5, 5);
		birthdayPanelBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		birthdayPanelBagConstraints.fill = GridBagConstraints.HORIZONTAL;

		GridBagConstraints third = new GridBagConstraints();
		third.gridx = 0;
		third.gridy = 2;
		third.gridwidth = 2;
		third.gridheight = 1;
		third.weightx = 1;
		third.weighty = 1;
		third.insets = new Insets(5, 5, 5, 5);
		third.anchor = GridBagConstraints.FIRST_LINE_START;
		third.fill = GridBagConstraints.HORIZONTAL;

		GridBagConstraints fourth = new GridBagConstraints();
		fourth.gridx = 1;
		fourth.gridy = 3;
		fourth.gridwidth = 1;
		fourth.gridheight = 1;
		fourth.weightx = 1;
		fourth.weighty = 1;
		fourth.insets = new Insets(5, 5, 5, 5);
		fourth.anchor = GridBagConstraints.LAST_LINE_END;
		fourth.fill = GridBagConstraints.HORIZONTAL;

		add(nameField, first);
		add(phoneNumberField, second);
		add(birthdayPanel, birthdayPanelBagConstraints);
		add(addressPanel, third);
		add(submitPanel, fourth);

		fourth.gridx = 0;
		fourth.anchor = GridBagConstraints.LAST_LINE_START;

		setVisible(true);
	}


	private class SubmitFormHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{

			String name = nameField.getText();
			int phoneNumber;
			try
			{
				phoneNumber = Integer.parseInt(phoneNumberField.getText().trim());
			} catch (NumberFormatException e2)
			{
				JOptionPane.showMessageDialog(AddPassengerFrame.this, "Phone number is invalid. Please only use whole numbers");
				return;
			}
			Passenger newPassenger = new Passenger(name, phoneNumber, birthdayPanel.getDate());
			if (streetNameField.getText() != null && !streetNameField.getText().trim().equals("") && streetHouseNumberField.getText() != null
					&& !streetHouseNumberField.getText().trim().equals("") && zipCodeField.getText() != null && !zipCodeField.getText().trim().equals(""))
			{
				int streetHouseNumber;
				int zipCode;
				try
				{
					streetHouseNumber = Integer.parseInt(streetHouseNumberField.getText().trim());
				} catch (NumberFormatException e2)
				{
					JOptionPane.showMessageDialog(AddPassengerFrame.this, "Street number is invalid. Please only use whole numbers");
					return;
				}
				try
				{
					zipCode = Integer.parseInt(zipCodeField.getText().trim());
				} catch (NumberFormatException e2)
				{
					JOptionPane.showMessageDialog(AddPassengerFrame.this, "Zipcode is invalid. Please only use whole numbers");
					return;
				}
				Address passengerAddress = new Address(streetNameField.getText().trim(), streetHouseNumber, zipCode);

				int doorNumber;
				if (doorNumberField.getText() != null && !doorNumberField.getText().trim().equals(""))
				{
					try
					{
						doorNumber = Integer.parseInt(doorNumberField.getText().trim());
					} catch (NumberFormatException e2)
					{
						JOptionPane.showMessageDialog(AddPassengerFrame.this, "Door number is invalid. Please only use whole numbers");
						return;
					}
					passengerAddress.setDoorNumber(doorNumber);
				}

				newPassenger.setAddress(passengerAddress);

			}
			
			double price;
			try
			{
				price = Double.parseDouble(ticketPriceField.getText().replace(',', '.'));
			} catch (NumberFormatException e2)
			{
				JOptionPane.showMessageDialog(AddPassengerFrame.this, "Ticket price is invalid. Please use only decimal numbers");
				return;
			}

			relatedCustomer.addPassenger(newPassenger, price);
			AddPassengerFrame.this.dispatchEvent(new java.awt.event.WindowEvent(AddPassengerFrame.this, java.awt.event.WindowEvent.WINDOW_CLOSING));
		}
	}

	private class MinimumInputHandler implements KeyListener, javax.swing.event.ChangeListener
	{
		private JTextField[] fields;

		public MinimumInputHandler(JTextField[] fields)
		{
			this.fields = fields;
			for (JTextField field : fields)
				field.addKeyListener(this);
		}

		@Override
		public void keyPressed(KeyEvent e)
		{
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
		}

		@Override
		public void keyTyped(KeyEvent e)
		{
			check();
		}

		private void check()
		{
			boolean allFieldsOK = true;
			for (JTextField field : fields)
			{
				if (field.getText() == null || field.getText().trim().equals(""))
					allFieldsOK = false;
			}
			submitFormButton.setEnabled(allFieldsOK && birthdayPanel.hasDateSelected());
		}

		@Override
		public void stateChanged(ChangeEvent e)
		{
			check();
		}
	}
}
