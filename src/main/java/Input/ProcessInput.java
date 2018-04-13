package Input;

import util.IO;

import java.io.FileNotFoundException;
import java.util.LinkedList;
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


	public LinkedList<Process> read_input(String file_name) throws FileNotFoundException {
		LinkedList<Process> input = new LinkedList<Process>();
		Scanner sc = new Scanner(IO.getFileFromURL(file_name));

		while (sc.hasNext()) {
			final String[] split = sc.nextLine().split(" ");
			input.add(new Process(split));
		}
		return input;
	}


}
