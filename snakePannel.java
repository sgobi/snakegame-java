
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class snakePannel extends JPanel {

    int ScreenHeight = 600, ScreenWidth = 600;

    snakePannel() {
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenWidth));
        this.setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics dg){
        dg.setColor(Color.green);

        dg.drawLine(10, 10, 100, 100);
    }
}
