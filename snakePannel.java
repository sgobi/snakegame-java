
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class snakePannel extends JPanel implements ActionListener {

    int screenHeight = 600, screenWidth = 600, unitSize = 25, gameUnits = (int) ((screenHeight * screenWidth) / (unitSize * unitSize));
    int delay = 125, bodyParts = 6;
    Random random;
    Timer timer;
    int eggX, eggY;

    int[] snakeX = new int[gameUnits];
    int[] snakeY = new int[gameUnits];
    char direction = 'R';
    boolean running = false;
    snakePannel() {
        random = new Random();
        timer = new Timer(delay, this);
        this.setPreferredSize(new Dimension(screenWidth, screenWidth));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new myKeyAdepter());
        startGame();
    }

    public void startGame() {
        newEgg();
        running = true;
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics dg) {

        for (int i = 0; i < bodyParts; i++) {
            dg.setColor(Color.RED);
            dg.fillOval(snakeX[i], snakeY[i], unitSize, unitSize);
        }

        dg.setColor(Color.green);
        dg.fillOval(eggX, eggY, unitSize, unitSize);

    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];

        }

        switch (direction) {
            case 'R':
                snakeX[0] = snakeX[0] + unitSize;
                break;
            case 'L':
                snakeX[0] = snakeX[0] - unitSize;
                break;
            case 'U':
                snakeY[0] = snakeY[0] - unitSize;
                break;
            case 'D':
                snakeY[0] = snakeY[0] + unitSize;
                break;

        }

    }

    public void newEgg() {
        eggX = random.nextInt(gameUnits);
        eggY = random.nextInt(gameUnits);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
if (running) {
    move();

}    
repaint();

}


public class myKeyAdepter implements KeyListener {




    @Override
    public void keyPressed(KeyEvent ke) {
       

        switch (ke.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            {
                
                if (direction != 'R') {
                    direction = 'L';
                }
            }
                break;

            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D';
                }
                break;
        }
    }

        @Override
        public void keyTyped(KeyEvent ke) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void keyReleased(KeyEvent ke) {
           // throw new UnsupportedOperationException("Not supported yet.");
        }


}

}
