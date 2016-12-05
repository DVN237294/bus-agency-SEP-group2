import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class TravelAgencyGUI extends JFrame
{
	private TravelAgency agency;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem exitItem;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel[] tabPanels;
    private String[] tabNames = new String[] { "Tours", "Bus & Chauffeur", "Busses", "Chauffeurs" };

	public TravelAgencyGUI()
	{
		super("VIA Bus");
		agency = new TravelAgency();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(960, 540);
		
		//Menu bar
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		exitItem = fileMenu.add("Exit");
		setJMenuBar(menuBar);
		exitItem.addActionListener(new CloseListener());
		
		//Tabbed panes
		add(tabbedPane);
		tabPanels = new JPanel[tabNames.length];
		for(int i = 0; i < tabNames.length; i++)
		{
            tabbedPane.add(tabNames[i], new JLabel(tabNames[i]));
            tabPanels[i] = new JPanel();
            tabbedPane.setComponentAt(i, tabPanels[i]);
		}
		
		
		//Tour pane
		JPanel toursNorthPanel = new JPanel();
		toursNorthPanel.setBorder(BorderFactory.createTitledBorder("Search"));
		toursNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		String[] destinations = agency.getAllDestinations();
		String[] temp = new String[destinations.length + 1];
		temp[0] = "Destination";
		for(int i = 0, index = 1; i < destinations.length;i++)
			temp[index++] = destinations[i];
			
		JComboBox destiBox = new JComboBox<String>(temp);
		destiBox.addFocusListener(new DropDownFocusListener());
		toursNorthPanel.add(destiBox);	
		tabPanels[0].setLayout(new BorderLayout());
		tabPanels[0].add(toursNorthPanel, BorderLayout.NORTH);

	}

	class CloseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			TravelAgencyGUI.this.dispose();
		}
	}
	
	class DropDownFocusListener implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent arg0)
		{
			if(arg0.getSource() instanceof JComboBox)
			{
				JComboBox cBox = (JComboBox)arg0.getSource();
				
				/*if(cBox.getItemAt(0) instanceof String && cBox.getItemAt(0))
				{
					
				}*/
			}
		
		}

		@Override
		public void focusLost(FocusEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
		
	}

}
