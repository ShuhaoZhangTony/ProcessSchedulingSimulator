package schedule;

public abstract class Scheduler {
	public Schedule schedule = new Schedule();
	int average_waiting_time = 0;
	int current_time = 0;
	int waiting_time = 0;

	public abstract int getAverage_waiting_time();

	abstract void output();
}
