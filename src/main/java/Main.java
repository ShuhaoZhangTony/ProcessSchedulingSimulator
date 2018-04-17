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


//		ProcessInput input = new ProcessInput("input.txt");

		ProcessInput input = new ProcessInput().interleave_process();


		LOG.info("printing input ----");
		for (Process process : input.process_list) {
			LOG.info(process.toString());
		}
		Scheduler scheduler = null;
		ScheduleOutput output;

//		input = new ProcessInput("input.txt");
		ProcessInput FCFS_input = new ProcessInput(input);
		LOG.info("simulating FCFS ----");
		scheduler = new FCFS(FCFS_input);
		output = new ScheduleOutput(scheduler);
		LOG.info("printing output ---- (current time, process id)");
		output.write_output("FCFS.txt");


		LOG.info("simulating RR ----");
		for (int i = 1; i < 100; i += 2) {
			ProcessInput RR_input = new ProcessInput(input);
			scheduler = new RR(RR_input, i);
//			output = new ScheduleOutput(scheduler);
//			LOG.info("printing output ---- (current time, process id)");
//			output.write_output("RR.txt");
			final int average_waiting_time = scheduler.getAverage_waiting_time();
			LOG.info("quantum:\t" + i + "\t average waiting time:\t" + average_waiting_time);
		}
		output = new ScheduleOutput(scheduler);
		output.write_output("RR.txt");

		input = new ProcessInput("input.txt");
		ProcessInput SRTF_input = new ProcessInput(input);
		LOG.info("simulating SRTF ----");
		scheduler = new SRTF(SRTF_input);
		output = new ScheduleOutput(scheduler);
		LOG.info("printing output ---- (current time, process id)");
		output.write_output("SRTF.txt");

		LOG.info("simulating SJF ----");
		for (double i = 0.1; i <= 1; i += 0.05) {
//			input = new ProcessInput("input.txt");
			ProcessInput SJF_input = new ProcessInput(input);
			scheduler = new SJF(SJF_input, i);
			final int average_waiting_time = scheduler.getAverage_waiting_time();
			LOG.info("a:\t" + i + "\t average waiting time:\t" + average_waiting_time);
//			output = new ScheduleOutput(scheduler);
//			LOG.info("printing output ---- (current time, process id)");
//			output.write_output("SJF.txt");
		}
	}
}
