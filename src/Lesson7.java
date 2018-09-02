/**
 * Java level 1, lesson 7
 *
 * 1. Has expanded the capabilities of classes of cat and plate
 *
 * 2. Did it so that in a plate with food could not get a negative
 * amount of food.
 *
 * 3. In the class the cat added a satiety field, if the cat
 * is hungry then satiety = false (Default = false)
 *
 * 4. If the cat's appetite is more than the rest of the
 * food in the dish, the cat does not touch the food
 *
 * 5. Created an array of cats and a plate of food,
 * in a cycle of feeding cats and output information
 * about their satiety in the console
 *
 * 6. Added a method to the plate class that could
 * be used to add food to a plate
 *
 * @author Chaykin Ivan
 * @version 02.09.2018
 */

public class Lesson7 {

    public static void main(String[] args) {

//      Create array Cat's
        Cat[] cats = {new Cat("Murzik", 10), new Cat("Barsik", 10),
                new Cat("Felix", 15), new Cat("Tom", 20)};

//      Create plate and fill 40 food
        Plate plate = new Plate(40);
        System.out.println(plate);

//      The cycle in which we feed cats
        for (Cat cat: cats) {
            if (!cat.satiety) {
                cat.eat(plate);
                System.out.println(cat.name + " satiety = " + cat.satiety);
                System.out.println(plate);
            }
        }

//     Fill 20 food in plate
        plate.fillPlate(20);
        System.out.println(plate);

//      Repeat the cycle in which we feed cats
        for (Cat cat: cats) {
            if (!cat.satiety) {
                cat.eat(plate);
                System.out.println(cat.name + " satiety = " + cat.satiety);
                System.out.println(plate);
            }
        }
    }
}

class Cat {
    protected String name;
    protected int appetite;
    protected boolean satiety;

    Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    void eat(Plate plate) {
        satiety = plate.dicreaseFood(appetite);
    }
}

class Plate {
    private int food;

    Plate(int food) {
        this.food = food;
    }

    boolean dicreaseFood(int food) {
        if(this.food >= food) {
            this.food -= food;
            return true;
        }
        return false;
    }

    void fillPlate(int food) {
        this.food += food;
    }

    @Override
    public String toString() {
        return "Food: " + food;
    }
}
