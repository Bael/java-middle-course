package io.github.bael.part3.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class MappedFileReadExample {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        Path path = Path.of("/home/dk/work/gitlab/jetalon/jetalabor/src/main/resources/dataconfiguration/systemConfig/dictionaries/okved2.xml");
        MessageDigest digest = MessageDigest.getInstance("MD5");
        long start = System.nanoTime();
        System.out.println(digest(path, digest));
        long finish1 = System.nanoTime();
        System.out.println(sha1ByMap(path, digest));
        long finish2 = System.nanoTime();
        System.out.printf("time 1 = %s ms, time 2 = %s ms ", (finish1 - start) / 1000000, (finish2 - finish1) / 1000000);

    }

    private static final int MAP_SIZE = 5242880; // 5 Мб в байтах

    public static int countOcurrences(Path path, String text) throws IOException {
        final byte[] textToFind = text.getBytes(StandardCharsets.UTF_8);
        int count = 0;
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
            int position = 0;
            long length = fileChannel.size();
            while (position < length) {
                long remaining = length - position;
                int bytesToMap = (int) Math.min(MAP_SIZE, remaining);
                MappedByteBuffer mbBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, position, bytesToMap);
                int limit = mbBuffer.limit();
                int lastSpace = -1;
                int firstChar = -1;
                while (mbBuffer.hasRemaining()) {
                    // спагетти-код опущен для краткости
                }

                /**
                 *
                 */
                IntBuffer ib = fileChannel.map(FileChannel.MapMode.READ_ONLY,
                                0, fileChannel.size()).asIntBuffer();

                while(ib.hasRemaining()) {
                    ib.get();

                }
            }
        }
        return count;
    }

    private static String sha1ByMap(Path path, MessageDigest md) throws IOException, NoSuchAlgorithmException {
        md.reset();
        FileInputStream fis = new FileInputStream(path.toFile());
        long size = fis.available();
        FileChannel fileChan = fis.getChannel();
        ByteBuffer bytes = fileChan.map(FileChannel.MapMode.READ_ONLY, 0L, size);


        md.update(bytes);

        byte[] digest = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));

        }
        return sb.toString();
    }

    private static String digest2(Path entry, MessageDigest messageDigest) {
        messageDigest.reset();
        if (entry.toFile().isDirectory()) {
            throw new RuntimeException("Получение дайджеста MD-5 для некорректного параметра " + entry.toFile().getAbsolutePath());
        }
        messageDigest.digest();

        try (InputStream is = Files.newInputStream(entry);
             DigestInputStream dis = new DigestInputStream(is, messageDigest)) {
            /* Read decorated stream (dis) to EOF as normal... */
            byte[] arr = new byte[1024 * 4];
            while (dis.read(arr) >= 0) {
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        byte[] digest = messageDigest.digest();
        return printHexBinary(digest);
    }


    private static String digest(Path entry, MessageDigest messageDigest) {
        messageDigest.reset();
        if (entry.toFile().isDirectory()) {
            throw new RuntimeException("Получение дайджеста MD-5 для некорректного параметра " + entry.toFile().getAbsolutePath());
        }

        try (InputStream is = Files.newInputStream(entry);
             DigestInputStream dis = new DigestInputStream(is, messageDigest)) {
            /* Read decorated stream (dis) to EOF as normal... */
            byte[] arr = new byte[1024 * 4];
            while (dis.read(arr) >= 0) {
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        byte[] digest = messageDigest.digest();
        return printHexBinary(digest);
    }

    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();
    public static String printHexBinary(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        byte[] var3 = data;
        int var4 = data.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            byte b = var3[var5];
            r.append(hexCode[b >> 4 & 15]);
            r.append(hexCode[b & 15]);
        }

        return r.toString();
    }



}
