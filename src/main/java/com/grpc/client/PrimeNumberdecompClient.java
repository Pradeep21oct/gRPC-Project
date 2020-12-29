package com.grpc.client;

import com.proto.calculator.CalculatorRequest;
import com.proto.calculator.CalculatorResponse;
import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.PrimeNumberDecompostionRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class PrimeNumberdecompClient {
    public static void main(String[] args) {
        System.out.println("Hello I am gRPC client");
        ManagedChannel channel= ManagedChannelBuilder
                            .forAddress("localhost",50053)
                            .usePlaintext()
                            .build();
        System.out.println("Creating stub");
        int number=120;
        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub=CalculatorServiceGrpc
                .newBlockingStub(channel);
       stub.primeNumberDecompostion(PrimeNumberDecompostionRequest.newBuilder()
                          .setNumber(number)
                          .build()).forEachRemaining(primeNumberDecompostionResponse -> {
                        System.out.println(primeNumberDecompostionResponse.getPrimeFactor());
       });


        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
