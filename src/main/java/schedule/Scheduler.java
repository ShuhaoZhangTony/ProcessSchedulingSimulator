package schedule;

import Input.Process;
import schedule.impl.Schedule;

import java.util.LinkedList;

public abstract class Scheduler {
	public Schedule schedule = new Schedule();
	protected int average_waiting_time = 0;
	protected int current_time = 0;
	protected int waiting_time = 0;

	public abstract int getAverage_waiting_time();

	protected boolean all_finished_process(LinkedList<Process> process_list) {
		for (Process process : process_list) {
			if (!process.finished()) {
				return false;
			}
		}
		return true;
	}

}
