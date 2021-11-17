package io.github.bael.part3.nio2;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PathsExamples1 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        // путь может быть указан не только с помощью директории но и uri:
//        Path path6 = Paths.get(new URI("ftp://user:12345@192.168.5.5"));
//        FileSystem fileSystem = FileSystems.getFileSystem(
//                new URI("http://www.selikoff.net"));
//        Path path233 = fileSystem.getPath("duck.txt");



//        URI uri = URI.create("http://lib.ru/ADAMS/rhit1.txt");
//        Path pathToUri = Paths.get(uri);
//        // но ничего не получится: Exception in thread "main" java.nio.file.FileSystemNotFoundException: Provider "http" not installed
//        List<String> content = Files.readAllLines(pathToUri, StandardCharsets.UTF_8);
//        System.out.println(content.stream().limit(20).collect(Collectors.joining("%n")));

        Path path = Paths.get("./");
        Path resolvedPath = path.resolve(".gitignore");
        Path path2 = Path.of(".gitignore");

        System.out.println(path2);
        System.out.println(resolvedPath);

        System.out.println("Files.exists(resolvedPath): " + Files.exists(resolvedPath));
        System.out.println("Files.exists(path2): " + Files.exists(path2));
        System.out.println("path2 == resolvedPath: " + (path2 == resolvedPath));
        System.out.println("path.equals(path2): " + resolvedPath.equals(path2));
        System.out.println("Files.isSameFile: " + Files.isSameFile(resolvedPath, path2));

        path2 = path2.normalize();
        resolvedPath = resolvedPath.normalize();
        System.out.println("Attempt #2. normalize:");
        System.out.println(path2);
        System.out.println(resolvedPath);

        System.out.println("Files.exists(resolvedPath): " + Files.exists(resolvedPath));
        System.out.println("Files.exists(path2): " + Files.exists(path2));
        System.out.println("path2 == resolvedPath: " + (path2 == resolvedPath));
        System.out.println("Files.isSameFile: " + Files.isSameFile(resolvedPath, path2));
        System.out.println("path.equals(path2): " + resolvedPath.equals(path2));

        // выводы. по == не сравниваем. по path.equals только после нормализации,
        // Files.isSameFile покажет что к файлам ведет один и тот же путь.
        // (равенство файлов не проверяется при этом, т.е. если одинаковые файлы будут лежать в разных местах, метод вернет FALSE)
        // давайте проверим с копией .gitignore
        Path gitignoreCopyPath =  Paths.get("./src", "io", "github", "bael/part3/nio2/.gitignore");
//        System.out.println(gitignoreCopyPath);
//        System.out.println(Files.exists(gitignoreCopyPath));
        System.out.println("Is same file? : " +  Files.isSameFile(Path.of(".gitignore"), gitignoreCopyPath));





    }


}
