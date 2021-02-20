//import edu.princeton.cs.introcs.*;
import java.util.*;
public class GameOfLifeGame implements Runnable{

	//public boolean run = false;
	int speed = 100;
	GameOfLife life;
	Thread thread;
	public GameOfLifeGame(GameOfLife l){
		life = l;
	}
	public void start() {
      //System.out.println("Thread Started");
      if (thread == null) {
         thread = new Thread (this);
         thread.start();
      }
   }
	public void run(){
		while(true){
			
				//System.out.println("Thread running");
			life.runMode();
			try{
				Thread.sleep(speed);
				//System.out.println("sleeping");
			}
			catch(InterruptedException e){}
				
			
		}
		
	}
	
	
	
	
	
}