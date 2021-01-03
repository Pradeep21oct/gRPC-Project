package com.grpc.client;

import com.proto.greet.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GreetingEveryoneClient {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello I am gRPC client");
        ManagedChannel channel= ManagedChannelBuilder
                            .forAddress("localhost",50051)
                            .usePlaintext()
                            .build();
        System.out.println("Creating stub");
        CountDownLatch latch=new CountDownLatch(1);
        //DummyServiceGrpc.DummyServiceBlockingStub synstub=DummyServiceGrpc
        GreetServiceGrpc.GreetServiceStub asyClient=
                GreetServiceGrpc.newStub(channel);
        StreamObserver <GreetEveryoneRequest> request=
                asyClient.greetEveryone(new StreamObserver<GreetEveryoneResponse>() {
                    @Override
                    public void onNext(GreetEveryoneResponse value) {
                        System.out.println("Server Response "+value.getResult());
                    }

                    @Override
                    public void onError(Throwable t) {
                    latch.countDown();
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("server respose complted");
                        latch.countDown();
                    }
                });
        Arrays.asList("Praddep","Ram","shyam").forEach(
                name->request.onNext(GreetEveryoneRequest.newBuilder()
                            .setGreeting(Greeting.newBuilder()
                                    .setFirstName(name)).build())

        );
        request.onCompleted();
        latch.await(3, TimeUnit.SECONDS);
        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
