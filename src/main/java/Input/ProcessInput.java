package Input;

import util.IO;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class ProcessInput {

	public LinkedList<Process> process_list;

	public ProcessInput(String file_name) {
		try {
			this.process_list = read_input(file_name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ProcessInput() {
		process_list = new LinkedList<>();
	}

	public ProcessInput(ProcessInput input) {
		process_list = new LinkedList<>();
		for (Process process : input.process_list) {
			process_list.add(new Process(process));
		}
	}

	public ProcessInput all_short_process() {
		int entry = 20;//100 entries
		Random r = new Random();
		int current_time = 0;
		process_list.add(new Process(0, 0, 1));

		for (int i = 0; i < entry; i++) {
			int id = r.nextInt(3);
			int arrival_time = current_time + r.nextInt(2);
			current_time = arrival_time;
			int burst_time = 1 + r.nextInt(4);
			process_list.add(new Process(id, arrival_time, burst_time));
		}
		return this;
	}

	public ProcessInput interleave_process() {
		int entry = 20;//100 entries
		Random r = new Random();
		int current_time = 0;
		process_list.add(new Process(0, 0, 1));

		for (int i = 0; i < entry; i++) {
			int id = r.nextInt(3);
			int arrival_time = current_time + r.nextInt(10);
			current_time = arrival_time;
			int burst_time = 1 + r.nextInt(100);
			process_list.add(new Process(id, arrival_time, burst_time));
		}
		return this;
	}

	private LinkedList<Process> read_input(String file_name) throws FileNotFoundException {
		LinkedList<Process> input = new LinkedList<Process>();
		Scanner sc = new Scanner(IO.getFileFromURL(file_name));

		while (sc.hasNext()) {
			final String[] split = sc.nextLine().split(" ");
			input.add(new Process(split));
		}
		return input;
	}
}
