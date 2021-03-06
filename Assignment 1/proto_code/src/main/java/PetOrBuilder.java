// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: catalog.proto

public interface PetOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Pet)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional int32 pet_id = 1;</code>
   * @return Whether the petId field is set.
   */
  boolean hasPetId();
  /**
   * <code>optional int32 pet_id = 1;</code>
   * @return The petId.
   */
  int getPetId();

  /**
   * <code>optional string name = 2;</code>
   * @return Whether the name field is set.
   */
  boolean hasName();
  /**
   * <code>optional string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>optional string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>optional string species = 3;</code>
   * @return Whether the species field is set.
   */
  boolean hasSpecies();
  /**
   * <code>optional string species = 3;</code>
   * @return The species.
   */
  java.lang.String getSpecies();
  /**
   * <code>optional string species = 3;</code>
   * @return The bytes for species.
   */
  com.google.protobuf.ByteString
      getSpeciesBytes();

  /**
   * <code>optional string gender = 4;</code>
   * @return Whether the gender field is set.
   */
  boolean hasGender();
  /**
   * <code>optional string gender = 4;</code>
   * @return The gender.
   */
  java.lang.String getGender();
  /**
   * <code>optional string gender = 4;</code>
   * @return The bytes for gender.
   */
  com.google.protobuf.ByteString
      getGenderBytes();

  /**
   * <code>optional int32 weight = 5;</code>
   * @return Whether the weight field is set.
   */
  boolean hasWeight();
  /**
   * <code>optional int32 weight = 5;</code>
   * @return The weight.
   */
  int getWeight();

  /**
   * <code>optional string birth = 6;</code>
   * @return Whether the birth field is set.
   */
  boolean hasBirth();
  /**
   * <code>optional string birth = 6;</code>
   * @return The birth.
   */
  java.lang.String getBirth();
  /**
   * <code>optional string birth = 6;</code>
   * @return The bytes for birth.
   */
  com.google.protobuf.ByteString
      getBirthBytes();

  /**
   * <code>optional string form_desc = 7;</code>
   * @return Whether the formDesc field is set.
   */
  boolean hasFormDesc();
  /**
   * <code>optional string form_desc = 7;</code>
   * @return The formDesc.
   */
  java.lang.String getFormDesc();
  /**
   * <code>optional string form_desc = 7;</code>
   * @return The bytes for formDesc.
   */
  com.google.protobuf.ByteString
      getFormDescBytes();

  /**
   * <code>optional int32 owner_id = 8;</code>
   * @return Whether the ownerId field is set.
   */
  boolean hasOwnerId();
  /**
   * <code>optional int32 owner_id = 8;</code>
   * @return The ownerId.
   */
  int getOwnerId();
}
