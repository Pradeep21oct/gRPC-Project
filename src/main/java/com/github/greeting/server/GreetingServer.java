package com.github.greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("hello Grpc");
        Server server= ServerBuilder.forPort(50051)
                .build();
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Recieve Shutdown Request");
            server.shutdown();
            System.out.println("Successfully shutdown");
        }));
        server.awaitTermination();

    }
}
