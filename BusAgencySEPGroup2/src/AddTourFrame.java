import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.util.converter.LocalDateTimeStringConverter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class AddTourFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private TravelAgency agency;
	private String[] months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
			"December" };

	private JPanel resvStartDatePanel;
	private JPanel resvEndDatePanel;
	private JPanel departureDatePanel;
	private JPanel arrivalDatePanel;
	private JPanel returnDatePanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private JPanel middleDestinationPanel;
	private JPanel passengerLimitPanel;
	private JExtendedComboBox<String> resvStartYearCBox;
	private JExtendedComboBox<String> resvStartMonthCBox;
	private JExtendedComboBox<String> resvStartDayCBox;
	private JExtendedComboBox<String> resvStartHourCBox;
	private JExtendedComboBox<String> resvStartMinCBox;
	private JExtendedComboBox<String> resvEndYearCBox;
	private JExtendedComboBox<String> resvEndMonthCBox;
	private JExtendedComboBox<String> resvEndDayCBox;
	private JExtendedComboBox<String> resvEndHourCBox;
	private JExtendedComboBox<String> resvEndMinCBox;
	private JExtendedComboBox<String> departureYearCBox;
	private JExtendedComboBox<String> departureMonthCBox;
	private JExtendedComboBox<String> departureDayCBox;
	private JExtendedComboBox<String> departureHourCBox;
	private JExtendedComboBox<String> departureMinCBox;
	private JExtendedComboBox<String> arrivalYearCBox;
	private JExtendedComboBox<String> arrivalMonthCBox;
	private JExtendedComboBox<String> arrivalDayCBox;
	private JExtendedComboBox<String> arrivalHourCBox;
	private JExtendedComboBox<String> arrivalMinCBox;
	private JExtendedComboBox<String> returnYearCBox;
	private JExtendedComboBox<String> returnMonthCBox;
	private JExtendedComboBox<String> returnDayCBox;
	private JExtendedComboBox<String> returnHourCBox;
	private JExtendedComboBox<String> returnMinCBox;
	private JExtendedComboBox<String> maxPassengerCountCBox;

	private JExtendedComboBox<Chauffeur> chauffeurCBox;
	private JExtendedComboBox<Bus> busCBox;
	private JExtendedComboBox<String> destinationCBox;
	private JButton submitFormButton;
	private JCheckBox busChauffeurCheckBox;
	private JCheckBox enableDiscounts;
	private JTextField basePriceField;

	public AddTourFrame(TravelAgency agency)
	{
		super("Add Tour");
		this.agency = agency;
		setSize(735, 540);
		setResizable(false);
		BorderLayout mainLayout = new BorderLayout();
		setLayout(mainLayout);

		enableDiscounts = new JCheckBox("Use default discount rate");
		int[] busCapacities = agency.getBusCapacities();
		String[] passengerCounts = new String[busCapacities.length];
		for(int i = 0; i < busCapacities.length; i++)
			passengerCounts[i] = Integer.toString(busCapacities[i]);
		maxPassengerCountCBox = new JExtendedComboBox<String>(passengerCounts);
		maxPassengerCountCBox.setDefaultDisplayedItem("Passenger limit");
		basePriceField = new JTextField("Price per seat");
		basePriceField.setFont(new Font(basePriceField.getFont().getName(), Font.ITALIC, basePriceField.getFont().getSize()));
		busChauffeurCheckBox = new JCheckBox("<html>Bus & Chauffeur<br>reservation");
		submitFormButton = new JButton("Submit");
		passengerLimitPanel = new JPanel();
		//passengerLimitPanel.setBorder(BorderFactory.createTitledBorder("Passenger limit"));
		passengerLimitPanel.setLayout(new GridLayout());
		passengerLimitPanel.add(maxPassengerCountCBox);
		middlePanel = new JPanel();
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		lowerPanel.add(submitFormButton);
		middlePanel.setLayout(new GridLayout(1, 2));
		// middleDestinationPanel = new JPanel();
		// middleDestinationPanel.setBorder(BorderFactory.createTitledBorder("Destination"));
		// middlePanel.setBorder(BorderFactory.createTitledBorder("Bus & Chauffeur"));

		setupDateCBoxes();

		// middle panel
		chauffeurCBox = new JExtendedComboBox<Chauffeur>();
		chauffeurCBox.setDefaultDisplayedItem(new Chauffeur("Chauffeur", null, Integer.MIN_VALUE));
		chauffeurCBox.setPrototypeDisplayValue(new Chauffeur("Chauffeur", null, Integer.MIN_VALUE));
		chauffeurCBox.setEnabled(false);

		busCBox = new JExtendedComboBox<Bus>();
		busCBox.setDefaultDisplayedItem(new Bus("Bus", null, null, Integer.MIN_VALUE));
		busCBox.setPrototypeDisplayValue(new Bus("Bus", null, null, Integer.MIN_VALUE));
		busCBox.setEnabled(false);

		destinationCBox = new JExtendedComboBox<String>(new String[] { "teest", "Teest", "ll" });
		destinationCBox.setDefaultDisplayedItem("Destination");
		destinationCBox.setPrototypeDisplayValue("Destination");
		destinationCBox.setEditable(true);

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
		middleEastLowerSouthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JList<Customer> customerList = new JList<Customer>();
		JButton addCustomerButton = new JButton("Add Customer");
		middleEastLowerSouthPanel.add(addCustomerButton);
		// middleEastUpperPanel.setBorder(BorderFactory.createEmptyBorder(3, 0,
		// 6, 0));
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
		JButton addDstButton = new JButton("Add destination");
		JList<String> destinationsList = new JList<String>(new String[] { "hej", "hejsa" });

		middleEastUpperCenterPanel.add(destinationsList);
		middleEastUpperNorthPanel.add(destinationCBox);
		middleEastUpperNorthPanel.add(addDstButton);

		middleWestFirstPanel.setLayout(new GridLayout());
		middleWestSecondPanel.setLayout(new GridLayout());
		middleWestThirdPanel.setLayout(new GridLayout());

		middleWestFirstPanel.add(busChauffeurCheckBox);
		middleWestFirstPanel.add(passengerLimitPanel);

		middleWestSecondPanel.add(chauffeurCBox);
		middleWestSecondPanel.add(busCBox);

		middleWestThirdPanel.add(basePriceField);
		middleWestThirdPanel.add(enableDiscounts);

		middleWestUpperPanel.add(middleWestFirstPanel);
		middleWestUpperPanel.add(middleWestSecondPanel);
		middleWestUpperPanel.add(middleWestThirdPanel);
		middleWestLowerPanel.add(departureDatePanel);
		middleWestLowerPanel.add(arrivalDatePanel);
		middleWestLowerPanel.add(returnDatePanel);

		middlePanel.add(middleWestPanel);
		middlePanel.add(middleEastPanel);

		JPanel datePanel = new JPanel();
		datePanel.add(resvStartDatePanel);
		datePanel.add(resvEndDatePanel);

		add(datePanel, BorderLayout.NORTH);
		add(middlePanel, BorderLayout.CENTER);
		add(lowerPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	private void setupDateCBoxes()
	{
		resvStartDatePanel = new JPanel();
		resvEndDatePanel = new JPanel();
		departureDatePanel = new JPanel();
		arrivalDatePanel = new JPanel();
		returnDatePanel = new JPanel();

		resvStartDatePanel.setBorder(BorderFactory.createTitledBorder("Reservation start date"));
		resvEndDatePanel.setBorder(BorderFactory.createTitledBorder("Reservation end date"));
		departureDatePanel.setBorder(BorderFactory.createTitledBorder("Departure date"));
		arrivalDatePanel.setBorder(BorderFactory.createTitledBorder("Arrival date"));
		returnDatePanel.setBorder(BorderFactory.createTitledBorder("Returning Date"));

		resvStartDatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		resvEndDatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		departureDatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		arrivalDatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		returnDatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		resvStartYearCBox = new JExtendedComboBox<String>();
		resvEndYearCBox = new JExtendedComboBox<String>();
		departureYearCBox = new JExtendedComboBox<String>();
		arrivalYearCBox = new JExtendedComboBox<String>();
		returnYearCBox = new JExtendedComboBox<String>();
		for (int i = 0, year = LocalDateTime.now().getYear(); i < 10; i++, year++)
		{
			resvStartYearCBox.addItem(Integer.toString(year));
			resvEndYearCBox.addItem(Integer.toString(year));
			departureYearCBox.addItem(Integer.toString(year));
			arrivalYearCBox.addItem(Integer.toString(year));
			returnYearCBox.addItem(Integer.toString(year));
		}
		resvStartYearCBox.setDefaultDisplayedItem("Year");
		resvEndYearCBox.setDefaultDisplayedItem("Year");
		departureYearCBox.setDefaultDisplayedItem("Year");
		arrivalYearCBox.setDefaultDisplayedItem("Year");
		returnYearCBox.setDefaultDisplayedItem("Year");
		resvStartYearCBox.setPrototypeDisplayValue("0000");
		resvEndYearCBox.setPrototypeDisplayValue("0000");
		departureYearCBox.setPrototypeDisplayValue("0000");
		arrivalYearCBox.setPrototypeDisplayValue("0000");
		returnYearCBox.setPrototypeDisplayValue("0000");

		resvStartMonthCBox = new JExtendedComboBox<String>(months);
		resvEndMonthCBox = new JExtendedComboBox<String>(months);
		departureMonthCBox = new JExtendedComboBox<String>(months);
		arrivalMonthCBox = new JExtendedComboBox<String>(months);
		returnMonthCBox = new JExtendedComboBox<String>(months);
		resvStartMonthCBox.setDefaultDisplayedItem("Month");
		resvEndMonthCBox.setDefaultDisplayedItem("Month");
		departureMonthCBox.setDefaultDisplayedItem("Month");
		arrivalMonthCBox.setDefaultDisplayedItem("Month");
		returnMonthCBox.setDefaultDisplayedItem("Month");
		resvStartMonthCBox.setPrototypeDisplayValue(months[8]);
		resvEndMonthCBox.setPrototypeDisplayValue(months[8]);
		departureMonthCBox.setPrototypeDisplayValue(months[8]);
		arrivalMonthCBox.setPrototypeDisplayValue(months[8]);
		returnMonthCBox.setPrototypeDisplayValue(months[8]);

		resvStartDayCBox = new JExtendedComboBox<String>();
		resvEndDayCBox = new JExtendedComboBox<String>();
		departureDayCBox = new JExtendedComboBox<String>();
		arrivalDayCBox = new JExtendedComboBox<String>();
		returnDayCBox = new JExtendedComboBox<String>();
		resvStartDayCBox.setDefaultDisplayedItem("Day");
		resvEndDayCBox.setDefaultDisplayedItem("Day");
		departureDayCBox.setDefaultDisplayedItem("Day");
		arrivalDayCBox.setDefaultDisplayedItem("Day");
		returnDayCBox.setDefaultDisplayedItem("Day");
		resvStartDayCBox.setPrototypeDisplayValue("Day");
		resvEndDayCBox.setPrototypeDisplayValue("Day");
		departureDayCBox.setPrototypeDisplayValue("Day");
		arrivalDayCBox.setPrototypeDisplayValue("Day");
		returnDayCBox.setPrototypeDisplayValue("Day");
		resvStartDayCBox.setEnabled(false);
		resvEndDayCBox.setEnabled(false);
		departureDayCBox.setEnabled(false);
		arrivalDayCBox.setEnabled(false);
		returnDayCBox.setEnabled(false);

		YearMonthSelectionChangedHandler resvStartDateHandler = new YearMonthSelectionChangedHandler(resvStartYearCBox, resvStartMonthCBox, resvStartDayCBox);
		YearMonthSelectionChangedHandler resvEndDateHandler = new YearMonthSelectionChangedHandler(resvEndYearCBox, resvEndMonthCBox, resvEndDayCBox);
		YearMonthSelectionChangedHandler departureDateHandler = new YearMonthSelectionChangedHandler(departureYearCBox, departureMonthCBox, departureDayCBox);
		YearMonthSelectionChangedHandler arrivalDateHandler = new YearMonthSelectionChangedHandler(arrivalYearCBox, arrivalMonthCBox, arrivalDayCBox);
		YearMonthSelectionChangedHandler returnDateHandler = new YearMonthSelectionChangedHandler(returnYearCBox, returnMonthCBox, returnDayCBox);

		resvStartYearCBox.addItemListener(resvStartDateHandler);
		resvStartMonthCBox.addItemListener(resvStartDateHandler);
		resvEndYearCBox.addItemListener(resvEndDateHandler);
		resvEndMonthCBox.addItemListener(resvEndDateHandler);
		departureYearCBox.addItemListener(departureDateHandler);
		departureMonthCBox.addItemListener(departureDateHandler);
		arrivalYearCBox.addItemListener(arrivalDateHandler);
		arrivalMonthCBox.addItemListener(arrivalDateHandler);
		returnYearCBox.addItemListener(returnDateHandler);
		returnMonthCBox.addItemListener(returnDateHandler);

		resvStartHourCBox = new JExtendedComboBox<String>();
		resvEndHourCBox = new JExtendedComboBox<String>();
		departureHourCBox = new JExtendedComboBox<String>();
		arrivalHourCBox = new JExtendedComboBox<String>();
		returnHourCBox = new JExtendedComboBox<String>();
		for (int i = 0; i < 24; i++)
		{
			resvStartHourCBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
			resvEndHourCBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
			departureHourCBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
			arrivalHourCBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
			returnHourCBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
		}
		resvStartHourCBox.setDefaultDisplayedItem("Hour");
		resvEndHourCBox.setDefaultDisplayedItem("Hour");
		departureHourCBox.setDefaultDisplayedItem("Hour");
		arrivalHourCBox.setDefaultDisplayedItem("Hour");
		returnHourCBox.setDefaultDisplayedItem("Hour");
		resvStartHourCBox.setPrototypeDisplayValue("Hour");
		resvEndHourCBox.setPrototypeDisplayValue("Hour");
		departureHourCBox.setPrototypeDisplayValue("Hour");
		arrivalHourCBox.setPrototypeDisplayValue("Hour");
		returnHourCBox.setPrototypeDisplayValue("Hour");

		resvStartMinCBox = new JExtendedComboBox<String>();
		resvEndMinCBox = new JExtendedComboBox<String>();
		departureMinCBox = new JExtendedComboBox<String>();
		arrivalMinCBox = new JExtendedComboBox<String>();
		returnMinCBox = new JExtendedComboBox<String>();
		for (int i = 0; i < 60; i++)
		{
			resvStartMinCBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
			resvEndMinCBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
			departureMinCBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
			arrivalMinCBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
			returnMinCBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
		}
		resvStartMinCBox.setDefaultDisplayedItem("Minute");
		resvEndMinCBox.setDefaultDisplayedItem("Minute");
		departureMinCBox.setDefaultDisplayedItem("Minute");
		arrivalMinCBox.setDefaultDisplayedItem("Minute");
		returnMinCBox.setDefaultDisplayedItem("Minute");
		resvStartMinCBox.setPrototypeDisplayValue("Minute");
		resvEndMinCBox.setPrototypeDisplayValue("Minute");
		departureMinCBox.setPrototypeDisplayValue("Minute");
		arrivalMinCBox.setPrototypeDisplayValue("Minute");
		returnMinCBox.setPrototypeDisplayValue("Minute");

		resvStartDatePanel.add(resvStartYearCBox);
		resvStartDatePanel.add(resvStartMonthCBox);
		resvStartDatePanel.add(resvStartDayCBox);
		resvStartDatePanel.add(resvStartHourCBox);
		resvStartDatePanel.add(resvStartMinCBox);

		resvEndDatePanel.add(resvEndYearCBox);
		resvEndDatePanel.add(resvEndMonthCBox);
		resvEndDatePanel.add(resvEndDayCBox);
		resvEndDatePanel.add(resvEndHourCBox);
		resvEndDatePanel.add(resvEndMinCBox);

		departureDatePanel.add(departureYearCBox);
		departureDatePanel.add(departureMonthCBox);
		departureDatePanel.add(departureDayCBox);
		departureDatePanel.add(departureHourCBox);
		departureDatePanel.add(departureMinCBox);

		arrivalDatePanel.add(arrivalYearCBox);
		arrivalDatePanel.add(arrivalMonthCBox);
		arrivalDatePanel.add(arrivalDayCBox);
		arrivalDatePanel.add(arrivalHourCBox);
		arrivalDatePanel.add(arrivalMinCBox);

		returnDatePanel.add(returnYearCBox);
		returnDatePanel.add(returnMonthCBox);
		returnDatePanel.add(returnDayCBox);
		returnDatePanel.add(returnHourCBox);
		returnDatePanel.add(returnMinCBox);

		new ReservationDateChangedHandler(resvStartDatePanel, resvEndDatePanel);
	}

	private class ReservationDateChangedHandler implements ItemListener
	{
		private JPanel reservationStartDatePanel;
		private JPanel reservationEndDatePanel;

		public ReservationDateChangedHandler(JPanel reservationStartDatePanel, JPanel reservationEndDatePanel)
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
			boolean datesSelected = true;
			if (e.getStateChange() == ItemEvent.SELECTED && !maxPassengerCountCBox.isDefaultItemSelected())
			{
				for (int i = 0; i < 2; i++)
				{
					JPanel thisPanel = i == 0 ? reservationStartDatePanel : reservationEndDatePanel;
					if (datesSelected)
						for (Component comp : thisPanel.getComponents())
						{
							if (comp instanceof JExtendedComboBox)
							{
								JExtendedComboBox<String> box = (JExtendedComboBox<String>) comp;
								if (box.isDefaultItemSelected())
								{
									datesSelected = false;
									break;
								}
							}
						}
				}
				if (datesSelected)
				{
					int startYear = Integer.parseInt(resvStartYearCBox.getSelectedItem());
					int startMonth = resvStartMonthCBox.getSelectedIndex();
					int startDay = Integer.parseInt(resvStartDayCBox.getSelectedItem());
					int startHour = Integer.parseInt(resvStartHourCBox.getSelectedItem());
					int startMinute = Integer.parseInt(resvStartMinCBox.getSelectedItem());
					int endYear = Integer.parseInt(resvEndYearCBox.getSelectedItem());
					int endMonth = resvEndMonthCBox.getSelectedIndex();
					int endDay = Integer.parseInt(resvEndDayCBox.getSelectedItem());
					int endHour = Integer.parseInt(resvEndHourCBox.getSelectedItem());
					int endMinute = Integer.parseInt(resvEndMinCBox.getSelectedItem());
					LocalDateTime startDate =  LocalDateTime.of(startYear, startMonth, startDay, startHour, startMinute);
					LocalDateTime endDate = LocalDateTime.of(endYear, endMonth, endDay, endHour, endMinute);
					
					if(startDate.isBefore(endDate))
					{
						//update bus and chauffeur boxes with entities available within this time interval
						busCBox.removeAllItems();
						chauffeurCBox.removeAllItems();
						
						for(Bus bus : agency.listAvailableBusses(startDate, endDate, Integer.parseInt(maxPassengerCountCBox.getSelectedItem())))
							busCBox.addItem(bus);
						
						for(Chauffeur chauffeur : agency.listAvailableChauffeurs(startDate, endDate))
							chauffeurCBox.addItem(chauffeur);
						
						busCBox.setEnabled(true);
						chauffeurCBox.setEnabled(true);
					}
				}
			}
			else {

				busCBox.removeAllItems();
				chauffeurCBox.removeAllItems();
				busCBox.reset();
				chauffeurCBox.reset();
				busCBox.setEnabled(false);
				chauffeurCBox.setEnabled(false);
			}
		}
	}

	private class YearMonthSelectionChangedHandler implements ItemListener
	{
		private JExtendedComboBox<String> yearBox;
		private JExtendedComboBox<String> monthBox;
		private JExtendedComboBox<String> dayBox;

		public YearMonthSelectionChangedHandler(JExtendedComboBox<String> yearBox, JExtendedComboBox<String> monthBox, JExtendedComboBox<String> dayBox)
		{
			this.yearBox = yearBox;
			this.monthBox = monthBox;
			this.dayBox = dayBox;
			// TODO Auto-generated constructor stub
		}

		@Override
		public void itemStateChanged(ItemEvent e)
		{
			// TODO Auto-generated method stub
			if (!yearBox.isDefaultItemSelected() && !monthBox.isDefaultItemSelected())
			{
				int year = Integer.parseInt(yearBox.getSelectedItem());
				int daysThisMonth = (new GregorianCalendar(year, java.util.Arrays.asList(months).indexOf(monthBox.getSelectedItem()), 1))
						.getActualMaximum(Calendar.DAY_OF_MONTH);

				dayBox.removeAllItems();
				for (int i = 1; i <= daysThisMonth; i++)
					dayBox.addItem(Integer.toString(i));

				dayBox.setEnabled(true);
			}
			if (yearBox.isDefaultItemSelected() || monthBox.isDefaultItemSelected())
				dayBox.setEnabled(false);
		}
	}
}
