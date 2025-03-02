package com.sreeram;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
public class CalcWindow extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 7689665368854844115L;

    JLabel display;
    public JLabel msTextDisplay;
    public JLabel msValueDisplay;
    public JLabel stackDisplay;
    public JLabel operatorDisplay;
    public boolean changeSign = false;

    public CalcWindow() {

        // Direkte Individualisierung zu Beginn.
        this.setTitle("Calcmaster V1");
        this.setLayout(null);
        this.setSize(350,560);
        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        // Bereitstellung von CalcEngine.
        var calcEngine = new CalcEngine(this);

        // Bereitstellen der Zeichensätze
        Font displayFont = new Font("Arial", Font.PLAIN, 20);
        Font digitsFont = new Font("Arial", Font.PLAIN, 10);

        //Font digitsFont = new Font("Ink Free", Font.PLAIN,10);

        // Konstanten für die Buttonpositionierung
        final int buttonSize = 50;
        final int buttonOffset = 60;
        final int xStart = 25;
        final int yStart = 115;

        //MS text display
        this.msTextDisplay = new JLabel("");
        this.msTextDisplay.setBounds(xStart,20,30,40);
        this.msTextDisplay.setForeground(Color.WHITE);
        this.msTextDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(this.msTextDisplay);
        //MS value display
        this.msValueDisplay = new JLabel("");
        this.msValueDisplay.setBounds(60,20,100,40);
        this.msValueDisplay.setForeground(Color.WHITE);
        this.msValueDisplay.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(this.msValueDisplay);

        // Stack Display
        this.stackDisplay = new JLabel("");
        this.stackDisplay.setBounds(135,20,160,40);
        this.stackDisplay.setForeground(Color.WHITE);
        this.stackDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(this.stackDisplay);

        //operatorDisplay
        this.operatorDisplay = new JLabel("");
        this.operatorDisplay.setBounds(300,20,50,40);
        this.operatorDisplay.setForeground(Color.WHITE);
        this.operatorDisplay.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(this.operatorDisplay);




        //Input Area
        this.display = new JLabel("0");
        this.display.setBounds(xStart,65,300,40);
        this.display.setFont(displayFont);
        this.display.setHorizontalAlignment(SwingConstants.RIGHT);
        this.display.setBackground(Color.GRAY);
        this.display.setForeground(Color.GREEN);
        this.display.setOpaque(true);
        this.add(this.display);

        // Tastenfeld aufbauen
        // Get Listener
        //ActionListener dl = calcEngine.getDigitListener();
        //ActionListener ol = calcEngine.getOperatorListener();
        ActionListener all = calcEngine.getAllListener();



        // ButtonLayout
        String[][] tasten = {
                {"M+","M-","MS","MR","MC"},
                {"X²","1/X","%","√X","/"},
                {"1","2","3","4","*"},
                {"5","6","7", "8","-"},
                {"9","0",".","+/-","+"},
                {"AC","Del","=","",""}
        };

        // building Button Layout
        for (int row = 0, x = xStart; row < 5; row++, x += buttonOffset) {
            for (int col = 0,y = yStart; col < 6; col++, y += buttonOffset) {
                String taste = tasten[col][row];
                JButton actButton = new JButton(taste);
                actButton.setActionCommand(taste);
                actButton.setBounds(x,y,buttonSize,buttonSize);
                actButton.setFont(digitsFont);
                //adjustButtonFontSize(actButton);

                actButton.addActionListener(all);
                /* 
                if ("0123456789.".contains(taste)) { // || taste.matches(".*\\+/-.*")) {
                    actButton.addActionListener(dl);//calcEngine.getDigitListener());
                    //actButton.setBackground(Color.GRAY);

                } else {
                    actButton.addActionListener(ol);//calcEngine.getOperatorListener());
                    //actButton.setBackground(Color.ORANGE);

                }
                */

                //actButton.setOpaque(true);
                this.add(actButton);

            }

        }

        // Alles anschalten.
        this.setVisible(true);
    }


    /**
     * Liefert Referenz auf das Displayfeld zurück, als einer Schnittstellen für die
     * Kommunikation zwischen CalcWindow und CalcEngine.
     * @return JTextField Referenz
     */
    public JLabel getDisplayField() {
        return this.display;
    }
}
