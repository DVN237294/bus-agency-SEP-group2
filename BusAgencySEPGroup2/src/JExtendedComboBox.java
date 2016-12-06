import javax.swing.JComboBox;
import java.awt.event.*;

public class JExtendedComboBox<T> extends JComboBox<T>
{
	private static final long serialVersionUID = 1L;
	private T defaultDisplayedItem;
	public JExtendedComboBox(T[] arg0)
	{
		super(arg0);
		addFocusListener(new FocusListen());
	}
	
	public JExtendedComboBox()
	{
		super();
		addFocusListener(new FocusListen());
	}
	
	public T getDefaultDisplayedItem()
	{
		return defaultDisplayedItem;
	}
	public void setDefaultDisplayedItem(T input)
	{
		defaultDisplayedItem = input;
		if(getItemAt(0) == null || !getItemAt(0).equals(defaultDisplayedItem))
		{
			insertItemAt(defaultDisplayedItem, 0);
			setSelectedIndex(0);
		}
	}
	@Override 
	public T getSelectedItem()
	{
		return super.getItemAt(super.getSelectedIndex());
	}
	public boolean isDefaultItemSelected()
	{
		return getSelectedItem().equals(getDefaultDisplayedItem());
	}
	public void reset()
	{
		if(defaultDisplayedItem != null)
		{
			if(getItemAt(0) == null || !getItemAt(0).equals(defaultDisplayedItem))
				insertItemAt(defaultDisplayedItem, 0);

			setSelectedIndex(0);
		}
	}
	
	private class FocusListen implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent arg0)
		{
			// TODO Auto-generated method stub
			if(getItemAt(0) != null && getItemAt(0).equals(defaultDisplayedItem))
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
				insertItemAt(defaultDisplayedItem, 0);
				setSelectedIndex(0);				
			}
		}
		
	}
}
