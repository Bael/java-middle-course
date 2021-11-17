package io.github.bael.part3.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;
import java.time.Instant;
import java.time.temporal.TemporalField;
import java.util.stream.Stream;

public class FindExample {
    public static void main(String[] args) throws IOException {
        Path start = Path.of("./out/");
        Stream<Path> pathStream = Files.find(start, 10,
                (path, basicFileAttributes) -> !basicFileAttributes.isDirectory()
                            && path.getFileName().toString().endsWith(".class")
                            && basicFileAttributes.lastModifiedTime()
                                .compareTo(FileTime.from(Instant.now()
                                .minusMillis(20000000L))) > 0
        );
        pathStream.forEach(FindExample::printPath);
    }

    private static void printPath(Path path)  {
        PosixFileAttributeView view = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        try {
            PosixFileAttributes attributes = view.readAttributes();
            System.out.printf("Разрешения: %s, Путь: %s %n, Размер: %d bytes %n", attributes.permissions(), path, attributes.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
