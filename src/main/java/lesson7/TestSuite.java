package lesson7;

public class TestSuite {

    @BeforeSuite
    public static void init() {
        System.out.println("BeforeSuite");
    }

    @AfterSuite
    public static void teardown() {
        System.out.println("AfterSuite");
    }

    @Test(priority = 3)
    public static void test1() {
        System.out.println("# test 1");
    }

    @Test(priority = 2)
    public static void test2() {
        System.out.println("# test 2");
    }

    @Test
    public static void test3() {
        System.out.println("# test 3");
    }
}
