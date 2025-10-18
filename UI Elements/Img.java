import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Image
 *
 * @author Nurmukhammed Aissauyt
 */

// Class representing an Image
public class Img implements MouseListener, MouseMotionListener{
    private BufferedImage image;
    private JPanel imagePanel;
    private boolean dragging = false;
    private boolean resizing = false;
    private int lastX, lastY;
    private int cornerBoundary = 10;


    public Img(BufferedImage image, JPanel imagePanel) {
        this.image = image;
        this.imagePanel = imagePanel;
        this.imagePanel.addMouseListener(this);
        this.imagePanel.addMouseMotionListener(this);


    }
    @Override
    public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
        // Check if the press is near the corner for resizing
        if ((Math.abs(e.getX() - imagePanel.getWidth()) < cornerBoundary && Math.abs(e.getY() - imagePanel.getHeight()) < cornerBoundary) || (e.getX() < cornerBoundary && e.getY() < cornerBoundary)) {
            resizing = true;
        } else {
            dragging = true;
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX() - lastX;
        int dy = e.getY() - lastY;
        int newX = e.getX() - imagePanel.getWidth();
        int newY = e.getY() - imagePanel.getHeight();


        if (dragging) {
            imagePanel.setLocation(imagePanel.getX() + newX, imagePanel.getY() + newY);
        }else if (resizing) {
            imagePanel.setSize(imagePanel.getWidth() + dx, imagePanel.getHeight() + dy);
            imagePanel.revalidate();
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


    public BufferedImage getImage() {
        return image;
    }

    public int getXPosition() {
        return imagePanel.getX();
    }

    public int getYPosition() {
        return imagePanel.getY();
    }
    public int getH() {
        return imagePanel.getHeight();
    }


    public int getW() {
        return imagePanel.getWidth();
    }
    public JPanel getImagePanel() {
        return imagePanel;
    }


}




