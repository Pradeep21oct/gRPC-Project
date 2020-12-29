package com.github.greeting.client;

import com.proto.calculator.CalculatorRequest;
import com.proto.calculator.CalculatorResponse;
import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {
    public static void main(String[] args) {
        System.out.println("Hello I am gRPC client");
        ManagedChannel channel= ManagedChannelBuilder
                            .forAddress("localhost",50052)
                            .usePlaintext()
                            .build();
        System.out.println("Creating stub");
        //DummyServiceGrpc.DummyServiceBlockingStub synstub=DummyServiceGrpc
         //                                       .newBlockingStub(channel);
        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub=CalculatorServiceGrpc
                .newBlockingStub(channel);
        CalculatorRequest calc=CalculatorRequest.newBuilder()
                          .setFirstNumber(12)
                          .setSecondNumber(30)
                          .build();


        CalculatorResponse calcResponse=stub.sum(calc);
        System.out.println("Calculator Response " +calcResponse.getResult());
        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
