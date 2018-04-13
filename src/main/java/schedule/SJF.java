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
public class SJF extends Scheduler {
	private static final Logger LOG = LoggerFactory.getLogger(SJF.class);
	private int last_interval = 1;//allow the first task to be added.
	private final Queue<Process> q = new Queue();

	public SJF(ProcessInput input) {

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
					LOG.debug("SJF finished scheduling for all process!");
					break;
				}
			}

			if (!q.isEmpty()) {
				//schedule the queue
				final Process process = q.SJ();//pick the shortest job.

				schedule.add(current_time, process.id());

				waiting_time += (current_time - process.arrive_time());

				last_interval = process.burst_time();

				process.finish();//do the work.

			} else {
				last_interval = 1;//advance time by 1 time slice.
			}
			current_time += last_interval;

		} while (true);
		average_waiting_time = waiting_time / input.process_list.size();
	}

	protected boolean all_finished_process(LinkedList<Process> process_list) {
		for (Process process : process_list) {
			if (process.burst_time() > 0) {
				return false;
			}
		}
		return true;
	}


	private boolean arrive_in_interval(Process process, int current_time) {
		return process.arrive_time() <= current_time && process.arrive_time() > current_time - last_interval;
	}


	public int getAverage_waiting_time() {
		return average_waiting_time;
	}

}
