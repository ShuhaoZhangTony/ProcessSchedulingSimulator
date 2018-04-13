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


	public Process ESJ(Estimator estimator) {
		Process SJ = new Process();
		estimator.update_burst(SJ.id(), Integer.MAX_VALUE);

		for (Process e : this) {
			final int i = estimator.estimate_burst(e.id());
			if (i < estimator.estimate_burst(SJ.id())) {
				SJ = e;
			}
		}
		return SJ;
	}

}
