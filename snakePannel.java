
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
    int delay = 100, bodyParts = 6;
    Random random;
    Timer timer;
    int eggX, eggY;
    int score = 0;

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
if(running)
{
        for (int i = 0; i < bodyParts; i++) {
            dg.setColor(Color.RED);
            dg.fillOval(snakeX[i], snakeY[i], unitSize, unitSize);
        }

        dg.setColor(Color.green);
        dg.fillOval(eggX, eggY, unitSize, unitSize);
    }
    else {
        gameOver(dg);
    }
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
        eggX = random.nextInt((screenWidth / unitSize)) * unitSize;
        eggY = random.nextInt((screenHeight / unitSize)) * unitSize;
    }

    public void checkEgg() {

        if ((snakeX[0] == eggX) && (snakeY[0] == eggY)) {
            score++;
            bodyParts++;
            newEgg();
            System.out.println(score);
        }
    }

    public void checkCollesion(){
        if (snakeX[0]<0||snakeY[0]<0||snakeX[0]>screenWidth||snakeY[0]>screenHeight) {
       running=false;
        }

        for(int i = bodyParts;i>0;i--){
            if((snakeX[0]==snakeX[i])&&(snakeY[0]==snakeY[i])){

                running=false;
                break;
            }
        }
        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        // Display Game Over text
        g.setColor(Color.RED);
        g.setFont(new java.awt.Font("Ink Free", java.awt.Font.BOLD, 75));
        java.awt.FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (screenWidth - metrics1.stringWidth("Game Over")) / 2, screenHeight / 2);
        g.drawString("Score : "+score, (screenWidth - metrics1.stringWidth("Score : "+score )) / 2, (screenHeight / 2)+100);

        // Optionally, display the score
        /*
        g.setColor(Color.WHITE);
        g.setFont(new java.awt.Font("Ink Free", java.awt.Font.BOLD, 40));
        java.awt.FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: " + (bodyParts - 6), (SCREEN_WIDTH - metrics2.stringWidth("Score: " + (bodyParts - 6))) / 2, g.getFont().getSize());
        */
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (running) {
            move();
            checkEgg();
            checkCollesion();

        }
    
        repaint();

    }

    public class myKeyAdepter implements KeyListener {

        @Override
        public void keyPressed(KeyEvent ke) {

            switch (ke.getKeyCode()) {
                case KeyEvent.VK_LEFT: {

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
