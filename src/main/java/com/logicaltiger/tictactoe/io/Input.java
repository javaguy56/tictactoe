package com.logicaltiger.tictactoe.io;

import java.util.Scanner;

import com.logicaltiger.tictactoe.io.StringCallback;

public class Input {
	private Scanner in = new Scanner(System.in);

	public void getAnswer(StringCallback sc, String charList) {
		getValidChars(sc, charList, false);
	}
	
	public void getMove(StringCallback sc, String charList) {
		getValidChars(sc, charList, true);
	}
	
    /**
     * Requires a valid callback reference or the program goes zombie. 
     */
	public void getValidChars(StringCallback sc, String charList, boolean addQ) {

		/*
		 * Always offer the "quit game" choice.
		 */
		if(charList == null || charList.length() == 0)
			charList = "";
		
		charList = charList.toUpperCase();
		
		if(addQ && charList.indexOf("Q") == -1)
			charList += "Q";

		while(true) {
            System.out.println("Enter a single value \nfrom this list " + charList 
            		+ (addQ ? " (Q quits game)" : "") 
            		+ ", followed by the Enter key: ");
	        String value = in.next().toUpperCase();
	        
	        if(validResponse(charList, value)) {
	            sc.fetchedString(value);
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
