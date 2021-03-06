package schedule;

import Input.Process;
import Input.ProcessInput;

/**
 * First come first service
 */
public class FCFS extends Scheduler {


	public FCFS(ProcessInput input) {
		for (Process process : input.process_list) {
			if (current_time < process.arrive_time()) {
				current_time = process.arrive_time();
			}
			schedule.add(current_time, process.id());
			current_time += process.burst_time();
			waiting_time += (current_time - process.arrive_time() - process.burst_time());
		}
		average_waiting_time = waiting_time / input.process_list.size();
	}



}
