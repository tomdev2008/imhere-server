// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserProtos.proto

package org.xiaoxiancai.imhere.server.protos;

public final class UserProtos {
  private UserProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface UserOrBuilder extends
      // @@protoc_insertion_point(interface_extends:org.xiaoxiancai.imhere.server.protos.User)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required string mobile = 1;</code>
     */
    boolean hasMobile();
    /**
     * <code>required string mobile = 1;</code>
     */
    java.lang.String getMobile();
    /**
     * <code>required string mobile = 1;</code>
     */
    com.google.protobuf.ByteString
        getMobileBytes();

    /**
     * <code>optional string email = 2;</code>
     */
    boolean hasEmail();
    /**
     * <code>optional string email = 2;</code>
     */
    java.lang.String getEmail();
    /**
     * <code>optional string email = 2;</code>
     */
    com.google.protobuf.ByteString
        getEmailBytes();

    /**
     * <code>optional string nickName = 3;</code>
     */
    boolean hasNickName();
    /**
     * <code>optional string nickName = 3;</code>
     */
    java.lang.String getNickName();
    /**
     * <code>optional string nickName = 3;</code>
     */
    com.google.protobuf.ByteString
        getNickNameBytes();

    /**
     * <code>optional string avatar = 4;</code>
     */
    boolean hasAvatar();
    /**
     * <code>optional string avatar = 4;</code>
     */
    java.lang.String getAvatar();
    /**
     * <code>optional string avatar = 4;</code>
     */
    com.google.protobuf.ByteString
        getAvatarBytes();
  }
  /**
   * Protobuf type {@code org.xiaoxiancai.imhere.server.protos.User}
   */
  public static final class User extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:org.xiaoxiancai.imhere.server.protos.User)
      UserOrBuilder {
    // Use User.newBuilder() to construct.
    private User(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private User(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final User defaultInstance;
    public static User getDefaultInstance() {
      return defaultInstance;
    }

    public User getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private User(
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
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              mobile_ = bs;
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              email_ = bs;
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              nickName_ = bs;
              break;
            }
            case 34: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000008;
              avatar_ = bs;
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
      return org.xiaoxiancai.imhere.server.protos.UserProtos.internal_static_org_xiaoxiancai_imhere_server_protos_User_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.xiaoxiancai.imhere.server.protos.UserProtos.internal_static_org_xiaoxiancai_imhere_server_protos_User_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.xiaoxiancai.imhere.server.protos.UserProtos.User.class, org.xiaoxiancai.imhere.server.protos.UserProtos.User.Builder.class);
    }

    public static com.google.protobuf.Parser<User> PARSER =
        new com.google.protobuf.AbstractParser<User>() {
      public User parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new User(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<User> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int MOBILE_FIELD_NUMBER = 1;
    private java.lang.Object mobile_;
    /**
     * <code>required string mobile = 1;</code>
     */
    public boolean hasMobile() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string mobile = 1;</code>
     */
    public java.lang.String getMobile() {
      java.lang.Object ref = mobile_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          mobile_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string mobile = 1;</code>
     */
    public com.google.protobuf.ByteString
        getMobileBytes() {
      java.lang.Object ref = mobile_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        mobile_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int EMAIL_FIELD_NUMBER = 2;
    private java.lang.Object email_;
    /**
     * <code>optional string email = 2;</code>
     */
    public boolean hasEmail() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional string email = 2;</code>
     */
    public java.lang.String getEmail() {
      java.lang.Object ref = email_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          email_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string email = 2;</code>
     */
    public com.google.protobuf.ByteString
        getEmailBytes() {
      java.lang.Object ref = email_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        email_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int NICKNAME_FIELD_NUMBER = 3;
    private java.lang.Object nickName_;
    /**
     * <code>optional string nickName = 3;</code>
     */
    public boolean hasNickName() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string nickName = 3;</code>
     */
    public java.lang.String getNickName() {
      java.lang.Object ref = nickName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          nickName_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string nickName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getNickNameBytes() {
      java.lang.Object ref = nickName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        nickName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int AVATAR_FIELD_NUMBER = 4;
    private java.lang.Object avatar_;
    /**
     * <code>optional string avatar = 4;</code>
     */
    public boolean hasAvatar() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional string avatar = 4;</code>
     */
    public java.lang.String getAvatar() {
      java.lang.Object ref = avatar_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          avatar_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string avatar = 4;</code>
     */
    public com.google.protobuf.ByteString
        getAvatarBytes() {
      java.lang.Object ref = avatar_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        avatar_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      mobile_ = "";
      email_ = "";
      nickName_ = "";
      avatar_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasMobile()) {
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
        output.writeBytes(1, getMobileBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getEmailBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getNickNameBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBytes(4, getAvatarBytes());
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
          .computeBytesSize(1, getMobileBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getEmailBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getNickNameBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, getAvatarBytes());
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

    public static org.xiaoxiancai.imhere.server.protos.UserProtos.User parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.xiaoxiancai.imhere.server.protos.UserProtos.User parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.xiaoxiancai.imhere.server.protos.UserProtos.User parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.xiaoxiancai.imhere.server.protos.UserProtos.User parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.xiaoxiancai.imhere.server.protos.UserProtos.User parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static org.xiaoxiancai.imhere.server.protos.UserProtos.User parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static org.xiaoxiancai.imhere.server.protos.UserProtos.User parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static org.xiaoxiancai.imhere.server.protos.UserProtos.User parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static org.xiaoxiancai.imhere.server.protos.UserProtos.User parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static org.xiaoxiancai.imhere.server.protos.UserProtos.User parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(org.xiaoxiancai.imhere.server.protos.UserProtos.User prototype) {
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
     * Protobuf type {@code org.xiaoxiancai.imhere.server.protos.User}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:org.xiaoxiancai.imhere.server.protos.User)
        org.xiaoxiancai.imhere.server.protos.UserProtos.UserOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.xiaoxiancai.imhere.server.protos.UserProtos.internal_static_org_xiaoxiancai_imhere_server_protos_User_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.xiaoxiancai.imhere.server.protos.UserProtos.internal_static_org_xiaoxiancai_imhere_server_protos_User_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.xiaoxiancai.imhere.server.protos.UserProtos.User.class, org.xiaoxiancai.imhere.server.protos.UserProtos.User.Builder.class);
      }

      // Construct using org.xiaoxiancai.imhere.server.protos.UserProtos.User.newBuilder()
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
        mobile_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        email_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        nickName_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        avatar_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.xiaoxiancai.imhere.server.protos.UserProtos.internal_static_org_xiaoxiancai_imhere_server_protos_User_descriptor;
      }

      public org.xiaoxiancai.imhere.server.protos.UserProtos.User getDefaultInstanceForType() {
        return org.xiaoxiancai.imhere.server.protos.UserProtos.User.getDefaultInstance();
      }

      public org.xiaoxiancai.imhere.server.protos.UserProtos.User build() {
        org.xiaoxiancai.imhere.server.protos.UserProtos.User result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public org.xiaoxiancai.imhere.server.protos.UserProtos.User buildPartial() {
        org.xiaoxiancai.imhere.server.protos.UserProtos.User result = new org.xiaoxiancai.imhere.server.protos.UserProtos.User(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.mobile_ = mobile_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.email_ = email_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.nickName_ = nickName_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.avatar_ = avatar_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.xiaoxiancai.imhere.server.protos.UserProtos.User) {
          return mergeFrom((org.xiaoxiancai.imhere.server.protos.UserProtos.User)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.xiaoxiancai.imhere.server.protos.UserProtos.User other) {
        if (other == org.xiaoxiancai.imhere.server.protos.UserProtos.User.getDefaultInstance()) return this;
        if (other.hasMobile()) {
          bitField0_ |= 0x00000001;
          mobile_ = other.mobile_;
          onChanged();
        }
        if (other.hasEmail()) {
          bitField0_ |= 0x00000002;
          email_ = other.email_;
          onChanged();
        }
        if (other.hasNickName()) {
          bitField0_ |= 0x00000004;
          nickName_ = other.nickName_;
          onChanged();
        }
        if (other.hasAvatar()) {
          bitField0_ |= 0x00000008;
          avatar_ = other.avatar_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasMobile()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        org.xiaoxiancai.imhere.server.protos.UserProtos.User parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (org.xiaoxiancai.imhere.server.protos.UserProtos.User) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object mobile_ = "";
      /**
       * <code>required string mobile = 1;</code>
       */
      public boolean hasMobile() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string mobile = 1;</code>
       */
      public java.lang.String getMobile() {
        java.lang.Object ref = mobile_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            mobile_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string mobile = 1;</code>
       */
      public com.google.protobuf.ByteString
          getMobileBytes() {
        java.lang.Object ref = mobile_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          mobile_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string mobile = 1;</code>
       */
      public Builder setMobile(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        mobile_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string mobile = 1;</code>
       */
      public Builder clearMobile() {
        bitField0_ = (bitField0_ & ~0x00000001);
        mobile_ = getDefaultInstance().getMobile();
        onChanged();
        return this;
      }
      /**
       * <code>required string mobile = 1;</code>
       */
      public Builder setMobileBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        mobile_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object email_ = "";
      /**
       * <code>optional string email = 2;</code>
       */
      public boolean hasEmail() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional string email = 2;</code>
       */
      public java.lang.String getEmail() {
        java.lang.Object ref = email_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            email_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string email = 2;</code>
       */
      public com.google.protobuf.ByteString
          getEmailBytes() {
        java.lang.Object ref = email_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          email_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string email = 2;</code>
       */
      public Builder setEmail(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        email_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string email = 2;</code>
       */
      public Builder clearEmail() {
        bitField0_ = (bitField0_ & ~0x00000002);
        email_ = getDefaultInstance().getEmail();
        onChanged();
        return this;
      }
      /**
       * <code>optional string email = 2;</code>
       */
      public Builder setEmailBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        email_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object nickName_ = "";
      /**
       * <code>optional string nickName = 3;</code>
       */
      public boolean hasNickName() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional string nickName = 3;</code>
       */
      public java.lang.String getNickName() {
        java.lang.Object ref = nickName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            nickName_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string nickName = 3;</code>
       */
      public com.google.protobuf.ByteString
          getNickNameBytes() {
        java.lang.Object ref = nickName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          nickName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string nickName = 3;</code>
       */
      public Builder setNickName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        nickName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string nickName = 3;</code>
       */
      public Builder clearNickName() {
        bitField0_ = (bitField0_ & ~0x00000004);
        nickName_ = getDefaultInstance().getNickName();
        onChanged();
        return this;
      }
      /**
       * <code>optional string nickName = 3;</code>
       */
      public Builder setNickNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        nickName_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object avatar_ = "";
      /**
       * <code>optional string avatar = 4;</code>
       */
      public boolean hasAvatar() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional string avatar = 4;</code>
       */
      public java.lang.String getAvatar() {
        java.lang.Object ref = avatar_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            avatar_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string avatar = 4;</code>
       */
      public com.google.protobuf.ByteString
          getAvatarBytes() {
        java.lang.Object ref = avatar_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          avatar_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string avatar = 4;</code>
       */
      public Builder setAvatar(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        avatar_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string avatar = 4;</code>
       */
      public Builder clearAvatar() {
        bitField0_ = (bitField0_ & ~0x00000008);
        avatar_ = getDefaultInstance().getAvatar();
        onChanged();
        return this;
      }
      /**
       * <code>optional string avatar = 4;</code>
       */
      public Builder setAvatarBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        avatar_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:org.xiaoxiancai.imhere.server.protos.User)
    }

    static {
      defaultInstance = new User(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:org.xiaoxiancai.imhere.server.protos.User)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_xiaoxiancai_imhere_server_protos_User_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_org_xiaoxiancai_imhere_server_protos_User_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020UserProtos.proto\022$org.xiaoxiancai.imhe" +
      "re.server.protos\"G\n\004User\022\016\n\006mobile\030\001 \002(\t" +
      "\022\r\n\005email\030\002 \001(\t\022\020\n\010nickName\030\003 \001(\t\022\016\n\006ava" +
      "tar\030\004 \001(\tB2\n$org.xiaoxiancai.imhere.serv" +
      "er.protosB\nUserProtos"
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
    internal_static_org_xiaoxiancai_imhere_server_protos_User_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_org_xiaoxiancai_imhere_server_protos_User_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_org_xiaoxiancai_imhere_server_protos_User_descriptor,
        new java.lang.String[] { "Mobile", "Email", "NickName", "Avatar", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
