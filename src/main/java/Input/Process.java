package Input;

public class Process {


	final String[] record;

	public Process(String[] record) {
		this.record = record;
	}

	public int id() {
		return Integer.parseInt(record[0]);
	}

	public int arrive_time() {
		return Integer.parseInt(record[1]);
	}


	public int burst_time() {
		return Integer.parseInt(record[2]);
	}

	public String toString() {

		return "id:"+id()+"\t:arrive_time:"+arrive_time()+"\tburst_time:"+ burst_time();

	}
}
