package com.github.attiand.feedreader

import com.github.attiand.feedreader.printers.StandardPrinter
import com.github.attiand.feedreader.printers.XmlPrettyPrinter
import com.github.attiand.feedreader.printers.EntryPrinter

class PrinterFactory {

	enum Type{
		XML, ENTRY
	}

	static standard() {
		new StandardPrinter()
	}

	static Printer create(String type) {
		try {
			create(type.toUpperCase() as Type)
		}
		catch (IllegalArgumentException e) {
			throw new CommandLineException("No such formatter: $type")
		}
	}

	static Printer create(Type type) {
		if(type == Type.XML) {
			new XmlPrettyPrinter()
		}
		else if(type == Type.ENTRY) {
			new EntryPrinter()
		}
		else {
			standard()
		}
	}
}
