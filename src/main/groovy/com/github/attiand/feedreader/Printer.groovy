package com.github.attiand.feedreader

import com.github.attiand.archive.Entry

interface Printer {

	void print(Entry entry, PrintStream out)
}
