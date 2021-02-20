//import edu.princeton.cs.introcs.*;
import java.util.*;
public class Runner implements Runnable{
	Thread thread;
	GameOfLife life;
	public Runner(GameOfLife l){
		life = l;
	}
	public void start() {
      //System.out.println("Thread Started");
      if (thread == null) {
         thread = new Thread(this);
         thread.start();
      }
	}
	public void run(){
		while(true){
			//System.out.println("calling input");
			life.input();
			
		}
	}
}