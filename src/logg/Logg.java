package logg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Logg {
	static Logg logg = null;

	private Logg() {

	}

	public static Logg getLogg() {
		if (logg == null) {
			logg = new Logg();
		}
		return logg;
	}

	public static void addLine(String string) {

	}

	// Writing new event to top of text file
	public void writeData(String string, String path) {
		String s = readData(path);
		try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {

			br.write(string + "\n" + s);
			br.flush();// flushes buffer

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String readData(String path) {
		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			// gets strings from file row by row
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				// puts back new line in the string
				sb.append("\n");
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return sb.toString();
	}

}
