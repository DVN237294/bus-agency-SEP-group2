import javax.swing.JComboBox;

import java.awt.event.*;
public class JExtendedComboBox<T> extends JComboBox<T>
{
	private static final long serialVersionUID = 1L;
	private T defaultDisplayedValue;
	public JExtendedComboBox(T[] arg0)
	{
		super(arg0);
		addFocusListener(new FocusListen());
	}
	
	public T getDefaultDisplayedValue()
	{
		return defaultDisplayedValue;
	}
	public void setDefaultDisplayedValue(T input)
	{
		defaultDisplayedValue = input;
		if(getItemAt(0) == null || !getItemAt(0).equals(defaultDisplayedValue))
		{
			insertItemAt(defaultDisplayedValue, 0);
			setSelectedIndex(0);
		}
	}
	
	private class FocusListen implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent arg0)
		{
			// TODO Auto-generated method stub
			if(getItemAt(0) != null && getItemAt(0).equals(defaultDisplayedValue))
			{
				removeItemAt(0);
			}
			if(getItemCount() != 0)
			{
				setSelectedIndex(0);				
			}
		}

		@Override
		public void focusLost(FocusEvent arg0)
		{
			// TODO Auto-generated method stub
			if(getItemCount() == 0)
			{
				insertItemAt(defaultDisplayedValue, 0);
				setSelectedIndex(0);				
			}
		}
		
	}
}
