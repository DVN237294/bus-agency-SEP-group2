package travelAgencyGUI;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.event.*;

import javax.swing.*;

import travelAgencyModel.TravelAgency;

public class TravelAgencyGUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private TravelAgency agency;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem exitItem;
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel[] tabPanels;
	private String[] tabNames = new String[] { "Reservations", "Busses", "Chauffeurs" };

	public TravelAgencyGUI()
	{
		super("VIA Bus");
		agency = new TravelAgency();

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
		tabPanels[0] = new ReservationsPane(agency);
		tabbedPane.setComponentAt(0, tabPanels[0]);

		// BusAndChauffeur pane
		/*
		 * tabPanels[1] = new BusAndChauffeurPanel(agency);
		 * tabbedPane.setComponentAt(1, tabPanels[1]);
		 */

		// Busses pane
		tabPanels[1] = new BussesPane(agency);
		tabbedPane.setComponentAt(1, tabPanels[1]);

		tabPanels[2] = new ChauffeursPane(agency);
		tabbedPane.setComponentAt(2, tabPanels[2]);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(960, 540);

		this.setFocusTraversalPolicy(new FocusTraversalPolicy() {
			@Override
			public Component getLastComponent(Container aContainer)
			{
				return null;
			}

			@Override
			public Component getFirstComponent(Container aContainer)
			{
				return null;
			}

			@Override
			public Component getDefaultComponent(Container aContainer)
			{
				return null;
			}

			@Override
			public Component getComponentBefore(Container aContainer, Component aComponent)
			{
				return null;
			}

			@Override
			public Component getComponentAfter(Container aContainer, Component aComponent)
			{
				return null;
			}
		});

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
