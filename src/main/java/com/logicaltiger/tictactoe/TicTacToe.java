package com.logicaltiger.tictactoe;

import com.logicaltiger.tictactoe.ai.Strategy;
import com.logicaltiger.tictactoe.board.Board;
import com.logicaltiger.tictactoe.io.History;
import com.logicaltiger.tictactoe.io.Input;
import com.logicaltiger.tictactoe.io.Output;
import com.logicaltiger.tictactoe.player.Computer;
import com.logicaltiger.tictactoe.player.Human;

/**
 * Jerome Mrozak modestly presents his demo tic-tac-toe program.
 */
public class TicTacToe {
	
    public static void main(String[] args) {
        System.out.println("Welcome to Jerome's Tic-Tac-Toe!");
        initialize();
    }
    
    public static void initialize() {
        Game game = new Game();
        Strategy strategy = new Strategy();
        Board board = new Board();
        History history = new History();
        Input input = new Input();
        Output output = new Output();
        Computer computer = new Computer();
        Human human = new Human();

        strategy.loadDependencies(board, history, computer);
        computer.loadDependencies(strategy, output);
        human.loadDependencies(input, output);
        game.loadDependencies(strategy, board, history, input, output, computer, human);
  
        board.initialize();
        history.loadHistory();
    
    	Thread thread = new Thread(game);
    	thread.start();
    }

}
