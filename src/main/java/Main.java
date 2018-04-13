import Input.Process;
import Input.ProcessInput;
import Output.ScheduleOutput;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import schedule.FCFS;
import schedule.RR;
import schedule.Scheduler;


public class Main {
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void configLOG(org.slf4j.Logger LOG) {
		LogManager.getLogger(LOG.getName()).setLevel(Level.INFO);
	}

	public static void main(String[] args) {
		configLOG(LOG);
		ProcessInput input = new ProcessInput("input.txt");
		LOG.info("printing input ----");
		for (Process process : input.process_list) {
			LOG.info(process.toString());
		}

		LOG.info("simulating FCFS ----");
		Scheduler scheduler = new FCFS(input);
		ScheduleOutput output = new ScheduleOutput(scheduler);
		LOG.info("printing output ---- (current time, process id)");
		output.write_output("FCFS.txt");

		LOG.info("simulating RR ----");
		scheduler = new RR(input, 1);
		output = new ScheduleOutput(scheduler);
		LOG.info("printing output ---- (current time, process id)");
		output.write_output("RR.txt");
	}
}
