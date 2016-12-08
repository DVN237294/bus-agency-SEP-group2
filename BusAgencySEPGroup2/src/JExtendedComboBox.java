import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.metal.MetalComboBoxUI;

import com.sun.javafx.scene.traversal.Direction;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;

public class JExtendedComboBox<T> extends JComboBox<T>
{
	private class ExtendedComboBoxUI extends MetalComboBoxUI
	{
		private JButton superArrowButton;
		private JButton otherButton;
		private boolean usingOtherButton = false;

		public ExtendedComboBoxUI()
		{
			super();
			
			this.superArrowButton = super.arrowButton;
			this.otherButton = otherButton;
		}

		public void toggleButton()
		{
			if (usingOtherButton)
				super.arrowButton = superArrowButton;
			else
				super.arrowButton = otherButton;
		}
		@Override 
		protected JButton createArrowButton() {
			JButton button = new BasicArrowButton(BasicArrowButton.SOUTH,
					                                     javax.swing.UIManager.getColor("ComboBox.buttonBackground"),
					                                     javax.swing.UIManager.getColor("ComboBox.buttonShadow"),
					                                     javax.swing.UIManager.getColor("ComboBox.buttonDarkShadow"),
					                                     javax.swing.UIManager.getColor("ComboBox.buttonHighlight"));
					         button.setName("ComboBox.arrowButton");
					         return button;
	    }
	}

	private static final long serialVersionUID = 1L;
	private T defaultDisplayedItem = null;
	private ExtendedComboBoxUI ui;

	public JExtendedComboBox(T[] arg0)
	{
		super(arg0);
		//this.ui = new ExtendedComboBoxUI();
		//this.setUI(this.ui);
		addFocusListener(new FocusListen());
		for(Component comp : super.getComponents())
		{
			if(comp instanceof JButton)
			{
				/*BasicArrowButton theButton = (BasicArrowButton) comp;
				theButton.addActionListener(new TestAction());
				theButton.setBackground(new Color(1f, 0, 0, 0.5f));*/
			}
		}
		//super.add(ttButton);
		//ui.toggleButton();
	}
	private class TestAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			System.out.println("yeah");
		}
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
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
			if (defaultDisplayedItem != null && getItemCount() == 0)
			{
				insertItemAt(defaultDisplayedItem, 0);
				setSelectedIndex(0);
			}
		}

	}
}
