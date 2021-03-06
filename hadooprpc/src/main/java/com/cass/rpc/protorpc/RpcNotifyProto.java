// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: rpc_notify_service(1).proto

package com.cass.rpc.protorpc;

public final class RpcNotifyProto {
  private RpcNotifyProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface RpcMessageOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // optional string content = 1;
    /**
     * <code>optional string content = 1;</code>
     */
    boolean hasContent();
    /**
     * <code>optional string content = 1;</code>
     */
    String getContent();
    /**
     * <code>optional string content = 1;</code>
     */
    com.google.protobuf.ByteString
        getContentBytes();
  }
  /**
   * Protobuf type {@code cass.utils.RpcMessage}
   */
  public static final class RpcMessage extends
      com.google.protobuf.GeneratedMessage
      implements RpcMessageOrBuilder {
    // Use RpcMessage.newBuilder() to construct.
    private RpcMessage(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private RpcMessage(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final RpcMessage defaultInstance;
    public static RpcMessage getDefaultInstance() {
      return defaultInstance;
    }

    public RpcMessage getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private RpcMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              content_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return RpcNotifyProto.internal_static_cass_utils_RpcMessage_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return RpcNotifyProto.internal_static_cass_utils_RpcMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              RpcMessage.class, Builder.class);
    }

    public static com.google.protobuf.Parser<RpcMessage> PARSER =
        new com.google.protobuf.AbstractParser<RpcMessage>() {
      public RpcMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new RpcMessage(input, extensionRegistry);
      }
    };

    @Override
    public com.google.protobuf.Parser<RpcMessage> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // optional string content = 1;
    public static final int CONTENT_FIELD_NUMBER = 1;
    private Object content_;
    /**
     * <code>optional string content = 1;</code>
     */
    public boolean hasContent() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional string content = 1;</code>
     */
    public String getContent() {
      Object ref = content_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          content_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string content = 1;</code>
     */
    public com.google.protobuf.ByteString
        getContentBytes() {
      Object ref = content_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        content_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      content_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getContentBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getContentBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    protected Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static RpcMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static RpcMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static RpcMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static RpcMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static RpcMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static RpcMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(RpcMessage prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code cass.utils.RpcMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements RpcMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return RpcNotifyProto.internal_static_cass_utils_RpcMessage_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return RpcNotifyProto.internal_static_cass_utils_RpcMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                RpcMessage.class, Builder.class);
      }

      // Construct using com.cass.rpc.protorpc.RpcNotifyProto.RpcMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        content_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return RpcNotifyProto.internal_static_cass_utils_RpcMessage_descriptor;
      }

      public RpcMessage getDefaultInstanceForType() {
        return RpcMessage.getDefaultInstance();
      }

      public RpcMessage build() {
        RpcMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public RpcMessage buildPartial() {
        RpcMessage result = new RpcMessage(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.content_ = content_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof RpcMessage) {
          return mergeFrom((RpcMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(RpcMessage other) {
        if (other == RpcMessage.getDefaultInstance()) return this;
        if (other.hasContent()) {
          bitField0_ |= 0x00000001;
          content_ = other.content_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        RpcMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (RpcMessage) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // optional string content = 1;
      private Object content_ = "";
      /**
       * <code>optional string content = 1;</code>
       */
      public boolean hasContent() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional string content = 1;</code>
       */
      public String getContent() {
        Object ref = content_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          content_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>optional string content = 1;</code>
       */
      public com.google.protobuf.ByteString
          getContentBytes() {
        Object ref = content_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          content_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string content = 1;</code>
       */
      public Builder setContent(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        content_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string content = 1;</code>
       */
      public Builder clearContent() {
        bitField0_ = (bitField0_ & ~0x00000001);
        content_ = getDefaultInstance().getContent();
        onChanged();
        return this;
      }
      /**
       * <code>optional string content = 1;</code>
       */
      public Builder setContentBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        content_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:cass.utils.RpcMessage)
    }

    static {
      defaultInstance = new RpcMessage(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:cass.utils.RpcMessage)
  }

  /**
   * Protobuf service {@code cass.utils.RpcNotify}
   *
   * <pre>
   * the meta service
   * </pre>
   */
  public static abstract class RpcNotify
      implements com.google.protobuf.Service {
    protected RpcNotify() {}

    public interface Interface {
      /**
       * <code>rpc notif(.cass.utils.RpcMessage) returns (.cass.utils.RpcMessage);</code>
       */
      public abstract void notif(
              com.google.protobuf.RpcController controller,
              RpcMessage request,
              com.google.protobuf.RpcCallback<RpcMessage> done);

    }

    public static com.google.protobuf.Service newReflectiveService(
        final Interface impl) {
      return new RpcNotify() {
        @Override
        public  void notif(
            com.google.protobuf.RpcController controller,
            RpcMessage request,
            com.google.protobuf.RpcCallback<RpcMessage> done) {
          impl.notif(controller, request, done);
        }

      };
    }

    public static com.google.protobuf.BlockingService
        newReflectiveBlockingService(final BlockingInterface impl) {
      return new com.google.protobuf.BlockingService() {
        public final com.google.protobuf.Descriptors.ServiceDescriptor
            getDescriptorForType() {
          return getDescriptor();
        }

        public final com.google.protobuf.Message callBlockingMethod(
            com.google.protobuf.Descriptors.MethodDescriptor method,
            com.google.protobuf.RpcController controller,
            com.google.protobuf.Message request)
            throws com.google.protobuf.ServiceException {
          if (method.getService() != getDescriptor()) {
            throw new IllegalArgumentException(
              "Service.callBlockingMethod() given method descriptor for " +
              "wrong service type.");
          }
          switch(method.getIndex()) {
            case 0:
              return impl.notif(controller, (RpcMessage)request);
            default:
              throw new AssertionError("Can't get here.");
          }
        }

        public final com.google.protobuf.Message
            getRequestPrototype(
            com.google.protobuf.Descriptors.MethodDescriptor method) {
          if (method.getService() != getDescriptor()) {
            throw new IllegalArgumentException(
              "Service.getRequestPrototype() given method " +
              "descriptor for wrong service type.");
          }
          switch(method.getIndex()) {
            case 0:
              return RpcMessage.getDefaultInstance();
            default:
              throw new AssertionError("Can't get here.");
          }
        }

        public final com.google.protobuf.Message
            getResponsePrototype(
            com.google.protobuf.Descriptors.MethodDescriptor method) {
          if (method.getService() != getDescriptor()) {
            throw new IllegalArgumentException(
              "Service.getResponsePrototype() given method " +
              "descriptor for wrong service type.");
          }
          switch(method.getIndex()) {
            case 0:
              return RpcMessage.getDefaultInstance();
            default:
              throw new AssertionError("Can't get here.");
          }
        }

      };
    }

    /**
     * <code>rpc notif(.cass.utils.RpcMessage) returns (.cass.utils.RpcMessage);</code>
     */
    public abstract void notif(
        com.google.protobuf.RpcController controller,
        RpcMessage request,
        com.google.protobuf.RpcCallback<RpcMessage> done);

    public static final
        com.google.protobuf.Descriptors.ServiceDescriptor
        getDescriptor() {
      return RpcNotifyProto.getDescriptor().getServices().get(0);
    }
    public final com.google.protobuf.Descriptors.ServiceDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }

    public final void callMethod(
        com.google.protobuf.Descriptors.MethodDescriptor method,
        com.google.protobuf.RpcController controller,
        com.google.protobuf.Message request,
        com.google.protobuf.RpcCallback<
          com.google.protobuf.Message> done) {
      if (method.getService() != getDescriptor()) {
        throw new IllegalArgumentException(
          "Service.callMethod() given method descriptor for wrong " +
          "service type.");
      }
      switch(method.getIndex()) {
        case 0:
          this.notif(controller, (RpcMessage)request,
            com.google.protobuf.RpcUtil.<RpcMessage>specializeCallback(
              done));
          return;
        default:
          throw new AssertionError("Can't get here.");
      }
    }

    public final com.google.protobuf.Message
        getRequestPrototype(
        com.google.protobuf.Descriptors.MethodDescriptor method) {
      if (method.getService() != getDescriptor()) {
        throw new IllegalArgumentException(
          "Service.getRequestPrototype() given method " +
          "descriptor for wrong service type.");
      }
      switch(method.getIndex()) {
        case 0:
          return RpcMessage.getDefaultInstance();
        default:
          throw new AssertionError("Can't get here.");
      }
    }

    public final com.google.protobuf.Message
        getResponsePrototype(
        com.google.protobuf.Descriptors.MethodDescriptor method) {
      if (method.getService() != getDescriptor()) {
        throw new IllegalArgumentException(
          "Service.getResponsePrototype() given method " +
          "descriptor for wrong service type.");
      }
      switch(method.getIndex()) {
        case 0:
          return RpcMessage.getDefaultInstance();
        default:
          throw new AssertionError("Can't get here.");
      }
    }

    public static Stub newStub(
        com.google.protobuf.RpcChannel channel) {
      return new Stub(channel);
    }

    public static final class Stub extends RpcNotify implements Interface {
      private Stub(com.google.protobuf.RpcChannel channel) {
        this.channel = channel;
      }

      private final com.google.protobuf.RpcChannel channel;

      public com.google.protobuf.RpcChannel getChannel() {
        return channel;
      }

      public  void notif(
          com.google.protobuf.RpcController controller,
          RpcMessage request,
          com.google.protobuf.RpcCallback<RpcMessage> done) {
        channel.callMethod(
          getDescriptor().getMethods().get(0),
          controller,
          request,
          RpcMessage.getDefaultInstance(),
          com.google.protobuf.RpcUtil.generalizeCallback(
            done,
            RpcMessage.class,
            RpcMessage.getDefaultInstance()));
      }
    }

    public static BlockingInterface newBlockingStub(
        com.google.protobuf.BlockingRpcChannel channel) {
      return new BlockingStub(channel);
    }

    public interface BlockingInterface {
      public RpcMessage notif(
              com.google.protobuf.RpcController controller,
              RpcMessage request)
          throws com.google.protobuf.ServiceException;
    }

    private static final class BlockingStub implements BlockingInterface {
      private BlockingStub(com.google.protobuf.BlockingRpcChannel channel) {
        this.channel = channel;
      }

      private final com.google.protobuf.BlockingRpcChannel channel;

      public RpcMessage notif(
          com.google.protobuf.RpcController controller,
          RpcMessage request)
          throws com.google.protobuf.ServiceException {
        return (RpcMessage) channel.callBlockingMethod(
          getDescriptor().getMethods().get(0),
          controller,
          request,
          RpcMessage.getDefaultInstance());
      }

    }

    // @@protoc_insertion_point(class_scope:cass.utils.RpcNotify)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_cass_utils_RpcMessage_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_cass_utils_RpcMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\033rpc_notify_service(1).proto\022\ncass.util" +
      "s\"\035\n\nRpcMessage\022\017\n\007content\030\001 \001(\t2D\n\tRpcN" +
      "otify\0227\n\005notif\022\026.cass.utils.RpcMessage\032\026" +
      ".cass.utils.RpcMessageB0\n\025com.cass.rpc.p" +
      "rotorpcB\016RpcNotifyProto\200\001\001\210\001\001\220\001\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_cass_utils_RpcMessage_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_cass_utils_RpcMessage_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_cass_utils_RpcMessage_descriptor,
              new String[] { "Content", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
