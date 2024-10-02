package com.example.petstoremonolithique.GrpcController;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
public class GrpcServerConfig {

    @Autowired
    private OrderControllerGrpc orderServiceGrpc;

    private Server server;

    @Bean
    public Server grpcServer() {
        return ServerBuilder.forPort(4001)
                .addService(orderServiceGrpc)
                .addService(ProtoReflectionService.newInstance())  // Enable reflection service
                .build();
    }

    @PostConstruct
    public void start() throws IOException {
        server = grpcServer();
        server.start();
        System.out.println("gRPC server started, listening on " + server.getPort());
    }

    @PreDestroy
    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
}
