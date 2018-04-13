package schedule.impl;

import java.util.HashMap;

public class Estimator {

	private HashMap<Integer, Integer> process_burst = new HashMap<>();
	private HashMap<Integer, Integer> estimate_burst = new HashMap<>();
	private final int initial_guess = 5;

	private final double a = 0.5;

	private int get_preburst(int pid) {
		return process_burst.getOrDefault(pid, initial_guess);
	}

	private int get_preestimate(int pid) {
		return estimate_burst.getOrDefault(pid, initial_guess);
	}

	public void update_burst(int pid, int burst) {
		process_burst.put(pid, burst);
	}

	public int estimate_burst(int pid) {
		final int estimate = (int) (a * get_preburst(pid) + (1 - a) * get_preestimate(pid));
		estimate_burst.put(pid, estimate);
		return estimate;
	}
}
