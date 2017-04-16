package com.github.attiand.feedreader.printers

import java.io.PrintStream

import com.github.attiand.archive.Entry
import com.github.attiand.feedreader.EntryPrinter
import com.github.attiand.feedreader.internal.XPathEvaluator

class XPathPrinter implements EntryPrinter {

	final XPathEvaluator evaluator;

	public XPathPrinter(String expression) {
		evaluator = new XPathEvaluator(expression)
	}

	@Override
	public void print(Entry entry, PrintStream out) {
		entry.dom().each { dom ->
			out.println(evaluator.value(dom))
		}
	}
}
