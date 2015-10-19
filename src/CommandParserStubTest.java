
/**
 * CommandParserStubTest checks if the CommandParser returns the correct Command object
 * by using stubs command.
 * 
 * @author Bobby Lin
 *
 */

public class CommandParserStubTest {

    public static void main(String[] args) throws Exception {

	// invalid command      
	try {
	    Command invalid = CommandParser.parse("This is an invalid command");
	    System.out.println(invalid.getCommandType());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// exit
	try {
	    Command exit = CommandParser.parse("exit");
	    System.out.println(exit.getCommandType());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// undo
	try {
	    Command undo = CommandParser.parse("undo");
	    System.out.println(undo.getCommandType());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// redo
	try {
	    Command redo = CommandParser.parse("redo");
	    System.out.println(redo.getCommandType());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// complete task
	try {
	    Command complete = CommandParser.parse("complete D1");
	    System.out.println(complete.getCommandType() + " " + complete.getTaskType() + " " + complete.getTaskID());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// complete task
	try {
	    Command complete = CommandParser.parse("complete F1");
	    System.out.println(complete.getCommandType() + " " + complete.getTaskType() + " " + complete.getTaskID());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// complete task
	try {
	    Command complete = CommandParser.parse("complete E1");
	    System.out.println(complete.getCommandType() + " " + complete.getTaskType() + " " + complete.getTaskID());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// search task with keywords
	try {
	    Command search = CommandParser.parse("search meeting NUS");
	    String keywords = "";
	    for(int index = 0 ; index < search.getSearchKeyword().size(); index++) {
		keywords = keywords + search.getSearchKeyword().get(index) + " - ";
	    }
	    System.out.println(search.getCommandType() + " " + keywords);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Adding event task
	try {
	    Command add = CommandParser.parse("add Meeting with Boss>>09/10/2015>>13:00-14:00");
	    System.out.println(add.getCommandType() + " " + add.getTaskType() +" " + add.getTaskDescription() + " " + add.getTaskEventDate() + " " + add.getTaskEventTime());
	}catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Adding deadline task
	try {
	    Command add = CommandParser.parse("add Meeting with Boss>>09/10/2015 12:00");
	    System.out.println(add.getCommandType() + " " + add.getTaskType() +" " + add.getTaskDescription() + " " + add.getTaskDeadline());
	}catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Adding floating task
	try {
	    Command add = CommandParser.parse("add Meeting with Boss");
	    System.out.println(add.getCommandType() + " " + add.getTaskType() +" " + add.getTaskDescription());
	}catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Deleting deadline task
	try {
	    Command delete = CommandParser.parse("delete D1");
	    System.out.println(delete.getCommandType() + " " + delete.getTaskType() + " " + delete.getTaskID());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Deleting event task
	try {
	    Command delete = CommandParser.parse("delete E2");
	    System.out.println(delete.getCommandType() + " " + delete.getTaskType() + " " + delete.getTaskID());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Deleting floating task
	try {
	    Command delete = CommandParser.parse("delete F4");
	    System.out.println(delete.getCommandType() + " " + delete.getTaskType() + " " + delete.getTaskID());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Deleting floating task
	try {
	    Command delete = CommandParser.parse("delete f4");
	    System.out.println(delete.getCommandType() + " " + delete.getTaskType() + " " + delete.getTaskID());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	//==========================================================================
	//=====================Testing Updating of Deadline tasks==============================
	//==========================================================================

	// Update description and deadline
	try {
	    Command update = CommandParser.parse("update D1 Arrange meeting 09/10/2015 09:00");
	    System.out.println(update.getCommandType() + " " + update.getTaskType() + " "  + update.getTaskID() + 
		    " "  + update.getTaskDescription() + " " + update.getTaskDeadline());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Update deadline
	try {
	    Command update = CommandParser.parse("update D2 09/10/2015 09:00");
	    System.out.println(update.getCommandType() + " " + update.getTaskType() + " "  + update.getTaskDescription() + update.getTaskID() + " " + update.getTaskDeadline());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Update description
	try {
	    Command update = CommandParser.parse("update D3 Arrange meeting");
	    System.out.println(update.getCommandType() + " " + update.getTaskType() + " "  + update.getTaskID() + 
		    " "  + update.getTaskDescription());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	//=======================================================================================================
	//====================================Testing Updating of Floating tasks=================================
	//=======================================================================================================

	try {
	    Command update = CommandParser.parse("update F4 This is a floating task");
	    System.out.println(update.getCommandType() + " " + update.getTaskType() + " "  + update.getTaskID() + " "  + update.getTaskDescription());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	//=======================================================================================================
	//====================================Testing Updating of Event tasks====================================
	//=======================================================================================================

	// Update description, date and duration
	try {
	    Command update = CommandParser.parse("update E5 Arrange meeting 09/10/2015 13:00-14:00");
	    System.out.println(update.getCommandType() + " " + update.getTaskType() + " "  + update.getTaskID() + 
		    " "  + update.getTaskDescription() + " " + update.getTaskEventDate() + " " + update.getTaskEventTime());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Update description and duration
	try {
	    Command update = CommandParser.parse("update E6 Arrange meeting 13:00-14:00");
	    System.out.println(update.getCommandType() + " " + update.getTaskType() + " "  + update.getTaskID() + 
		    " "  + update.getTaskDescription() + " " + update.getTaskEventTime());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Update date
	try {
	    Command update = CommandParser.parse("update E7 09/10/2015");
	    System.out.println(update.getCommandType() + " " + update.getTaskType() + " " + update.getTaskID() + " " + update.getTaskEventDate());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Updating duration
	try {
	    Command update = CommandParser.parse("update E8 13:00-14:00");
	    System.out.println(update.getCommandType() + " " + update.getTaskType() + " "  + update.getTaskID() + " " + update.getTaskEventTime());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Update description
	try {
	    Command update = CommandParser.parse("update E9 Marketing Research Survey");
	    System.out.println(update.getCommandType() + " " + update.getTaskType() + " "  + update.getTaskID() + 
		    " "  + update.getTaskDescription());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	//=================================================================================
	//============================Testing Recurrence tasks=====================================
	//=================================================================================
	// Command: repeat <task description> hh:mm-hh:mm dd/mm/yyyy {<period> , <frequency>, <until dd/mm/yy or forever>}

	// Daily: repeat <task description> hh:mm-hh:mm dd/mm/yyyy {day , every X day, Until dd/mm/yy or forever}
	try {
	    Command repeat = CommandParser.parse("repeat team meeting 18:00-20:00 14/10/2015 {day, 1, forever}");
	    System.out.println(repeat.getCommandType() + " " + repeat.getDateOfRepeatAdded() + " " + repeat.getTaskRepeatDuration() + " " 
		    + repeat.getTaskDescription() + " " + repeat.getTaskRepeatType()  + " " +repeat.getTaskRepeatDayFrequency() 
		    + " " + repeat.getTaskRepeatEndDate());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Weekly: repeat <task description> hh:mm-hh:mm dd/mm/yyyy {week , <Mon-Sun>,  <until dd/mm/yy or forever>}
	try {
	    Command repeat = CommandParser.parse("repeat team meeting 08:00-10:00 09/11/2015 {week, mon/tue/wed ,forever}");
	    System.out.println(repeat.getCommandType() + " " + repeat.getDateOfRepeatAdded() + " " + repeat.getTaskRepeatDuration() + " " 
		    + repeat.getTaskDescription() + " " + repeat.getTaskRepeatType()  + " " + repeat.getTaskRepeatEndDate());
	    Boolean[] week = repeat.isTaskRepeatOnDayOfWeek();
	    for(int i =0; i< week.length; i++) {
		if(week[i]) {
		    System.out.println("Day " + (i + 1) + " of Week");
		}
	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	try {
	    Command repeat = CommandParser.parse("repeat team meeting 08:00-10:00 09/11/2015 {week, sun/thu/wed ,forever}");
	    System.out.println(repeat.getCommandType() + " " + repeat.getDateOfRepeatAdded() + " " + repeat.getTaskRepeatDuration() + " " 
		    + repeat.getTaskDescription() + " " + repeat.getTaskRepeatType()  + " " + repeat.getTaskRepeatEndDate());
	    Boolean[] week = repeat.isTaskRepeatOnDayOfWeek();
	    for(int i =0; i< week.length; i++) {
		if(week[i]) {
		    System.out.println("Day " + (i + 1) + " of Week");
		}
	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}


	// Monthly: repeat month {Duration X-Y time, Repeat: Every X month, On Xst/nd/rd/th of month or every third <Mon-Sun> of month, Until Date or Forever}
	try {
	    Command repeat = CommandParser.parse("repeat team meeting 09:00-13:00 29/10/2015 {month, on 19, 15/06/2016}");
	    System.out.println(repeat.getCommandType() + " " + repeat.getDateOfRepeatAdded() + " " + repeat.getTaskRepeatDuration() + " " 
		    + repeat.getTaskDescription() + " " + repeat.getTaskRepeatType()  + " " +repeat.getTaskRepeatMonthFrequency() 
		    + " " + repeat.getTaskRepeatEndDate());
	    System.out.println("Repeating Monthly on day : " + repeat.getTaskRepeatMonthFrequencyBySpecificDate());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	try {
	    Command repeat = CommandParser.parse("repeat team meeting 09:00-13:00 29/10/2015 {month, 2-sun, 15/06/2016}");
	    System.out.println(repeat.getCommandType() + " " + repeat.getDateOfRepeatAdded() + " " + repeat.getTaskRepeatDuration() + " " 
		    + repeat.getTaskDescription() + " " + repeat.getTaskRepeatType()  + " " +repeat.getTaskRepeatMonthFrequency() 
		    + " " + repeat.getTaskRepeatEndDate());
	    System.out.println("Repeating monthly on : " + repeat.getTaskRepeatMonthFrequencyBySpecificDayOfWeek()[1] + " of week " +
		    repeat.getTaskRepeatMonthFrequencyBySpecificDayOfWeek()[0]);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Yearly: repeat <task description> hh:mm-hh:mm dd/mm/yyyy {year , every X year,  <until dd/mm/yy or forever>}
	try {
	    Command repeat = CommandParser.parse("repeat team meeting 14:00-18:00 19/12/2015 {year, 1, forever}");
	    System.out.println(repeat.getCommandType() + " " + repeat.getDateOfRepeatAdded() + " " + repeat.getTaskRepeatDuration() + " " 
		    + repeat.getTaskDescription() + " " + repeat.getTaskRepeatType()  + " " +repeat.getTaskRepeatYearFrequency() 
		    + " " + repeat.getTaskRepeatEndDate());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// This will delete the whole recurrence task
	try {
	    Command repeat = CommandParser.parse("delete R1");
	    System.out.println(repeat.getCommandType() + " " + repeat.getTaskType() + " " + repeat.getTaskID());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Stop recurrence on specifc frequency
	try {
	    Command repeat = CommandParser.parse("stop R1 {15/10/15, 20/10/15}");
	    System.out.print(repeat.getCommandType());
	    for(int i=0; i< repeat.getStopRepeat().size(); i++ ) {
		System.out.print(" " + repeat.getStopRepeat().get(i));
	    }
	    System.out.println();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	// Stop recurrence on specifc frequency
	try {
	    Command repeat = CommandParser.parse("stop R1 {mon, tue, sat}");
	    System.out.print(repeat.getCommandType());
	    for(int i=0; i< repeat.getStopRepeat().size(); i++ ) {
		System.out.print(" " + repeat.getStopRepeat().get(i));
	    }
	    System.out.println();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	//=================================================================================
	//============================Updating Recurrence tasks====================================
	//=================================================================================
	try {
	    Command repeat = CommandParser.parse("update R3 team meeting");
	    System.out.println(repeat.getCommandType()  + " " + repeat.getTaskType() + " " + repeat.getTaskID() + " " + repeat.getTaskDescription());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	try {
	    Command repeat = CommandParser.parse("update R4 18:00-20:00 14/10/2015");
	    System.out.println(repeat.getCommandType() + " " + repeat.getTaskType() + " " + repeat.getTaskID() + " "  
		    +  repeat.getTaskEventDate() + " " + repeat.getTaskEventTime());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	
	try {
	    Command repeat = CommandParser.parse("update R2 team meeting 18:00-20:00 14/10/2015");
	    System.out.println(repeat.getCommandType() + " " + repeat.getTaskType() + " " + repeat.getTaskID() + " " +  repeat.getTaskEventDate() + " " 
	    + repeat.getTaskEventTime() + " " +   repeat.getTaskDescription());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	try {
	    Command repeat = CommandParser.parse("update R1 team meeting 18:00-20:00 14/10/2015 {day, 1, forever}");
	    System.out.println(repeat.getCommandType() + " " + repeat.getTaskType() + " " + repeat.getTaskID() + " " +  repeat.getTaskEventDate() + 
		    " " + repeat.getTaskEventTime() + " " + repeat.getTaskDescription());
	    for(int i =0; i < repeat.getUpdateRepeat().size(); i++) {
		System.out.println("Attribute "+ (i+1) +" " + repeat.getUpdateRepeat().get(i));
	    }
	    System.out.println();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	/*
        // view task from a specific day
        try {
            Command view = CommandParser.parse("view <09/10/2015>");
            System.out.println(view.getCommandType() + " " + view.getTaskTime());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // search task with keywords
        try {
            Command search = CommandParser.parse("search meeting jardine Singapore");
            String keywords = "";
            for(int index = 0 ; index < search.getSearchKeyword().size(); index++) {
                keywords = keywords + search.getSearchKeyword().get(index) + " - ";
            }
            System.out.println(search.getCommandType() + " " + keywords);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("============================Testing exception handling============================");
        // Test exception handling for adding task
        try {
            System.out.println("add <dsffdadsf safsdfsf");
            Command add = CommandParser.parse("add <dsffdadsf safsdfsf");
            System.out.println(add.getCommandType() + " " + add.getTaskTitle() + " " + add.getTaskTime());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Test exception handling for updating task
        try {
            System.out.println("update <2> <Ar <09/10/2015>");
            Command add = CommandParser.parse("update <2> <Ar <09/10/2015>");
            System.out.println(add.getCommandType() + " " + add.getTaskTitle() + " " + add.getTaskTime());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Test exception handling for deleting task
        try {
            System.out.println("delete <1 <09/10201");
            Command delete = CommandParser.parse("delete <1 <09/10201");
            System.out.println(delete.getCommandType() + " " + delete.getTaskNumber() + " " + delete.getTaskTime());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Test exception handling for complete task
        try {
            System.out.println("complete <1a> <09adasdaf/10/2015>");
            Command complete = CommandParser.parse("complete <1a> <09adasdaf/10/2015>");
            System.out.println(complete.getCommandType() + " " + complete.getTaskNumber() + " " +complete.getTaskTime());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // [TO DEBUG: Test exception handling for view task from a specific day]
        try {
            Command view = CommandParser.parse("view <abc <129302");
            System.out.println("Error: " + view.getCommandType() + " " + view.getTaskTime());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        CommandParser.viewCommandParserLog();

        System.out.println("=================================Testing assertion================================");

        // Test assertion handling for undo
        try {
            Command undo = CommandParser.parse(null);
            System.out.println(undo.getCommandType());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Test assertion handling for undo
        try {
            Command redo = CommandParser.parse(null);
            System.out.println(redo.getCommandType());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("This message should not appear");
	 */
    } 

}
