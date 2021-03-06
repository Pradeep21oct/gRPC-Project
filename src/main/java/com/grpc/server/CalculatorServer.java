package com.grpc.server;

import com.grpc.service.CalculatorServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class CalculatorServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("calculator hello Grpc");
        Server server= ServerBuilder.forPort(50053)
                .addService(new CalculatorServiceImpl())
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
