package com.example.petstoremonolithique.Entities;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: PetStore.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class PetStoreGrpc {

  private PetStoreGrpc() {}

  public static final java.lang.String SERVICE_NAME = "PetStore";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrders> getGetOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getOrders",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrders.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrders> getGetOrdersMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrders> getGetOrdersMethod;
    if ((getGetOrdersMethod = PetStoreGrpc.getGetOrdersMethod) == null) {
      synchronized (PetStoreGrpc.class) {
        if ((getGetOrdersMethod = PetStoreGrpc.getGetOrdersMethod) == null) {
          PetStoreGrpc.getGetOrdersMethod = getGetOrdersMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrders>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrders.getDefaultInstance()))
              .setSchemaDescriptor(new PetStoreMethodDescriptorSupplier("getOrders"))
              .build();
        }
      }
    }
    return getGetOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest,
      com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> getGetOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getOrder",
      requestType = com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest.class,
      responseType = com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest,
      com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> getGetOrderMethod() {
    io.grpc.MethodDescriptor<com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest, com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> getGetOrderMethod;
    if ((getGetOrderMethod = PetStoreGrpc.getGetOrderMethod) == null) {
      synchronized (PetStoreGrpc.class) {
        if ((getGetOrderMethod = PetStoreGrpc.getGetOrderMethod) == null) {
          PetStoreGrpc.getGetOrderMethod = getGetOrderMethod =
              io.grpc.MethodDescriptor.<com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest, com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder.getDefaultInstance()))
              .setSchemaDescriptor(new PetStoreMethodDescriptorSupplier("getOrder"))
              .build();
        }
      }
    }
    return getGetOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder,
      com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> getAddOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addOrder",
      requestType = com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder.class,
      responseType = com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder,
      com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> getAddOrderMethod() {
    io.grpc.MethodDescriptor<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder, com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> getAddOrderMethod;
    if ((getAddOrderMethod = PetStoreGrpc.getAddOrderMethod) == null) {
      synchronized (PetStoreGrpc.class) {
        if ((getAddOrderMethod = PetStoreGrpc.getAddOrderMethod) == null) {
          PetStoreGrpc.getAddOrderMethod = getAddOrderMethod =
              io.grpc.MethodDescriptor.<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder, com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder.getDefaultInstance()))
              .setSchemaDescriptor(new PetStoreMethodDescriptorSupplier("addOrder"))
              .build();
        }
      }
    }
    return getAddOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder,
      com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> getUpdateOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateOrder",
      requestType = com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder.class,
      responseType = com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder,
      com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> getUpdateOrderMethod() {
    io.grpc.MethodDescriptor<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder, com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> getUpdateOrderMethod;
    if ((getUpdateOrderMethod = PetStoreGrpc.getUpdateOrderMethod) == null) {
      synchronized (PetStoreGrpc.class) {
        if ((getUpdateOrderMethod = PetStoreGrpc.getUpdateOrderMethod) == null) {
          PetStoreGrpc.getUpdateOrderMethod = getUpdateOrderMethod =
              io.grpc.MethodDescriptor.<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder, com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updateOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder.getDefaultInstance()))
              .setSchemaDescriptor(new PetStoreMethodDescriptorSupplier("updateOrder"))
              .build();
        }
      }
    }
    return getUpdateOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest,
      com.google.protobuf.Empty> getDeleteOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteOrder",
      requestType = com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest,
      com.google.protobuf.Empty> getDeleteOrderMethod() {
    io.grpc.MethodDescriptor<com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest, com.google.protobuf.Empty> getDeleteOrderMethod;
    if ((getDeleteOrderMethod = PetStoreGrpc.getDeleteOrderMethod) == null) {
      synchronized (PetStoreGrpc.class) {
        if ((getDeleteOrderMethod = PetStoreGrpc.getDeleteOrderMethod) == null) {
          PetStoreGrpc.getDeleteOrderMethod = getDeleteOrderMethod =
              io.grpc.MethodDescriptor.<com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new PetStoreMethodDescriptorSupplier("deleteOrder"))
              .build();
        }
      }
    }
    return getDeleteOrderMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PetStoreStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PetStoreStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PetStoreStub>() {
        @java.lang.Override
        public PetStoreStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PetStoreStub(channel, callOptions);
        }
      };
    return PetStoreStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PetStoreBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PetStoreBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PetStoreBlockingStub>() {
        @java.lang.Override
        public PetStoreBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PetStoreBlockingStub(channel, callOptions);
        }
      };
    return PetStoreBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PetStoreFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PetStoreFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PetStoreFutureStub>() {
        @java.lang.Override
        public PetStoreFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PetStoreFutureStub(channel, callOptions);
        }
      };
    return PetStoreFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getOrders(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrders> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOrdersMethod(), responseObserver);
    }

    /**
     */
    default void getOrder(com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest request,
        io.grpc.stub.StreamObserver<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOrderMethod(), responseObserver);
    }

    /**
     */
    default void addOrder(com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder request,
        io.grpc.stub.StreamObserver<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddOrderMethod(), responseObserver);
    }

    /**
     */
    default void updateOrder(com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder request,
        io.grpc.stub.StreamObserver<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateOrderMethod(), responseObserver);
    }

    /**
     */
    default void deleteOrder(com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteOrderMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service PetStore.
   */
  public static abstract class PetStoreImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return PetStoreGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service PetStore.
   */
  public static final class PetStoreStub
      extends io.grpc.stub.AbstractAsyncStub<PetStoreStub> {
    private PetStoreStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PetStoreStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PetStoreStub(channel, callOptions);
    }

    /**
     */
    public void getOrders(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrders> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOrder(com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest request,
        io.grpc.stub.StreamObserver<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addOrder(com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder request,
        io.grpc.stub.StreamObserver<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateOrder(com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder request,
        io.grpc.stub.StreamObserver<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteOrder(com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteOrderMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service PetStore.
   */
  public static final class PetStoreBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<PetStoreBlockingStub> {
    private PetStoreBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PetStoreBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PetStoreBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrders getOrders(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOrdersMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder getOrder(com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder addOrder(com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder updateOrder(com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty deleteOrder(com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteOrderMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service PetStore.
   */
  public static final class PetStoreFutureStub
      extends io.grpc.stub.AbstractFutureStub<PetStoreFutureStub> {
    private PetStoreFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PetStoreFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PetStoreFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrders> getOrders(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOrdersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> getOrder(
        com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> addOrder(
        com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder> updateOrder(
        com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deleteOrder(
        com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteOrderMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ORDERS = 0;
  private static final int METHODID_GET_ORDER = 1;
  private static final int METHODID_ADD_ORDER = 2;
  private static final int METHODID_UPDATE_ORDER = 3;
  private static final int METHODID_DELETE_ORDER = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ORDERS:
          serviceImpl.getOrders((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrders>) responseObserver);
          break;
        case METHODID_GET_ORDER:
          serviceImpl.getOrder((com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest) request,
              (io.grpc.stub.StreamObserver<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder>) responseObserver);
          break;
        case METHODID_ADD_ORDER:
          serviceImpl.addOrder((com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder) request,
              (io.grpc.stub.StreamObserver<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder>) responseObserver);
          break;
        case METHODID_UPDATE_ORDER:
          serviceImpl.updateOrder((com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder) request,
              (io.grpc.stub.StreamObserver<com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder>) responseObserver);
          break;
        case METHODID_DELETE_ORDER:
          serviceImpl.deleteOrder((com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetOrdersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrders>(
                service, METHODID_GET_ORDERS)))
        .addMethod(
          getGetOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest,
              com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder>(
                service, METHODID_GET_ORDER)))
        .addMethod(
          getAddOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder,
              com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder>(
                service, METHODID_ADD_ORDER)))
        .addMethod(
          getUpdateOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder,
              com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder>(
                service, METHODID_UPDATE_ORDER)))
        .addMethod(
          getDeleteOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest,
              com.google.protobuf.Empty>(
                service, METHODID_DELETE_ORDER)))
        .build();
  }

  private static abstract class PetStoreBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PetStoreBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.petstoremonolithique.Entities.PetStoreGenerated.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PetStore");
    }
  }

  private static final class PetStoreFileDescriptorSupplier
      extends PetStoreBaseDescriptorSupplier {
    PetStoreFileDescriptorSupplier() {}
  }

  private static final class PetStoreMethodDescriptorSupplier
      extends PetStoreBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    PetStoreMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PetStoreGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PetStoreFileDescriptorSupplier())
              .addMethod(getGetOrdersMethod())
              .addMethod(getGetOrderMethod())
              .addMethod(getAddOrderMethod())
              .addMethod(getUpdateOrderMethod())
              .addMethod(getDeleteOrderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
