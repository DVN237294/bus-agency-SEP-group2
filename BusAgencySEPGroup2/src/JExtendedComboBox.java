import javax.swing.JComboBox;

import java.awt.event.*;
public class JExtendedComboBox<T> extends JComboBox<T>
{
	private static final long serialVersionUID = 1L;
	private T defaultDisplayedValue;
	public JExtendedComboBox(T type)
	{
		super();
	}
	
	public T getDefaultDisplayedValue()
	{
		return defaultDisplayedValue;
	}
	public void setDefaultDisplayedValue(T input)
	{
		defaultDisplayedValue = input;
	}
	
	private class FocusListen implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
}
