package util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class IO {

	/**
	 * Make sure the file is in resource.
	 *
	 * @param file_name
	 * @return
	 */
	public static File getFileFromURL(String file_name) {
		URL url = IO.class.getClassLoader().getResource(file_name);
		File file = null;
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			file = new File(url.getPath());
		} finally {
			return file;
		}
	}


	public static File getFileToOutput(String file_name) {
		File dir = new File("src/output");
		dir.mkdir();
		return new File(dir + file_name);
	}
}
