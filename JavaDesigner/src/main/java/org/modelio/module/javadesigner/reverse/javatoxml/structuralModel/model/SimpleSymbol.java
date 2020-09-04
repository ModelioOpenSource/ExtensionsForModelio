package org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model;

public class SimpleSymbol implements Symbol {
	private String name;
	
	public SimpleSymbol(final String aName) {
		this.name = aName;
	}
	@Override
	public String getName() {
		return this.name;
	}

}
