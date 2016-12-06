import java.awt.event.*;

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

		// Dummy data, to test layout:
		agency.addDestination("xyz");
		agency.addDestination("abcd");
		agency.addChauffeur("Hans", "Hansen", 666);
		agency.addChauffeur("Afonso", "Taborda", 999);
		agency.addBus("Volkswagen", "jdsskdj", "12323AB", 50);
		agency.addBus("Volvo", "jdj", "1234", 20);

		// Menu bar
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		exitItem = fileMenu.add("Exit");
		setJMenuBar(menuBar);
		exitItem.addActionListener(new CloseListener());

		// Tabbed panes
		add(tabbedPane);
		tabPanels = new JPanel[tabNames.length];
		for (int i = 0; i < tabNames.length; i++)
		{
			tabbedPane.add(tabNames[i], new JLabel(tabNames[i]));
		}

		// Tour pane
		tabPanels[0] = new ToursPane(agency);
		tabbedPane.setComponentAt(0, tabPanels[0]);

		// Busses pane
		tabPanels[2] = new BussesPane(agency);
		tabbedPane.setComponentAt(2, tabPanels[2]);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(960, 540);
		setVisible(true);

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
