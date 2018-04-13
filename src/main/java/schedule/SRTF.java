package schedule;

import Input.Process;
import Input.ProcessInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import schedule.impl.Queue;

import java.util.LinkedList;

/**
 * Shortest Job First
 */
public class SRTF extends Scheduler {
	private static final Logger LOG = LoggerFactory.getLogger(SRTF.class);
	private final Queue<Process> q = new Queue();

	public SRTF(ProcessInput input) {

		Process current_process = null;
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
			if (new_process) {
				Process pre_process = current_process;
				current_process = q.SJ();//pick the shortest job.
				if (pre_process != current_process) {
					schedule.add(current_time, current_process.id());
				}
			} else if (current_process == null && !q.isEmpty()) {
				current_process = q.SJ();//pick the shortest job.
				schedule.add(current_time, current_process.id());
			}


			if (current_process != null) {
				final int remaining = current_process.progress(1);
				current_time += 1;//advance time by 1 time slice.
				if (remaining == 0) {
					waiting_time += (current_time - current_process.arrive_time() - current_process.burst_time());
					q.remove(current_process);
					current_process = null;
				}
			} else {
				current_time += 1;//advance time by 1 time slice.
			}

		} while (true);
		average_waiting_time = waiting_time / input.process_list.size();
	}



	private boolean arrive_in_interval(Process process, int current_time) {
		return process.arrive_time() <= current_time && process.arrive_time() > current_time - 1;
	}


	public int getAverage_waiting_time() {
		return average_waiting_time;
	}

}
