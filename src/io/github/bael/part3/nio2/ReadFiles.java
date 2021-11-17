package io.github.bael.part3.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadFiles {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/io/github/bael/part3/nio2/ReadFiles.java");
        // простой способ
        List<String> strings = Files.readAllLines(path);
        strings.stream().forEach(System.out::println);

        // не перегружаем память (java 11)
        Path path2Logs = Paths.get("/home/dk/work/jetalon_logs/jetalabor/jetalabor.log");
        try (var s = Files.lines(path2Logs)) {
            s.filter(f -> f.contains("Произошла ошибка"))
                    .forEach(System.out::println);
        }

    }
}
