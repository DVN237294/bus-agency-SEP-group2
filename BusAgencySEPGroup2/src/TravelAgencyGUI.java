import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class TravelAgencyGUI extends JFrame
{
   private static final long serialVersionUID = 1L;
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
		
		//Dummy data, to test layout:
		agency.addDestination("xyz");
		agency.addDestination("abcd");
		agency.addChauffeur("Hans", "Hansen", 666);
		agency.addChauffeur("Afonso", "Taborda", 999);
		agency.addBus("Volkswagen", "jdsskdj", "12323AB", 50);
		agency.addBus("Volvo", "jdj", "1234", 20);
		
		
		
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
		}
		
		
		//Tour pane
		JPanel toursNorthPanel = new JPanel();
		toursNorthPanel.setBorder(BorderFactory.createTitledBorder("Search"));
		toursNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		String[] destinations = agency.getAllDestinations();
		JExtendedComboBox<String> destiBox = new JExtendedComboBox<String>(destinations);
		destiBox.setPrototypeDisplayValue("Destination");
		destiBox.setDefaultDisplayedValue("Destination");
		JExtendedComboBox<Chauffeur> chauffeurBox = new JExtendedComboBox<Chauffeur>(agency.getAllChauffeurs());
		
		toursNorthPanel.add(destiBox);	
		toursNorthPanel.add(chauffeurBox);
		tabPanels[0].setLayout(new BorderLayout());
		tabPanels[0].add(toursNorthPanel, BorderLayout.NORTH);
		tabbedPane.setComponentAt(0, tabPanels[0]);
		
		//Busses pane
		JPanel bussesNorthPanel = new JPanel();
      bussesNorthPanel.setBorder(BorderFactory.createTitledBorder("Reserve"));
      bussesNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      JExtendedComboBox<Bus> busBox = new JExtendedComboBox<>(agency.getAllBusses());
      destinations = agency.getAllDestinations();
      destiBox = new JExtendedComboBox<String>(destinations);
      destiBox.setPrototypeDisplayValue("Destination");
      destiBox.setDefaultDisplayedValue("Destination");
      chauffeurBox = new JExtendedComboBox<Chauffeur>(agency.getAllChauffeurs());
      
      bussesNorthPanel.add(destiBox);   
      bussesNorthPanel.add(busBox);
      bussesNorthPanel.add(chauffeurBox);
      tabPanels[2].setLayout(new BorderLayout());
      tabPanels[2].add(bussesNorthPanel, BorderLayout.NORTH);
		
		tabPanels[2] = new BussesPane(agency);
		tabbedPane.setComponentAt(2, tabPanels[2]);
		repaint();
		
		//i dont get it

	}

	class CloseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			TravelAgencyGUI.this.dispose();
		}
	}
}
