import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.CellRendererPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.metal.MetalComboBoxUI;

public class AddTourFrame extends JFrame
{
	private Tour resultTour;
	private static final long serialVersionUID = 1L;
	private TravelAgency agency;
	private DateJPanel resvStartDatePanel;
	private DateJPanel resvEndDatePanel;
	private DateJPanel departureDatePanel;
	private DateJPanel arrivalDatePanel;
	private DateJPanel returnDatePanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private JPanel passengerLimitPanel;
	private JExtendedComboBox<String> maxPassengerCountCBox;
	private JList<Customer> customerList;
	private JExtendedComboBox<Chauffeur> chauffeurCBox;
	private JExtendedComboBox<Bus> busCBox;
	private JExtendedComboBox<String> destinationCBox;
	private JButton submitFormButton;
	private JCheckBox enableDiscounts;
	private JTextField basePriceField;

	private JButton addCustomerButton;
	private JButton addRemoveDstButton;
	private JButton addPassengerButton;
	private DestinationAddButtonHandler addDestinationHandler;
	private JList<String> destinationsList;

	public AddTourFrame(TravelAgency agency)
	{
		super("Add Tour");
		this.agency = agency;
		setSize(735, 540);
		setResizable(false);
		BorderLayout mainLayout = new BorderLayout();
		setLayout(mainLayout);

		resvStartDatePanel = new DateJPanel(2016, 2030);
		resvEndDatePanel = new DateJPanel(2016, 2030);
		departureDatePanel = new DateJPanel(2016, 2030);
		arrivalDatePanel = new DateJPanel(2016, 2030);
		returnDatePanel = new DateJPanel(2016, 2030);

		resvStartDatePanel.setBorder(BorderFactory.createTitledBorder("Reservation start date"));
		resvEndDatePanel.setBorder(BorderFactory.createTitledBorder("Reservation end date"));
		departureDatePanel.setBorder(BorderFactory.createTitledBorder("Departure date"));
		arrivalDatePanel.setBorder(BorderFactory.createTitledBorder("Arrival date"));
		returnDatePanel.setBorder(BorderFactory.createTitledBorder("Returning Date"));

		int[] busCapacities = agency.getBusCapacities();
		String[] passengerCounts = new String[busCapacities.length];
		for (int i = 0; i < busCapacities.length; i++)
			passengerCounts[i] = Integer.toString(busCapacities[i]);
		maxPassengerCountCBox = new JExtendedComboBox<String>(passengerCounts);
		maxPassengerCountCBox.setDefaultDisplayedItem("Passenger limit");

		new ReservationDateChangedHandler(resvStartDatePanel, resvEndDatePanel);

		enableDiscounts = new JCheckBox("Use default discount rate", true);

		String basePriceDefaultText = "Price per seat";
		basePriceField = new JTextField(basePriceDefaultText);
		basePriceField.setFont(new Font(basePriceField.getFont().getName(), Font.ITALIC, basePriceField.getFont().getSize()));
		TextFieldFocusHandler basePriceHandler = new TextFieldFocusHandler(basePriceDefaultText);
		basePriceField.addFocusListener(basePriceHandler);

		submitFormButton = new JButton("Submit");
		submitFormButton.addActionListener(new SubmitFormHandler());
		passengerLimitPanel = new JPanel();

		passengerLimitPanel.setLayout(new GridLayout());
		passengerLimitPanel.add(maxPassengerCountCBox);
		middlePanel = new JPanel();
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		lowerPanel.add(submitFormButton);
		middlePanel.setLayout(new GridLayout(1, 2));

		// middle panel
		chauffeurCBox = new JExtendedComboBox<Chauffeur>();
		chauffeurCBox.setDefaultDisplayedItem(new Chauffeur("Chauffeur", null, Integer.MIN_VALUE));
		chauffeurCBox.setPrototypeDisplayValue(new Chauffeur("Chauffeur", null, Integer.MIN_VALUE));
		chauffeurCBox.addItemListener(new BusAndChauffeurSelectedHandler());
		chauffeurCBox.setEnabled(false);

		busCBox = new JExtendedComboBox<Bus>();
		busCBox.setDefaultDisplayedItem(new Bus("Bus", null, null, Integer.MIN_VALUE));
		busCBox.setPrototypeDisplayValue(new Bus("Bus", null, null, Integer.MIN_VALUE));
		busCBox.addItemListener(new BusAndChauffeurSelectedHandler());
		busCBox.setEnabled(false);

		String[] destinations = agency.getAllDestinations();
		destinationCBox = new JExtendedComboBox<String>(destinations);
		DestinationSearchHandler destinationBoxInputHandler = new DestinationSearchHandler(destinationCBox, destinations);
		destinationCBox.setDefaultDisplayedItem("Destination");
		destinationCBox.setPrototypeDisplayValue("Destination");
		destinationCBox.setPreferredSize(new Dimension(217, (int) destinationCBox.getPreferredSize().getHeight()));
		destinationCBox.setEditable(true);
		destinationCBox.getEditor().getEditorComponent().addKeyListener(destinationBoxInputHandler);
		destinationCBox.getEditor().getEditorComponent().addFocusListener(destinationBoxInputHandler);

		JPanel middleWestPanel = new JPanel();
		JPanel middleEastPanel = new JPanel();
		middleWestPanel.setLayout(new GridLayout(2, 1));
		middleWestPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		JPanel middleWestUpperPanel = new JPanel();
		JPanel middleWestLowerPanel = new JPanel();
		middleWestPanel.add(middleWestUpperPanel);
		middleWestPanel.add(middleWestLowerPanel);

		middleWestUpperPanel.setLayout(new GridLayout(5, 1, 0, 10));

		JPanel middleWestFirstPanel = new JPanel();
		JPanel middleWestSecondPanel = new JPanel();
		JPanel middleWestThirdPanel = new JPanel();
		JPanel middleWestFourthPanel = new JPanel();

		JPanel middleEastUpperPanel = new JPanel();
		JPanel middleEastLowerPanel = new JPanel();
		JPanel middleEastUpperNorthPanel = new JPanel();
		JPanel middleEastUpperCenterPanel = new JPanel();
		JPanel middleEastLowerCenterPanel = new JPanel();
		JPanel middleEastLowerSouthPanel = new JPanel();
		middleEastLowerCenterPanel.setLayout(new GridLayout());
		middleEastUpperPanel.setLayout(new BorderLayout());
		middleEastUpperCenterPanel.setLayout(new GridLayout());
		middleEastUpperNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		middleEastLowerSouthPanel.setLayout(new BoxLayout(middleEastLowerSouthPanel, BoxLayout.LINE_AXIS));
		middleEastLowerSouthPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		customerList = new JList<Customer>(new DefaultListModel<Customer>());
		customerList.addListSelectionListener(new EnablePassengerAddHandler());
		addCustomerButton = new JButton("Add Customer");
		addCustomerButton.addActionListener(new AddCustomerHandler());
		addCustomerButton.setEnabled(false);
		addPassengerButton = new JButton("Add Passenger");
		addPassengerButton.setEnabled(false);
		addPassengerButton.addActionListener(new AddPassengerHandler());
		middleEastLowerSouthPanel.add(addCustomerButton);
		middleEastLowerSouthPanel.add(Box.createHorizontalGlue());
		middleEastLowerSouthPanel.add(addPassengerButton);

		middleEastPanel.setLayout(new GridLayout(2, 1));
		middleEastPanel.add(middleEastUpperPanel);
		middleEastPanel.add(middleEastLowerPanel);
		middleEastUpperPanel.setBorder(BorderFactory.createTitledBorder("Destinations"));
		middleEastLowerPanel.setLayout(new BorderLayout());
		middleEastLowerCenterPanel.add(customerList);
		middleEastLowerPanel.add(middleEastLowerCenterPanel, BorderLayout.CENTER);
		middleEastLowerPanel.add(middleEastLowerSouthPanel, BorderLayout.SOUTH);

		middleEastLowerPanel.setBorder(BorderFactory.createTitledBorder("Customers"));
		middleEastUpperPanel.add(middleEastUpperNorthPanel, BorderLayout.NORTH);
		middleEastUpperPanel.add(middleEastUpperCenterPanel, BorderLayout.CENTER);

		addDestinationHandler = new DestinationAddButtonHandler();
		addRemoveDstButton = new JButton("Add destination");
		addRemoveDstButton.addActionListener(addDestinationHandler);
		destinationsList = new JList<String>(new DefaultListModel<String>());
		DestinationButtonLabelSwapper swapper = new DestinationButtonLabelSwapper();
		destinationCBox.addItemListener(swapper);
		destinationsList.addFocusListener(swapper);

		destinationCBox.setPreferredSize(new Dimension(180, (int) destinationCBox.getPreferredSize().getHeight()));

		middleEastUpperCenterPanel.add(destinationsList);
		middleEastUpperNorthPanel.add(destinationCBox);
		middleEastUpperNorthPanel.add(addRemoveDstButton);

		middleWestFirstPanel.setLayout(new GridLayout());
		middleWestSecondPanel.setLayout(new GridLayout());
		middleWestThirdPanel.setLayout(new GridLayout());
		middleWestFourthPanel.setLayout(new GridLayout());

		middleWestFirstPanel.add(passengerLimitPanel);
		middleWestFirstPanel.add(Box.createHorizontalStrut(1));
		middleWestFirstPanel.add(Box.createHorizontalStrut(1));

		middleWestSecondPanel.add(busCBox);

		middleWestThirdPanel.add(chauffeurCBox);

		middleWestFourthPanel.add(basePriceField);
		middleWestFourthPanel.add(enableDiscounts);

		middleWestUpperPanel.add(middleWestFirstPanel);
		middleWestUpperPanel.add(middleWestSecondPanel);
		middleWestUpperPanel.add(middleWestThirdPanel);
		middleWestUpperPanel.add(middleWestFourthPanel);

		middleWestLowerPanel.add(departureDatePanel);
		middleWestLowerPanel.add(arrivalDatePanel);
		middleWestLowerPanel.add(returnDatePanel);

		middlePanel.add(middleWestPanel);
		middlePanel.add(middleEastPanel);

		JPanel datePanel = new JPanel();
		datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		datePanel.add(resvStartDatePanel);
		datePanel.add(resvEndDatePanel);

		add(datePanel, BorderLayout.NORTH);
		add(middlePanel, BorderLayout.CENTER);
		add(lowerPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	public Tour getResultTour()
	{
		return resultTour;
	}

	private class BusAndChauffeurSelectedHandler implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			submitFormButton.setEnabled(!busCBox.isDefaultItemSelected() && !chauffeurCBox.isDefaultItemSelected());
		}
	}

