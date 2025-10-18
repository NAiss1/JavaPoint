import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.html.ImageView;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Ellipse2D;
import javax.swing.event.DocumentEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.text.StyledEditorKit;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.BufferedImage;


/**
 * Canvas
 *
 * @author Nurmukhammed Aissauyt
 */


class Canvas extends JPanel {
    private Slide currentSlide;
    private int x = 0;
    private int y = 0;
    private JPanel rectanglePanel = new JPanel();
    private JTextPane currentActiveTextBox;
    private JComponent currentActiveObject;

    public Canvas(JFrame frame) {

        Color PRESETBLUE = new Color(152, 186, 213);
        Color PRESETLIGHTBLUE = new Color(178, 203, 222);
        Color PRESETlightestBLUE = new Color(198, 211, 227);
        Color PRESETlighestesBLUE = new Color(216, 225, 232);
        Color PRESETdarkBLUE = new Color(48, 70, 116);

        this.setBorder(BorderFactory.createLineBorder(PRESETlightestBLUE));
        this.setPreferredSize(new Dimension(1000, 600));
        this.setLayout(null);
        this.setBackground(PRESETlightestBLUE);

        int canvasWidth = (int) this.getPreferredSize().getWidth();
        int canvasHeight = (int) this.getPreferredSize().getHeight();

        int h = frame.getHeight();
        int w = frame.getWidth();


        rectanglePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rectanglePanel.setBounds(w/2-canvasWidth*2/4, h/2-canvasHeight*2/4,  canvasWidth, canvasHeight); // Position the rectangle inside the canvas
        rectanglePanel.setBackground(Color.WHITE);
        rectanglePanel.setOpaque(true);
        rectanglePanel.setLayout(null);

        this.add(rectanglePanel,BorderLayout.CENTER);
        frame.getContentPane().add(this);
        this.revalidate();
        this.repaint();

    }

    public void setCurrentSlide(Slide slide) {
        currentSlide = slide;
        rectanglePanel.removeAll();
    }

    public Slide getCurrentSlide() {
        return currentSlide;
    }

    public JPanel getPanel(){
        return rectanglePanel;
    }
    public void draw(){
        loadImages();
        loadShapes();
        loadTextboxes();
        this.revalidate();
        this.repaint();
    }

    public void loadTextboxes(){
        for (TextBox text : currentSlide.getTextboxes()){
            text.getText().setVisible(true);


            // textPanel.setBounds(text.getX(), text.getY(), text.getWidth(), text.getHeight());


            text.getText().setOpaque(false);
            text.getText().setEditable(true);
            rectanglePanel.add(text.getPanel());
            this.revalidate();
            this.repaint();
        }


            

    }
    public void loadImages(){
        
        for (Img img : currentSlide.getImages()){
            rectanglePanel.add(img.getImagePanel());
            this.getPanel().revalidate();
            this.getPanel().repaint();

            System.out.println("Added Image");
        }

      
        
    }
    public void loadShapes(){
        System.out.println("Added shape");
        
        for (Shapes shape: currentSlide.getShapes()){

            rectanglePanel.add(shape);
            this.getPanel().revalidate();
            this.getPanel().repaint();
            System.out.println(shape);

            System.out.println("Added shape");
        }


      
        
    }
    
    public void setcurrentActiveTextBox(JTextPane textbox){
        currentActiveTextBox=textbox;
    }

    public BufferedImage getBufferedImage(){
        BufferedImage slidepreviewImage = new BufferedImage((int) this.getPreferredSize().getWidth(), (int) this.getPreferredSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = slidepreviewImage.createGraphics();
        this.paint(graphics); 
        graphics.dispose();
        return slidepreviewImage;

    }


    public JTextPane getcurrentActiveTextBox() {
        return currentActiveTextBox;
    }

}