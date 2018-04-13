package Output;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import schedule.Record;
import schedule.Scheduler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScheduleOutput {
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleOutput.class);

	private final Scheduler scheduler;

	public ScheduleOutput(Scheduler scheduler) {

		this.scheduler = scheduler;
	}

	public void write_output(String file_name) {

		File dir = new File("output");
		dir.mkdir();

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(dir + "/" + file_name));
			bw.write("printing output ---- (current time, process id)\n");
			for (Record record : scheduler.schedule) {
				bw.write("(" + record.getCurrent_time() + "," + record.getId() + ")\n");
				LOG.info("(" + record.getCurrent_time() + "," + record.getId() + ")");
			}
			bw.write("average waiting time:" + scheduler.getAverage_waiting_time());
			LOG.info("average waiting time:" + scheduler.getAverage_waiting_time());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