	private class SubmitFormHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// collect all of the information into a tour object

			Tour newTour = new Tour(busCBox.getSelectedItem(), chauffeurCBox.getSelectedItem(), resvStartDatePanel.getDate(), resvEndDatePanel.getDate());

			try
			{
				double price = Double.parseDouble(basePriceField.getText().replace(',', '.'));
				newTour.setBasePrice(price);
			} catch (NumberFormatException e2)
			{
				//user didn't input a tour price. sooo 0?
				newTour.setBasePrice(0);
			}

			if (departureDatePanel.hasDateSelected())
				newTour.setDepartureDate(departureDatePanel.getDate());

			if (arrivalDatePanel.hasDateSelected())
				newTour.setArrivalDate(arrivalDatePanel.getDate());

			if (returnDatePanel.hasDateSelected())
				newTour.setReturnDate(returnDatePanel.getDate());

			DefaultListModel<String> destinationsModel = (DefaultListModel<String>) destinationsList.getModel();

			if (!destinationsModel.isEmpty())
			{
				String[] destinations = new String[destinationsModel.getSize()];
				for (int i = 0; i < destinations.length; i++)
					destinations[i] = destinationsModel.get(i);

				newTour.setDestinations(destinations);
			}

			DefaultListModel<Customer> customers = (DefaultListModel<Customer>)customerList.getModel();
			for(int i = 0; i < customers.getSize(); i++)
				newTour.addCustomer(customers.get(i));
			
