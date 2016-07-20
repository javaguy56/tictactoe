package com.logicaltiger.tictactoe;

import java.util.function.Consumer;

import com.logicaltiger.tictactoe.ai.Strategy;
import com.logicaltiger.tictactoe.board.Board;
import com.logicaltiger.tictactoe.io.History;
import com.logicaltiger.tictactoe.io.Input;
import com.logicaltiger.tictactoe.io.Output;
import com.logicaltiger.tictactoe.player.Computer;
import com.logicaltiger.tictactoe.player.Human;
import com.logicaltiger.tictactoe.player.Player;

public class Game implements Runnable {
	private History history;
	private Input input;
	private Output output;
	private Board board;
	private Human human;
	private Computer computer;
	private Strategy strategy;

	private Player playerX;
	private Player playerO;
	private Player currentPlayer;

	private static final int GAME_START = 19;
	private static final int PLAYER_X_MOVE = 20;
	private static final int EVAL_X_WINNING = 21;
	private static final int PLAYER_O_MOVE = 22;
	private static final int EVAL_O_WINNING = 23;
	private static final int GAME_X_WON = 40;
	private static final int GAME_O_WON = 41;
	private static final int GAME_DRAW = 42;
	private static final int START_NEW_GAME = 70;
	private static final int QUIT_GAME = 80;
	private int gameStatus;
	private boolean firstTurn;
	
	public void loadDependencies(Strategy strategy, Board board, History history, Input input, Output output, Computer computer, Human human) {
		this.strategy = strategy;
		this.board = board;
		this.history = history;
		this.input = input;
		this.output = output;
		this.computer = computer;
		this.human = human;
	}

	public void run() {
		begin();
	}

    private Consumer<String> moveConsumer = new Consumer<String>() {

    	public void accept(String move) {
			evaluateMove(move);
        }

    };

	private Consumer<String> newGameConsumer = new Consumer<String>() {

		public void accept(String choice) {
			evaluateNewGame(choice);
        }

    };

	private Consumer<String> whichPlayerXConsumer = new Consumer<String>() {

		public void accept(String choice) {
			evaluateWhichX(choice);
        }

    };

	public void begin() {
		firstTurn = true;
		gameStatus = GAME_START;
		gameSteps();
	}
	
	public void gameSteps() {
		boolean quitLoop = false;
		
		while(true) {
			
			/*
			 * Once the game begins the cycle is for X to move, test for win, Y to move, test for win, repeat.
			 * The other logic deals with start or end of game conditions.
			 */
			if(EVAL_O_WINNING == gameStatus) {
				gameStatus = PLAYER_X_MOVE;
			} else if(PLAYER_X_MOVE == gameStatus || EVAL_X_WINNING == gameStatus || PLAYER_O_MOVE == gameStatus) {
				gameStatus++;
			}
		
			switch(gameStatus) {
			case PLAYER_X_MOVE:
				playerMove(playerX);
				break;
			case EVAL_X_WINNING:
				evaluateGame();
				break;
			case PLAYER_O_MOVE:
				playerMove(playerO);
				break;
			case EVAL_O_WINNING:
				evaluateGame();
				break;
			case GAME_X_WON:
				showWinner(playerX);
				storeComputerLoss(playerO);
				askForNewGame();
				break;
			case GAME_O_WON:
				showWinner(playerO);
				storeComputerLoss(playerX);
				askForNewGame();
				break;
			case GAME_DRAW:
				showDraw();
				askForNewGame();
				break;
			case GAME_START:
				whichPlayerX();
				break;
			case START_NEW_GAME:
				TicTacToe.initialize();
				quitLoop = true;
				break;
			case QUIT_GAME:
				quitLoop = true;
				break;
			default:
				break;
			}
		
			if(quitLoop)
				break;
			
		}
		
	}
	
	/**
	 * The player is asked for the move, but the selected move
	 * is delivered by way of moveConsumer().  
	 */
	public void playerMove(Player player) {
		currentPlayer = player;
		
		if(!firstTurn || Player.PLAYER_HUMAN.equals(player.getIdentity())) {
			output.show(board);
		}
		
		firstTurn = false;
		player.makeMove(moveConsumer, board.getValidMoves());
	}
	
	private void evaluateMove(String code) {
		
		if("Q".equals(code)) {
			gameStatus = QUIT_GAME;
		} else {
			board.setMark(code, currentPlayer.getMark());
		}
		
	}
	
	private void evaluateGame() {
		String winner = board.getWinner();
		
		if(Board.MARK_X.equals(winner)) {
			gameStatus = GAME_X_WON;
		} else if(Board.MARK_O.equals(winner)) {
			gameStatus = GAME_O_WON;
		} else if(board.isDraw()) {
			gameStatus = GAME_DRAW;
		}

	}
	
	private void showWinner(Player winningPlayer) {
		output.show(board);
		output.show(winningPlayer.getIdentity() + " player has WON!");
		output.show("");
	}
	
	private void showDraw() {
		output.show("Game is over with a DRAW");
		output.show("");
	}
	
	private void storeComputerLoss(Player losingPlayer) {
		
		if(Player.PLAYER_COMPUTER.equals(losingPlayer.getIdentity())) {
			history.storeHistory(strategy.getLosingPosition());
		}
			
	}
	
	private void askForNewGame() {
		output.show("Want to play again?");
		input.getAnswer(newGameConsumer, "YN");
	}

	private void evaluateNewGame(String code) {
		
		if("Y".equals(code)) {
			gameStatus = START_NEW_GAME;
		} else {
			gameStatus = QUIT_GAME;
		}
		
	}
	
	private void whichPlayerX() {
		output.show("The 'X' goes first.  Should the Human or Computer go first?");
		input.getAnswer(whichPlayerXConsumer, "HC");
	}

	private void evaluateWhichX(String code) {

		if("H".equals(code)) {
			playerX = human;
			playerO = computer;
			
		} else {
			playerX = computer;
			playerO = human;
		}

		playerX.setMark(Board.MARK_X);
		playerO.setMark(Board.MARK_O);

		// The first move occurs by pretending we just finished move #0.
		gameStatus = EVAL_O_WINNING;
	}
	
}
