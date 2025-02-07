import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 1487225919900733810L;
    private Font mwFont = new Font("Times New Roman", Font.BOLD, 20);

    //constructor
    public MainWindow() {
        // set title
        this.setTitle ("SwingLive");
        // set size
        this.setSize(300, 400);
        // change layout (from default 'borderLayout)
        this.setLayout(null);
        // closing the window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);



        JTextField input01 = new JTextField();
        input01.setBounds(5, 5, 100, 20);
        //input01.setEditable(false);
        this.add(input01);

        JTextField output01 = new JTextField();
        output01.setBounds(5, 30, 100, 20);
        output01.setEditable(false);
        this.add(output01);

        ActionListener copyAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transferText = input01.getText();
                output01.setText(transferText);
            }

        };

        JButton button03 = new JButton("Copy");
        button03.setBounds(5, 55, 100, 20);
        button03.addActionListener(copyAction);
        this.add(button03);




        ActionListener emptyAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transferText = input01.getText();
                output01.setText("");
            }

        };

        JButton button04 = new JButton("clear");
        button04.setBounds(5, 80, 100, 20);
        button04.addActionListener(emptyAction);
        this.add(button04);

        JButton button05 = new JButton("Change to lowercase text");
        JButton button06 = new JButton("Change to UPPERCASE text");
        ActionListener multipleActions = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button05) {
                    output01.setText(input01.getText().toLowerCase());
                }
                if (e.getSource() == button06) {
                    output01.setText(input01.getText().toUpperCase());
                }
            }

        };

        button05.setBounds(5,115,200, 20);
        button05.addActionListener(multipleActions);
        this.add(button05);

        button06.setBounds(5,140,200, 20);
        button06.addActionListener(multipleActions);
        this.add(button06);

        //from Sascha

        ActionListener multipleActions02 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                switch (command) {
                    case "RED":
                        input01.setForeground(Color.RED);
                        break;
                    case "GREEN":
                        input01.setForeground(Color.GREEN);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,
                                "Ein Kommando konnte nicht zugeordnet werden",
                                "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };


        JButton button07 = new JButton("change to red");

        button07.setBounds(5,190,130,20);
        button07.setActionCommand("RED");
        button07.addActionListener(multipleActions02);
        this.add(button07);

        JButton button08 = new JButton("change to green");

        button08.setBounds(5,210,130,20);
        button08.addActionListener(multipleActions02);
        button08.setActionCommand("GREEN");
        this.add(button08);


        //JList
        String[] names = {"Anna", "Horst", "Bernd", "Peter", "SreeRam"};
        JList<String> list01 = new JList<>(names);
        list01.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list01.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedValue = list01.getSelectedValue();
                    input01.setText(selectedValue);
                }
            }

        });

        JScrollPane scrollPane01 = new JScrollPane(list01);
        scrollPane01.setBounds(5, 240,100,75);
        this.add(scrollPane01);


        // make the window visible (which is already created, with storage allocated)
        this.setVisible(true);
    }
}