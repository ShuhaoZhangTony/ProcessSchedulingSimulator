package schedule;

import Input.Process;
import Input.ProcessInput;

/**
 * First come first service
 */
public class FCFS implements Scheduler  {

	int average_waiting_time = 0;
	int current_time = 0;
	int waiting_time = 0;

	public FCFS(ProcessInput input) {
		for (Process process : input.process_list) {
			if (current_time < process.arrive_time()) {
				current_time = process.arrive_time();
			}
			schedule.add(current_time , process.id());
			waiting_time += (current_time - process.arrive_time());
			current_time += process.burst_time();
		}
		average_waiting_time = waiting_time / input.process_list.size();
	}

	public int getAverage_waiting_time(){
		return average_waiting_time;
	}

	public void output() {

	}
}
