import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AddChauffeurPanel extends JFrame
{
   private static final long serialVersionUID = 1L;
   private TravelAgency agency;
   
   private JPanel addChauffeurNorthPanel = new JPanel();

   public AddChauffeurPanel(TravelAgency agency)
   {
      super("Add Chauffeur");
      this.agency = agency;
      addChauffeurNorthPanel.setBorder(BorderFactory
            .createTitledBorder("Input Bus Information"));
      addChauffeurNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      setSize(960, 540);
      setVisible(true);
      
      this.setLayout(new BorderLayout());
      this.add(addChauffeurNorthPanel, BorderLayout.NORTH);
   }
}
