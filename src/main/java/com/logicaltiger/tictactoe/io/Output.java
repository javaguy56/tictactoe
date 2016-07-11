package com.logicaltiger.tictactoe.io;

import java.util.ArrayList;
import java.util.List;

import com.logicaltiger.tictactoe.board.Board;

public class Output {

	public void show(String text) {
		System.out.println(text);
	}

	public void show(List<String> texts) {

		for(String text : texts) {
			show(text);
		}

	}

	public void show(Board board) {
		show(boardToString(board));
	}

	private List<String> boardToString(Board board) {
		List<String> output = new ArrayList<String>();
		output.add("    |   |    ");
		
		for(int row = 0; row < 3; row++) {
			StringBuilder sb = new StringBuilder();
			sb.append("  ");
			
			for(int col = 0; col < 3; col++) {
				String mark = board.getMark(row, col);
				
				if(Board.MARK_BLANK.equals(mark))
					mark = board.getCode(row, col).toLowerCase();
				
				sb.append(mark); 

				if(col < 2)
					sb.append(" | ");
					
			}

			sb.append("  ");
			output.add(sb.toString());
			
			if(row < 2)
				output.add("----+---+----");
			
		}
		
    	output.add("    |   |    ");
    	output.add("");
    	return output;
	}
	
}
