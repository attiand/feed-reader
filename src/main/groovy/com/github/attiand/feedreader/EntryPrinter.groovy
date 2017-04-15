package com.github.attiand.feedreader

import com.github.attiand.archive.Entry

interface EntryPrinter {

	void print(Entry entry, PrintStream out)
}
