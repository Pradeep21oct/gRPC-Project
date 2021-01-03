package com.grpc.client;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.ComputeAverageRequest;
import com.proto.calculator.ComputeAverageResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ConputerAverageClient {
    public static void main(String[] args) {
        ManagedChannel channel= ManagedChannelBuilder.forAddress("localhost",50053)
                               .usePlaintext()
                               .build();
        ConputerAverageClient conputerAverageClient=new ConputerAverageClient();
        conputerAverageClient.doClientStremcall(channel);
        channel.shutdown();
    }

    private void doClientStremcall(ManagedChannel channel){
        CalculatorServiceGrpc.CalculatorServiceStub asyncall=CalculatorServiceGrpc.newStub(channel);
        CountDownLatch latch=new CountDownLatch(1);
       StreamObserver<ComputeAverageRequest>  req= asyncall.computeAverage(new StreamObserver<ComputeAverageResponse>() {
            @Override
            public void onNext(ComputeAverageResponse value) {
                System.out.println("Recived response from service "+value.getAverage());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("Server stop sending reponse " );
                  latch.countDown();
            }
        });


        req.onNext(ComputeAverageRequest.newBuilder().setNun(1).build());
        req.onNext(ComputeAverageRequest.newBuilder().setNun(2).build());
        req.onNext(ComputeAverageRequest.newBuilder().setNun(3).build());
        req.onNext(ComputeAverageRequest.newBuilder().setNun(4).build());
        req.onCompleted();
        try {
            latch.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
