package schedule;

public interface Scheduler {
	public Schedule schedule = new Schedule();

	int getAverage_waiting_time();

	void output();
}