			resultTour = newTour;
			
			//signal parent frame to get the result
			AddTourFrame.this.dispatchEvent(
					new java.awt.event.WindowEvent(AddTourFrame.this, java.awt.event.WindowEvent.WINDOW_CLOSING));
		}
	}

	private class EnablePassengerAddHandler implements ListSelectionListener
	{
		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			addPassengerButton.setEnabled(customerList.hasFocus());
		}
	}

	private class DestinationButtonLabelSwapper implements ItemListener, FocusListener
	{

		@Override
		public void focusGained(FocusEvent e)
		{
			if (!((DefaultListModel<String>) destinationsList.getModel()).isEmpty())
				addRemoveDstButton.setText("Remove destination");
		}

		@Override
		public void focusLost(FocusEvent e)
		{
			addRemoveDstButton.setText("Add destination");
		}

		@Override
		public void itemStateChanged(ItemEvent e)
		{
			addRemoveDstButton.setText("Add destination");
		}
	}

	private class DestinationAddButtonHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (!destinationCBox.isDefaultItemSelected())
			{
				((DefaultListModel<String>) destinationsList.getModel()).addElement(destinationCBox.getSelectedItem());
				destinationCBox.reset();
			} else if (destinationsList.getSelectedIndex() != -1)
			{
				((DefaultListModel<String>) destinationsList.getModel()).removeElementAt(destinationsList.getSelectedIndex());
				destinationsList.clearSelection();
				addRemoveDstButton.setText("Add destination");
			}
		}
	}

	private class AddPassengerHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			double price = Double.parseDouble(basePriceField.getText().replace(',', '.'));
			AddPassengerFrame frame = new AddPassengerFrame(agency, customerList.getSelectedValue(), price);
			frame.addWindowListener(new AddPassengerFrameClosedHandler());
		}
	}

	private class AddCustomerHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			double price = Double.parseDouble(basePriceField.getText().replace(',', '.'));
			AddCustomerFrame frame = new AddCustomerFrame(agency, price);
			frame.addWindowListener(new AddCustomerFrameClosedHandler());
		}
	}

	private class AddPassengerFrameClosedHandler implements WindowListener
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
			customerList.repaint();
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

	private class AddCustomerFrameClosedHandler implements WindowListener
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
			if (e.getSource() instanceof AddCustomerFrame)
			{
				AddCustomerFrame source = (AddCustomerFrame) e.getSource();
				((DefaultListModel<Customer>) customerList.getModel()).addElement(source.getCustomerResult());
			}
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

	private class DestinationSearchHandler implements KeyListener, FocusListener
	{
		private JExtendedComboBox<String> cbox;
		private String[] destinations;
		private ListDataListener autoSelectingListener;
		private String previousSearchString;

		public DestinationSearchHandler(JExtendedComboBox<String> cbox, String[] destinations)
		{
			this.cbox = cbox;
			this.destinations = destinations;
			this.previousSearchString = null;
		}

		@Override
		public void keyPressed(KeyEvent e)
		{
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			String searchString = (cbox.getEditor().getItem() == null ? "" : cbox.getEditor().getItem().toString().toLowerCase());
			if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && previousSearchString != null)
			{
				for (int i = 0; i < destinations.length; i++)
				{
					String destination = destinations[i].toLowerCase();
					if (destination.startsWith(searchString) && !destination.startsWith(previousSearchString))
					{
						cbox.addItem(destinations[i]);
					}
				}
			} else
			{
				// filter out the list based on keyinput..
				for (int i = cbox.getItemCount() - 1; i >= 0; i--)
				{
					if (!cbox.getItemAt(i).toLowerCase().startsWith(searchString))
					{
						cbox.removeItemAt(i);
					}
				}
			}
			previousSearchString = searchString;
		}

		@Override
		public void keyTyped(KeyEvent e)
		{

		}

		@Override
		public void focusGained(FocusEvent e)
		{
			for (ListDataListener listener : ((DefaultComboBoxModel<String>) cbox.getModel()).getListDataListeners())
			{
				if (listener.getClass().getEnclosingClass() != null && listener.getClass().getEnclosingClass().equals(BasicComboBoxUI.class))
				{
					/*
					 * this listener sets the selected item of the combobox when
					 * the underlying model is changed which is behavious we
					 * don't want right here.
					 */
					autoSelectingListener = listener;
					cbox.getModel().removeListDataListener(listener);
				}
			}
			cbox.setItems(destinations);
			cbox.setPopupVisible(true);
			cbox.getEditor().setItem("");
		}

		@Override
		public void focusLost(FocusEvent e)
		{
			cbox.getModel().addListDataListener(autoSelectingListener);
		}

	}

	private class TextFieldFocusHandler implements FocusListener
	{
		private String defaultText;

		public TextFieldFocusHandler(String defaultText)
		{
			this.defaultText = defaultText;
		}

		@Override
		public void focusGained(FocusEvent e)
		{
			if (e.getSource() instanceof JTextField)
			{
				JTextField field = (JTextField) e.getSource();
				if (field.getText().equals(defaultText))
					field.setText("");
			}
		}

		@Override
		public void focusLost(FocusEvent e)
		{
			if (e.getSource() instanceof JTextField)
			{
				JTextField field = (JTextField) e.getSource();
				String currentText = field.getText().trim();
				if (currentText.equals(""))
					field.setText(defaultText);

				StringBuilder sb = new StringBuilder();
				boolean decimalpointSeen = false;
				for (int i = 0; i < currentText.length(); i++)
				{
					char theChar = currentText.charAt(i);
					if ((!decimalpointSeen && (theChar == '.' || theChar == ',')) || (theChar >= '0' && theChar <= '9'))
					{
						if (theChar == '.' || theChar == ',')
							decimalpointSeen = true;

						sb.append(theChar);
					}
				}

				field.setText(sb.toString());
				currentText = field.getText().trim();
				if (currentText.equals(""))
				{
					field.setText(defaultText);
					addCustomerButton.setEnabled(false);
				} else
				{
					// valid price
					addCustomerButton.setEnabled(true);
				}
			}
		}
	}

	private class ReservationDateChangedHandler implements ItemListener
	{
		private DateJPanel reservationStartDatePanel;
		private DateJPanel reservationEndDatePanel;

		public ReservationDateChangedHandler(DateJPanel reservationStartDatePanel, DateJPanel reservationEndDatePanel)
		{
			this.reservationStartDatePanel = reservationStartDatePanel;
			this.reservationEndDatePanel = reservationEndDatePanel;

			for (int i = 0; i < 2; i++)
			{
				JPanel thisPanel = i == 0 ? reservationStartDatePanel : reservationEndDatePanel;
				for (Component comp : thisPanel.getComponents())
				{
					if (comp instanceof JExtendedComboBox)
					{
						JExtendedComboBox<String> box = (JExtendedComboBox<String>) comp;
						box.addItemListener(this);
					}
				}
			}
			maxPassengerCountCBox.addItemListener(this);
		}

		@Override
		public void itemStateChanged(ItemEvent e)
		{
			if (e.getStateChange() == ItemEvent.SELECTED && !maxPassengerCountCBox.isDefaultItemSelected())
			{
				if (resvStartDatePanel.hasDateSelected() && resvEndDatePanel.hasDateSelected())
				{
					LocalDateTime startDate = reservationStartDatePanel.getDate();
					LocalDateTime endDate = reservationEndDatePanel.getDate();

					if (startDate.isBefore(endDate))
					{
						// update bus and chauffeur boxes with entities
						// available within this time interval
						busCBox.removeAllItems();
						chauffeurCBox.removeAllItems();

						for (Bus bus : agency.listAvailableBusses(startDate, endDate, Integer.parseInt(maxPassengerCountCBox.getSelectedItem())))
							busCBox.addItem(bus);

						for (Chauffeur chauffeur : agency.listAvailableChauffeurs(startDate, endDate))
							chauffeurCBox.addItem(chauffeur);

						busCBox.setEnabled(true);
						chauffeurCBox.setEnabled(true);
					}
				}
			} else
			{

				busCBox.removeAllItems();
				chauffeurCBox.removeAllItems();
				busCBox.reset();
				chauffeurCBox.reset();
				busCBox.setEnabled(false);
				chauffeurCBox.setEnabled(false);
			}
		}
	}
}
