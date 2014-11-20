// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AddFriendResponse.proto

package org.xiaoxiancai.imhere.common.protos.business;

public final class AddFriendRequestProtos {
  private AddFriendRequestProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface AddFriendRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:org.xiaoxiancai.imhere.server.business.protos.AddFriendRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 fromUserId = 1;</code>
     *
     * <pre>
     * 发起用户Id
     * </pre>
     */
    boolean hasFromUserId();
    /**
     * <code>required int32 fromUserId = 1;</code>
     *
     * <pre>
     * 发起用户Id
     * </pre>
     */
    int getFromUserId();

    /**
     * <code>required string fromUserMobile = 2;</code>
     *
     * <pre>
     * 发起用户电话
     * </pre>
     */
    boolean hasFromUserMobile();
    /**
     * <code>required string fromUserMobile = 2;</code>
     *
     * <pre>
     * 发起用户电话
     * </pre>
     */
    java.lang.String getFromUserMobile();
    /**
     * <code>required string fromUserMobile = 2;</code>
     *
     * <pre>
     * 发起用户电话
     * </pre>
     */
    com.google.protobuf.ByteString
        getFromUserMobileBytes();

    /**
     * <code>required string fromUserNickname = 3;</code>
     *
     * <pre>
     * 发起用户昵称
     * </pre>
     */
    boolean hasFromUserNickname();
    /**
     * <code>required string fromUserNickname = 3;</code>
     *
     * <pre>
     * 发起用户昵称
     * </pre>
     */
    java.lang.String getFromUserNickname();
    /**
     * <code>required string fromUserNickname = 3;</code>
     *
     * <pre>
     * 发起用户昵称
     * </pre>
     */
    com.google.protobuf.ByteString
        getFromUserNicknameBytes();

    /**
     * <code>optional int32 toUserId = 4;</code>
     *
     * <pre>
     * 被发起用户Id
     * </pre>
     */
    boolean hasToUserId();
    /**
     * <code>optional int32 toUserId = 4;</code>
     *
     * <pre>
     * 被发起用户Id
     * </pre>
     */
    int getToUserId();

