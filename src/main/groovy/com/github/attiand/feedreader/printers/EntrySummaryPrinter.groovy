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
		LocalDateTime local = LocalDateTime.ofInstant(entry.getUpdatedDate().toInstant(), ZoneId.systemDefault());
		out.print(local.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
		out.print(';')

		out.print(entry.getUri())
		out.print(';')

		out.print(entry.getCategories().stream().map{c -> c.getName()}.collect(Collectors.joining(",")));
		out.println()
	}
}
