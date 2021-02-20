import edu.jenks.gol.view.*;
import edu.princeton.cs.introcs.*;

public class GameOfLifeGame extends Thread{
	private final int height;
	private final int width;
	private final int cellLength;
	public boolean run = true;
	public Integer genCount = 1;
	GameOfLifeViewStdDraw draw;
	boolean[][] aliveOriginal;
	public GameOfLifeGame(int h, int w, int c, GameOfLifeViewStdDraw in){
		height = h;
		width = w;
		cellLength = c;
		draw = in;
		aliveOriginal = new boolean[h / c][w / c];
	}
	public void start(){
		
	}
	public void run(){
		while(true){
			step();
			long tempTime = System.currentTimeMillis();
			System.out.println("thread ran");
			while(System.currentTimeMillis() - tempTime  < 1000){}
		}
	}
	public void randomInitializer(){
		for(int r = 0; r < width / cellLength; r++){
			for(int c = 0; c < height / cellLength; c++){
				if(((int) (Math.random() * 2)) == 0){
					draw.drawCell(r, c, false);
					aliveOriginal[r][c] = false;
				}
				else{
					draw.drawCell(r, c, true);
					aliveOriginal[r][c] = true;
				}
			}
		}
	}
	public void emptyInitializer(){
		for(int r = 0; r < width / cellLength; r++){
			for(int c = 0; c < height / cellLength; c++){
				draw.drawCell(r, c, false);
				aliveOriginal[r][c] = false;
			}
		}
	}
	public void addCells(int...xy){
		for(int i = 0; i < xy.length - 1; i+= 2){
			draw.drawCell(xy[i], xy[i+1], true);
			aliveOriginal[xy[i]][xy[i + 1]] = true;
		}
	}
	/* public void addCellsA(int...xy){
		for(int i = 0; i < xy.length - 1; i++){
			aliveOriginal[xy[i]][xy[i + 1]] = true;
		}
	} */
	public void stillLifeInitializer(){
		addCells(1,1, 1,2, 2,1, 2,2);//square
	
		
		addCells(2,5, 2,6, 3,4, 3,7, 4,5, 4,6);//beehive
	
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
				int neighborsAlive = 0;
				//counts number of alive neighbors 
				if(r == 0 || r == alive.length - 1 || c == 0 || c == alive[r].length - 1){
					//System.out.println("first neighbor counter");
					if(r == 0 && c == 0){
						
						if(aliveOriginal[r + 1][c + 1])
							neighborsAlive++;
						if(aliveOriginal[r + 1][c])
							neighborsAlive++;
						if(aliveOriginal[r + 1][alive[0].length - 1])
							neighborsAlive++;
						if(aliveOriginal[r][c + 1])
							neighborsAlive++;
						if(aliveOriginal[r][alive[0].length - 1])
							neighborsAlive++;
						if(aliveOriginal[alive.length - 1][c + 1])
							neighborsAlive++;
						if(aliveOriginal[alive.length - 1][c])
							neighborsAlive++;
						if(aliveOriginal[alive.length - 1][alive[0].length - 1])
							neighborsAlive++;
						
					}
					
					else if(r == 0 && c == alive[0].length - 1){
						if(aliveOriginal[r + 1][0])
							neighborsAlive++;
						if(aliveOriginal[r + 1][c])
							neighborsAlive++;
						if(aliveOriginal[r + 1][c - 1])
							neighborsAlive++;
						if(aliveOriginal[r][0])
							neighborsAlive++;
						if(aliveOriginal[r][c - 1])
							neighborsAlive++;
						if(aliveOriginal[alive.length - 1][0])
							neighborsAlive++;
						if(aliveOriginal[alive.length - 1][c])
							neighborsAlive++;
						if(aliveOriginal[alive.length - 1][c - 1])
							neighborsAlive++;
					}
					
					else if(r == alive.length - 1 && c == 0){
						if(aliveOriginal[0][c + 1])
							neighborsAlive++;
						if(aliveOriginal[0][c])
							neighborsAlive++;
						if(aliveOriginal[0][alive[0].length - 1])
							neighborsAlive++;
						if(aliveOriginal[r][c + 1])
							neighborsAlive++;
						if(aliveOriginal[r][alive[0].length - 1])
							neighborsAlive++;
						if(aliveOriginal[r - 1][c + 1])
							neighborsAlive++;
						if(aliveOriginal[r - 1][c])
							neighborsAlive++;
						if(aliveOriginal[r - 1][alive[0].length - 1])
							neighborsAlive++;
					}
						
					else if(r == alive.length - 1 && c == alive[0].length - 1){
						if(aliveOriginal[0][0])
							neighborsAlive++;
						if(aliveOriginal[0][c])
							neighborsAlive++;
						if(aliveOriginal[0][c - 1])
							neighborsAlive++;
						if(aliveOriginal[r][0])
							neighborsAlive++;
						if(aliveOriginal[r][c - 1])
							neighborsAlive++;
						if(aliveOriginal[r - 1][0])
							neighborsAlive++;
						if(aliveOriginal[r - 1][c])
							neighborsAlive++;
						if(aliveOriginal[r - 1][c - 1])
							neighborsAlive++;
					}
					
				}
				
				else{
				//	System.out.println("default neighbor counter");
					if(aliveOriginal[r + 1][c + 1])
						neighborsAlive++;
					if(aliveOriginal[r + 1][c])
						neighborsAlive++;
					if(aliveOriginal[r - 1][c])
						neighborsAlive++;
					if(aliveOriginal[r][c + 1])
						neighborsAlive++;
					if(aliveOriginal[r][c - 1])
						neighborsAlive++;
					if(aliveOriginal[r - 1][c + 1])
						neighborsAlive++;
					if(aliveOriginal[r - 1][c])
						neighborsAlive++;
					if(aliveOriginal[r - 1][c - 1])
						neighborsAlive++;
				}
			
				/* System.out.println("Cell: " + r + ", " + c);
				System.out.println(aliveOriginal[r][c]);
				System.out.println(neighborsAlive + "\n"); */
				
				if(aliveOriginal[r][c]){//determines if a cell is alive or dead
					if(neighborsAlive < 2){
						draw.drawCell(r, c, false);
						//System.out.println("Drawed cell false");
						alive[r][c] = false;
					}
					else if(neighborsAlive > 3){
						draw.drawCell(r, c, false);
						//System.out.println("Drawed cell false");
						alive[r][c] = false;
					}
				}
				else{
					if(neighborsAlive == 3){
						alive[r][c] = true;
						draw.drawCell(r, c, true);
						//System.out.println("Added live cell at " + r + " " + c);
					}	
				}
			}	
		}
		aliveOriginal = alive;
		genCount = genCount.intValue() + 1;
		draw.drawGenerationCounter(genCount);
		draw.show();
		
	}
	
	/* public void run(){
		boolean[][] alive = new boolean[height / cellLength][width / cellLength];
		for(int r = 0; r < alive.length; r++){
			for(int c = 0; c < alive[r].length; c++){
				alive[r][c] = aliveOriginal[r][c];
			}
		}
		while(run){
			for(int r = 0; r < alive.length; r++){
				for(int c = 0; c < alive[r].length; c++){
					int neighborsAlive = 0;
					//counts number of alive neighbors 
					if(r == 0 || r == alive.length - 1 || c == 0 || c == alive[r].length - 1){
						if(r == 0 && c == 0){
							
							if(aliveOriginal[r + 1][c + 1])
								neighborsAlive++;
							if(aliveOriginal[r + 1][c])
								neighborsAlive++;
							if(aliveOriginal[r + 1][alive[0].length - 1])
								neighborsAlive++;
							if(aliveOriginal[r][c + 1])
								neighborsAlive++;
							if(aliveOriginal[r][alive[0].length - 1])
								neighborsAlive++;
							if(aliveOriginal[alive.length - 1][c + 1])
								neighborsAlive++;
							if(aliveOriginal[alive.length - 1][c])
								neighborsAlive++;
							if(aliveOriginal[alive.length - 1][alive[0].length - 1])
								neighborsAlive++;
							
						}
						
						else if(r == 0 && c == alive[0].length - 1){
							if(aliveOriginal[r + 1][0])
								neighborsAlive++;
							if(aliveOriginal[r + 1][c])
								neighborsAlive++;
							if(aliveOriginal[r + 1][c - 1])
								neighborsAlive++;
							if(aliveOriginal[r][0])
								neighborsAlive++;
							if(aliveOriginal[r][c - 1])
								neighborsAlive++;
							if(aliveOriginal[alive.length - 1][0])
								neighborsAlive++;
							if(aliveOriginal[alive.length - 1][c])
								neighborsAlive++;
							if(aliveOriginal[alive.length - 1][c - 1])
								neighborsAlive++;
						}
						
						else if(r == alive.length - 1 && c == 0){
							if(aliveOriginal[0][c + 1])
								neighborsAlive++;
							if(aliveOriginal[0][c])
								neighborsAlive++;
							if(aliveOriginal[0][alive[0].length - 1])
								neighborsAlive++;
							if(aliveOriginal[r][c + 1])
								neighborsAlive++;
							if(aliveOriginal[r][alive[0].length - 1])
								neighborsAlive++;
							if(aliveOriginal[r - 1][c + 1])
								neighborsAlive++;
							if(aliveOriginal[r - 1][c])
								neighborsAlive++;
							if(aliveOriginal[r - 1][alive[0].length - 1])
								neighborsAlive++;
						}
						
						else if(r == alive.length - 1 && c == alive[0].length - 1){
							if(aliveOriginal[0][0])
								neighborsAlive++;
							if(aliveOriginal[0][c])
								neighborsAlive++;
							if(aliveOriginal[0][c - 1])
								neighborsAlive++;
							if(aliveOriginal[r][0])
								neighborsAlive++;
							if(aliveOriginal[r][c - 1])
								neighborsAlive++;
							if(aliveOriginal[r - 1][0])
								neighborsAlive++;
							if(aliveOriginal[r - 1][c])
								neighborsAlive++;
							if(aliveOriginal[r - 1][c - 1])
								neighborsAlive++;
						}
						
					}
					
					else{
							if(aliveOriginal[r + 1][c + 1])
								neighborsAlive++;
							if(aliveOriginal[r + 1][c])
								neighborsAlive++;
							if(aliveOriginal[r - 1][c])
								neighborsAlive++;
							if(aliveOriginal[r][c + 1])
								neighborsAlive++;
							if(aliveOriginal[r][c - 1])
								neighborsAlive++;
							if(aliveOriginal[r - 1][c + 1])
								neighborsAlive++;
							if(aliveOriginal[r - 1][c])
								neighborsAlive++;
							if(aliveOriginal[r - 1][c - 1])
								neighborsAlive++;
					}
				
					
					if(aliveOriginal[r][c]){//determines if a cell is alive or dead
						if(neighborsAlive < 2){
							draw.drawCell(r, c, false);
							//System.out.println("Drawed cell false");
							alive[r][c] = false;
						}
						else if(neighborsAlive > 3){
							draw.drawCell(r, c, false);
							//System.out.println("Drawed cell false");
							alive[r][c] = false;
						}
					}
					else{
						if(neighborsAlive == 3){
							alive[r][c] = true;
							draw.drawCell(r, c, true);
						}	
					}
				}
			}
			aliveOriginal = alive;
			genCount = genCount.intValue() + 1;
			draw.drawGenerationCounter(genCount);
			draw.show();
			long tempTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - tempTime  < 200){}
			
		}
		
	} */
	
	
}