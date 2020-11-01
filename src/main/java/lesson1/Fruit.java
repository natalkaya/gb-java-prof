package lesson1;


import java.util.ArrayList;
import java.util.Random;

abstract class Fruit<T> {
    protected final Random random = new Random();
    private double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public abstract ArrayList<T> putSeveral();
}

class Apple extends Fruit<Apple> {
    public Apple() {
        this.setWeight(1.0f);
    }

    public ArrayList<Apple> putSeveral() {
        ArrayList<Apple> apples = new ArrayList<>();
        for (int i = 0; i < random.nextInt(5); i++) {
            apples.add(new Apple());
        }
        System.out.println("Added " + apples.size() + " apple(s)");
        return apples;
    }
}

class Orange extends Fruit<Orange> {
    public Orange() {
        this.setWeight(1.5f);
    }

    public ArrayList<Orange> putSeveral() {
        ArrayList<Orange> oranges = new ArrayList<>();
        for (int i = 0; i < random.nextInt(5); i++) {
            oranges.add(new Orange());
        }
        System.out.println("Added " + oranges.size() + " orange(s)");
        return oranges;
    }
}

class Box<F extends Fruit<F>> {
    private ArrayList<F> fruits = new ArrayList<>();
    private double weight;

    public ArrayList<F> getFruits() {
        return fruits;
    }

    public double getWeight() {
        final int fruitsCount = fruits.size();
        if (fruitsCount != 0) {
            double fruitWeight = fruits.get(0).getWeight();
            return fruitsCount + fruitWeight;
        }
        return weight;
    }

    @Override
    public String toString() {
        return "Box weight: " + this.getWeight();
    }

    public Box(ArrayList<F> fruits) {
        this.fruits = fruits;
    }

    public Box() {
        this.weight = 0f;
    }

    public <E extends Fruit<E>> boolean compare(Box<E> anotherBox) {
        return this.getWeight() == anotherBox.getWeight();
    }

    public Box<F> intersperse(Box<F> anotherBox) {
        this.fruits.addAll(anotherBox.fruits);
        return this;
    }
}

