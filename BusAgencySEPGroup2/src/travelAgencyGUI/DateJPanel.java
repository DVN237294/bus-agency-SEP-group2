package travelAgencyGUI;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.scene.layout.Border;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DateJPanel extends JPanel
{	
	private String[] months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
"December" };
	private JExtendedComboBox<String> yearComboBox;
	private JExtendedComboBox<String> monthComboBox;
	private JExtendedComboBox<String> dayComboBox;
	private JExtendedComboBox<String> hourComboBox;
	private JExtendedComboBox<String> minComboBox;
	public DateJPanel(int startYear, int endYear)
	{
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		

		yearComboBox = new JExtendedComboBox<String>();
		for (int year = startYear; year <= endYear; year++)
		{
			yearComboBox.addItem(Integer.toString(year));
		}
		yearComboBox.setDefaultDisplayedItem("Year");
		yearComboBox.setPrototypeDisplayValue("0000");

		monthComboBox = new JExtendedComboBox<String>(months);
		monthComboBox.setDefaultDisplayedItem("Month");
		monthComboBox.setPrototypeDisplayValue(months[8]);

		dayComboBox = new JExtendedComboBox<String>();
		dayComboBox.setDefaultDisplayedItem("Day");
		dayComboBox.setPrototypeDisplayValue("Day");
		dayComboBox.setEnabled(false);

		YearMonthSelectionChangedHandler handler = new YearMonthSelectionChangedHandler(yearComboBox, monthComboBox, dayComboBox);
		
		yearComboBox.addItemListener(handler);
		monthComboBox.addItemListener(handler);

		hourComboBox = new JExtendedComboBox<String>();
		for (int i = 0; i < 24; i++)
		{
			hourComboBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
		}
		hourComboBox.setDefaultDisplayedItem("Hour");
		hourComboBox.setPrototypeDisplayValue("Hour");

		minComboBox = new JExtendedComboBox<String>();
		for (int i = 0; i < 60; i++)
		{
			minComboBox.addItem(i < 10 ? "0" + Integer.toString(i) : Integer.toString(i));
		}
		minComboBox.setDefaultDisplayedItem("Minute");
		minComboBox.setPrototypeDisplayValue("Minute");

		add(yearComboBox);
		add(monthComboBox);
		add(dayComboBox);
		add(hourComboBox);
		add(minComboBox);
	}
	
	@Override
	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		yearComboBox.setEnabled(enabled);
		monthComboBox.setEnabled(enabled);
		dayComboBox.setEnabled(enabled);
		hourComboBox.setEnabled(enabled);
		minComboBox.setEnabled(enabled);
	}
	public boolean hasDateSelected()
	{
		return !yearComboBox.isDefaultItemSelected() && 
				!monthComboBox.isDefaultItemSelected() &&
				!dayComboBox.isDefaultItemSelected() &&
				!hourComboBox.isDefaultItemSelected() &&
				!minComboBox.isDefaultItemSelected();
	}
	public LocalDateTime getDate()
	{
		if(hasDateSelected())
		{
			int year = Integer.parseInt(yearComboBox.getSelectedItem());
			int month = monthComboBox.getSelectedIndex() + 1;
			int day = Integer.parseInt(dayComboBox.getSelectedItem());
			int hour = Integer.parseInt(hourComboBox.getSelectedItem());
			int minute = Integer.parseInt(minComboBox.getSelectedItem());
			return LocalDateTime.of(year, month, day, hour, minute);
		}
		else
			return null;
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
