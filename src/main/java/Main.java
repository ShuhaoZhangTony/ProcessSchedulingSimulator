import Input.Process;
import Input.ProcessInput;
import Output.ScheduleOutput;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import schedule.FCFS;
import schedule.SJF;
import schedule.SRTF;
import schedule.Scheduler;
import schedule.rr.RR;


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
		LOG.info("printing input ----");
		for (Process process : input.process_list) {
			LOG.info(process.toString());
		}
		Scheduler scheduler;
		ScheduleOutput output;

		input = new ProcessInput("input.txt");
//		LOG.info("simulating FCFS ----");
//		scheduler = new FCFS(input);
//		output = new ScheduleOutput(scheduler);
//		LOG.info("printing output ---- (current time, process id)");
//		output.write_output("FCFS.txt");

		input = new ProcessInput("input.txt");
		LOG.info("simulating RR ----");
		scheduler = new RR( input, 1);
		output = new ScheduleOutput(scheduler);
		LOG.info("printing output ---- (current time, process id)");
		output.write_output("RR.txt");

		input = new ProcessInput("input.txt");
		LOG.info("simulating SRTF ----");
		scheduler = new SRTF(input);
		output = new ScheduleOutput(scheduler);
		LOG.info("printing output ---- (current time, process id)");
		output.write_output("SRTF.txt");
	}
}
