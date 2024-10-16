
import javax.swing.JFrame;

public class snakeFrame extends JFrame {

    snakeFrame() {
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new snakePannel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
