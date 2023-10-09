
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

public class myarray {
	private static ArrayList<student> arraylist;
	private static ArrayList<String> filelist;
	private static String activefile;
	public static Preferences prefs;

	// function to update the arrayList from file
	public static void setArrayList() {
		prefs = Preferences.userRoot().node("defaultFile");
		student.extdeadline = LocalDate.parse(myarray.prefs.get("extDeadline", LocalDate.now().getYear() + "-01-23")); // Deadline
																														// for
																														// report
																														// from
																														// external;
																														// 1
																														// represent
																														// month
																														// and
																														// 23
																														// represents
																														// day
																														// of
																														// month
		student.synopdeadline = LocalDate
				.parse(myarray.prefs.get("synopDeadline", LocalDate.now().getYear() + "-02-23")); // Deadline for
																									// Synopsis;
		student.letterdeadline = LocalDate
				.parse(myarray.prefs.get("letterDeadline", LocalDate.now().getYear() + "-03-23"));// Deadline for
																									// Letter;
		student.prog1deadline = LocalDate
				.parse(myarray.prefs.get("prog1Deadline", LocalDate.now().getYear() + "-04-23")); // Deadline for
																									// progress 1;
		student.prog2deadline = LocalDate
				.parse(myarray.prefs.get("prog2Deadline", LocalDate.now().getYear() + "-05-23")); // Deadline for
																									// progress 2;
		student.prog3deadline = LocalDate
				.parse(myarray.prefs.get("prog3Deadline", LocalDate.now().getYear() + "-06-23")); // Deadline for
																									// progress 3;

		// This is for when someone changes active file from settings
		if (activefile != null) {
			readArrayList();

		} else {

			File dir = new File(".");
			String tempfilelist[] = dir.list();
			filelist = new ArrayList<String>();
			for (int i = 0; i < tempfilelist.length; i++) {
				if (tempfilelist[i].endsWith(".db")) {
					filelist.add(tempfilelist[i].substring(0, tempfilelist[i].length() - 3));
				}
			}
			// When no file is found in the disk with .db extension then we create a new
			// file
			if (filelist.size() == 0) {
				String firstfile = "";
				do {
					firstfile = JOptionPane.showInputDialog("Enter File Name", "File");
					// if someone press cancel instead of creating new file
					if (firstfile == null) {
						System.exit(0);
					}
				} while (firstfile.length() == 0);
				File f = new File(firstfile + ".db");
				try {
					f.createNewFile();
					filelist.add(firstfile);
					activefile = firstfile;
					prefs.put("DEFAULT_FILE", firstfile);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error Creating File");
					System.exit(0);
				}
			} else {
				activefile = prefs.get("DEFAULT_FILE", "");
				// unable to get DEFAULT_FILE or DEFAULT_FILE is not present in file system
				if (activefile == "" || !filelist.contains(prefs.get("DEFAULT_FILE", ""))) {
					activefile = filelist.get(0);
				}
			}

			readArrayList();
		}
	}

	public static void readArrayList() {
		arraylist = new ArrayList<student>();
		File f = new File(activefile + ".db");
		// done to avoid EOFException
		if (f.length() <= 8) {
			arraylist = new ArrayList<student>();
		} else
			try (FileInputStream fp = new FileInputStream(activefile + ".db");
					ObjectInputStream ofp = new ObjectInputStream(fp);) {

				arraylist = (ArrayList<student>) ofp.readObject();

			} catch (EOFException e) {
				JOptionPane.showMessageDialog(null, "End of file reached: " + e.getMessage());
			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, e.getStackTrace());
			}
	}

	// function to write the arrayList to file
	public static void writeArrayList() {

		try (FileOutputStream fp = new FileOutputStream(activefile + ".db");
				ObjectOutputStream ofp = new ObjectOutputStream(fp);) {
			ofp.writeObject(arraylist);
		}

		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, " writeArrayList mai error aaya hai");
		}
	}

	public static void setFile(ArrayList<String> file) {
		myarray.filelist = file;
	}

	public static String getFileName() {
		return activefile;
	}

	public static void setFileName(String fileName) {
		myarray.activefile = fileName;
	}

	// function to return the whole arrayList
	public static ArrayList<student> returnlist() {
		return arraylist;
	}

	// function to add an element
	public static void add(student s) {
		arraylist.add(s);
	}

//	public static void randMarks() {
//		int max = 20;
//		int min = 10;
//		int range = max - min + 1;
//		for (int i = 0; i < arraylist.size(); i++) {
//			arraylist.get(i).setExt((int) (Math.random() * range) + min);
//			arraylist.get(i).setLetter((int) (Math.random() * range) + min);
//			arraylist.get(i).setSnop((int) (Math.random() * range) + min);
//			arraylist.get(i).setProg1((int) (Math.random() * range) + min);
//			arraylist.get(i).setProg2((int) (Math.random() * range) + min);
//			arraylist.get(i).setProg3((int) (Math.random() * range) + min);
//		}
//		writeArrayList();
//	}

	// function to search the arrayList
	public static int searchByName(String name) {
		boolean found = false;
		int index = 0;
		for (; index < arraylist.size(); index++) {
			student s = arraylist.get(index);
			if (s.getName().equalsIgnoreCase(name)) {
				found = true;
				break;
			}
		}
		if (found == false) {
			index = -1;
		}
		return index;
	}

	// function to search by roll
	public static int searchByRoll(int roll) {
		boolean found = false;
		int index = 0;
		for (; index < arraylist.size(); index++) {
			student s = arraylist.get(index);
			if (s.getRoll() == roll) {
				found = true;
				break;
			}
		}
		if (found == false) {
			index = -1;
		}
		return index;
	}

	// function to delete an element of ArrayList
	public static void delete(int i) {

		arraylist.remove(i);
		writeArrayList();
	}

	public static void replace(int index, student s) {
		arraylist.set(index, s);
	}

	public static ArrayList<String> getFile() {
		return filelist;
	}

}
