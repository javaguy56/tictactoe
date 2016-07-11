package com.logicaltiger.tictactoe.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.logicaltiger.tictactoe.board.Board;

public class History {
	private List<String> actualHistory = new ArrayList<String>();
	private List<String> rotatedHistories = new ArrayList<String>();
	private static final String HISTORY_FILE_NAME = "tictactoe.txt";
	
	public void loadHistory() {
		this.actualHistory = new ArrayList<String>();
		this.rotatedHistories = new ArrayList<String>();
		this.loadFile();
		this.buildRotatedHistories();
	}
	
	public List<String> getRotatedHistories() {
		return this.rotatedHistories;
	}
	
	private void loadFile() {
		File f = new File(History.HISTORY_FILE_NAME);
		
		if(!f.exists())
			return;
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(History.HISTORY_FILE_NAME));
			String line;

			while ((line = br.readLine()) != null) {
				actualHistory.add(line);
			}

		} catch(IOException e) {
			System.out.println("Can't finish loading file, quitting game");
			System.exit(-1);
		} finally {
			
			try {
				
				if (br != null)
					br.close();
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
		}		
		
	}

	public void storeHistory(String losingPosition) {
		this.actualHistory.add(losingPosition);
		this.saveFile();
	}
	
	private void saveFile() {
		
		try {
			File f = new File(History.HISTORY_FILE_NAME);

			if(!f.exists()) {
				f.createNewFile();
			}

			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			
		    for(String s : this.actualHistory) {
			      fw.write(s + "\n");
			}
			
		    fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		

	}

	private void buildRotatedHistories() {
	
		for(String s : this.actualHistory) {
			buildRotatedHistory(s);
		}
		
	}
	
	private void buildRotatedHistory(String originalPosition) {
		
		/*
		 * Get the original position plus three 90-degree rotations.
		 */
		this.rotatedHistories.add(originalPosition);
		
		Board b = new Board();
		b.create(originalPosition);
		String position;
		
		for(int idx = 0; idx < 3; idx++) {
			b.rotate();
			position = b.getPosition();
			this.rotatedHistories.add(position);
		}

		/*
		 * Get a mirror of the original position and three rotations of that.
		 */
		b.create(originalPosition);
		b.mirror();
		position = b.getPosition(); 
		this.rotatedHistories.add(position);

		for(int idx = 0; idx < 3; idx++) {
			b.rotate();
			position = b.getPosition();
			this.rotatedHistories.add(position);
		}
		
	}

}
