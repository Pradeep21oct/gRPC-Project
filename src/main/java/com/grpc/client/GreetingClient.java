package com.grpc.client;

import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("Hello I am gRPC client");
        ManagedChannel channel= ManagedChannelBuilder
                            .forAddress("localhost",50051)
                            .usePlaintext()
                            .build();
        System.out.println("Creating stub");
        //DummyServiceGrpc.DummyServiceBlockingStub synstub=DummyServiceGrpc
         //                                       .newBlockingStub(channel);
        GreetServiceGrpc.GreetServiceBlockingStub stub=GreetServiceGrpc
                .newBlockingStub(channel);
        Greeting greeting=Greeting.newBuilder()
                          .setFirstName("Pradeep")
                          .setLastName("Singh")
                          .build();

        GreetRequest greetRequest=GreetRequest.newBuilder()
                                  .setGreeting(greeting)
                                  .build();
        GreetResponse greetResponse=stub.greet(greetRequest);
        System.out.println("Greet Response " +greetResponse.getResult());
        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
