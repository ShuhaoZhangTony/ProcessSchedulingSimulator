package Input;

public class Process {


	int id;
	int arrive_time;
	final int burst_time;

	public int getRemaining_time() {
		return remaining_time;
	}

	int remaining_time;




	private int iteration=0;

	public Process(String[] record) {
		id = Integer.parseInt(record[0]);
		arrive_time = Integer.parseInt(record[1]);
		burst_time = Integer.parseInt(record[2]);
		remaining_time = burst_time;
	}

	public Process() {
		id = -1;
		arrive_time = -1;
		burst_time = Integer.MAX_VALUE;
		remaining_time = burst_time;
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
	 * @param quantum
	 * @return whether this process has finished computation.
	 */
	public int progress(int quantum) {
		remaining_time -= quantum;
		return remaining_time;
	}

	public String toString() {

		return "id:" + id() + "\t:arrive_time:" + arrive_time() + "\tburst_time:" + burst_time() + "\tremaining time:" + remaining_time;

	}

	public void finish() {
		remaining_time = 0;

	}

	public boolean finished() {
		return remaining_time <= 0;
	}
}
