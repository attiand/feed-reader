package com.github.attiand.feedreader

import com.github.attiand.feedarchive.*
import com.github.attiand.feedreader.EntryFilterFactory.Type

def cli = new CliBuilder(usage: 'feedreader [OPTIONS] URL')

cli.with {
	h  longOpt:'help', 'usage information'
	b  longOpt:'backward', 'read the last feed entry from URL and move to the beginning'
	f  longOpt:'format', args:2, argName:'formatter', 'print using specified formatter, available are: XML, ENTRY, XPATH:expr'
	k  longOpt:'unsecure', 'try insecure SSL connection'
	x  longOpt:'xpath', args:2, argName:'expression', 'print only entries with content that match specified xpath 2.0 expression'
	m  longOpt:'match', 'print only the first matching entry'
	o  longOpt:'output', args:2, argName:'file', 'write to specified file instead of stdout'
	'D' args:2, argName:'property=value', valueSeparator:'=', 'sets a system property value'
}

def opt = cli.parse(args)

if(opt.arguments().size() != 1 || !opt.arguments().head() || opt.h) {
	cli.usage()
	return
}

def url = opt.arguments().head()

if(opt.D) {
	opt.Ds.toSpreadMap().each { k, v ->
		System.setProperty(k, v)
	}
}

try {
	def factory = opt.k ? FeedReader.unsecure() : FeedReader.secure()
	def feed = FeedReader.fromUri(url, factory)
	def entries = opt.b ? feed.reverseStream() : feed.stream()
	def filter = opt.x ? EntryFilterFactory.create(Type.XPATH, opt.x) : EntryFilterFactory.nop()
	def out = opt.o ? new PrintStream(opt.o) : System.out
	def printer = opt.f ? EntryPrinterFactory.create(opt.f) : EntryPrinterFactory.standard()

	if(opt.m) {
		entries.filter(filter).findFirst().ifPresent { e ->
			printer.print(e, out)
		}
	}
	else {
		entries.filter(filter).forEach { e ->
			printer.print(e, out)
		}
	}
}
catch (CommandLineException | XPathEvaluationException e) {
	System.err << e.getMessage()
}