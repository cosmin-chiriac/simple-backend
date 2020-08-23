package com.simple.simplebackend.enumtype;

/**
 * The Enum OperationTypeEnum will contain API operation types
 */
public enum OperationTypeEnum {
	/* @formatter:off */
	
	CREATE("create"), 
	UPDATE("update"),
	REAUTHORIZATION("reauthorization");
	
	/* @formatter:on */

	private final String text;

	OperationTypeEnum(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
