package com.github.attiand.feedreader

import com.github.attiand.feedreader.printers.StandardPrinter
import com.github.attiand.feedreader.printers.XPathPrinter
import com.github.attiand.feedreader.printers.XmlPrettyPrinter
import com.github.attiand.feedreader.printers.EntrySummaryPrinter

import java.util.ResourceBundle.NoFallbackControl

class EntryPrinterFactory {

	enum Type{
		XML, ENTRY, XPATH
	}

	static standard() {
		new StandardPrinter()
	}

	static EntryPrinter create(String type) {
		def args = type.split(/:/, 2)
		def name = args[0]

		try {
			create(name.toUpperCase() as Type, args.length == 2 ? Optional.of(args[1]) : Optional.empty())
		}
		catch (IllegalArgumentException e) {
			throw new CommandLineException("No such formatter: $name")
		}
	}

	static EntryPrinter create(Type type, Optional<String> arg) {
		if(type == Type.XML) {
			new XmlPrettyPrinter()
		}
		else if(type == Type.XPATH) {
			arg.orElseThrow{new CommandLineException("No XPath expression specified")}
			new XPathPrinter(arg.get())
		}
		else if(type == Type.ENTRY) {
			new EntrySummaryPrinter()
		}
		else {
			standard()
		}
	}
}
