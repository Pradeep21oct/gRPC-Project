package com.grpc.service;

import com.proto.calculator.*;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {
    @Override
    public void sum(CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {

        int num1=request.getFirstNumber();
        int num2=request.getSecondNumber();

        int result=num1+num2;
        CalculatorResponse response=CalculatorResponse.newBuilder()
                .setResult(result)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void primeNumberDecompostion(PrimeNumberDecompostionRequest request, StreamObserver<PrimeNumberDecompostionResponse> responseObserver) {
        Integer num=request.getNumber();
        int divisor=2;
        while (num>1){
            if(num%divisor==0){
                num=num/divisor;
                responseObserver.onNext(PrimeNumberDecompostionResponse
                        .newBuilder()
                        .setPrimeFactor(divisor)
                        .build());
            }else {
                divisor++;
            }
        }
        responseObserver.onCompleted();
    }
}
