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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;


public class AddTourFrame extends JFrame
{
	private String[] months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
	private JPanel westPanel;
	private JPanel eastPanel;
	private JPanel westNorthPanel;
	private JExtendedComboBox<String> resvStartYearCBox;
	private JExtendedComboBox<String> resvStartMonthCBox;
	private JExtendedComboBox<String> resvStartDayCBox;
	public AddTourFrame()
	{
		super("Add Tour");
		setSize(960, 540);
		setLayout(new GridLayout());
		westPanel = new JPanel();
		eastPanel = new JPanel();
		westNorthPanel = new JPanel();
		westNorthPanel.setBorder(BorderFactory.createTitledBorder("Reservation start date"));
		westNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
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
		
		
		westNorthPanel.add(resvStartYearCBox);
		westNorthPanel.add(resvStartMonthCBox);
		westNorthPanel.add(resvStartDayCBox);
		
		add(westPanel);
		add(eastPanel);
		
		westPanel.setLayout(new GridLayout(5, 1));
		westPanel.add(westNorthPanel);
		
		
		setVisible(true);
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
			if(!yearBox.isDefaultItemSelected() && !monthBox.isDefaultItemSelected())
			{
				int year = Integer.parseInt(yearBox.getSelectedItem());
				int daysThisMonth = (new GregorianCalendar(year, java.util.Arrays.asList(months).indexOf(monthBox.getSelectedItem()), 1)).getActualMaximum(Calendar.DAY_OF_MONTH);
				
				//System.out.println(daysThisMonth);
			}
			//System.out.println(e.getID());
		}
	}
}
