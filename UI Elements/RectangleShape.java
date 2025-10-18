import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Rectangle Shape
 * 
 * @author Nurmukhammed Aissauyt
 */

public class RectangleShape extends Shapes implements MouseListener, MouseMotionListener {



    public RectangleShape(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color,"Rectangle"); // Assuming Shapes constructor
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        this.setLocation(x, y);
        this.setSize(w, h);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;   
        g2d.setColor(getColor()); 
        g2d.fillRect(0, 0, getWidth(), getHeight());
        this.getWidth();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
        // Check if the press is near the corner for resizing
        if ((Math.abs(e.getX() - getWidth()) < cornerBoundary && Math.abs(e.getY() - getHeight()) < cornerBoundary) || (e.getX() < cornerBoundary && e.getY() < cornerBoundary)) {
            resizing = true;
        } else {
            dragging = true;
        }
    }
   

    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX() - lastX;
        int dy = e.getY() - lastY;
        int newX = e.getX() - getWidth();
        int newY = e.getY() -getHeight();
        

        if (dragging) {
            setLocation(getX() + newX, getY() + newY);
        }else if (resizing) {
            setSize(getWidth() + dx, getHeight() + dy);
        }

        lastX = e.getX();
        lastY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        resizing = false;
        dragging = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
}