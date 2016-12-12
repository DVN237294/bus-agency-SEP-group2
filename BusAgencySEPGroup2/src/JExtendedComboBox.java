import javax.swing.JComboBox;

import java.awt.event.*;

public class JExtendedComboBox<T> extends JComboBox<T>
{
	private static final long serialVersionUID = 1L;
	private T defaultDisplayedItem = null;

	public JExtendedComboBox(T[] arg0)
	{
		super(arg0);
		addFocusListener(new FocusListen());
		addMouseListener(new MouseClickAction());
		addMouseWheelListener(new MouseScroll());
	}

	public JExtendedComboBox()
	{
		super();
		addFocusListener(new FocusListen());
		addMouseListener(new MouseClickAction());
		addMouseWheelListener(new MouseScroll());
	}

	public T getDefaultDisplayedItem()
	{
		return defaultDisplayedItem;
	}

	public void setDefaultDisplayedItem(T input)
	{
		defaultDisplayedItem = input;
		if (getItemAt(0) == null || !getItemAt(0).equals(defaultDisplayedItem))
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
		T item = getSelectedItem();
		if(item == null)
			return true;
		return getSelectedItem().equals(getDefaultDisplayedItem());
	}

	public void reset()
	{
		if (defaultDisplayedItem != null)
		{
			if (getItemAt(0) == null || !getItemAt(0).equals(defaultDisplayedItem))
				insertItemAt(defaultDisplayedItem, 0);

			getModel().setSelectedItem(defaultDisplayedItem);
		}
	}
	public void setItems(T[] items)
	{
		removeAllItems();
		for(T item : items)
			addItem(item);
	}
	public boolean isEmpty()
	{
		return this.getItemCount() <= 0;
	}
	
	@Override
	public void setPopupVisible(boolean input)
	{
		if(input)
			removeDefaultFromList();
		super.setPopupVisible(input);
	}
	
	private void removeDefaultFromList()
	{
		if (getItemAt(0) != null && getItemAt(0).equals(defaultDisplayedItem))
		{
			removeItemAt(0);
		}
		if (getItemCount() != 0)
		{
			setSelectedIndex(0);
		}
	}
	private class MouseScroll implements MouseWheelListener
	{

		@Override
		public void mouseWheelMoved(MouseWheelEvent e)
		{
			if(isDefaultItemSelected())
				removeDefaultFromList();
			else if((JExtendedComboBox.this.getSelectedIndex() > 0 && e.getPreciseWheelRotation() < 0) || (JExtendedComboBox.this.getSelectedIndex() < JExtendedComboBox.this.getItemCount() - 1 && e.getPreciseWheelRotation() > 0))
				JExtendedComboBox.this.setSelectedIndex(JExtendedComboBox.this.getSelectedIndex() + (int)e.getPreciseWheelRotation());
		}	
	}
	private class MouseClickAction implements MouseListener
	{
		
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if (e.getButton() == MouseEvent.BUTTON3 && !JExtendedComboBox.this.isPopupVisible())
			{
				JExtendedComboBox.this.reset();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{}

		@Override
		public void mouseExited(MouseEvent e)
		{}

		@Override
		public void mousePressed(MouseEvent e)
		{}

		@Override
		public void mouseReleased(MouseEvent e)
		{}
	}
	private class FocusListen implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent arg0)
		{
			if (defaultDisplayedItem != null)
			{
				removeDefaultFromList();
			}
		}

		@Override
		public void focusLost(FocusEvent arg0)
		{
			if (defaultDisplayedItem != null && getItemCount() == 0)
			{
				insertItemAt(defaultDisplayedItem, 0);
				setSelectedIndex(0);
			}
		}

	}
}
