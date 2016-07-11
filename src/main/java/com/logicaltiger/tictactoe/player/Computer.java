package com.logicaltiger.tictactoe.player;

import com.logicaltiger.tictactoe.ai.Strategy;
import com.logicaltiger.tictactoe.io.Output;
import com.logicaltiger.tictactoe.io.StringCallback;

public class Computer implements Player {
	private Strategy strategy;
	private Output output;
	private String mark = "?"; // default of "uninitialized"

	public void loadDependencies(Strategy strategy, Output output) {
		this.strategy = strategy;
		this.output = output;
	}

	public String getIdentity() {
		return PLAYER_COMPUTER;
	}
	
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	public String getMark() {
		return this.mark;
	}

	/**
	 * Let the brains do all of the thinking.
	 * The move is actually processed by the passed-in
     * Game object, acting as a callback destination.
     */
	public void makeMove(StringCallback sc, String validMoves) {
		output.show("Computer player turn");
		strategy.makeMove(sc, validMoves);
	}

}
