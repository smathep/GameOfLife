import edu.jenks.gol.view.*;
import edu.princeton.cs.introcs.*;
import java.util.*;

public class GameOfLife{
	
	public static void main(String[] args){
		System.out.println("Options: ");
		System.out.println("\tr for random");
		System.out.println("\ts for still life");
		System.out.println("\to for oscillators");
		System.out.println("\tls for lightweight spaceship");
		System.out.println("\te for empty grid");
		Scanner s = new Scanner(System.in);
		String gameType = s.next();
		if(!(gameType.toLowerCase() != "r" || gameType.toLowerCase() != "s" || gameType.toLowerCase() != "o" || gameType.toLowerCase() != "ls" || gameType.toLowerCase() != "e"))
			throw new IllegalArgumentException("Character input for game type is not recognized");
		System.out.println("Enter the width ");
		int width = s.nextInt();
		System.out.println("Enter the height");
		int height = s.nextInt();
		System.out.println("Enter the cell length");
		int cellSize = s.nextInt();
		GameOfLifeViewStdDraw grid = new GameOfLifeViewStdDraw(height, width, cellSize);
		GameOfLifeGame game = new GameOfLifeGame(height, width, cellSize, grid);
		
		int gen = 1;
		switch(gameType.toLowerCase()){
			case "r":
				grid.drawGrid();
				//grid.enableDoubleBuffering();
				grid.drawGenerationCounter(gen);
				game.randomInitializer();
				grid.show();
				break;
	/* 		case "o":
				game.oscillater();
				break;
				*/
			case "s":
				grid.drawGrid();
				grid.enableDoubleBuffering();
				grid.drawGenerationCounter(game.genCount);
				game.stillLifeInitializer(); 
				grid.show();
				break;
			case "e":
				grid.drawGrid();
				grid.enableDoubleBuffering();
				grid.drawGenerationCounter(game.genCount);
				game.emptyInitializer();
				grid.show();
				break;
		}
		System.out.println("Options for running");
		System.out.println("\tr to run");
		System.out.println("\ts to step through a generation");
		while(true){
			switch(s.next()){
				case "r":
					Thread gameRun = new Thread(game);
					gameRun.start();
					//gameRun.run();
					stop: while(true){
						switch(s.next()){
							case "stop":
								try{
									gameRun.join(1000);
								}
								catch(InterruptedException e){
									
								}
								System.out.println("Stopped");
								break stop;
						}
					}
				case "s":
					game.step();
				}
		}
		
	}
}