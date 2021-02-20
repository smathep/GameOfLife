//import edu.princeton.cs.introcs.*;
import java.util.*;
public class MouseMonitor implements Runnable{
	GameOfLife life;
	Thread thread;
	public MouseMonitor(GameOfLife l){
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
			life.mouse();
	   }
   }
}