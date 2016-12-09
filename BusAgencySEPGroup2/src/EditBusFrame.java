import javax.swing.JFrame;


public class EditBusFrame extends JFrame
{
   private static final long serialVersionUID = 1L;
   private TravelAgency agency;
   
   public EditBusFrame(TravelAgency agency) {
      this.agency = agency;
      setSize(960, 540);
      setVisible(true);
   }
}
