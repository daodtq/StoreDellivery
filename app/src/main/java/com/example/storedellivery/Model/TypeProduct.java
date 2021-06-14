package com.example.storedellivery.Model;

public class TypeProduct {
    private int TypeID;
    private String TypeName;
    private String TypeNote;

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getTypeNote() {
        return TypeNote;
    }

    public void setTypeNote(String typeNote) {
        TypeNote = typeNote;
    }

    public TypeProduct() {
    }

    public TypeProduct(int typeID, String typeName, String typeNote) {
        TypeID = typeID;
        TypeName = typeName;
        TypeNote = typeNote;
    }
}
