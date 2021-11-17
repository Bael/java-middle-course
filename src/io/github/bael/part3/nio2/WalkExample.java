package io.github.bael.part3.nio2;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class WalkExample {
    public static void main(String[] args) throws IOException {
        // Don't Use DirectoryStream and FileVisitor - устарели, вместо них используем walk and find
        // в глубину
        Stream<Path> walk = Files.walk(Path.of("."), 20, FileVisitOption.FOLLOW_LINKS);
        walk.forEach(System.out::println);
        // FileVisitOption.FOLLOW_LINKS можем зациклиться.
        // почитать про символьные ссылки https://en.wikipedia.org/wiki/Symbolic_link


    }
}
