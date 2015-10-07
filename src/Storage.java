import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
	
	public File taskFile;

	public static ArrayList<String> taskList; 
	
	public File createFile(){
		
		File taskFile = new File("C:\\Users\\user\\Desktop\\taskFile.txt");
		
		return taskFile;
	}
	
	public ArrayList<String> retrieveTexts(File file) {
		try {
			Scanner scanner = new Scanner(taskFile);
			while (scanner.hasNextLine()) {
				taskList.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
		}
		return taskList;
	}
	
	public void saveToFile(ArrayList<String> taskList) throws FileNotFoundException {			
		try {
			File tempFile = new File(taskFile.getAbsolutePath());
			PrintWriter pw = new PrintWriter(tempFile);
			pw.print("");
			pw.close();
			taskFile.delete();
			tempFile.renameTo(taskFile);
			FileWriter fileW = new FileWriter(taskFile);
			BufferedWriter buffW = new BufferedWriter(fileW);
			for (int i = 0; i < taskList.size(); i++) {
				buffW.write(taskList.get(i));
				buffW.newLine();
			}
			buffW.close();
		} catch (IOException e) {
		}
	}

	public void saveToFileRCS(ArrayList<String> rcsTaskList) throws FileNotFoundException {
		try {
			
		} catch (IOException e) {			
		}		
	}

}
