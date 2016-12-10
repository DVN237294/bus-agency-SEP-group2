import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;


public class AddTourFrame extends JFrame
{
   private static final long serialVersionUID = 1L;
   private TravelAgency agency;
	private String[] months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

	private JPanel resvStartDatePanel;
	private JPanel resvEndDatePanel;
	private JPanel middlePanel;
	private JPanel middleDestinationPanel;
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
	private JExtendedComboBox<Chauffeur> chauffeurCBox;
	private JExtendedComboBox<Bus> busCBox;
	private JExtendedComboBox<String> destinationCBox;
	public AddTourFrame(TravelAgency agency)
	{
		super("Add Tour");
		this.agency = agency;
		setSize(735, 540);
		setResizable(false);
		BorderLayout mainLayout = new BorderLayout();
		setLayout(mainLayout);
		middlePanel = new JPanel();
		middlePanel.setLayout(new GridLayout(1, 2));
		//middleDestinationPanel = new JPanel();
		//middleDestinationPanel.setBorder(BorderFactory.createTitledBorder("Destination"));
		//middlePanel.setBorder(BorderFactory.createTitledBorder("Bus & Chauffeur"));
		
		
		setUpDateCBoxes();
		
		//middle panel
		chauffeurCBox = new JExtendedComboBox<Chauffeur>();
		chauffeurCBox.setDefaultDisplayedItem(new Chauffeur("Chauffeur", null, Integer.MIN_VALUE));
		chauffeurCBox.setPrototypeDisplayValue(new Chauffeur("Chauffeur", null, Integer.MIN_VALUE));
		chauffeurCBox.setEnabled(false);
		
		busCBox = new JExtendedComboBox<Bus>();
		busCBox.setDefaultDisplayedItem(new Bus("Bus", null, null, Integer.MIN_VALUE, null));
		busCBox.setPrototypeDisplayValue(new Bus("Bus", null, null, Integer.MIN_VALUE, null));
		busCBox.setEnabled(false);
		
		destinationCBox = new JExtendedComboBox<String>(new String[] { "teest", "Teest", "ll" });
		destinationCBox.setDefaultDisplayedItem("Destination");
		destinationCBox.setPrototypeDisplayValue("Destination");
		destinationCBox.setEditable(true);
		
		
		JPanel middleWestPanel = new JPanel();
		JPanel middleEastPanel = new JPanel();
		JPanel middleEastUpperPanel = new JPanel();
		JPanel middleEastLowerPanel = new JPanel();
		JPanel middleEastUpperNorthPanel = new JPanel();
		JPanel middleEastUpperCenterPanel = new JPanel();
		middleEastUpperPanel.setLayout(new BorderLayout());
		middleEastUpperCenterPanel.setLayout(new GridLayout());
		middleEastUpperNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		//middleEastUpperPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 6, 0));
		middleEastPanel.setLayout(new GridLayout(2, 1));
		middleEastPanel.add(middleEastUpperPanel);
		middleEastPanel.add(middleEastLowerPanel);
		middleEastUpperPanel.setBorder(BorderFactory.createTitledBorder("Destinations"));
		middleEastUpperPanel.add(middleEastUpperNorthPanel, BorderLayout.NORTH);
		middleEastUpperPanel.add(middleEastUpperCenterPanel, BorderLayout.CENTER);
		JButton addDstButton = new JButton("Add destination");
		JList<String> destinationsList = new JList<String>(new String[] { "hej", "hejsa" });

		middleEastUpperCenterPanel.add(destinationsList);
		middleEastUpperNorthPanel.add(destinationCBox);
		middleEastUpperNorthPanel.add(addDstButton);
		
		

		middlePanel.add(middleWestPanel);
		middlePanel.add(middleEastPanel);
		
		
		JPanel datePanel = new JPanel();
		datePanel.add(resvStartDatePanel);
		datePanel.add(resvEndDatePanel);
		add(datePanel, BorderLayout.NORTH);
		
		add(middlePanel, BorderLayout.CENTER);
		setVisible(true);
	}
	private void setUpDateCBoxes()
	{
		resvStartDatePanel = new JPanel();
		resvEndDatePanel = new JPanel();
		resvStartDatePanel.setBorder(BorderFactory.createTitledBorder("Reservation start date"));
		resvEndDatePanel.setBorder(BorderFactory.createTitledBorder("Reservation end date"));
		resvStartDatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		resvEndDatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//Start date
		resvStartYearCBox = new JExtendedComboBox<String>();
		for(int i = 0, year = LocalDateTime.now().getYear(); i < 10; i++, year++)
			resvStartYearCBox.addItem(Integer.toString(year));
		resvStartYearCBox.setDefaultDisplayedItem("Year");
		resvStartYearCBox.setPrototypeDisplayValue("2016");
		resvStartMonthCBox = new JExtendedComboBox<String>(months);
		resvStartMonthCBox.setDefaultDisplayedItem("Month");
		resvStartMonthCBox.setPrototypeDisplayValue(months[8]);
		resvStartDayCBox = new JExtendedComboBox<String>();
		resvStartDayCBox.setDefaultDisplayedItem("Day");
		resvStartDayCBox.setPrototypeDisplayValue("Day");
		resvStartDayCBox.setEnabled(false);
		YearMonthSelectionChangedHandler handler = new YearMonthSelectionChangedHandler(resvStartYearCBox, resvStartMonthCBox, resvStartDayCBox);
		resvStartYearCBox.addItemListener(handler);
		resvStartMonthCBox.addItemListener(handler);
		resvStartHourCBox = new JExtendedComboBox<String>();
		for(int i = 0; i < 24; i++)
			resvStartHourCBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
		resvStartHourCBox.setDefaultDisplayedItem("Hour");
		resvStartHourCBox.setPrototypeDisplayValue("Hour");
		resvStartMinCBox = new JExtendedComboBox<String>();
		for(int i = 0; i < 60; i++)
			resvStartMinCBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
		resvStartMinCBox.setDefaultDisplayedItem("Minute");
		resvStartMinCBox.setPrototypeDisplayValue("Minute");
		
		//end date
		resvEndYearCBox = new JExtendedComboBox<String>();
		for(int i = 0, year = LocalDateTime.now().getYear(); i < 10; i++, year++)
			resvEndYearCBox.addItem(Integer.toString(year));
		resvEndYearCBox.setDefaultDisplayedItem("Year");
		resvEndYearCBox.setPrototypeDisplayValue("2016");
		resvEndMonthCBox = new JExtendedComboBox<String>(months);
		resvEndMonthCBox.setDefaultDisplayedItem("Month");
		resvEndMonthCBox.setPrototypeDisplayValue(months[8]);
		resvEndDayCBox = new JExtendedComboBox<String>();
		resvEndDayCBox.setDefaultDisplayedItem("Day");
		resvEndDayCBox.setPrototypeDisplayValue("Day");
		resvEndDayCBox.setEnabled(false);
		YearMonthSelectionChangedHandler handler2 = new YearMonthSelectionChangedHandler(resvEndYearCBox, resvEndMonthCBox, resvEndDayCBox);
		resvEndYearCBox.addItemListener(handler2);
		resvEndMonthCBox.addItemListener(handler2);
		resvEndHourCBox = new JExtendedComboBox<String>();
		for(int i = 0; i < 24; i++)
			resvEndHourCBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
		resvEndHourCBox.setDefaultDisplayedItem("Hour");
		resvEndHourCBox.setPrototypeDisplayValue("Hour");
		resvEndMinCBox = new JExtendedComboBox<String>();
		for(int i = 0; i < 60; i++)
			resvEndMinCBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
		resvEndMinCBox.setDefaultDisplayedItem("Minute");
		resvEndMinCBox.setPrototypeDisplayValue("Minute");

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
<<<<<<< HEAD
=======
		
		//middle panel
		chauffeurCBox = new JExtendedComboBox<Chauffeur>();
		chauffeurCBox.setDefaultDisplayedItem(new Chauffeur("Chauffeur", null, Integer.MIN_VALUE));
		chauffeurCBox.setPrototypeDisplayValue(new Chauffeur("Chauffeur", null, Integer.MIN_VALUE));
		chauffeurCBox.setEnabled(false);
		
		busCBox = new JExtendedComboBox<Bus>();
		busCBox.setDefaultDisplayedItem(new Bus("Bus", null, null, Integer.MIN_VALUE, null, null));
		busCBox.setPrototypeDisplayValue(new Bus("Bus", null, null, Integer.MIN_VALUE, null, null));
		busCBox.setEnabled(false);
		
		destinationCBox = new JExtendedComboBox<String>(new String[] { "teest", "Teest", "ll" });
		destinationCBox.setDefaultDisplayedItem("Destination");
		destinationCBox.setPrototypeDisplayValue("Destination");
		destinationCBox.setEditable(true);
		
		
		middlePanel.add(chauffeurCBox);
		middlePanel.add(busCBox);
		middlePanel.add(destinationCBox);
		
		
		add(westPanel);
		add(eastPanel);
		
		FlowLayout westLayout = new FlowLayout(FlowLayout.LEFT);
		westPanel.setLayout(westLayout);
		
		westPanel.add(resvStartDatePanel);
		westPanel.add(resvEndDatePanel);
		westPanel.add(middlePanel);
		
		setVisible(true);
>>>>>>> branch 'develop' of https://github.com/DVN237294/bus-agency-SEP-group2.git
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
			if(!yearBox.isDefaultItemSelected() && !monthBox.isDefaultItemSelected() && yearBox.isPopupVisible() ^ monthBox.isPopupVisible())
			{
				int year = Integer.parseInt(yearBox.getSelectedItem());
				int daysThisMonth = (new GregorianCalendar(year, java.util.Arrays.asList(months).indexOf(monthBox.getSelectedItem()), 1)).getActualMaximum(Calendar.DAY_OF_MONTH);
				
				dayBox.removeAllItems();
				for(int i = 1; i <= daysThisMonth; i++)
					dayBox.addItem(Integer.toString(i));
				
				dayBox.setEnabled(true);
			}
			if(yearBox.isDefaultItemSelected() || monthBox.isDefaultItemSelected())
				dayBox.setEnabled(false);
		}
	}
}
