import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Logic {
    private static CommandParser commandParser;
    private static ArrayList <String> taskList ;
    private static ArrayList <String> recList;
    private static ArrayList <Integer> currentList;
    private static Storage storage; 

    public Logic (){
	commandParser = new CommandParser();
	taskList = new ArrayList <String>();
	recList = new ArrayList <String>();
	storage = new Storage();
	currentList = new ArrayList <Integer>();
    }

    public static String executeCommand (String userInput) throws FileNotFoundException{
	String displayMessage = "";
	storage.createFile();
	taskList = storage.retrieveTexts();

	Command command = commandParser.parse(userInput);
	switch (command.getCommandType()){
	case ADD : 
	    displayMessage = addTask(command);
	    break;
	case ADDRECURRENCE : 
	    displayMessage = addRec(command);
	    break;
	case DELETE :
	    displayMessage = deleteTask(command);
	    break;
	case VIEW :
	    displayMessage = viewTask (command);
	    break;
	case UPDATE :
	    displayMessage = updateTask(command);
	    break;
	    // case SEARCH:
		//	displayMessage = searchTask(command);
		//	break;
	    case EXIT :
		break;
	    default :
		// return Command.Type.INVALID;
	}
	
	taskList.clear();
	return displayMessage;

    }

    // ================================================================
    // "Add" command methods
    // ================================================================

    private static String addTask(Command com) throws FileNotFoundException{
	String message;
	String detailStored = com.getTaskTime() + "#" + com.getTaskTitle();
	taskList.add(detailStored);
	sortForAdd();
	storage.saveToFile(taskList); 
	message = "add " + com.getTaskTitle() + " successful!";
	return message;

    }

    public static void sortForAdd(){
	Collections.sort(taskList, new Comparator<String>() {
	    DateFormat f = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	    @Override
	    public int compare(String o1, String o2) {
		try {
		    return f.parse(o1).compareTo(f.parse(o2));
		} catch (ParseException e) {
		    throw new IllegalArgumentException(e);
		}
	    }
	});
    }

    // ================================================================
    // "Addrc" command methods
    // ================================================================

    private static String addRec(Command com) throws FileNotFoundException{
	String message;
	storage = new Storage();     
	String detailStored =  com.getRecurringPeriod() +"#" + com.getTaskTitle();
	recList.add(detailStored);
	storage.saveToFileRC(recList);
	message =  "addrc " + com.getTaskTitle() + " successful!";
	return message;

    }
    /* 
    public static void sortForRec(){
    	Collections.sort(inputList, new Comparator<String>() {
            DateFormat f = new SimpleDateFormat("EEE");
            @Override
            public int compare(String o1, String o2) {
                try {
                    return f.parse(o1).compareTo(f.parse(o2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
    }
     */
    public static void printArrayList (){
	for (int i=0; i<taskList.size(); i++){
	    System.out.println(taskList.get(i));
	}
    }


    // ================================================================
    // "Delete" command methods
    // ================================================================

    public static String deleteTask(Command com){
	String message = "";
	try{
	    int index = currentList.get(com.getTaskNumber()-1);
	    taskList.remove(index);
	    currentList.remove(com.getTaskNumber()-1);
	    storage.saveToFile(taskList); 
	    message = "delete "+com.getTaskNumber() + " successful!";
	}catch(Exception e){
	    message = "Error. Invalid task number";
	}
	return message;
    }

    // ================================================================
    // "View" command methods
    // ================================================================

    public static String viewTask(Command com){
	String message = "";
	int index = 1;

	//	taskList = Storage.retrieveTexts();
	for (int i=0; i<taskList.size(); i++){
	    if (taskList.get(i).contains(com.getTaskTime())){
		currentList.add(i);
		String[] str = taskList.get(i).trim().split("#");
		message += (index++) + ". " + str[1] + "\n";
	    }	
	}

	return message;
    }
    
 // ================================================================
    // "Update" command methods
    // ================================================================
    
    public static String updateTask(Command com) throws FileNotFoundException{
    	String message = "";
    	int taskListIndex = currentList.get(com.getTaskNumber()-1);
    	String[] str = taskList.get(taskListIndex).trim().split("#");
    	str[1] = com.getTaskTitle();
    	String updateString = "";
    	for (int i=0; i < str.length-1; i++){
    	    updateString += (str[i] + "#");
    	}
    	updateString += str[str.length-1];
    	taskList.set(taskListIndex, updateString);
    	
    	storage.saveToFile(taskList);
    	return message;
    }


    // ================================================================
    // "show to user" command methods
    // ================================================================
    private static String showToUser(String message){
	//   System.out.println( message);
	return message;
    }
}

class LogicTest {
    public static void main(String[] arg) throws FileNotFoundException {
	Logic logic = new Logic();
	//logic.executeCommand("add <meeting with team-mates> <09/10/2015 18:00>");
	//logic.executeCommand("add <testing program component> <09/10/2015 14:00>");
	//logic.executeCommand("add <meeting with team-mates for integration> <08/10/2015 18:00>");
	//logic.printArrayList();
	System.out.print(logic.executeCommand("view <09/10/2015>"));
	//System.out.print(logic.executeCommand("update <2> <UPDATED> <09/10/2015>"));
	//System.out.print(logic.executeCommand("view <09/10/2015>"));
	System.out.print(logic.executeCommand("delete <2> <09/10/2015>"));
	System.out.print(logic.executeCommand("view <09/10/2015>"));
	System.out.println("End of Test");
    }

}
