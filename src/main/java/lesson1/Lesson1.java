package lesson1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
 * 2. Написать метод, который преобразует массив в ArrayList;
 * 3. Большая задача:
 * Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
 * Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
 * поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
 * Для хранения фруктов внутри коробки можно использовать ArrayList;
 *      Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
 *      (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
 *
 *      Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
 *      которую подадут в compare в качестве параметра, true – если она равны по весу, false – в противном случае
 *      (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
 *
 *      Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку фруктов:
 *      нельзя яблоки высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов не остается,
 *      а в другую перекидываются объекты, которые были в этой коробке;
 *      Не забываем про метод добавления фрукта в коробку.
 */

public class Lesson1 {

    public static <T> T[] changePlaces(T[] arr, int oneElementIndex, int anotherElementIndex) {
        T temp;
        temp = arr[oneElementIndex];
        arr[oneElementIndex] = arr[anotherElementIndex];
        arr[anotherElementIndex] = temp;
        return arr;
    }

    public static <T> ArrayList<T> toArrayList(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    public static void main(String[] args) {

        String[] strings = {"1", "2", "3"};
        Integer[] nums = {1, 2, 3};

        System.out.println("\nTask 1:");
        System.out.println("Changed Strings: " + Arrays.toString(changePlaces(strings, 0, 1)));
        System.out.println("Changed Integers: " + Arrays.toString(changePlaces(nums, 1, 2)));

        System.out.println("\nTask 2:");
        System.out.println("To ArrayList<String>: " + toArrayList(strings));
        System.out.println("To ArrayList<Integer>: " + toArrayList(nums));

        System.out.println("\nTask 3:");
        final Apple apple = new Apple();
        final Orange orange = new Orange();
        Box<Apple> boxWithApple = new Box<>(apple.putSeveral());
        Box<Orange> boxWithOrange = new Box<>(orange.putSeveral());
        Box<Apple> anotherBoxWithApple = new Box<>(apple.putSeveral());
        System.out.println("Compare a box with apples and a box with oranges: " + boxWithApple.compare(boxWithOrange));
        System.out.println("Add fruits to the box with apple: " + boxWithApple.intersperse(anotherBoxWithApple));
        System.out.println("Compare a box with apples and a box with oranges: " + boxWithApple.compare(boxWithOrange));
    }

}
