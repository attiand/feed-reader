package com.github.attiand.feedreader.printers

import java.io.PrintStream
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

import groovy.lang.Closure
import com.github.attiand.archive.Entry
import com.github.attiand.feedreader.EntryPrinter;

class EntrySummaryPrinter implements EntryPrinter {

	@Override
	public void print(Entry entry, PrintStream out) {
		entry.getUpdatedDate().ifPresent { d ->
			LocalDateTime local = LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault());
			out.print(local.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
			out.print(';')
		}

		entry.getUri().ifPresent { u ->
			out.print(u)
			out.print(';')
		}

		if(! entry.getCategories().isEmpty()) {
			out.print(entry.getCategories().stream().map{c -> c.getName()}.collect(Collectors.joining(",")));
		}

		out.println()
	}
}
