import java.time.chrono.JapaneseEra;
import java.io.File;
import java.io.IOException;

import javax.swing.event.DocumentListener;
import javax.swing.text.StyledEditorKit;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;

import java.awt.image.BufferedImage;

/**
 * Textbox
 * 
 * @author Nurmukhammed Aissauyt
 */

// Class representing an TextBox
public class TextBox implements MouseListener, MouseMotionListener {
    // Properties for TextBox like size, position, text, font.
    private JTextPane textboxTextPane;
    private JPanel textPanel = new JPanel(new BorderLayout());
    private boolean dragging = false;
    private boolean resizing = false;
    private int lastX, lastY;
    private int cornerBoundary = 10;

    public TextBox(JTextPane textboxTextPane,JPanel textPanel){
        this.textboxTextPane = textboxTextPane;
        this.textPanel=textPanel;
        this.textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        // Add mouse listeners for interaction
        this.textPanel.addMouseListener(this);
        this.textPanel.addMouseMotionListener(this);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
        // Check if the press is near the corner for resizing
        if ((Math.abs(e.getX() - textPanel.getWidth()) < cornerBoundary && Math.abs(e.getY() - textPanel.getHeight()) < cornerBoundary) || (e.getX() < cornerBoundary && e.getY() < cornerBoundary)) {
            resizing = true;
        } else {
            dragging = true;
        }
    }
   

    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX() - lastX;
        int dy = e.getY() - lastY;
        int newX = e.getX() - textPanel.getWidth();
        int newY = e.getY() - textPanel.getHeight();
        

        if (dragging) {
            textPanel.setLocation(textPanel.getX() + newX, textPanel.getY() + newY);
        }else if (resizing) {
            textPanel.setSize(textPanel.getWidth() + dx, textPanel.getHeight() + dy);
            textPanel.revalidate();
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


    public int getX(){
        return textPanel.getX();
    }
    public int getY(){
        return textPanel.getY();
    }
    public int getW(){
        return textPanel.getWidth();
    }
    public int getH(){
        return textPanel.getHeight();
    }
    public JTextPane getText(){
        return textboxTextPane;
    } 
    public JPanel getPanel(){
        return textPanel;
    }    


}




