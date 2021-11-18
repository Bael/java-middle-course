package io.github.bael.part3.nio;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class WatchExample {
    public static void main(String[] args) throws IOException, InterruptedException {
//        if (Objects.isNull(args) || args.length == 0) {
//            System.out.println("Укажите параметр - какую папку отслеживать.");
//            return;
//        }

//        Path pathToWatch = Paths.get(args[0]);
        Path pathToWatch = Path.of("/tmp");
        watch(5, pathToWatch);

    }

    private static void watch(long watchTimeSeconds, Path pathToWatch) throws IOException, InterruptedException {
        if (!Files.exists(pathToWatch)) {
            System.out.println("Указанный путь не существует!");
            return;
        }
        WatchService watchService = FileSystems.getDefault().newWatchService();
        LocalDateTime finishWatchTime = LocalDateTime.now().plusSeconds(watchTimeSeconds);
        try(watchService) {
            pathToWatch.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
            while(LocalDateTime.now().isBefore(finishWatchTime)) {
                WatchKey watchKey = watchService.poll(1, TimeUnit.SECONDS);
                if (Objects.isNull(watchKey)) {
                    System.out.println("Изменений не обнаружено!");
                } else {
                    for (WatchEvent<?> event : watchKey.pollEvents()) {
                        System.out.println(eventToString(event));
                    }
                    watchKey.reset();
                }
            }
        }
    }
    private static String eventToString(WatchEvent<?> event) {
        return event.kind()  +": " + event.context().toString();
    }
}
