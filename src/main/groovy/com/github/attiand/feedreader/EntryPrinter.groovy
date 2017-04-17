package com.github.attiand.feedreader

import com.github.attiand.feedarchive.Entry

interface EntryPrinter {

	void print(Entry entry, PrintStream out)
}
