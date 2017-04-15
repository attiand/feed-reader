package com.github.attiand.feedreader

import java.util.function.Predicate
import com.github.attiand.feedreader.filters.XPathFilter

import com.github.attiand.feedreader.EntryPrinterFactory.Type;

class EntryFilterFactory {

	enum Type{
		XPATH
	}

	static Predicate nop() {
		return { true } as Predicate
	}

	static Predicate create(String type, String arg) {
		try {
			create(type.toUpperCase() as Type)
		}
		catch (IllegalArgumentException e) {
			throw new CommandLineException("No such filter: $type")
		}
	}

	static Predicate create(Type type, String arg) {
		if(type == Type.XPATH) {
			XPathFilter filter = new XPathFilter(arg)
			return { 
				entry -> entry.dom().filter{
					dom-> filter.elements(dom)
				}.findFirst().isPresent()
			} as Predicate
		}
		else {
			nop()
		}
	}
}
