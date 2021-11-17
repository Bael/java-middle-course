package io.github.bael.part3.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TempFiles {
    public static void main(String[] args) throws IOException {
        Path tempFile = Files.createTempFile("prefix", ".log");
        System.out.println(tempFile);

        Path tempDir = Files.createTempDirectory("testDir");
        System.out.println(tempDir);

    }
}
