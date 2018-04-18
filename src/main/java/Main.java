import Input.Process;
import Input.ProcessInput;
import Output.ScheduleOutput;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import schedule.*;


public class Main {
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void configLOG(org.slf4j.Logger LOG) {
		LogManager.getLogger(LOG.getName()).setLevel(Level.INFO);
	}

	public static void main(String[] args) {
		configLOG(LOG);


		/**
		 * 	Arrival Time:       Time at which the process arrives in the ready queue.
		 *	Completion Time:    Time at which process completes its execution.
		 *	Burst Time:         Time required by a process for CPU execution.
		 *	Turn Around Time:   Time Difference between completion time and arrival time.
		 *	Turn Around Time = Completion Time - Arrival Time

		 *	Waiting Time(W.T): Time Difference between turn around time and burst time.
		 *	Waiting Time = Turn Around Time - Burst Time
		 */


		ProcessInput input = new ProcessInput("input.txt");


		double fcfs_ratio = 0;
		double rr_ratio = 0;
		double sjf_ratio = 0;
		int repeat;
		for (repeat = 0; repeat < 1; repeat++) {
//			ProcessInput input = new ProcessInput().interleave_process();

//			LOG.info("printing input ----");
//			for (Process process : input.process_list) {
//				LOG.info(process.toString());
//			}
			Scheduler scheduler = null;
			ScheduleOutput output;


			ProcessInput SRTF_input = new ProcessInput(input);
			final int waiting_time_SRTF;
//			LOG.info("simulating SRTF ----");
			scheduler = new SRTF(SRTF_input);
//			output = new ScheduleOutput(scheduler);
//		LOG.info("printing output ---- (current time, process id)");
//		output.write_output("SRTF.txt");

			waiting_time_SRTF = scheduler.getAverage_waiting_time();

			ProcessInput FCFS_input = new ProcessInput(input);
//			LOG.info("simulating FCFS ----");
			int min_waiting_FCFS;
			scheduler = new FCFS(FCFS_input);
			min_waiting_FCFS = scheduler.getAverage_waiting_time();
//		output = new ScheduleOutput(scheduler);
//		LOG.info("printing output ---- (current time, process id)");
//		output.write_output("FCFS.txt");


//			LOG.info("simulating RR ----");
			int min_waiting_RR = Integer.MAX_VALUE;
			for (int i = 1; i < 100; i += 2) {
				ProcessInput RR_input = new ProcessInput(input);
				scheduler = new RR(RR_input, i);
//			output = new ScheduleOutput(scheduler);
//			LOG.info("printing output ---- (current time, process id)");
//			output.write_output("RR.txt");
				final int average_waiting_time = scheduler.getAverage_waiting_time();
				if (average_waiting_time < min_waiting_RR) {
					min_waiting_RR = average_waiting_time;
				}
//				LOG.info("quantum:\t" + i + "\t average waiting time:\t" + average_waiting_time);
			}
//			output = new ScheduleOutput(scheduler);
//			output.write_output("RR.txt");

//			LOG.info("simulating SJF ----");
			int min_waiting_SFJ = Integer.MAX_VALUE;
			for (double i = 0.1; i <= 1; i += 0.05) {
//			input = new ProcessInput("input.txt");
				ProcessInput SJF_input = new ProcessInput(input);
				scheduler = new SJF(SJF_input, i);
				final int average_waiting_time = scheduler.getAverage_waiting_time();
				if (average_waiting_time < min_waiting_SFJ) {
					min_waiting_SFJ = average_waiting_time;
				}
//			LOG.info("a:\t" + i + "\t average waiting time:\t" + average_waiting_time);
			output = new ScheduleOutput(scheduler);
			LOG.info("printing output ---- (current time, process id)");
			output.write_output("SJF.txt");
			}
			fcfs_ratio += (double) min_waiting_FCFS / waiting_time_SRTF;
			rr_ratio += (double) min_waiting_RR / waiting_time_SRTF;
			sjf_ratio += (double) min_waiting_SFJ / waiting_time_SRTF;
//			LOG.info("FCFS ratio:" + (double) min_waiting_FCFS / waiting_time_SRTF);
//			LOG.info("RR ratio:" + (double) min_waiting_RR / waiting_time_SRTF);
//			LOG.info("SJF ratio:" + (double) min_waiting_SFJ / waiting_time_SRTF);
		}
//		LOG.info("FCFS ratio:" + fcfs_ratio / repeat);
//		LOG.info("RR ratio:" + rr_ratio / repeat);
//		LOG.info("SJF ratio:" + sjf_ratio / repeat);
	}
}
