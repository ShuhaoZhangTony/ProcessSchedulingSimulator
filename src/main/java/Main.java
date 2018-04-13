import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;


public class Main {
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void configLOG(org.slf4j.Logger LOG) {
		LogManager.getLogger(LOG.getName()).setLevel(Level.INFO);

	}

	public static void main(String[] args) throws FileNotFoundException {
		configLOG(LOG);
		LinkedList<String[]> process_list = new Input().read_input();
		LOG.info("printing input ----");
		for (String[] process : process_list) {
			LOG.info(Arrays.toString(process));
		}
	}

}
