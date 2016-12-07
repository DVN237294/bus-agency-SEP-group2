import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddBusFrame extends JFrame
{
   private static final long serialVersionUID = 1L;
   private TravelAgency agency;
   private TextField makeArea;
   private TextField modelArea;
   private TextField licensePlateArea;
   private TextField maxCapacityArea;
   private JButton saveButton;
   private JLabel makeJLabel;
   private JLabel modelJLabel;
   private JLabel licensePlateJLabel;
   private JLabel maxCapacityJLabel;

   JPanel addBusNorthPanel = new JPanel();

   public AddBusFrame(TravelAgency agency)
   {
      super("Add Bus");
      this.agency = agency;
      addBusNorthPanel.setBorder(BorderFactory
            .createTitledBorder("Input Information"));
      addBusNorthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      addBusNorthPanel.setPreferredSize(new Dimension(400, 100));
      makeArea = new TextField(10);
      modelArea = new TextField(10);
      licensePlateArea = new TextField(10);
      maxCapacityArea = new TextField(10);
      makeJLabel = new JLabel("Make");
      modelJLabel = new JLabel("Model");
      licensePlateJLabel = new JLabel("License Plate");
      maxCapacityJLabel = new JLabel("Max Capacity");
      saveButton = new JButton("Save");
      addBusNorthPanel.add(makeJLabel);
      addBusNorthPanel.add(makeArea);
      addBusNorthPanel.add(modelJLabel);
      addBusNorthPanel.add(modelArea);
      addBusNorthPanel.add(licensePlateJLabel);
      addBusNorthPanel.add(licensePlateArea);
      addBusNorthPanel.add(maxCapacityJLabel);
      addBusNorthPanel.add(maxCapacityArea);
      addBusNorthPanel.add(saveButton);
      saveButton.addActionListener(new SaveListener());

      setSize(960, 540);
      setVisible(true);

      this.setLayout(new BorderLayout());
      this.add(addBusNorthPanel, BorderLayout.NORTH);
   }

   private class SaveListener implements ActionListener
   {

      @Override
      public void actionPerformed(ActionEvent e)
      {
            agency.addBus(makeArea.getText(), modelArea.getText(),
                  licensePlateArea.getText(), Integer.parseInt(maxCapacityArea.getText()));
      }

   }

}
