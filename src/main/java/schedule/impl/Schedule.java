package schedule.impl;

import schedule.impl.Record;

import java.util.Iterator;
import java.util.LinkedList;

public class Schedule implements Iterable<Record> {
	LinkedList<Record> records = new LinkedList();

	public void add(int current_time, int id) {
		records.add(new Record(current_time, id));
	}

	public Iterator<Record> iterator() {
		return records.iterator();
	}
}
