
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ru.dubov.primitives.Point;

public class Voronoi {
	
    private static int SIZE = 512;
    private Point[][] nearest = new Point[SIZE][SIZE];
    private int N = 100;
    private Draw draw;


    /**
     * Constructor
     */
    public Voronoi() {
    	//init
    	draw = new Draw();
        draw.setCanvasSize(SIZE, SIZE);
        draw.setXscale(0, SIZE);
        draw.setYscale(0, SIZE);
        draw.show(20);
        
        //chose window
    	JTextField sizeTextField = new JTextField("20",10);
    	String message = "Chose!";  
    	Object[] params = {message, sizeTextField};  
    	JOptionPane.showConfirmDialog(null, params,
    			"Setting", JOptionPane.DEFAULT_OPTION);
    	
    	N = Integer.parseInt(sizeTextField.getText());
    	
        //add points one by one
        for(int i = 0;i<N;i++){
        	addToDiagram(Math.random()*(SIZE), Math.random()*(SIZE));
        }
    }

    /**
     * Process a point and compute diagram
     * 
     * @param x
     * @param y
     */
    public void addToDiagram(double x, double y) {
    	//variables
        Point p = new Point(x, y);

        // compare each pixel (i, j) and find nearest point
        draw.setPenColor(Color.getHSBColor((float) Math.random(), .7f, .7f));
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
            	//pixel q
                Point q = new Point(i, j);
                if ((nearest[i][j] == null) || (q.dist(p) < q.dist(nearest[i][j]))) {
                    nearest[i][j] = p;
                    //draw pixel
                    draw.filledSquare(i+0.5, j+0.5, 0.5);
                }
            }
        }

        // draw the point afterwards
        draw.setPenColor(Color.BLACK);
        draw.filledCircle(x, y, 4);
        draw.show(20);
    }

    /**
     * Main method
     * 
     * @param args
     */
    public static void main(String[] args) {
        new Voronoi();
    }
 
   
}
