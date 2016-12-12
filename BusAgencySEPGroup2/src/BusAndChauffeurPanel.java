import java.awt.BorderLayout;

import javax.swing.JFrame;


public class BusAndChauffeurPanel extends JFrame
{
   private static final long serialVersionUID = 1L;
   private TravelAgency agency;

   public BusAndChauffeurPanel(TravelAgency agency)
   {
      super();
      this.agency = agency;
      
      setSize(960, 540);
      setVisible(true);

      this.setLayout(new BorderLayout());
   }
}
