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
		addMouseListener(new TestAction());
	}

	private class TestAction implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if (e.getButton() == MouseEvent.BUTTON3 && !JExtendedComboBox.this.isPopupVisible())
			{
				JExtendedComboBox.this.reset();
			}
			if (e.getButton() == MouseEvent.BUTTON1 && JExtendedComboBox.this.hasFocus() && defaultDisplayedItem != null)
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
			System.out.println(e.getButton());
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}
	}

	public JExtendedComboBox()
	{
		super();
		addFocusListener(new FocusListen());
		addMouseListener(new TestAction());
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

	private class FocusListen implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent arg0)
		{
			if (defaultDisplayedItem != null)
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
