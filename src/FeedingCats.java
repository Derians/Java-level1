/**
 * Java level 1, lesson 7 Additional assignment
 *
 * 1. Created a graphic mode for feeding cats
 *
 * @author Chaykin Ivan
 * @version 02.09.2018
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeedingCats {

    public static void main(String[] args) {
        new FeedingWindow();
    }
}

class FeedingWindow extends JFrame {

//  Create array Cat's
    GraphicCat[] cats = {new GraphicCat("Murzik", 55), new GraphicCat("Barsik", 10),
            new GraphicCat("Felix", 15), new GraphicCat("Tom", 20),
            new GraphicCat("Garfield", 60), new GraphicCat("Oscar", 20),
            new GraphicCat("Tiger", 45), new GraphicCat("Simba", 20)};

//  Create plate and fill 100 food
    GraphicPlate plate = new GraphicPlate(100);
    CatsPanel catsPanel;

    FeedingWindow() {
        setTitle("Graphic mode of feeding cats");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 200);
        setResizable(false);

        catsPanel = new CatsPanel();
        catsPanel.setLayout(null);

        JButton feedingCatsButton = new JButton("Feeding cats");
        feedingCatsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (GraphicCat cat : cats) {
                    cat.eat(plate);
                }
                catsPanel.repaint();
            }
        });
        feedingCatsButton.setBounds(120,100,110,30);
        feedingCatsButton.setBackground(Color.lightGray);

        JButton fillPlateButton = new JButton("Fill plate");
        fillPlateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plate.fillPlate();
                catsPanel.repaint();
            }
        });
        fillPlateButton.setBounds(240,100,110,30);
        fillPlateButton.setBackground(Color.lightGray);

        catsPanel.add(fillPlateButton);
        catsPanel.add(feedingCatsButton);

        add(catsPanel);
        setVisible(true);
    }

    class CatsPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            plate.paint(g);

            for (int i = 0; i < cats.length; i++) {
                cats[i].paint(g, (i * 70) + 10, cats[i].name);
            }
        }
    }
}

class GraphicCat {
    protected String name;
    protected int appetite;
    protected boolean satiety;

    GraphicCat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    void eat(GraphicPlate plate) {
        if (!satiety) {
            satiety = plate.dicreaseFood(appetite);
        }
    }

    void paint(Graphics g, int x, String name) {
        if (satiety) {
            g.setColor(Color.green);
            g.fillRect(x, 20, 60, 60);
        }
        g.setColor(Color.black);
        g.drawRect(x, 20, 60, 60);
        g.drawString(name, x + 5, 15);
    }
}

class GraphicPlate {
    final int MAX_FOOD = 100;
    protected int plate;

    GraphicPlate(int food) {
        this.plate = food;
    }

    boolean dicreaseFood(int food) {
        if (plate >= food) {
            plate -= food;
            return true;
        }
        return false;
    }

    void fillPlate() {
        plate = MAX_FOOD;
    }

    void paint(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(10, 100, plate, 30);
        g.setColor(Color.black);
        g.drawRect(10, 100, 100, 30);
        g.drawString("Plate with food", 20, 120);
    }

    @Override
    public String toString() {
        return "Food: " + plate;
    }
}
