package com.github.attiand.feedreader.printers

import java.io.PrintStream

import com.github.attiand.archive.Entry
import com.github.attiand.feedreader.EntryPrinter
import groovy.xml.*

class XmlPrettyPrinter implements EntryPrinter {

	@Override
	public void print(Entry entry, PrintStream out) {
		entry.getContents().each { content ->
			def writer = new StringWriter()
			XmlUtil.serialize content.getValue(), writer
			out.print(writer.toString())
		}
	}
}
