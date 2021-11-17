package io.github.bael.part3.nio2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileOperations {
    public static void main(String[] args) throws IOException {

        Path tempDir = Files.createTempDirectory("testDir");
        System.out.println(tempDir);
        Path tempFile = Files.createTempFile(tempDir, "prefix", ".log");
        System.out.println(tempFile);
        Path secret_text = Files.writeString(tempFile, "Secret text", StandardCharsets.UTF_8);
        System.out.println(secret_text);

        Path tempDir2 = Files.createTempDirectory("newTestDir");
        Path movedFile = Files.move(tempFile, tempDir2.resolve(tempFile.getFileName()), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Файл переехал " + movedFile);
        System.out.printf("Файл существует на старом месте? %s %n", Files.exists(tempFile));

        System.out.printf("Файл существует на новом месте? %s %n", Files.exists(movedFile));
        Files.delete(movedFile);
        System.out.printf("После удаления файл существует на новом месте? %s %n", Files.exists(movedFile));


    }
}
