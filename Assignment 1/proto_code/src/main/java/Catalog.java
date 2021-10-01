// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: catalog.proto

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * Protobuf type {@code Catalog}
 */
public final class Catalog extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Catalog)
    CatalogOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Catalog.newBuilder() to construct.
  private Catalog(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Catalog() {
    allOwners_ = java.util.Collections.emptyList();
    allPets_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Catalog();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Catalog(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              allOwners_ = new java.util.ArrayList<Owner>();
              mutable_bitField0_ |= 0x00000001;
            }
            allOwners_.add(
                input.readMessage(Owner.parser(), extensionRegistry));
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) != 0)) {
              allPets_ = new java.util.ArrayList<Pet>();
              mutable_bitField0_ |= 0x00000002;
            }
            allPets_.add(
                input.readMessage(Pet.parser(), extensionRegistry));
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        allOwners_ = java.util.Collections.unmodifiableList(allOwners_);
      }
      if (((mutable_bitField0_ & 0x00000002) != 0)) {
        allPets_ = java.util.Collections.unmodifiableList(allPets_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return CatalogOuterClass.internal_static_Catalog_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return CatalogOuterClass.internal_static_Catalog_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            Catalog.class, Catalog.Builder.class);
  }

  public static final int ALLOWNERS_FIELD_NUMBER = 1;
  private java.util.List<Owner> allOwners_;
  /**
   * <code>repeated .Owner allOwners = 1;</code>
   */
  @XmlElement
  public java.util.List<Owner> getAllOwners() {
    return allOwners_;
  }
  /**
   * <code>repeated .Owner allOwners = 1;</code>
   */
  public void addOwner(Owner owner) { (this.allOwners_).add(owner); }

  @java.lang.Override
  public java.util.List<? extends OwnerOrBuilder> 
      getAllOwnersOrBuilderList() {
    return allOwners_;
  }

  @Override
  public List<Owner> getAllOwnersList() {
    return null;
  }

  @Override
  public Owner getAllOwners(int index) {
    return null;
  }

  /**
   * <code>repeated .Owner allOwners = 1;</code>
   */
  @java.lang.Override
  public int getAllOwnersCount() {
    return allOwners_.size();
  }
  /**
   * <code>repeated .Owner allOwners = 1;</code>
   */
  @java.lang.Override
  public OwnerOrBuilder getAllOwnersOrBuilder(
      int index) {
    return allOwners_.get(index);
  }

  @Override
  public List<Pet> getAllPetsList() {
    return null;
  }

  @Override
  public Pet getAllPets(int index) {
    return null;
  }

  public static final int ALLPETS_FIELD_NUMBER = 2;
  private java.util.List<Pet> allPets_;
  /**
   * <code>repeated .Pet allPets = 2;</code>
   */
  public java.util.List<Pet> getAllPets() {
    return allPets_;
  }
  /**
   * <code>repeated .Pet allPets = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends PetOrBuilder> 
      getAllPetsOrBuilderList() {
    return allPets_;
  }
  /**
   * <code>repeated .Pet allPets = 2;</code>
   */
  @java.lang.Override
  public int getAllPetsCount() {
    return allPets_.size();
  }
  /**
   * <code>repeated .Pet allPets = 2;</code>
   */
  @java.lang.Override
  public PetOrBuilder getAllPetsOrBuilder(
      int index) {
    return allPets_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < allOwners_.size(); i++) {
      output.writeMessage(1, allOwners_.get(i));
    }
    for (int i = 0; i < allPets_.size(); i++) {
      output.writeMessage(2, allPets_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < allOwners_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, allOwners_.get(i));
    }
    for (int i = 0; i < allPets_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, allPets_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof Catalog)) {
      return super.equals(obj);
    }
    Catalog other = (Catalog) obj;

    if (!getAllOwnersList()
        .equals(other.getAllOwnersList())) return false;
    if (!getAllPetsList()
        .equals(other.getAllPetsList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getAllOwnersCount() > 0) {
      hash = (37 * hash) + ALLOWNERS_FIELD_NUMBER;
      hash = (53 * hash) + getAllOwnersList().hashCode();
    }
    if (getAllPetsCount() > 0) {
      hash = (37 * hash) + ALLPETS_FIELD_NUMBER;
      hash = (53 * hash) + getAllPetsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static Catalog parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Catalog parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Catalog parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Catalog parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Catalog parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Catalog parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Catalog parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static Catalog parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static Catalog parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static Catalog parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static Catalog parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static Catalog parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(Catalog prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code Catalog}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Catalog)
      CatalogOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return CatalogOuterClass.internal_static_Catalog_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return CatalogOuterClass.internal_static_Catalog_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Catalog.class, Catalog.Builder.class);
    }

    // Construct using Catalog.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getAllOwnersFieldBuilder();
        getAllPetsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (allOwnersBuilder_ == null) {
        allOwners_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        allOwnersBuilder_.clear();
      }
      if (allPetsBuilder_ == null) {
        allPets_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        allPetsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return CatalogOuterClass.internal_static_Catalog_descriptor;
    }

    @java.lang.Override
    public Catalog getDefaultInstanceForType() {
      return Catalog.getDefaultInstance();
    }

    @java.lang.Override
    public Catalog build() {
      Catalog result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public Catalog buildPartial() {
      Catalog result = new Catalog(this);
      int from_bitField0_ = bitField0_;
      if (allOwnersBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          allOwners_ = java.util.Collections.unmodifiableList(allOwners_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.allOwners_ = allOwners_;
      } else {
        result.allOwners_ = allOwnersBuilder_.build();
      }
      if (allPetsBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0)) {
          allPets_ = java.util.Collections.unmodifiableList(allPets_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.allPets_ = allPets_;
      } else {
        result.allPets_ = allPetsBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof Catalog) {
        return mergeFrom((Catalog)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(Catalog other) {
      if (other == Catalog.getDefaultInstance()) return this;
      if (allOwnersBuilder_ == null) {
        if (!other.allOwners_.isEmpty()) {
          if (allOwners_.isEmpty()) {
            allOwners_ = other.allOwners_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureAllOwnersIsMutable();
            allOwners_.addAll(other.allOwners_);
          }
          onChanged();
        }
      } else {
        if (!other.allOwners_.isEmpty()) {
          if (allOwnersBuilder_.isEmpty()) {
            allOwnersBuilder_.dispose();
            allOwnersBuilder_ = null;
            allOwners_ = other.allOwners_;
            bitField0_ = (bitField0_ & ~0x00000001);
            allOwnersBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getAllOwnersFieldBuilder() : null;
          } else {
            allOwnersBuilder_.addAllMessages(other.allOwners_);
          }
        }
      }
      if (allPetsBuilder_ == null) {
        if (!other.allPets_.isEmpty()) {
          if (allPets_.isEmpty()) {
            allPets_ = other.allPets_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureAllPetsIsMutable();
            allPets_.addAll(other.allPets_);
          }
          onChanged();
        }
      } else {
        if (!other.allPets_.isEmpty()) {
          if (allPetsBuilder_.isEmpty()) {
            allPetsBuilder_.dispose();
            allPetsBuilder_ = null;
            allPets_ = other.allPets_;
            bitField0_ = (bitField0_ & ~0x00000002);
            allPetsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getAllPetsFieldBuilder() : null;
          } else {
            allPetsBuilder_.addAllMessages(other.allPets_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Catalog parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (Catalog) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<Owner> allOwners_ =
      java.util.Collections.emptyList();
    private void ensureAllOwnersIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        allOwners_ = new java.util.ArrayList<Owner>(allOwners_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        Owner, Owner.Builder, OwnerOrBuilder> allOwnersBuilder_;

    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public java.util.List<Owner> getAllOwnersList() {
      if (allOwnersBuilder_ == null) {
        return java.util.Collections.unmodifiableList(allOwners_);
      } else {
        return allOwnersBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public int getAllOwnersCount() {
      if (allOwnersBuilder_ == null) {
        return allOwners_.size();
      } else {
        return allOwnersBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public Owner getAllOwners(int index) {
      if (allOwnersBuilder_ == null) {
        return allOwners_.get(index);
      } else {
        return allOwnersBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public Builder setAllOwners(
        int index, Owner value) {
      if (allOwnersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllOwnersIsMutable();
        allOwners_.set(index, value);
        onChanged();
      } else {
        allOwnersBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public Builder setAllOwners(
        int index, Owner.Builder builderForValue) {
      if (allOwnersBuilder_ == null) {
        ensureAllOwnersIsMutable();
        allOwners_.set(index, builderForValue.build());
        onChanged();
      } else {
        allOwnersBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public Builder addAllOwners(Owner value) {
      if (allOwnersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllOwnersIsMutable();
        allOwners_.add(value);
        onChanged();
      } else {
        allOwnersBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public Builder addAllOwners(
        int index, Owner value) {
      if (allOwnersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllOwnersIsMutable();
        allOwners_.add(index, value);
        onChanged();
      } else {
        allOwnersBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public Builder addAllOwners(
        Owner.Builder builderForValue) {
      if (allOwnersBuilder_ == null) {
        ensureAllOwnersIsMutable();
        allOwners_.add(builderForValue.build());
        onChanged();
      } else {
        allOwnersBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public Builder addAllOwners(
        int index, Owner.Builder builderForValue) {
      if (allOwnersBuilder_ == null) {
        ensureAllOwnersIsMutable();
        allOwners_.add(index, builderForValue.build());
        onChanged();
      } else {
        allOwnersBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public Builder addAllAllOwners(
        java.lang.Iterable<? extends Owner> values) {
      if (allOwnersBuilder_ == null) {
        ensureAllOwnersIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, allOwners_);
        onChanged();
      } else {
        allOwnersBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public Builder clearAllOwners() {
      if (allOwnersBuilder_ == null) {
        allOwners_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        allOwnersBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public Builder removeAllOwners(int index) {
      if (allOwnersBuilder_ == null) {
        ensureAllOwnersIsMutable();
        allOwners_.remove(index);
        onChanged();
      } else {
        allOwnersBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public Owner.Builder getAllOwnersBuilder(
        int index) {
      return getAllOwnersFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public OwnerOrBuilder getAllOwnersOrBuilder(
        int index) {
      if (allOwnersBuilder_ == null) {
        return allOwners_.get(index);  } else {
        return allOwnersBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public java.util.List<? extends OwnerOrBuilder> 
         getAllOwnersOrBuilderList() {
      if (allOwnersBuilder_ != null) {
        return allOwnersBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(allOwners_);
      }
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public Owner.Builder addAllOwnersBuilder() {
      return getAllOwnersFieldBuilder().addBuilder(
          Owner.getDefaultInstance());
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public Owner.Builder addAllOwnersBuilder(
        int index) {
      return getAllOwnersFieldBuilder().addBuilder(
          index, Owner.getDefaultInstance());
    }
    /**
     * <code>repeated .Owner allOwners = 1;</code>
     */
    public java.util.List<Owner.Builder> 
         getAllOwnersBuilderList() {
      return getAllOwnersFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        Owner, Owner.Builder, OwnerOrBuilder> 
        getAllOwnersFieldBuilder() {
      if (allOwnersBuilder_ == null) {
        allOwnersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            Owner, Owner.Builder, OwnerOrBuilder>(
                allOwners_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        allOwners_ = null;
      }
      return allOwnersBuilder_;
    }

    private java.util.List<Pet> allPets_ =
      java.util.Collections.emptyList();
    private void ensureAllPetsIsMutable() {
      if (!((bitField0_ & 0x00000002) != 0)) {
        allPets_ = new java.util.ArrayList<Pet>(allPets_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        Pet, Pet.Builder, PetOrBuilder> allPetsBuilder_;

    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public java.util.List<Pet> getAllPetsList() {
      if (allPetsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(allPets_);
      } else {
        return allPetsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public int getAllPetsCount() {
      if (allPetsBuilder_ == null) {
        return allPets_.size();
      } else {
        return allPetsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public Pet getAllPets(int index) {
      if (allPetsBuilder_ == null) {
        return allPets_.get(index);
      } else {
        return allPetsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public Builder setAllPets(
        int index, Pet value) {
      if (allPetsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllPetsIsMutable();
        allPets_.set(index, value);
        onChanged();
      } else {
        allPetsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public Builder setAllPets(
        int index, Pet.Builder builderForValue) {
      if (allPetsBuilder_ == null) {
        ensureAllPetsIsMutable();
        allPets_.set(index, builderForValue.build());
        onChanged();
      } else {
        allPetsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public Builder addAllPets(Pet value) {
      if (allPetsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllPetsIsMutable();
        allPets_.add(value);
        onChanged();
      } else {
        allPetsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public Builder addAllPets(
        int index, Pet value) {
      if (allPetsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAllPetsIsMutable();
        allPets_.add(index, value);
        onChanged();
      } else {
        allPetsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public Builder addAllPets(
        Pet.Builder builderForValue) {
      if (allPetsBuilder_ == null) {
        ensureAllPetsIsMutable();
        allPets_.add(builderForValue.build());
        onChanged();
      } else {
        allPetsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public Builder addAllPets(
        int index, Pet.Builder builderForValue) {
      if (allPetsBuilder_ == null) {
        ensureAllPetsIsMutable();
        allPets_.add(index, builderForValue.build());
        onChanged();
      } else {
        allPetsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public Builder addAllAllPets(
        java.lang.Iterable<? extends Pet> values) {
      if (allPetsBuilder_ == null) {
        ensureAllPetsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, allPets_);
        onChanged();
      } else {
        allPetsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public Builder clearAllPets() {
      if (allPetsBuilder_ == null) {
        allPets_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        allPetsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public Builder removeAllPets(int index) {
      if (allPetsBuilder_ == null) {
        ensureAllPetsIsMutable();
        allPets_.remove(index);
        onChanged();
      } else {
        allPetsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public Pet.Builder getAllPetsBuilder(
        int index) {
      return getAllPetsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public PetOrBuilder getAllPetsOrBuilder(
        int index) {
      if (allPetsBuilder_ == null) {
        return allPets_.get(index);  } else {
        return allPetsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public java.util.List<? extends PetOrBuilder> 
         getAllPetsOrBuilderList() {
      if (allPetsBuilder_ != null) {
        return allPetsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(allPets_);
      }
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public Pet.Builder addAllPetsBuilder() {
      return getAllPetsFieldBuilder().addBuilder(
          Pet.getDefaultInstance());
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public Pet.Builder addAllPetsBuilder(
        int index) {
      return getAllPetsFieldBuilder().addBuilder(
          index, Pet.getDefaultInstance());
    }
    /**
     * <code>repeated .Pet allPets = 2;</code>
     */
    public java.util.List<Pet.Builder> 
         getAllPetsBuilderList() {
      return getAllPetsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        Pet, Pet.Builder, PetOrBuilder> 
        getAllPetsFieldBuilder() {
      if (allPetsBuilder_ == null) {
        allPetsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            Pet, Pet.Builder, PetOrBuilder>(
                allPets_,
                ((bitField0_ & 0x00000002) != 0),
                getParentForChildren(),
                isClean());
        allPets_ = null;
      }
      return allPetsBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:Catalog)
  }

  // @@protoc_insertion_point(class_scope:Catalog)
  private static final Catalog DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new Catalog();
  }

  public static Catalog getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Catalog>
      PARSER = new com.google.protobuf.AbstractParser<Catalog>() {
    @java.lang.Override
    public Catalog parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Catalog(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Catalog> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Catalog> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public Catalog getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

