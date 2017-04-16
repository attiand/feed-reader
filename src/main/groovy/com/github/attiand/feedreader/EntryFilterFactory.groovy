package com.github.attiand.feedreader

import java.util.function.Predicate
import com.github.attiand.feedreader.internal.XPathEvaluator

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
			XPathEvaluator filter = new XPathEvaluator(arg)
			return { 
				entry -> entry.dom().filter{
					dom-> filter.match(dom)
				}.findFirst().isPresent()
			} as Predicate
		}
		else {
			nop()
		}
	}
}
