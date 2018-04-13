package schedule;

public class Record {
	public int getCurrent_time() {
		return current_time;
	}

	public int getId() {
		return id;
	}

	final int current_time;
	final int id;

	Record(int current_time, int id) {
		this.current_time = current_time;
		this.id = id;
	}
}
