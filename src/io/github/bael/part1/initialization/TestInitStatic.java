package io.github.bael.part1.initialization;

public class TestInitStatic {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Step 1");
        Class<InitStatic> initStaticTestClass = InitStatic.class;
        // работа с метаданными класса не приводит к инициализации его полей
        initStaticTestClass.getFields();
        initStaticTestClass.toString();


        System.out.println("Step 2");
        // инициализация произойдет только при обращении к полям класса
        System.out.println("i = " + InitStatic.i);
        System.out.println("g = " + InitStatic.g);
        System.out.println("j = " + InitStatic.j);

        System.out.println("Step 3");
        InitStatic test = new InitStatic();
        System.out.println(test);


    }
}
