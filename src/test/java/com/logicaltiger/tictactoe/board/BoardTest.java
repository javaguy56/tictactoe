package com.logicaltiger.tictactoe.board;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
	private Board board;
	
	protected void setUp() throws Exception {
		super.setUp();
		board = new Board();
	}

	@Test
	public final void testSetMarkIntIntString() {
		board.setMark(0, 0, "X");
		assertThat(board.getMark(0,  0), equalTo("X"));
		board.setMark(0, 1, "X");
		assertThat(board.getMark(0,  1), equalTo("X"));
		board.setMark(0, 2, "X");
		assertThat(board.getMark(0,  2), equalTo("X"));
		board.setMark(1, 0, "X");
		assertThat(board.getMark(1,  0), equalTo("X"));
		board.setMark(1, 1, "X");
		assertThat(board.getMark(1,  1), equalTo("X"));
		board.setMark(1, 2, "X");
		assertThat(board.getMark(1,  2), equalTo("X"));
		board.setMark(2, 0, "X");
		assertThat(board.getMark(2,  0), equalTo("X"));
		board.setMark(2, 1, "X");
		assertThat(board.getMark(2,  1), equalTo("X"));
		board.setMark(2, 2, "X");
		assertThat(board.getMark(2,  2), equalTo("X"));
	}

	@Test
	public final void testSetMarkStringString() {
		board.setMark("A", "O");
		assertThat(board.getMark(0,  0), equalTo("O"));
		board.setMark("B", "O");
		assertThat(board.getMark(0,  1), equalTo("O"));
		board.setMark("C", "O");
		assertThat(board.getMark(0,  2), equalTo("O"));
		board.setMark("D", "O");
		assertThat(board.getMark(1,  0), equalTo("O"));
		board.setMark("E", "O");
		assertThat(board.getMark(1,  1), equalTo("O"));
		board.setMark("F", "O");
		assertThat(board.getMark(1,  2), equalTo("O"));
		board.setMark("G", "O");
		assertThat(board.getMark(2,  0), equalTo("O"));
		board.setMark("H", "O");
		assertThat(board.getMark(2,  1), equalTo("O"));
		board.setMark("I", "O");
		assertThat(board.getMark(2,  2), equalTo("O"));
	}

}
