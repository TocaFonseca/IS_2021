// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: projeto.proto

package proto.example;

public final class Projeto {
  private Projeto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tutorial_Catalog_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tutorial_Catalog_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tutorial_Catalog_Owner_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tutorial_Catalog_Owner_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tutorial_Catalog_Owner_Pet_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tutorial_Catalog_Owner_Pet_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rprojeto.proto\022\010tutorial\"\233\004\n\007Catalog\022\'\n" +
      "\006people\030\001 \003(\0132\027.tutorial.Catalog.Owner\032\346" +
      "\003\n\005Owner\022\025\n\010owner_id\030\001 \001(\005H\000\210\001\001\022\021\n\004name\030" +
      "\002 \001(\tH\001\210\001\001\022\022\n\005birth\030\003 \001(\tH\002\210\001\001\022\026\n\tteleph" +
      "one\030\004 \001(\003H\003\210\001\001\022\024\n\007address\030\005 \001(\tH\004\210\001\001\022)\n\004" +
      "pets\030\006 \003(\0132\033.tutorial.Catalog.Owner.Pet\032" +
      "\213\002\n\003Pet\022\023\n\006pet_id\030\001 \001(\005H\000\210\001\001\022\021\n\004name\030\002 \001" +
      "(\tH\001\210\001\001\022\024\n\007species\030\003 \001(\tH\002\210\001\001\022\023\n\006gender\030" +
      "\004 \001(\tH\003\210\001\001\022\023\n\006weight\030\005 \001(\005H\004\210\001\001\022\022\n\005birth" +
      "\030\006 \001(\tH\005\210\001\001\022\026\n\tform_desc\030\007 \001(\tH\006\210\001\001\022\025\n\010o" +
      "wner_id\030\010 \001(\005H\007\210\001\001B\t\n\007_pet_idB\007\n\005_nameB\n" +
      "\n\010_speciesB\t\n\007_genderB\t\n\007_weightB\010\n\006_bir" +
      "thB\014\n\n_form_descB\013\n\t_owner_idB\013\n\t_owner_" +
      "idB\007\n\005_nameB\010\n\006_birthB\014\n\n_telephoneB\n\n\010_" +
      "addressB\021\n\rproto.exampleP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_tutorial_Catalog_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_tutorial_Catalog_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tutorial_Catalog_descriptor,
        new java.lang.String[] { "People", });
    internal_static_tutorial_Catalog_Owner_descriptor =
      internal_static_tutorial_Catalog_descriptor.getNestedTypes().get(0);
    internal_static_tutorial_Catalog_Owner_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tutorial_Catalog_Owner_descriptor,
        new java.lang.String[] { "OwnerId", "Name", "Birth", "Telephone", "Address", "Pets", "OwnerId", "Name", "Birth", "Telephone", "Address", });
    internal_static_tutorial_Catalog_Owner_Pet_descriptor =
      internal_static_tutorial_Catalog_Owner_descriptor.getNestedTypes().get(0);
    internal_static_tutorial_Catalog_Owner_Pet_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tutorial_Catalog_Owner_Pet_descriptor,
        new java.lang.String[] { "PetId", "Name", "Species", "Gender", "Weight", "Birth", "FormDesc", "OwnerId", "PetId", "Name", "Species", "Gender", "Weight", "Birth", "FormDesc", "OwnerId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
