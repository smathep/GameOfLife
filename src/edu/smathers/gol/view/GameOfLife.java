// import edu.princeton.cs.introcs.*;
import java.util.*;

public class GameOfLife{

	Grid grid;
	GameOfLifeGame game;
	MouseMonitor mouse = new MouseMonitor(this);

	boolean run = false;
	int width = 0;
	int height = 0;
	int cellLength = 0;
	int gen = 1;
	
	boolean [][] aliveOriginal;
	public Integer genCount = 1;
	public GameOfLife(){
		game = new GameOfLifeGame(this);
		gameSetup();
		mouse.start();
		Runner run = new Runner(this);
		run.start();
		
		
	}
	public static void main(String[] args){
		GameOfLife life = new GameOfLife();
		//Thread gameThread = new Thread(game);

	}
	public void oscillatorInitializer(){
		if((height / cellLength >= 14) && (width / cellLength >= 16)){//outside of pulsar
			addCells(4,4, 5,4, 6,4, 10,4, 11,4, 12,4, 14,6, 14,7, 14,8, 14,12, 14,13, 14,14, 12,16, 11,16, 10,16, 6,16, 5,16, 4,16, 2,14, 2,13, 2,12, 2,8, 2,7, 2,6);
			//inside of pulsar
			addCells(4,9, 5,9, 6,9, 7,8, 7,7, 7,6, 9,6, 9,7, 9,8, 10,9, 11,9, 12,9, 12,11, 11,11, 10,11, 9,12, 9,13, 9,14, 7,14, 7,13, 7,12, 6,11, 5,11, 4,11);
		}
		if((height / cellLength >= 21) && (width / cellLength >= 3))//blinker
			addCells(20,1, 20,2, 20,3);
		if((height / cellLength >= 22) && (width / cellLength >= 23))//toad
			addCells(19,21, 20,20, 21,20, 22,22, 21,23, 20,23 );
		if((height / cellLength >= 6) && (width / cellLength >= 28))//beacon
			addCells(5,25, 6,25, 6,26, 5,26, 4,27, 4,28, 3,28, 3,27);
	}
	public void stillLifeInitializer(){
		if((height / cellLength >= 2) && (width / cellLength >=2))
			addCells(1,1, 1,2, 2,1, 2,2);//square
	
		if((height / cellLength >= 5) && (width / cellLength >= 8))
			addCells(3,6, 3,7, 4,5, 4,8, 5,6, 5,7);//beehive
		
		if((height / cellLength >= 11) && (width / cellLength >= 5))
			addCells(8,4, 9,3, 10,2, 11,3, 11,4, 10,5, 9,5);//loaf
		
		if((height / cellLength >= 9) && (width / cellLength >= 17))
			addCells(8,15, 9,15, 9,16, 8,17, 7,16);//boat
		
		if((height / cellLength >= 15) && (width / cellLength >= 17))
			addCells(15,15, 16,16, 15,17, 14,16);//tub
	
	}
	public void gliderInitializer(){
		if((height / cellLength >= 10) && (width / cellLength >= 11))
			addCells(9,9, 9,10, 9,11, 10,11, 11, 10);

	}
	public void lsInitializer(){
		if((height / cellLength >= 10) && (width / cellLength >=11))
			addCells(8,8, 9,8, 8,9, 9,9, 10,9, 10,10, 9,10, 9,11, 8,11, 8,12, 7,11, 7,10);
	}
	public void randomInitializer(){
		for(int r = 0; r < width / cellLength; r++){
			for(int c = 0; c < height / cellLength; c++){
				if(((int) (Math.random() * 2)) == 0){
					grid.drawCell(r, c, false);
					aliveOriginal[r][c] = false;
				}
				else{
					grid.drawCell(r, c, true);
					aliveOriginal[r][c] = true;
				}
			}
		}
	}
	public void emptyInitializer(){
		for(int r = 0; r < width / cellLength; r++){
			for(int c = 0; c < height / cellLength; c++){
				grid.drawCell(r, c, false);
				aliveOriginal[r][c] = false;
			}
		}
	}
	public void changeCells(int x, int y){
		grid.drawCell(x, y, (!aliveOriginal[x][y]));
		aliveOriginal[x][y] = (!aliveOriginal[x][y]);
		grid.show();
	}
	public void addCells(int...xy){
		for(int i = 0; i < xy.length - 1; i+= 2){
			grid.drawCell(xy[i], xy[i+1], true);
			aliveOriginal[xy[i]][xy[i + 1]] = true;
		}
	}
	public void gameSetup(){
		String gameType;
		Scanner s = new Scanner(System.in);
		while(true){
			System.out.println("Options: ");
			System.out.println("\tr for random");
			System.out.println("\ts for still life");
			System.out.println("\to for oscillators");
			System.out.println("\tls for lightweight spaceship");
			System.out.println("\te for empty grid");
			System.out.println("\tg for glider");
			
			gameType = s.next();
			if(!(gameType.toLowerCase().equals("g") ||gameType.toLowerCase().equals("r") || gameType.toLowerCase().equals("s") || gameType.toLowerCase().equals("o") || gameType.toLowerCase().equals("ls") || gameType.toLowerCase().equals("e")))
				System.out.println("Input not recognized. Please enter a valid input.");
			else
				break;
		}
		String defaultSize = "";
		switch(gameType.toLowerCase()){
			case "r":
				
				while(true){
					System.out.println("Use a default size? (\"y\" for yes. \"n\" for no)");
					defaultSize = s.next();
					if(defaultSize.toLowerCase().equals("y") || defaultSize.toLowerCase().equals("n"))
						break;
				}
				if(defaultSize.toLowerCase().equals("y")){
					System.out.println("Small or large grid? (\"s\" for small or \"l\" for large)");
					defaultSize = s.next();
					boolean stopR = true;
					while(stopR){
						if(!(defaultSize.toLowerCase().equals("s") || defaultSize.toLowerCase().equals("l")))
							System.out.println("Input not recognized. Please enter a valid input.");
						else
							break;
						defaultSize = s.next();
					}
					while(stopR){
						if(defaultSize.toLowerCase().equals("s")){
							grid = new Grid(500, 500, 25);
							height = 500;
							width = 500;
							cellLength = 25;
							break;
						}
						else if(defaultSize.toLowerCase().equals("l")){
							grid = new Grid(800, 800, 20);
							height = 800;
							width = 800;
							cellLength = 20;
							break;
						}
						defaultSize = s.next();
					}
				}
				else{
					while(true){
						boolean stopR = true;
						while(stopR){
							System.out.println("Enter the width ");
							s = new Scanner(System.in);
							try{
								width = s.nextInt();
								stopR = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
								
						}
				
						stopR = true;
						while(stopR){
							System.out.println("Enter the height ");
							s = new Scanner(System.in);
							try{
								height = s.nextInt();
								stopR = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
							
						}
						
						stopR = true;
						while(stopR){
							System.out.println("Enter the cell length");
							s = new Scanner(System.in);
							try{
								cellLength = s.nextInt();
								stopR = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
								
						}
						//grid = new Grid(height, width, cellLength);
						if(cellLength > height || cellLength > width){
							System.out.println("Cell length can not be greater than the grid width or height");
						}
						else if(cellLength == 0){
							System.out.println("Cell length can not be 0");
						}
						else if(cellLength < 1 || width < 1 || cellLength < 1)
							System.out.println("Grid dimensions must be positive");
						else
							break;
					}
					grid = new Grid(height, width, cellLength);
					
					
				}
				aliveOriginal = new boolean[width/cellLength][height/cellLength];
				grid.enableDoubleBuffering();
				grid.drawGrid();
				grid.drawGenerationCounter(genCount);
				randomInitializer();
				grid.show();
				break;
			case "o":
				while(true){
					System.out.println("Use a default size? (\"y\" for yes. \"n\" for no)");
					defaultSize = s.next();
					if(defaultSize.toLowerCase().equals("y") || defaultSize.toLowerCase().equals("n"))
						break;
				}
				if(defaultSize.toLowerCase().equals("y")){
					System.out.println("Small or large grid? (\"s\" for small or \"l\" for large)");
					defaultSize = s.next();
					boolean stopO = true;
					while(stopO){
						if(!(defaultSize.toLowerCase().equals("s") || defaultSize.toLowerCase().equals("l")))
							System.out.println("Input not recognized. Please enter a valid input.");
						else
							break;
						defaultSize = s.next();
					}
					while(stopO){
						if(defaultSize.toLowerCase().equals("l")){
							grid = new Grid(800, 800, 20);
							height = 800;
							width = 800;
							cellLength = 20;
							break;
						}
						else if(defaultSize.toLowerCase().equals("s")){
							grid = new Grid(500, 500, 25);
							height = 500;
							width = 500;
							cellLength = 25;
							break;
						}
						defaultSize = s.next();
					}
					
				}
				else{
					while(true){
						boolean stopO = true;
						while(stopO){
							System.out.println("Enter the width ");
							s = new Scanner(System.in);
							try{
								width = s.nextInt();
								stopO = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
								
						}
				
						stopO = true;
						while(stopO){
							System.out.println("Enter the height ");
							s = new Scanner(System.in);
							try{
								height = s.nextInt();
								stopO = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
							
						}
						
						stopO = true;
						while(stopO){
							System.out.println("Enter the cell length");
							s = new Scanner(System.in);
							try{
								cellLength = s.nextInt();
								stopO = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
								
						}
						if(cellLength > height || cellLength > width){
							System.out.println("Cell length can not be greater than the grid width or height");
						}
						else if(cellLength == 0){
							System.out.println("Cell length can not be 0");
						}
						else if(cellLength < 1 || width < 1 || cellLength < 1)
							System.out.println("Grid dimensions must be positive");
						else
							break;
					
					}
					grid = new Grid(height, width, cellLength);
					
				}
				aliveOriginal = new boolean[width/cellLength][height/cellLength];
				grid.enableDoubleBuffering();
				grid.drawGrid();
				grid.drawGenerationCounter(genCount);
				oscillatorInitializer();
				grid.show();
				break;
				
			case "s":
				while(true){
					System.out.println("Use a default size? (\"y\" for yes. \"n\" for no)");
					defaultSize = s.next();
					if(defaultSize.toLowerCase().equals("y") || defaultSize.toLowerCase().equals("n"))
						break;
				}
				if(defaultSize.toLowerCase().equals("y")){
					System.out.println("Small or large grid? (\"s\" for small or \"l\" for large)");
					defaultSize = s.next();
					boolean stopS = true;
					while(stopS){
						if(!(defaultSize.toLowerCase().equals("s") || defaultSize.toLowerCase().equals("l")))
							System.out.println("Input not recognized. Please enter a valid input.");
						else
							break;
						defaultSize = s.next();
					}
					while(stopS){
						if(defaultSize.toLowerCase().equals("l")){
							grid = new Grid(800, 800, 20);
							height = 800;
							width = 800;
							cellLength = 20;
							break;
						}
						else if(defaultSize.toLowerCase().equals("s")){
							grid = new Grid(500, 500, 25);
							height = 500;
							width = 500;
							cellLength = 25;
							break;
						}
						defaultSize = s.next();
					}
					
				}
				else{
					while(true){
						boolean stopS = true;
						while(stopS){
							System.out.println("Enter the width ");
							s = new Scanner(System.in);
							try{
								width = s.nextInt();
								stopS = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
							
						}

						stopS = true;
						while(stopS){
							System.out.println("Enter the height");
							s = new Scanner(System.in);
							try{
								height = s.nextInt();
								stopS = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
							
						}
					
						stopS = true;
						while(stopS){
							System.out.println("Enter the cell length");
							s = new Scanner(System.in);
							try{
								cellLength = s.nextInt();
								stopS = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
								
						}
						if(cellLength > height || cellLength > width){
							System.out.println("Cell length can not be greater than the grid width or height");
						}
						else if(cellLength == 0){
							System.out.println("Cell length can not be 0");
						}
						else if(cellLength < 1 || width < 1 || cellLength < 1)
							System.out.println("Grid dimensions must be positive");
						else
							break;
					}
					grid = new Grid(height, width, cellLength);
					
				
				}
				aliveOriginal = new boolean[width/cellLength][height/cellLength];
				grid.enableDoubleBuffering();
				grid.drawGrid();
				grid.drawGenerationCounter(genCount);
				stillLifeInitializer(); 
				grid.show();
				break;
			case "e":
				while(true){
					System.out.println("Use a default size? (\"y\" for yes. \"n\" for no)");
					defaultSize = s.next();
					if(defaultSize.toLowerCase().equals("y") || defaultSize.toLowerCase().equals("n"))
						break;
				}
				if(defaultSize.toLowerCase().equals("y")){
					System.out.println("Small or large grid? (\"s\" for small or \"l\" for large)");
					defaultSize = s.next();
					boolean stopE = true;
					while(stopE){
						if(!(defaultSize.toLowerCase().equals("s") || defaultSize.toLowerCase().equals("l")))
							System.out.println("Input not recognized. Please enter a valid input.");
						else
							break;
						defaultSize = s.next();
					}
					while(stopE){
						if(defaultSize.toLowerCase().equals("l")){
							grid = new Grid(600, 600, 20);
							height = 600;
							width = 600;
							cellLength = 20;
							break;
						}
						else if(defaultSize.toLowerCase().equals("s")){
							grid = new Grid(300, 300, 15);
							height = 300;
							width = 300;
							cellLength = 15;
							
							break;
						}
						defaultSize = s.next();
					}
					
				}
				else{
					while(true){
						boolean stopE = true;
						while(stopE){
							System.out.println("Enter the width ");
							s = new Scanner(System.in);
							try{
								width = s.nextInt();
								stopE = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
							
						}

						stopE = true;
						while(stopE){
							System.out.println("Enter the height");
							s = new Scanner(System.in);
							try{
								height = s.nextInt();
								stopE = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
							
						}
					
						stopE = true;
						while(stopE){
							System.out.println("Enter the cell length");
							s = new Scanner(System.in);
							try{
								cellLength = s.nextInt();
								stopE = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
								
						}
						if(cellLength > height || cellLength > width){
							System.out.println("Cell length can not be greater than the grid width or height");
						}
						else if(cellLength == 0){
							System.out.println("Cell length can not be 0");
						}
						else if(cellLength < 1 || width < 1 || cellLength < 1)
							System.out.println("Grid dimensions must be positive");
						else
							break;
					}
					
					grid = new Grid(height, width, cellLength);
					
				}
				aliveOriginal = new boolean[width/cellLength][height/cellLength];
				grid.enableDoubleBuffering();
				grid.drawGrid();
				grid.drawGenerationCounter(genCount);
				emptyInitializer();
				grid.show();
				break;
			case "ls":
				while(true){
					System.out.println("Use a default size? (\"y\" for yes. \"n\" for no)");
					defaultSize = s.next();
					if(defaultSize.toLowerCase().equals("y") || defaultSize.toLowerCase().equals("n"))
						break;
				}
				if(defaultSize.toLowerCase().equals("y")){
					System.out.println("Small or large grid? (\"s\" for small or \"l\" for large)");
					defaultSize = s.next();
					boolean stopLS = true;
					while(stopLS){
						if(!(defaultSize.toLowerCase().equals("s") || defaultSize.toLowerCase().equals("l")))
							System.out.println("Input not recognized. Please enter a valid input.");
						else
							break;
						defaultSize = s.next();
					}
					while(stopLS){
						if(defaultSize.toLowerCase().equals("l")){
							grid = new Grid(800, 800, 20);
							height = 800;
							width = 800;
							cellLength = 20;
							break;
						}
						else if(defaultSize.toLowerCase().equals("s")){
							grid = new Grid(500, 500, 25);
							height = 500;
							width = 500;
							cellLength = 25;
							break;
						}
						defaultSize = s.next();
					}
					
				}
				else{
					while(true){
						boolean stopLS = true;
						while(stopLS){
							System.out.println("Enter the width ");
							s = new Scanner(System.in);
							try{
								width = s.nextInt();
								stopLS = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
							
						}

						stopLS = true;
						while(stopLS){
							System.out.println("Enter the height");
							s = new Scanner(System.in);
							try{
								height = s.nextInt();
								stopLS = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
							
						}
					
						stopLS = true;
						while(stopLS){
							System.out.println("Enter the cell length");
							s = new Scanner(System.in);
							try{
								cellLength = s.nextInt();
								stopLS = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
								
						}
						if(cellLength > height || cellLength > width){
							System.out.println("Cell length can not be greater than the grid width or height");
						}
						else if(cellLength == 0){
							System.out.println("Cell length can not be 0");
						}
						else if(cellLength < 1 || width < 1 || cellLength < 1)
							System.out.println("Grid dimensions must be positive");
						else
							break;
					}
					grid = new Grid(height, width, cellLength);
					
				}
				aliveOriginal = new boolean[width/cellLength][height/cellLength];
				grid.enableDoubleBuffering();
				grid.drawGrid();
				grid.drawGenerationCounter(genCount);
				lsInitializer(); 
				grid.show();
				break;
			case "g":
				while(true){
					System.out.println("Use a default size? (\"y\" for yes. \"n\" for no)");
					defaultSize = s.next();
					if(defaultSize.toLowerCase().equals("y") || defaultSize.toLowerCase().equals("n"))
						break;
				}
				if(defaultSize.toLowerCase().equals("y")){
					System.out.println("Small or large grid? (\"s\" for small or \"l\" for large)");
					defaultSize = s.next();
					boolean stopG = true;
					while(stopG){
						if(!(defaultSize.toLowerCase().equals("s") || defaultSize.toLowerCase().equals("l")))
							System.out.println("Input not recognized. Please enter a valid input.");
						else
							break;
						defaultSize = s.next();
					}
					while(stopG){
						if(defaultSize.toLowerCase().equals("l")){
							grid = new Grid(800, 800, 20);
							height = 800;
							width = 800;
							cellLength = 20;
							break;
						}
						else if(defaultSize.toLowerCase().equals("s")){
							grid = new Grid(500, 500, 25);
							height = 500;
							width = 500;
							cellLength = 25;
							break;
						}
						defaultSize = s.next();
					}
					
				}
				else{
					while(true){
						boolean stopG = true;
						while(stopG){
							System.out.println("Enter the width ");
							s = new Scanner(System.in);
							try{
								width = s.nextInt();
								stopG = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
							
						}

						stopG = true;
						while(stopG){
							System.out.println("Enter the height");
							s = new Scanner(System.in);
							try{
								height = s.nextInt();
								stopG = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
							
						}
					
						stopG = true;
						while(stopG){
							System.out.println("Enter the cell length");
							s = new Scanner(System.in);
							try{
								cellLength = s.nextInt();
								stopG = false;
							}
							catch(InputMismatchException e){
								System.out.println("Input not recognized. Please enter a valid character.");
							}
								
						}
						if(cellLength > height || cellLength > width){
							System.out.println("Cell length can not be greater than the grid width or height");
						}
						else if(cellLength == 0){
							System.out.println("Cell length can not be 0");
						}
						else if(cellLength < 1 || width < 1 || cellLength < 1)
							System.out.println("Grid dimensions must be positive");
						else
							break;
					}
					grid = new Grid(height, width, cellLength);
					
				}
				aliveOriginal = new boolean[width/cellLength][height/cellLength];
				grid.enableDoubleBuffering();
				grid.drawGrid();
				grid.drawGenerationCounter(genCount);
				gliderInitializer(); 
				grid.show();
				break;
				
		}
		
		System.out.println("Options for running");
		System.out.println("\tr to run");
		System.out.println("\ts to step through a generation");
	}
	public void step(){
		boolean[][] alive = new boolean[height / cellLength][width / cellLength];
		for(int r = 0; r < alive.length; r++){
			for(int c = 0; c < alive[r].length; c++){
				alive[r][c] = aliveOriginal[r][c];
			}
		}
		for(int r = 0; r < alive.length; r++){
			for(int c = 0; c < alive[r].length; c++){
				int neighborsAlive = 0; //if(aliveOriginal[(r + alive.length) % alive.length][(c + alive[0].length) % alive[0].length])
				//counts number of alive neighbors 
				if(aliveOriginal[(r + alive.length + 1) % alive.length][(c + alive[0].length + 1) % alive[0].length])
					neighborsAlive++;
				if(aliveOriginal[(r + alive.length + 1) % alive.length][(c + alive[0].length) % alive[0].length])
					neighborsAlive++;
				if(aliveOriginal[(r + alive.length + 1) % alive.length][(c + alive[0].length - 1) % alive[0].length])
					neighborsAlive++;
				if(aliveOriginal[(r + alive.length) % alive.length][(c + alive[0].length + 1) % alive[0].length])
					neighborsAlive++;
				if(aliveOriginal[(r + alive.length) % alive.length][(c + alive[0].length - 1) % alive[0].length])
					neighborsAlive++;
				if(aliveOriginal[(r + alive.length - 1) % alive.length][(c + alive[0].length + 1) % alive[0].length])
					neighborsAlive++;
				if(aliveOriginal[(r + alive.length - 1) % alive.length][(c + alive[0].length) % alive[0].length])
					neighborsAlive++;
				if(aliveOriginal[(r + alive.length - 1) % alive.length][(c + alive[0].length - 1) % alive[0].length])
					neighborsAlive++;
			
				//System.out.printlnprintln("Cell: " + r + ", " + c);
				//System.out.printlnprintln(aliveOriginal[r][c]);
				//System.out.printlnprintln(neighborsAlive + "\n");
				
				if(aliveOriginal[r][c]){//determines if a cell is alive or dead
					if(neighborsAlive < 2){
						grid.drawCell(r, c, false);
						//System.out.println("Drawed cell false");
						alive[r][c] = false;
					}
					else if(neighborsAlive > 3){
						grid.drawCell(r, c, false);
						//System.out.println("Drawed cell false");
						alive[r][c] = false;
					}
				}
				else{
					if(neighborsAlive == 3){
						alive[r][c] = true;
						grid.drawCell(r, c, true);
						//System.out.println("Added live cell at " + r + " " + c);
					}	
				}
			}	
		}
		aliveOriginal = alive;
		genCount = genCount.intValue() + 1;
		grid.drawGenerationCounter(genCount);
		grid.show();
		
	}
	public synchronized void mouse(){
		if(run){
			try{
				wait();
			}
			catch(InterruptedException e){}
		}
		else{
			if(grid.mousePressed()){
				grid.drawCell(grid.calculateRowFromMouse() , grid.calculateColumnFromMouse(), (!aliveOriginal[grid.calculateRowFromMouse()][grid.calculateColumnFromMouse()]));
				aliveOriginal[grid.calculateRowFromMouse()][grid.calculateColumnFromMouse()] = (!aliveOriginal[grid.calculateRowFromMouse()][grid.calculateColumnFromMouse()]);
				grid.show();
				while(grid.mousePressed()){}
			}
		}
	}
	public void resize(){
		
	}
	public synchronized void runMode(){
		if(run){
			resize();
			step();
			
			//System.out.println("ran");
		}
		else{
			try{
				System.out.println("run waiting");
				wait();
			}
			catch(InterruptedException e){}
		}
	}
	public void clearGrid(){
		//grid = new Grid(width, height, cellLength);
		//grid.clear();
		aliveOriginal = new boolean[width/cellLength][height/cellLength];
		grid.drawGrid();
		emptyInitializer();
		grid.enableDoubleBuffering();
		
		grid.show();
	}
	public void input(){
		//mouse.start();
		Scanner s = new Scanner(System.in);
		game.start();
		String option;
		option = s.next();
		
		switch(option.toLowerCase()){
			case "r":
				System.out.println("Speed? (time between ticks)");
				boolean stop1 = true;
				while(stop1){
					s = new Scanner(System.in);
					try{
						
						game.speed = s.nextInt();
						stop1 = false;
					}
					catch(InputMismatchException e){
						System.out.println("Input not recognized. Please enter a valid character.");
					}
				}
				
				System.out.println("\tenter \"stop\" to go back to step mode");
				//game.wait();
				run = true;
				System.out.println("\tto change speed, type \"speed\"");
				break;
			case "speed":
				boolean stop = true;
				System.out.println("Speed?");
				while(stop){
					s = new Scanner(System.in);
					try{
						game.speed = s.nextInt();
						stop = false;
					}
					catch(InputMismatchException e){
						System.out.println("Input not recognized. Please enter a valid character.");
					}	
				}
				break;
			case "stop":
				run  = false;
			
				break;
			case "s":
				run = false;
				step();
				break;
			case "clear":
				clearGrid();
				break;
			default:
				System.out.println("Input not recognized.");
		}
		System.out.println("input ends");
		synchronized(this){
			notifyAll();
		}		
	}
		
	
}