package com.github.attiand.feedreader.printers

import java.io.PrintStream

import groovy.lang.Closure
import com.github.attiand.archive.Entry
import com.github.attiand.feedreader.EntryPrinter

class StandardPrinter implements EntryPrinter {

	@Override
	public void print(Entry entry, PrintStream out) {
		printIfNotNull(out, entry.getTitle())
		printIfNotNull(out, entry.getUri())

		entry.getCategories().stream().each{ category ->
			out.println(category.getName())
		}

		entry.getContents().each { content ->
			out.println(content.getValue())
		}
	}

	private void printIfNotNull(def out, def content) {
		if(content) {
			out.println(content)
		}
	}
}
