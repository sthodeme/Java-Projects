
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//import javax.swing.*;
public class MainWindow extends JFrame{

    private static final long serialVersionUID = 1L;

    //constructor
    public void MainWindow() {
        
        //JFrame
        this.setTitle("Grusstabelle");
        this.setSize(20, 300);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        //JLabel
        JLabel label01 = new JLabel("Name:");
        label01.setEnabled(false);
        label01.setBounds(10, 10, 200, 15);
        this.add(label01);

        //JTesxtField

        JTextField name = new JTextField();
        name.setBounds(10, 35, 200, 15);
        name.setEditable(true);
        this.add(name);

        //JButton
        JButton nameButton = new JButton("Gruss");
        nameButton.setBounds(10, 60, 25, 15);
        this.add(nameButton);

        //ActionListener
        //String copyName;
        ActionListener takeName = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == nameButton) {
                //String copyName = name.getText();
                System.out.println("Hallo " + name);
                JOptionPane grussPane = new JOptionPane();
                grussPane.showMessageDialog(null, "Hallo " + name, "Gruss", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        };

        

        this.setVisible(true);
    }
}
