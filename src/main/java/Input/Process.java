package Input;

public class Process {


	int id;
	int arrive_time;
	int burst_time;

	public Process(String[] record) {
		id = Integer.parseInt(record[0]);
		arrive_time = Integer.parseInt(record[1]);
		burst_time = Integer.parseInt(record[2]);

	}

	public int id() {
		return id;
	}

	public int arrive_time() {
		return arrive_time;
	}


	public int burst_time() {
		return burst_time;
	}

	/**
	 *
	 * @param quantum
	 * @return whether this process has finished computation.
	 */
	public boolean setBurst_time(int quantum) {
		return (burst_time -= quantum) <= 0;
	}

	public String toString() {

		return "id:" + id() + "\t:arrive_time:" + arrive_time() + "\tburst_time:" + burst_time();

	}
}
