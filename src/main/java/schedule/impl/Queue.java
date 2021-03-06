package schedule.impl;

import Input.Process;

import java.util.LinkedList;

public class Queue<E extends Process> extends LinkedList<E> {


	public Process SRJ() {
		Process SJ = new Process();
		for (Process e : this) {
			final int i = e.getRemaining_time();
			if (i < SJ.getRemaining_time()) {
				SJ = e;
			}
		}
		remove(SJ);
		return SJ;
	}


	public Process ESJ(Estimator estimator) {
		Process SJ = new Process();
		for (Process e : this) {
			final int i = estimator.estimate_burst(e);
			if (i < estimator.estimate_burst(SJ)) {
				SJ = e;
			}
		}
		remove(SJ);
		return SJ;
	}

}
