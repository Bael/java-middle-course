package io.github.bael.part3.nio;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class FileLockExample {
    public static void main(String[] args) throws IOException {
        Path tempDir = Files.createTempDirectory("fileLockDir");
        System.out.println(tempDir);
        Path tempFile = Files.createTempFile(tempDir, "locked", ".lock");
        System.out.println(tempFile);
        Files.writeString(tempFile, "Secret text", StandardCharsets.UTF_8);

        FileChannel channel = FileChannel.open(tempFile, StandardOpenOption.WRITE);
        try (FileLock lock = channel.lock()) {
            System.out.printf("Файл заблокирован: " + lock.isShared() + " " + lock.isValid() + tempFile + "%n" +
                    "lslocks | grep " + tempFile + "%n для проверки. Нажмите Enter для разблокировки.");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
            // так не выйдет

            // Exception in thread "main" java.nio.channels.OverlappingFileLockException
            try (FileLock lock2 = channel.lock()) {
                System.out.println("Enter one more time! " + lock2.isShared());
                sc.nextLine();
            }
        }

        // вывод - блокировки скорее всего учитываются только самой java, либо дело в пользователе?

    }
}
