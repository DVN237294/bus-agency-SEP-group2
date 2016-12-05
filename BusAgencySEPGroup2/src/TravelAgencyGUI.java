import java.awt.MenuBar;

import javax.swing.*;
public class TravelAgencyGUI extends JFrame
{
	MenuBar menuBar;
	public TravelAgencyGUI()
	{
		super("VIA Bus");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	    setSize(400,  400);
	    menuBar = new MenuBar();
	    setMenuBar(menuBar);
	}
}
