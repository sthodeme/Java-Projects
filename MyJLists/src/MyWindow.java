import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MyWindow extends JFrame{
    private static final long serialVersionUID = 1487225919900733810L;
    //constructor
    public void MyWindow(){
        //set title
        this.setTitle("My List Collection");
        this.setSize(200, 400);
        this.setLayout(null);
        this.setResizable(false);

        //closing window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    /*
        JLabel jLabel01 = new JLabel("jLable01: test text");
        jLabel01.setSize(150,20);
        this.add(jLabel01);
    */
        JTextField input01 = new JTextField();
        input01.setBounds(5, 5, 100, 20);
        //input01.setEditable(false);
        this.add(input01);


        //make everything visible
        this.setVisible(true);
    }
}
