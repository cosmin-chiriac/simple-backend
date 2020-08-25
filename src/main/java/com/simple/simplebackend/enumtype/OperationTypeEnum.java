package com.simple.simplebackend.enumtype;

/**
 * The Enum OperationTypeEnum will contain API operation types
 */
public enum OperationTypeEnum {
  
    CREATE("create"),
    UPDATE("update");

    private final String text;

    OperationTypeEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
    }
