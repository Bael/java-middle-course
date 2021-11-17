package io.github.bael.part3.nio2;

import java.nio.file.Path;

public class PathManipulation {
    public static void main(String[] args) {
        // если otherPath содержит абсолютный путь, возвращаем otherPath. Иначе объединяем this + other
        // Path.resolve(otherPath)
        Path projectDir = Path.of("./src");
        System.out.printf("Example with relative path %s %n", projectDir.resolve("io"));
        System.out.printf("Example with absolute path %s %n", projectDir.resolve("/home"));

        // Path иммутабелен, следовательно операции над ним создают новый path. Учитываем это!
        System.out.println("projectPath is : " + projectDir);


    }
}
