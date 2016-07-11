package com.logicaltiger.tictactoe.player;

import com.logicaltiger.tictactoe.io.StringCallback;

public interface Player {
	public static final String PLAYER_HUMAN = "Human";
	public static final String PLAYER_COMPUTER = "Computer";
	
	public String getIdentity();
	
	public void setMark(String mark);
	
	public String getMark();
	
    public void makeMove(StringCallback sc, String validMoves);
	
}
