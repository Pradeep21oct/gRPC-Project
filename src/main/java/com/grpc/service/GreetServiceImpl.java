package com.grpc.service;

import com.proto.greet.*;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {
    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {

        Greeting greeting=request.getGreeting();
        String firstname=greeting.getFirstName();
       // Create response
         String result="Hello "+firstname;
         GreetResponse response=GreetResponse.newBuilder()
                                .setResult(result).build();

             // send response
         responseObserver.onNext(response);

         responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<GreetEveryoneRequest> greetEveryone(StreamObserver<GreetEveryoneResponse> responseObserver) {
        StreamObserver<GreetEveryoneRequest> greq=new StreamObserver<GreetEveryoneRequest>() {
            @Override
            public void onNext(GreetEveryoneRequest value) {
                String res="Hello "+value.getGreeting();
                GreetEveryoneResponse rs= GreetEveryoneResponse.newBuilder()
                        .setResult(res)
                        .build();
                responseObserver.onNext(rs);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
             responseObserver.onCompleted();
            }
        };

        return greq;
    }
}
