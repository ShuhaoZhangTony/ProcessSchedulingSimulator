package schedule;

import Input.Process;
import Input.ProcessInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import schedule.impl.Queue;

/**
 * First come first service
 */
public class RR extends Scheduler {
	private static final Logger LOG = LoggerFactory.getLogger(RR.class);
	private final Queue<Process> q = new Queue();
	private int last_slice = 1;
	private Process current_process;

	public RR(ProcessInput input, int quantum) {
		do {
			boolean new_process = false;
			//update the queue during the last quantum.
			for (Process process : input.process_list) {
				if (arrive_in_lastSlices(process, current_time, last_slice)) {
					q.addLast(process);
					new_process = true;

				}
			}

			//terminate case
			if (!new_process && q.size() == 0) {
				if (all_finished_process(input.process_list)) {
					LOG.trace("RR finished scheduling for all process!");
					break;
				}
			}

			if (q.size() > 0) {
				//schedule the queue
				final Process process = q.removeFirst();
				if (current_process != null) {
					if (current_process != process) {
						current_process = process;
						schedule.add(current_time, process.id());
					}
				} else {
					current_process = process;
					schedule.add(current_time, process.id());
				}

				final int remaining = process.progress(quantum);
				if (remaining <= 0) {
					LOG.trace("Process finishes early than its assigned quantum expire");
					last_slice = quantum + remaining;
					current_time += last_slice;
					process.finish();
					//clean the queue
					waiting_time += (current_time - process.arrive_time() - process.burst_time());
				} else {
					last_slice = quantum;
					current_time += last_slice;
					q.addLast(process);//haven't finish computing, add to the end of the queue.
				}
			} else {
				last_slice = 1;
				current_time += last_slice;//advance the timeslice to wait for next process
			}
		} while (true);

		average_waiting_time = waiting_time / input.process_list.size();
	}

	private boolean arrive_in_lastSlices(Process process, int current_time, int timeslice) {
//		return process.arrive_time() >= current_time && process.arrive_time() < current_time + quantum;
		return process.arrive_time() <= current_time && process.arrive_time() > current_time - timeslice;
	}
}
