package Input;

import util.IO;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ProcessInput {

	public LinkedList<Process> process_list;

	public ProcessInput() {
		try {
			this.process_list = read_input();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}



	public LinkedList<Process> read_input() throws FileNotFoundException {
		LinkedList<Process> input = new LinkedList<Process>();
		Scanner sc = new Scanner(IO.getFileFromURL("testinput.txt"));

		while (sc.hasNext()) {
			final String[] split = sc.nextLine().split(" ");
			input.add(new Process(split));
		}
		return input;
	}


}
