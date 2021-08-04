package com.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class Client {
    public static void main(String[] args) {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext(true)
                .build();

        GreetingServiceGrpc.GreetingServiceStub stub = GreetingServiceGrpc.newStub(channel);
        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest.newBuilder()
                .setName("Ray")
                .build();

       stub.greeting(request, new StreamObserver<GreetingServiceOuterClass.HelloResponse>() {
            @Override
            public void onNext(GreetingServiceOuterClass.HelloResponse helloResponse) {
                System.out.println(helloResponse);
            }
            @Override
            public void onError(Throwable throwable) {

            }
            @Override
            public void onCompleted() {
                channel.shutdownNow();
            }
        });
    }
}
