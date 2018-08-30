/**
 * Java level 1, lesson 6
 *
 * 1. Created classes Dog and Cat with inheritance from the class Animal.
 *
 * 2. Animals can perform actions: run, swim, jump over an obstacle.
 * As a parameter, each method is given a value indicating either
 * the length of the obstacle (for running and swimming)
 * or the height (for jumping).
 *
 * 3. Each animal has restrictions on actions
 * (running: cat 200 m, dog 500 m, jump: cat 2 m, dog 0.5 m,
 * swimming: cat can not swim, dog 10 m).
 *
 * 4. When an animal attempts to perform one of these actions,
 * it must report the result to the console.
 *
 * 5. Animals have the scatter in the constraints.
 * That is, one dog has a run limit of 400 m, another 600 m.
 *
 * @author Chaykin Ivan
 * @version 30.08.2018
 */
public class Lesson6 {

    public static void main(String[] args) {
// Create array animals with class object Cat and Dog, and interface IAnimals
        IAnimal[] animals = {new Cat(200, 2),
                new Dog(400, 0.5, 10),
                new Dog(600, 0.5, 10)};

//  Cycle that print in console actions (run, jump and swim)
        for (IAnimal animal: animals) {
            System.out.println("Class: " + animal.getClass().getName() + " {");
            System.out.println("run: " + animal.run(500));
            System.out.println("jump: " + animal.jump(1));
            System.out.println("swim: " + animal.swim(3));
            System.out.println("}");
        }
    }
}

interface IAnimal {
    boolean run(int length);
    boolean jump(double height);
    boolean swim(int length);
}

abstract class Animal implements IAnimal {

private int run_max;
private double jump_max;
private int swim_max;

    Animal(int run_max, double jump_max, int swim_max) {
        this.run_max = run_max;
        this.jump_max = jump_max;
        this.swim_max = swim_max;
    }
    public boolean run(int length) {
        return run_max >= length;
    }
    public boolean jump(double height) {
        return jump_max >= height;
    }
    public boolean swim(int length) {
        return swim_max >= length;
    }
}

class Cat extends Animal {
    Cat(int run_max, double jump_max) {
        super(run_max, jump_max, 0);
    }
//  Override swim method, because cat can't swim
    @Override
    public boolean swim(int length) {
        return false;
    }
}

class Dog extends Animal {
    Dog(int run_max, double jump_max, int swim_max) {
        super(run_max, jump_max, swim_max);
    }
}