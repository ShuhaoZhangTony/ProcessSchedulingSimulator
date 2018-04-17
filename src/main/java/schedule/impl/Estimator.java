package schedule.impl;

import Input.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class Estimator {
	private static final Logger LOG = LoggerFactory.getLogger(Estimator.class);

	private HashMap<Integer, Integer> process_burst = new HashMap<>();
	private HashMap<Integer, Integer> estimate_burst = new HashMap<>();
	private final int initial_guess = 5;

	private final double a;

	public Estimator() {
		a = 0.5;
	}

	public Estimator(double i) {
		a = i;
	}

	private int get_preburst(int pid) {
		return process_burst.getOrDefault(pid, initial_guess);
	}

	private int get_preestimate(int pid) {
		return estimate_burst.getOrDefault(pid, initial_guess);
	}

	public void update_burst(int pid, int burst) {
		process_burst.put(pid, burst);
		//LOG.info("update burst of:" + pid + " to: " + burst);
	}

	public int estimate_burst(Process process) {
		final int id = process.id();
		if (id == -1) {
			return Integer.MAX_VALUE;
		}
		int estimate = (int) (a * get_preburst(id) + (1 - a) * get_preestimate(id));
//		if (id == 0) {
//			LOG.info("Estimated burst of pid:" + id + " is " + estimate + ", actual is:\t" + process.burst_time());
//		}
		estimate_burst.put(id, estimate);
		return estimate;
	}
}
