package com.github.attiand.feedreader.printers

import java.io.PrintStream

import groovy.lang.Closure
import com.github.attiand.archive.Entry
import com.github.attiand.feedreader.Printer;

class StandardPrinter implements Printer {

	@Override
	public void print(Entry entry, PrintStream out) {
		entry.getContents().each { content ->
			out.println(entry)
		}
	}
}
