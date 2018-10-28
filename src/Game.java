/**
 * Java level 1, lesson 8
 *
 *  Created classes Game, GamePanel, GameWindow, Ball, Menu, Player
 *
 *  Class Game creates an instance of the GameWindow class (Starts the game)
 *
 *  Class GameWindow generates and displays a game window
 *
 *  Class GamePanel contains the basic logic of the game and handles
 *  keystrokes from the keyboard
 *
 *  Class Ball contains the logic responsible for the movement of the ball
 *
 *  Class Player contains the logic responsible for the player
 *
 *  Class Menu contains the logic responsible for the player
 *
 *  Class Menu forms and draws menus
 *
 * @author Chaykin Ivan
 * @version 14.09.2018
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game {

    public static void main(String[] args) {
        new GameWindow();
    }

}

class GameWindow extends JFrame {

    private GamePanel gamePanel;
    private final static int WIDTH = 600, HEIGHT = 450;

    GameWindow() {
        setTitle("PinPong");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);

        gamePanel = new GamePanel(this);
        add(gamePanel);

        setVisible(true);
    }

    public GamePanel getPanel() {
        return gamePanel;
    }

}

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private GameWindow gameWindow;
    private Ball ball;
    private Player player1, player2;
    private Menu menu;
    private int score1, score2;
    private boolean GAME_STATUS = false;
    private boolean MENU_STATUS = true;
    private Timer timer;

    GamePanel(GameWindow gameWindow) {
        setBackground(Color.BLACK);
        this.gameWindow = gameWindow;
        ball = new Ball(gameWindow);
        player1 = new Player(gameWindow, KeyEvent.VK_UP, KeyEvent.VK_DOWN,
                gameWindow.getWidth() - 25);
        player2 = new Player(gameWindow, KeyEvent.VK_W, KeyEvent.VK_S, 10);
        menu = new Menu(gameWindow);
        timer = new Timer(10, this);
        if (gameStatus() || menuStatus()) {
            timer.start();
        }
        addKeyListener(this);
        setFocusable(true);
    }

    Player getPlayer(int playerNumber) {
        if (playerNumber == 1)
            return player1;
        else
            return player2;
    }

    public void increaseTheScore(int playerNumber) {
        if (gameStatus()) {
            if (playerNumber == 1)
                score1++;
            else
                score2++;
        }
    }

    private int getScore(int playerNumber) {
        if (playerNumber == 1)
            return score1;
        else
            return score2;
    }

    private void update() {
        if (gameStatus()) {
            ball.update();
            player1.update();
            player2.update();
        }
    }

    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        player1.pressed(e.getKeyCode());
        player2.pressed(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !gameStatus())
            newGame();

        if (e.getKeyCode() == KeyEvent.VK_M && !gameStatus())
            startMenu();
    }

    public void keyReleased(KeyEvent e) {
        player1.released(e.getKeyCode());
        player2.released(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    private void startMenu() {
        setMenuStatus(true);
        score1 = 0;
        score2 = 0;
        timer.start();
    }

    public boolean menuStatus() {
        return MENU_STATUS;
    }

    private void setMenuStatus(boolean menuStatus) {
        MENU_STATUS = menuStatus;
    }

    public boolean gameStatus() {
        return GAME_STATUS;
    }

    private void setGameStatus(boolean gameStatus) {
        GAME_STATUS = gameStatus;
    }

    private void newGame() {
        setGameStatus(true);
        timer.start();
        score1 = 0;
        score2 = 0;

        setMenuStatus(false);
    }

    private void stopGame() {
        setGameStatus(false);
        timer.stop();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial Black", Font.PLAIN, 30));

        if (gameWindow.getPanel().getScore(1) == 10) {
            g.drawString("Player 2 win", (gameWindow.getWidth() / 2)
                    - g.getFont().getSize() - 80, 100);
            stopGame();
            menu.paint(g);
        } else if (gameWindow.getPanel().getScore(2) == 10) {
            g.drawString("Player 1 win", (gameWindow.getWidth() / 2)
                    - g.getFont().getSize() - 80, 100);
            stopGame();
            menu.paint(g);
        } else if (menuStatus()) {
            menu.paint(g);
        } else if (gameStatus()) {
            g.drawString(gameWindow.getPanel().getScore(1) + " : "
                    + gameWindow.getPanel().getScore(2),
                    (gameWindow.getWidth() / 2) - g.getFont().getSize(), 30);
            ball.paint(g);
            player1.paint(g);
            player2.paint(g);

        }
    }
}

class Ball {

    private static final int WIDTH = 30, HEIGHT = 30;
    private GameWindow gameWindow;
    private int x, y, xa = 2, ya = 2;

    Ball(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        x = gameWindow.getWidth() / 2;
        y = gameWindow.getHeight() / 2;
    }

    public void update() {
        x += xa;
        y += ya;
        if (x < 0) {
            gameWindow.getPanel().increaseTheScore(1);
            x = gameWindow.getWidth() / 2;
            xa = -xa;
        } else if (x > gameWindow.getWidth() - WIDTH - 7) {
            gameWindow.getPanel().increaseTheScore(2);
            x = gameWindow.getWidth() / 2;
            xa = -xa;
        } else if (y < 0 || y > gameWindow.getHeight() - HEIGHT - 29)
            ya = -ya;
        checkCollision();
    }

    private void checkCollision() {
        if (gameWindow.getPanel().getPlayer(1).getBounds().intersects(getBounds())
                || gameWindow.getPanel().getPlayer(2).getBounds().intersects(getBounds()))
            xa = -xa;
    }

    private Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}

class Player {
    private GameWindow game;
    private static final int WIDTH = 10, HEIGHT = 60;
    private int up, down;
    private int x;
    private int y, ya;

    Player(GameWindow game, int up, int down, int x) {
        this.game = game;
        this.x = x;
        y = game.getHeight() / 2;
        this.up = up;
        this.down = down;
    }

    public void update() {
        if (y > 0 && y < game.getHeight() - HEIGHT - 29)
            y += ya;
        else if (y == 0)
            y++;
        else if (y == game.getHeight() - HEIGHT - 29)
            y--;
    }

    public void pressed(int keyCode) {
        if (keyCode == up)
            ya = -1;
        else if (keyCode == down)
            ya = 1;
    }

    public void released(int keyCode) {
        if (keyCode == up || keyCode == down)
            ya = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}

class Menu {
    private GameWindow gameWindow;

    Menu(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void paint(Graphics g) {
        if (gameWindow.getPanel().menuStatus()) {
            g.setFont(new Font("Arial Black", Font.PLAIN, 50));
            g.drawString("PinPong", 180, 100);

            g.setFont(new Font("Arial Black", Font.PLAIN, 20));
            g.drawString("Press 'Enter' to play", 180, 250);

            g.setFont(new Font("Arial", Font.PLAIN, 14));
            g.drawString("Control keys: Player 1 = 'W' and 'S', " +
                    "Player 2 = 'UP' and 'DOWDN' arrows", 60, 370);

        } else if (!gameWindow.getPanel().gameStatus()) {
            g.setFont(new Font("Arial Black", Font.PLAIN, 20));
            g.drawString("Press 'Enter' to play", 180, 250);
            g.drawString("Press 'M' to menu", 190, 300);
        }
    }

}