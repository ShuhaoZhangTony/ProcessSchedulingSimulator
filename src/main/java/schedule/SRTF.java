package schedule;

import Input.Process;
import Input.ProcessInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import schedule.impl.Queue;

/**
 * Shortest remaining task first.
 */
public class SRTF extends Scheduler {
	private static final Logger LOG = LoggerFactory.getLogger(SRTF.class);
	private final Queue<Process> q = new Queue();
	Process current_process = null;

	public SRTF(ProcessInput input) {

		do {
			boolean new_process = false;
			//update the queue during the last interval.
			for (Process process : input.process_list) {
				if (arrive_in_interval(process, current_time)) {
					q.addLast(process);
					new_process = true;

				}
			}

			//terminate case
			if (!new_process && q.size() == 0) {
				if (all_finished_process(input.process_list)) {
					LOG.debug("SRTF finished scheduling for all process!");
					break;
				}
			}

			//schedule
			if (q.size() > 0) {
				//schedule the queue
				final Process process = q.SRJ();
				if (current_process != null) {
					if (current_process != process) {
						current_process = process;
						schedule.add(current_time, process.id());
					}
				} else {
					current_process = process;
					schedule.add(current_time, process.id());
				}
				final int remaining = process.progress(1);
				if (remaining == 0) {
					current_time += 1;
					process.finish();
					//clean the queue
					waiting_time += (current_time - process.arrive_time() - process.burst_time());
				} else {
					current_time += 1;
					q.addLast(process);//haven't finish computing, add to the end of the queue.
				}
			} else {
				current_time += 1;//advance the timeslice to wait for next process
			}
		} while (true);
		average_waiting_time = waiting_time / input.process_list.size();
	}


	private boolean arrive_in_interval(Process process, int current_time) {
		return process.arrive_time() <= current_time && process.arrive_time() > current_time - 1;
	}

}
