package com.logicaltiger.tictactoe.io;

import java.util.Scanner;
import java.util.function.Consumer;

import com.logicaltiger.tictactoe.io.Output;

public class Input {
	private Scanner in = new Scanner(System.in);

	private Output output;
	
	public void loadDependencies(Output output) {
		this.output = output;
	}
	public void getAnswer(Consumer<String> c, String charList) {
		getValidChars(c, charList, false);
	}
	
	public void getMove(Consumer<String> c, String charList) {
		getValidChars(c, charList, true);
	}
	
    /**
     * Requires a valid Consumer reference or the program goes zombie. 
     */
	public void getValidChars(Consumer<String> c, String charList, boolean addQ) {

		/*
		 * Always offer the "quit game" choice.
		 */
		if(charList == null || charList.length() == 0)
			charList = "";
		
		charList = charList.toUpperCase();
		
		if(addQ && charList.indexOf("Q") == -1)
			charList += "Q";

		while(true) {
            output.show("Enter a single value");
            output.show("from this list " + charList 
            		+ (addQ ? " (Q quits game)" : "") 
            		+ ", followed by the Enter key: ");
	        String value = in.next().toUpperCase();
	        
	        if(validResponse(charList, value)) {
	            c.accept(value);
	        	break;
	        }
		
		}
        
	}

	private boolean validResponse(String charList, String value) {

		if(charList == null || charList.length() == 0)
			return false;

		if(value == null || value.length() != 1)
			return false;

		return charList.indexOf(value) != -1;
	}

}
