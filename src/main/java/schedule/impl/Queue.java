package schedule.impl;

import Input.Process;

import java.util.LinkedList;

public class Queue<E extends Process> extends LinkedList<E> {

	public Process SJ() {
		Process SJ = new Process();
		for (Process e : this) {
			final int i = e.burst_time();
			if (i < SJ.burst_time()) {
				SJ = e;
			}
		}
		return SJ;
	}
}
