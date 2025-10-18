import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/**
 * Shapes
 * 
 * @author Nurmukhammed Aissauyt
 */

public abstract class Shapes extends JPanel{
    protected int x;
    protected int y;
    protected int h;
    protected int w;
    protected Color c;
    protected String name;
    protected boolean dragging = false;
    protected boolean resizing = false;
    protected int lastX, lastY;
    protected int cornerBoundary = 10;

    public Shapes(int x, int y, int h, int w,Color c,String name) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.c = c;
        this.name = name;
    }
  

    public int getShapeX() {
        return this.getX();
    }

    public int getShapeY() {
        return this.getY();
    }

    public int getShapeH() {
        return this.getHeight();
    }

    public int getShapeW() {
        return this.getWidth();
    }
    
    // public void setShapeX(int x) {
    //     this.x = x;
    // }

    // public void setShapeY(int y) {
    //     this.y = y;
    // }

    // public void setShapeH(int h) {
    //     this.h = h;
    // }

    // public void setShapeW(int w) {
    //     this.w = w;
    // }

    public void setColor(Color c) {
        this.c= c;
    }
    public Color getColor() {
        return c;
    }
    public String getName(){
        return name;
    }
}
