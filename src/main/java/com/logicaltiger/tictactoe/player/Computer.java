package com.logicaltiger.tictactoe.player;

import java.util.function.Consumer;

import com.logicaltiger.tictactoe.ai.Strategy;
import com.logicaltiger.tictactoe.io.Output;

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
	 * The move is actually processed by way of the consumer.
     */
	public void makeMove(Consumer<String> c, String validMoves) {
		output.show("Computer player turn");
		strategy.makeMove(c, validMoves);
	}

}
