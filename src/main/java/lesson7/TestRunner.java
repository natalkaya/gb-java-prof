package lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Создать класс, который может выполнять «тесты». В качестве тестов выступают классы с наборами методов с аннотациями @Test.
 * Для этого у него должен быть статический метод start(), которому в качестве параметра передается или объект
 * типа Class, или имя класса. Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite,
 * если такой имеется. Далее запущены методы с аннотациями @Test, а по завершении всех тестов –
 * метод с аннотацией @AfterSuite. К каждому тесту необходимо добавить приоритеты (int числа от 1 до 10),
 * в соответствии с которыми будет выбираться порядок их выполнения. Если приоритет одинаковый, то порядок не имеет
 * значения. Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре, иначе
 * необходимо бросить RuntimeException при запуске «тестирования».
 */

public class TestRunner {

    private static Method[] getAnnotatedMethods(Method[] methods, Class annotationClazz) {
        return (Method[]) Arrays.stream(methods)
                .filter(m -> m.isAnnotationPresent(annotationClazz))
                .toArray(Method[]::new);
    }

    private static void invoke(Method[] methods) {
        Arrays.stream(methods).forEach(m -> {
            try {
                m.invoke(m);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    private static void invoke(Method[] methods, boolean prioritised) {
        if (prioritised) {
            Arrays.stream(methods)
                    .sorted(Comparator.comparingInt(m -> m.getAnnotation(Test.class).priority()))
                    .forEach(m -> {
                        try {
                            m.invoke(null);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
        } else {
            invoke(methods);
        }
    }

    public static void start(Class clazz, boolean prioritised) {
        Method[] methods = clazz.getDeclaredMethods();

        Method[] beforeSuiteMethods = getAnnotatedMethods(methods, BeforeSuite.class);
        Method[] afterSuiteMethods = getAnnotatedMethods(methods, AfterSuite.class);
        Method[] testMethods = getAnnotatedMethods(methods, Test.class);

        if (Arrays.stream(beforeSuiteMethods).count() > 1)
            throw new RuntimeException("BeforeSuite should be used once in class");
        if (Arrays.stream(afterSuiteMethods).count() > 1)
            throw new RuntimeException("AfterSuite should be used once in class");


        invoke(beforeSuiteMethods);
        invoke(testMethods, prioritised);
        invoke(afterSuiteMethods);
    }

    public static void main(String[] args) {
        TestRunner.start(TestSuite.class, true);
    }

}
