//import edu.princeton.cs.introcs.*;
public class Grid{
	int width;
	int height;
	int cellLength;
	int totalHeight;
	public Grid(int w, int h, int c){
		width = w;
		height = h;
		cellLength = c;
		totalHeight = height + 35;
	}
	/* public static void main(String[] args) {
		Grid g = new Grid(500, 500, 25);
		g.drawGrid();
    } */
	public void drawGrid(){
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, totalHeight);
		//StdDraw.setPenRadius(0.05);
		for(int r = cellLength; r < width; r += cellLength){
			StdDraw.line(0, r, width, r);
			//System.out.println("horizontal line printed");
		}
		StdDraw.line(0, height, width, height);
		for(int c = cellLength; c < height; c+= cellLength){
			StdDraw.line(c, 0, c, height);
		}
		//StdDraw.line(width / 4, height / 2, (width / 4) * 3, height / 2);
		//StdDraw.line(2, 2.5, 2, 2.5);
		//StdDraw.line(2, 1, 2, 0);
	}
	public void drawCell(int x, int y, boolean a){
		x = cellLength * x + cellLength / 2;
		y = cellLength * y + cellLength / 2;
		if(a){
			StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
			StdDraw.filledSquare(x, y, (cellLength / 2) - 1);
		}
		else{
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledSquare(x, y, (cellLength / 2) - 1);
		}
	}
	public void drawGenerationCounter(Integer count){
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(width / 2, totalHeight - 15, width / 2, (totalHeight - height) / 2);
		StdDraw.setPenColor(StdDraw.BLACK);
		String strC = "Generation: " + count;
		StdDraw.text(width / 2, totalHeight - 18, strC);
	}
	public boolean mousePressed(){
		return StdDraw.isMousePressed();
	}
	public int calculateRowFromMouse(){
		return (int)StdDraw.mouseX() / cellLength;
	}
	public int calculateColumnFromMouse(){
		return (int)StdDraw.mouseY() / cellLength;
	}
	public void show(){
		StdDraw.show();
	}
	public void enableDoubleBuffering(){
		StdDraw.enableDoubleBuffering();
	}
	public void disableDoubleBuffering(){
		StdDraw.disableDoubleBuffering();
	}
}