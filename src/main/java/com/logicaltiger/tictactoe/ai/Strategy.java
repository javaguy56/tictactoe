package com.logicaltiger.tictactoe.ai;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.logicaltiger.tictactoe.board.Board;
import com.logicaltiger.tictactoe.io.History;
import com.logicaltiger.tictactoe.io.StringCallback;
import com.logicaltiger.tictactoe.player.Computer;

public class Strategy {
	private Board board;
	private History history;
	private Computer computer;
	private String losingPosition;

	public void loadDependencies(Board board, History history, Computer computer) {
		this.board = board;
		this.history = history;
		this.computer = computer;
	}
	
	public String getLosingPosition() {
		return this.losingPosition;
	}
	
	/**
	 * Let the brains do all of the thinking.
	 * The move is actually processed by the Game object.
	 */
	public void makeMove(StringCallback sc, String validMoves) {
		String code = calculateMove(validMoves);
		
		Board b = this.board.clone();
		b.setMark(code, computer.getMark());
		this.losingPosition = b.getPosition();
		
		sc.fetchedString(code);
	}

	/**
	 * Based on the allowed moves, a history of failed positions
	 * and some native smarts calculate the next move.
	 * 
	 * The intent is that the computer should avoid mistakes by trying
	 * a move and deciding if the move creates a position that lost before.
	 * Only moves that haven't yet failed will be selected.
	 * 
	 * Or at least that is the plan.  The reality is that the "avoid
	 * losing positions" strategy allows the computer player to get 
	 * into a losing position.  For example, the previous computer move
	 * might have the human making a winning move or a (stupid) losing
	 * move.  
	 * 
	 * The result of filtering out losing moves might present no possible
	 * moves.  But the game must go on.  In that event, ignore any filtering
	 * and choose a move from the full set of valid choices. 
	 */
	public String calculateMove(String validMoves) {
		String mark = computer.getMark();
		StringBuffer sb = new StringBuffer();
		Map<String, String> trials = new HashMap<String, String>();
		
		for(String code : validMoves.split("")) {
			Board b = board.clone();
			b.setMark(code, mark);
			String trialPosition = b.getPosition();
			trials.put(code, trialPosition);
			boolean goodTry = true;
			
			for(String losingPosition : history.getRotatedHistories()) {
				
				if(losingPosition.equals(trialPosition)) {
					goodTry = false;
					break;
				}
				
			}
			
			if(goodTry) {
				sb.append(code);
			}
			
		}
		
		String netMoves = (sb.length() == 0) ? validMoves : sb.toString();
		int idx = 0;
		int len = netMoves.length();

		if(len > 1) {
		    Random ran = new Random();
		    idx = ran.nextInt(len);
		}

		String selectedCode = netMoves.substring(idx, idx + 1);
		this.losingPosition = trials.get(selectedCode);
		
		return selectedCode;
	}
	
}
