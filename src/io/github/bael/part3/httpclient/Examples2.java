package io.github.bael.part3.httpclient;

import java.net.http.HttpClient;
import java.time.Duration;

public class Examples2 {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(300))
                .build();

    }
}