    /**
     * <code>required string toUserMobile = 5;</code>
     *
     * <pre>
     * 被发起用户电话
     * </pre>
     */
    boolean hasToUserMobile();
    /**
     * <code>required string toUserMobile = 5;</code>
     *
     * <pre>
     * 被发起用户电话
     * </pre>
     */
    java.lang.String getToUserMobile();
    /**
     * <code>required string toUserMobile = 5;</code>
     *
     * <pre>
     * 被发起用户电话
     * </pre>
     */
    com.google.protobuf.ByteString
        getToUserMobileBytes();
  }
  /**
   * Protobuf type {@code org.xiaoxiancai.imhere.server.business.protos.AddFriendRequest}
   */
  public static final class AddFriendRequest extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:org.xiaoxiancai.imhere.server.business.protos.AddFriendRequest)
      AddFriendRequestOrBuilder {
    // Use AddFriendRequest.newBuilder() to construct.
    private AddFriendRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private AddFriendRequest(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final AddFriendRequest defaultInstance;
    public static AddFriendRequest getDefaultInstance() {
      return defaultInstance;
    }

    public AddFriendRequest getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private AddFriendRequest(
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
            case 8: {
              bitField0_ |= 0x00000001;
              fromUserId_ = input.readInt32();
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              fromUserMobile_ = bs;
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              fromUserNickname_ = bs;
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              toUserId_ = input.readInt32();
              break;
            }
            case 42: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000010;
              toUserMobile_ = bs;
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
      return org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.internal_static_org_xiaoxiancai_imhere_server_business_protos_AddFriendRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.internal_static_org_xiaoxiancai_imhere_server_business_protos_AddFriendRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest.class, org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest.Builder.class);
    }

    public static com.google.protobuf.Parser<AddFriendRequest> PARSER =
        new com.google.protobuf.AbstractParser<AddFriendRequest>() {
      public AddFriendRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new AddFriendRequest(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<AddFriendRequest> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int FROMUSERID_FIELD_NUMBER = 1;
    private int fromUserId_;
    /**
     * <code>required int32 fromUserId = 1;</code>
     *
     * <pre>
     * 发起用户Id
     * </pre>
     */
    public boolean hasFromUserId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 fromUserId = 1;</code>
     *
     * <pre>
     * 发起用户Id
     * </pre>
     */
    public int getFromUserId() {
      return fromUserId_;
    }

    public static final int FROMUSERMOBILE_FIELD_NUMBER = 2;
    private java.lang.Object fromUserMobile_;
    /**
     * <code>required string fromUserMobile = 2;</code>
     *
     * <pre>
     * 发起用户电话
     * </pre>
     */
    public boolean hasFromUserMobile() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string fromUserMobile = 2;</code>
     *
     * <pre>
     * 发起用户电话
     * </pre>
     */
    public java.lang.String getFromUserMobile() {
      java.lang.Object ref = fromUserMobile_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          fromUserMobile_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string fromUserMobile = 2;</code>
     *
     * <pre>
     * 发起用户电话
     * </pre>
     */
    public com.google.protobuf.ByteString
        getFromUserMobileBytes() {
      java.lang.Object ref = fromUserMobile_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        fromUserMobile_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int FROMUSERNICKNAME_FIELD_NUMBER = 3;
    private java.lang.Object fromUserNickname_;
    /**
     * <code>required string fromUserNickname = 3;</code>
     *
     * <pre>
     * 发起用户昵称
     * </pre>
     */
    public boolean hasFromUserNickname() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string fromUserNickname = 3;</code>
     *
     * <pre>
     * 发起用户昵称
     * </pre>
     */
    public java.lang.String getFromUserNickname() {
      java.lang.Object ref = fromUserNickname_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          fromUserNickname_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string fromUserNickname = 3;</code>
     *
     * <pre>
     * 发起用户昵称
     * </pre>
     */
    public com.google.protobuf.ByteString
        getFromUserNicknameBytes() {
      java.lang.Object ref = fromUserNickname_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        fromUserNickname_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TOUSERID_FIELD_NUMBER = 4;
    private int toUserId_;
    /**
     * <code>optional int32 toUserId = 4;</code>
     *
     * <pre>
     * 被发起用户Id
     * </pre>
     */
    public boolean hasToUserId() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 toUserId = 4;</code>
     *
     * <pre>
     * 被发起用户Id
     * </pre>
     */
    public int getToUserId() {
      return toUserId_;
    }

    public static final int TOUSERMOBILE_FIELD_NUMBER = 5;
    private java.lang.Object toUserMobile_;
    /**
     * <code>required string toUserMobile = 5;</code>
     *
     * <pre>
     * 被发起用户电话
     * </pre>
     */
    public boolean hasToUserMobile() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>required string toUserMobile = 5;</code>
     *
     * <pre>
     * 被发起用户电话
     * </pre>
     */
    public java.lang.String getToUserMobile() {
      java.lang.Object ref = toUserMobile_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          toUserMobile_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string toUserMobile = 5;</code>
     *
     * <pre>
     * 被发起用户电话
     * </pre>
     */
    public com.google.protobuf.ByteString
        getToUserMobileBytes() {
      java.lang.Object ref = toUserMobile_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        toUserMobile_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      fromUserId_ = 0;
      fromUserMobile_ = "";
      fromUserNickname_ = "";
      toUserId_ = 0;
      toUserMobile_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasFromUserId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasFromUserMobile()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasFromUserNickname()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasToUserMobile()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, fromUserId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getFromUserMobileBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getFromUserNicknameBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, toUserId_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeBytes(5, getToUserMobileBytes());
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
          .computeInt32Size(1, fromUserId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getFromUserMobileBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getFromUserNicknameBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, toUserId_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(5, getToUserMobileBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code org.xiaoxiancai.imhere.server.business.protos.AddFriendRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:org.xiaoxiancai.imhere.server.business.protos.AddFriendRequest)
        org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.internal_static_org_xiaoxiancai_imhere_server_business_protos_AddFriendRequest_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.internal_static_org_xiaoxiancai_imhere_server_business_protos_AddFriendRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest.class, org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest.Builder.class);
      }

      // Construct using org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
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
        fromUserId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        fromUserMobile_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        fromUserNickname_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        toUserId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        toUserMobile_ = "";
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.internal_static_org_xiaoxiancai_imhere_server_business_protos_AddFriendRequest_descriptor;
      }

      public org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest getDefaultInstanceForType() {
        return org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest.getDefaultInstance();
      }

      public org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest build() {
        org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest buildPartial() {
        org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest result = new org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.fromUserId_ = fromUserId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.fromUserMobile_ = fromUserMobile_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.fromUserNickname_ = fromUserNickname_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.toUserId_ = toUserId_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.toUserMobile_ = toUserMobile_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest) {
          return mergeFrom((org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest other) {
        if (other == org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest.getDefaultInstance()) return this;
        if (other.hasFromUserId()) {
          setFromUserId(other.getFromUserId());
        }
        if (other.hasFromUserMobile()) {
          bitField0_ |= 0x00000002;
          fromUserMobile_ = other.fromUserMobile_;
          onChanged();
        }
        if (other.hasFromUserNickname()) {
          bitField0_ |= 0x00000004;
          fromUserNickname_ = other.fromUserNickname_;
          onChanged();
        }
        if (other.hasToUserId()) {
          setToUserId(other.getToUserId());
        }
        if (other.hasToUserMobile()) {
          bitField0_ |= 0x00000010;
          toUserMobile_ = other.toUserMobile_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasFromUserId()) {
          
          return false;
        }
        if (!hasFromUserMobile()) {
          
          return false;
        }
        if (!hasFromUserNickname()) {
          
          return false;
        }
        if (!hasToUserMobile()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int fromUserId_ ;
      /**
       * <code>required int32 fromUserId = 1;</code>
       *
       * <pre>
       * 发起用户Id
       * </pre>
       */
      public boolean hasFromUserId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 fromUserId = 1;</code>
       *
       * <pre>
       * 发起用户Id
       * </pre>
       */
      public int getFromUserId() {
        return fromUserId_;
      }
      /**
       * <code>required int32 fromUserId = 1;</code>
       *
       * <pre>
       * 发起用户Id
       * </pre>
       */
      public Builder setFromUserId(int value) {
        bitField0_ |= 0x00000001;
        fromUserId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 fromUserId = 1;</code>
       *
       * <pre>
       * 发起用户Id
       * </pre>
       */
      public Builder clearFromUserId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        fromUserId_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object fromUserMobile_ = "";
      /**
       * <code>required string fromUserMobile = 2;</code>
       *
       * <pre>
       * 发起用户电话
       * </pre>
       */
      public boolean hasFromUserMobile() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string fromUserMobile = 2;</code>
       *
       * <pre>
       * 发起用户电话
       * </pre>
       */
      public java.lang.String getFromUserMobile() {
        java.lang.Object ref = fromUserMobile_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            fromUserMobile_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string fromUserMobile = 2;</code>
       *
       * <pre>
       * 发起用户电话
       * </pre>
       */
      public com.google.protobuf.ByteString
          getFromUserMobileBytes() {
        java.lang.Object ref = fromUserMobile_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          fromUserMobile_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string fromUserMobile = 2;</code>
       *
       * <pre>
       * 发起用户电话
       * </pre>
       */
      public Builder setFromUserMobile(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        fromUserMobile_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string fromUserMobile = 2;</code>
       *
       * <pre>
       * 发起用户电话
       * </pre>
       */
      public Builder clearFromUserMobile() {
        bitField0_ = (bitField0_ & ~0x00000002);
        fromUserMobile_ = getDefaultInstance().getFromUserMobile();
        onChanged();
        return this;
      }
      /**
       * <code>required string fromUserMobile = 2;</code>
       *
       * <pre>
       * 发起用户电话
       * </pre>
       */
      public Builder setFromUserMobileBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        fromUserMobile_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object fromUserNickname_ = "";
      /**
       * <code>required string fromUserNickname = 3;</code>
       *
       * <pre>
       * 发起用户昵称
       * </pre>
       */
      public boolean hasFromUserNickname() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required string fromUserNickname = 3;</code>
       *
       * <pre>
       * 发起用户昵称
       * </pre>
       */
      public java.lang.String getFromUserNickname() {
        java.lang.Object ref = fromUserNickname_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            fromUserNickname_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string fromUserNickname = 3;</code>
       *
       * <pre>
       * 发起用户昵称
       * </pre>
       */
      public com.google.protobuf.ByteString
          getFromUserNicknameBytes() {
        java.lang.Object ref = fromUserNickname_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          fromUserNickname_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string fromUserNickname = 3;</code>
       *
       * <pre>
       * 发起用户昵称
       * </pre>
       */
      public Builder setFromUserNickname(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        fromUserNickname_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string fromUserNickname = 3;</code>
       *
       * <pre>
       * 发起用户昵称
       * </pre>
       */
      public Builder clearFromUserNickname() {
        bitField0_ = (bitField0_ & ~0x00000004);
        fromUserNickname_ = getDefaultInstance().getFromUserNickname();
        onChanged();
        return this;
      }
      /**
       * <code>required string fromUserNickname = 3;</code>
       *
       * <pre>
       * 发起用户昵称
       * </pre>
       */
      public Builder setFromUserNicknameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        fromUserNickname_ = value;
        onChanged();
        return this;
      }

      private int toUserId_ ;
      /**
       * <code>optional int32 toUserId = 4;</code>
       *
       * <pre>
       * 被发起用户Id
       * </pre>
       */
      public boolean hasToUserId() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 toUserId = 4;</code>
       *
       * <pre>
       * 被发起用户Id
       * </pre>
       */
      public int getToUserId() {
        return toUserId_;
      }
      /**
       * <code>optional int32 toUserId = 4;</code>
       *
       * <pre>
       * 被发起用户Id
       * </pre>
       */
      public Builder setToUserId(int value) {
        bitField0_ |= 0x00000008;
        toUserId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 toUserId = 4;</code>
       *
       * <pre>
       * 被发起用户Id
       * </pre>
       */
      public Builder clearToUserId() {
        bitField0_ = (bitField0_ & ~0x00000008);
        toUserId_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object toUserMobile_ = "";
      /**
       * <code>required string toUserMobile = 5;</code>
       *
       * <pre>
       * 被发起用户电话
       * </pre>
       */
      public boolean hasToUserMobile() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>required string toUserMobile = 5;</code>
       *
       * <pre>
       * 被发起用户电话
       * </pre>
       */
      public java.lang.String getToUserMobile() {
        java.lang.Object ref = toUserMobile_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            toUserMobile_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string toUserMobile = 5;</code>
       *
       * <pre>
       * 被发起用户电话
       * </pre>
       */
      public com.google.protobuf.ByteString
          getToUserMobileBytes() {
        java.lang.Object ref = toUserMobile_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          toUserMobile_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string toUserMobile = 5;</code>
       *
       * <pre>
       * 被发起用户电话
       * </pre>
       */
      public Builder setToUserMobile(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        toUserMobile_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string toUserMobile = 5;</code>
       *
       * <pre>
       * 被发起用户电话
       * </pre>
       */
      public Builder clearToUserMobile() {
        bitField0_ = (bitField0_ & ~0x00000010);
        toUserMobile_ = getDefaultInstance().getToUserMobile();
        onChanged();
        return this;
      }
      /**
       * <code>required string toUserMobile = 5;</code>
       *
       * <pre>
       * 被发起用户电话
       * </pre>
       */
      public Builder setToUserMobileBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        toUserMobile_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:org.xiaoxiancai.imhere.server.business.protos.AddFriendRequest)
    }

    static {
      defaultInstance = new AddFriendRequest(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:org.xiaoxiancai.imhere.server.business.protos.AddFriendRequest)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_xiaoxiancai_imhere_server_business_protos_AddFriendRequest_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_org_xiaoxiancai_imhere_server_business_protos_AddFriendRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027AddFriendResponse.proto\022-org.xiaoxianc" +
      "ai.imhere.server.business.protos\"\200\001\n\020Add" +
      "FriendRequest\022\022\n\nfromUserId\030\001 \002(\005\022\026\n\016fro" +
      "mUserMobile\030\002 \002(\t\022\030\n\020fromUserNickname\030\003 " +
      "\002(\t\022\020\n\010toUserId\030\004 \001(\005\022\024\n\014toUserMobile\030\005 " +
      "\002(\tBG\n-org.xiaoxiancai.imhere.common.pro" +
      "tos.businessB\026AddFriendRequestProtos"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_org_xiaoxiancai_imhere_server_business_protos_AddFriendRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_org_xiaoxiancai_imhere_server_business_protos_AddFriendRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_org_xiaoxiancai_imhere_server_business_protos_AddFriendRequest_descriptor,
        new java.lang.String[] { "FromUserId", "FromUserMobile", "FromUserNickname", "ToUserId", "ToUserMobile", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
