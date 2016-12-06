import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

public class BussesPane extends JPanel
{
   private static final long serialVersionUID = 1L;
   private TravelAgency agency;
	public BussesPane(TravelAgency agency)
	{
		super();
		this.agency = agency;
		
		JPanel bussesNorthPanel = new JPanel();
		JPanel bussesEastPanel = new JPanel();
	      bussesNorthPanel.setBorder(BorderFactory.createTitledBorder("Search"));
	      bussesNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	      bussesEastPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	      JExtendedComboBox<Bus> busBox = new JExtendedComboBox<>(agency.getAllBusses());
	      JExtendedComboBox<String> destiBox = new JExtendedComboBox<String>(agency.getAllDestinations());
	      destiBox.setPrototypeDisplayValue("Destination");
	      destiBox.setDefaultDisplayedValue("Destination");
	      JExtendedComboBox<Chauffeur> chauffeurBox = new JExtendedComboBox<Chauffeur>(agency.getAllChauffeurs());
	      JList<Bus> busList = new JList<Bus>(agency.getAllBusses());
	      JButton searchButton = new JButton("Search");
	      
	      searchButton.addActionListener(new ActionListener()
         {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
               bussesEastPanel.add(busList);  
            }
         });
	      
	      bussesNorthPanel.add(destiBox);   
	      bussesNorthPanel.add(busBox);
	      bussesNorthPanel.add(chauffeurBox);
	      bussesNorthPanel.add(searchButton);
	      this.setLayout(new BorderLayout());
	      this.add(bussesNorthPanel, BorderLayout.NORTH);
	      this.add(bussesEastPanel, BorderLayout.EAST);
	}
}
