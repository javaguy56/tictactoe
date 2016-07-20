package com.logicaltiger.tictactoe.player;

import java.util.function.Consumer;

import com.logicaltiger.tictactoe.io.Input;
import com.logicaltiger.tictactoe.io.Output;

public class Human implements Player {
	private Input input = null;
	private Output output = null;
	private String mark = "?"; // default of "uninitialized"

	public void loadDependencies(Input input, Output output) {
		this.input = input;
		this.output = output;
	}

	public String getIdentity() {
		return PLAYER_HUMAN;
	}
	
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	public String getMark() {
		return this.mark;
	}
	
	/**
	 * Prompt the human that he/she must move.
	 * The move is actually processed by way of the consumer.
	 */
	public void makeMove(Consumer<String> c, String validMoves) {
		output.show("Human player turn");
		input.getMove(c, validMoves);
	}

}
