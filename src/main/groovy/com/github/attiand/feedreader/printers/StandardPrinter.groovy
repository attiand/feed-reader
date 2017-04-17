package com.github.attiand.feedreader.printers

import java.io.PrintStream

import groovy.lang.Closure
import com.github.attiand.feedarchive.Entry
import com.github.attiand.feedreader.EntryPrinter

class StandardPrinter implements EntryPrinter {

	@Override
	public void print(Entry entry, PrintStream out) {
		entry.getTitle().ifPresent { t -> out.println(t)}
		entry.getUri().ifPresent { u -> out.println(u)}

		entry.getCategories().stream().each{ category ->
			out.println(category.getName())
		}

		entry.getContents().each { content ->
			out.println(content.getValue())
		}
	}
}
