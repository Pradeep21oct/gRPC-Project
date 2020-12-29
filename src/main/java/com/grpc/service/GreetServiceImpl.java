package com.grpc.service;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
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
}
