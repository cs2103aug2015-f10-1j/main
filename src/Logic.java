import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Logic {
    private static ArrayList<String> taskStored = Storage.retrieveTexts();
    private static ArrayList<String> msgLogger = new ArrayList<String>();
    private static ArrayList<String> event = initList("event", taskStored);
    private static ArrayList<String> deadline = initList("deadline", taskStored);
    private static ArrayList<String> floatingTask = initList("floating", taskStored);
    private static ArrayList<String> repeatedTask = new ArrayList <String>();

    private static ArrayList<Integer> todayTask = new ArrayList <Integer>();
    private static ArrayList<Integer> currentList = new ArrayList <Integer>();
    private static ArrayList<String> searchList = new ArrayList <String>();
    private static CommandHistory history = new CommandHistory(new ArrayList<String>(taskStored));
    private static String currentDate = initDate();

    public static void executeCommand (String userInput) throws Exception{
        msgLogger.add("command : " + userInput);
        Command command = CommandParser.parse(userInput);

        switch (command.getCommandType()){
            case ADD : 
                addTask(command);
                break;
            case REPEAT : 
                addRec(command);
                break;
            case DELETE :
                deleteTask(command);
                break;
            case VIEW :
                viewTask (command);
                break;
            case UPDATE :
                updateTask(command);
                break;
            case SEARCH:
                searchTask(command);
                break;
            case UNDO :
                undoCommand();
                break;
            case REDO :
                redoCommand();
                break;
            case EXIT :
                break;
            default :
                msgLogger.add( "invalid command");
        }

    }

    private static ArrayList<String> initList(String type, ArrayList<String> taskStored) {
        ArrayList<String> list = new ArrayList <String>();
        for (int i = 0; i<taskStored.size(); i++){
            if(taskStored.get(i).contains(type)){
                list.add(taskStored.get(i));
            }
        }
        return list;
    }

    private static String initDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    // ================================================================
    // "Add" command methods
    // ================================================================

    private static void addTask(Command command) throws Exception{
        try{
            String taskType = command.getTaskType();
            String detailStored = "";

            if(taskType.equals("floating")) {
                detailStored = taskType + "#" + command.getTaskDescription();
                floatingTask.add(detailStored);
            } 
            else if(taskType.equals("event")) {
                detailStored = taskType + "#" + command.getTaskEventDate() + "#" + command.getTaskEventTime() + "#" + command.getTaskDescription();
                event.add(detailStored);
            }
            else if(taskType.equals("deadline")) {
                detailStored = taskType + "#" + command.getTaskDeadline() + "#" + command.getTaskDescription();
                deadline.add(detailStored);
            }
            else {
                throw new Exception("Fail to add an invalid task");
            }

            taskStored.add(detailStored);
            //TO SORT??? : sortForAdd();
            Storage.saveToFile(taskStored);
            msgLogger.add("add " + command.getTaskDescription() + " successful!");  
            history.addChangeToHistory(new ArrayList<String>(taskStored));

        }catch (FileNotFoundException e){
            msgLogger.add(e.toString());
        }

    }

    private static void sortForAdd(){
        Collections.sort(taskStored, new Comparator<String>() {
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

    private static void addRec(Command com) throws FileNotFoundException{  
        String detailStored =  com.getTaskRepeatPeriod() +"#" + com.getTaskDescription();
        repeatedTask.add(detailStored);
        Storage.saveToFileRC(repeatedTask);
        msgLogger.add("addrc " + com.getTaskDescription() + " successful!");
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

    public static void printArrayList(){
        for (int i=0; i<taskStored.size(); i++){
            System.out.println(taskStored.get(i));
        }
    }

    // ================================================================
    // "search" command methods
    // ================================================================

    private static void searchTask(Command com) throws FileNotFoundException{
        searchList.clear();
        int index = 1;
        for (int i = 0; i<taskStored.size(); i++){
            if (taskStored.get(i).contains((CharSequence) com.getSearchKeyword())){
                currentList.add(i);
                String[] str = taskStored.get(i).trim().split("#");
                msgLogger.add((index++) + ". " + str[1] + " " + str[0] ) ;
            }
        }

    }

    // ================================================================
    // "Delete" command methods
    // ================================================================

    private static void deleteTask(Command command){
        try{
            String taskType = command.getTaskType();
            int indexToRemove = command.getTaskID() - 1;
            String removedItem = "";
            
            if(taskType.equals("deadline")) {
                removedItem = deadline.remove(indexToRemove);
            }
            else if(taskType.equals("floating")) {
                removedItem = floatingTask.remove(indexToRemove);
            }
            else if(taskType.equals("event")) {
                removedItem = event.remove(indexToRemove);
            }
            
            taskStored.remove(new String(removedItem));
            Storage.saveToFile(taskStored);
            history.addChangeToHistory(new ArrayList<String>(taskStored));
            msgLogger.add( "deleted " + taskType + " index " + command.getTaskID() + " successfully!");

            /*int index = currentList.get(com.getTaskID()-1);
            if (com.getTaskEventDate().equals(currentDate)){
                todayTask.remove(com.getTaskID()-1);
            }*/
        }catch(Exception e){
            msgLogger.add("Invalid index used");
        }

    }

    // ================================================================
    // "View" command methods
    // ================================================================

    private static void viewTask(Command com){

        // taskList = Storage.retrieveTexts();
        for (int i = 0; i<taskStored.size(); i++){
            if (taskStored.get(i).contains(com.getTaskEventDate())){
                currentList.add(i);
                String[] str = taskStored.get(i).trim().split("#");
                event.add(str[1] + " " + str[0] ) ;
            }	
        }
    }

    // ================================================================
    // "Update" command methods
    // ================================================================

    private static void updateTask(Command com) throws FileNotFoundException{

        int taskListIndex = currentList.get(com.getTaskID()-1);
        String[] str = taskStored.get(taskListIndex).trim().split("#");
        str[1] = com.getTaskDescription();
        String updateString = "";
        for (int i=0; i < str.length-1; i++){
            updateString += (str[i] + "#");
        }
        updateString += str[str.length-1];
        taskStored.set(taskListIndex, updateString);
        history.addChangeToHistory(taskStored);
        Storage.saveToFile(taskStored);
        msgLogger.add("task updated!");

    }

    // ================================================================
    // "view today's task" command methods
    // ================================================================
    /*
    private static String viewUpcommingTask(){
    	String message = "Top 10 Upcomming Tasks: \n";
    	int index = 1;
    	taskList = storage.retrieveTexts();

    	for (int i = 0; i<10; i++){
                String[] str = taskList.get(i).trim().split("#");
                message += (index++) + ". " + str[1] + " " + str[0] + "\n" ;
                upcommingTask.add(i);
                currentList.add(i);

        }
    	if(currentList.isEmpty() && upcommingTask.isEmpty()){
    		message += "There is no task need to be finished.";
    	}

        return message;
    }
     */ 

    private static void viewTodayTask(){
        int index = 1;
        taskStored = Storage.retrieveTexts();

        for (int i = 0; i<taskStored.size(); i++){
            if (taskStored.get(i).contains(currentDate)){
                todayTask.add(i);
                currentList.add(i);
                String[] str = taskStored.get(i).trim().split("#");
                event.add( (index++) + ". " + str[1] + " " + str[0] ) ;
            }	
        }
        if(currentList.isEmpty() && todayTask.isEmpty()){
            event.add("There is no task need to be finished.");
        }
    }

    // ================================================================
    // Undo command method
    // ================================================================
    private static void redoCommand() throws FileNotFoundException {
        String message = "";
        try {
            message = "redo successfully";
            taskStored = new ArrayList<String>(history.redo());
            Storage.saveToFile(taskStored);
            event = initList("event", taskStored);
            deadline = initList("deadline", taskStored);
            floatingTask = initList("floating", taskStored);
            msgLogger.add(message);  
        } catch (Exception e) {
            msgLogger.add(e.getMessage());
        }
    }

    // ================================================================
    // Redo command method
    // ================================================================
    private static void undoCommand() throws FileNotFoundException {
        String message = "";
        try {
            message = "undo successfully";
            taskStored = new ArrayList<String>(history.undo());
            Storage.saveToFile(taskStored);
            event = initList("event", taskStored);
            deadline = initList("deadline", taskStored);
            floatingTask = initList("floating", taskStored);
            msgLogger.add(message);
        } catch (Exception e) {
            msgLogger.add(e.getMessage());
        }   
    }

    // ================================================================
    // Getter methods to retrieve lists for UI
    // ================================================================
    public static String getMessageLog(){
        String messageToPrint = "";
        for(int i=0; i<msgLogger.size(); i++) {
            messageToPrint += msgLogger.get(i) + "\n";
        }
        return messageToPrint.trim();
    }

    public static String getEvents(){
        String messageToPrint = "";
        if(deadline.size() == 0) {
            return messageToPrint = "No events";
        }
        for(int i=0; i<event.size(); i++) {
            messageToPrint += "E" + (i+1) + ". "+ event.get(i) + "\n";
        }
        return messageToPrint.trim();
    }

    public static String getDeadline(){
        String messageToPrint = "";
        if(deadline.size() == 0) {
            return messageToPrint = "No tasks with deadline";
        }
        for(int i=0; i<deadline.size(); i++) {
            messageToPrint += "D" + (i+1) + ". "+ deadline.get(i) + "\n";
        }
        return messageToPrint.trim();
    }

    public static String getFloatingTask(){
        String messageToPrint = "";
        if(floatingTask.size() == 0) {
            return messageToPrint = "No tasks without due date";
        }
        for(int i=0; i<floatingTask.size(); i++) {
            messageToPrint += "F" + (i+1) + ". "+ floatingTask.get(i) + "\n";
        }
        return messageToPrint.trim();
    }

}
