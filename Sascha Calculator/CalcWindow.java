import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class CalcWindow extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 7689665368854844115L;

    JLabel display;
    public JLabel stackDisplay;
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
        Font displayFont = new Font("Ink Free", Font.BOLD,30);
        Font digitsFont = new Font("Ink Free", Font.BOLD,18);

        // Konstanten für die Buttonpositionierung
        final int buttonSize = 50;
        final int buttonOffset = 60;
        final int xStart = 25;
        final int yStart = 115;

        // Stack Display
        this.stackDisplay = new JLabel("xx");
        this.stackDisplay.setBounds(xStart,20,300,40);
        this.stackDisplay.setForeground(Color.WHITE);
        this.stackDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(this.stackDisplay);

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
        // Listener holen
        ActionListener dl = calcEngine.getDigitListener();
        ActionListener ol = calcEngine.getOperatorListener();



        // Tastenfeld
        String[][] tasten = {
                {"M+","M-","MS","MR","MC"},
                {"X2","1/X","%","XR","/"},
                {"1","2","3","4","*"},
                {"5","6","7", "8","-"},
                {"9","0",".","+/-","+"},
                {"C","Del","=","#","#"}
        };

        // Tastenmatrix aufbauen
        for (int row = 0, x = xStart; row < 5; row++, x += buttonOffset) {
            for (int col = 0,y = yStart; col < 6; col++, y += buttonOffset) {
                String taste = tasten[col][row];
                JButton actButton = new JButton(taste);
                actButton.setActionCommand(taste);
                actButton.setBounds(x,y,buttonSize,buttonSize);
                actButton.setFont(digitsFont);

                if ("0123456789.".contains(taste)) {
                    actButton.addActionListener(dl);//calcEngine.getDigitListener());
                    //actButton.setBackground(Color.GRAY);

                } else {
                    actButton.addActionListener(ol);//calcEngine.getOperatorListener());
                    //actButton.setBackground(Color.ORANGE);

                }
                actButton.setOpaque(true);
                this.add(actButton);

            }
            /*
            if("+/-" == e.getActio{
                changeSign = true;
            }
            */

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
