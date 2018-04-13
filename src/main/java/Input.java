import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

public class Input {
	private File getFileFromURL() {
		URL url = this.getClass().getClassLoader().getResource("input.txt");
		File file = null;
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			file = new File(url.getPath());
		} finally {
			return file;
		}
	}

	public LinkedList<String[]> read_input() throws FileNotFoundException {
		LinkedList<String[]> input = new LinkedList<String[]>();
		Scanner sc = new Scanner(getFileFromURL());

		while (sc.hasNext()) {
			final String[] split = sc.nextLine().split(" ");
			input.add(split);
		}
		return input;
	}


}
