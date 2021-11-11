package io.github.bael.part1.initialization;

/**
 * статические поля инициализируются в том порядке в котором они указаны в классе.
 * при попытке инициализировать одно поле другим, которое еще не проинициализировано будет ошибка компиляции,
 * и компилятор нас предостережет от ошибок.
 * но если это обернуть в метод то мы пожнем то что посеяли. (неициализированное значение)
 *
 */
public class InitStatic {
//    private static int i = j; // won't compile
    public static int i = getJ();

    private static int getJ() {
        System.out.println("getJ is called!");
        return j;
    }

    static {
        System.out.println("Called static initialization block for j");
        j = 3;
    }




    public static int g = 1;
    static {
        System.out.println("Called static initialization block for g");
        g = 3;
        j = 4;
    }

    @Override
    public String toString() {
        return "InitStaticTest{" +
                String.format("i = %s, j = %s, g = %s", i, j, g) +
                "}";
    }

    public static int j = 2;


}
