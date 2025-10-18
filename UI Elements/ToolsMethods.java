import java.io.File;
import java.io.IOException;

import javax.swing.event.DocumentListener;
import javax.swing.text.StyledEditorKit;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.event.DocumentEvent;

import java.awt.image.BufferedImage;


public class ToolsMethods implements ToolsInterface {
    //implement the actual usage of the tools in here please
    public void addImage(Canvas canvas){

        // choose image through built-in file browser
        JFileChooser fileChooser = new JFileChooser();
        int choice = fileChooser.showOpenDialog(canvas.getPanel());
        File imgFile = fileChooser.getSelectedFile();
        System.out.println(imgFile.getPath());

        java.awt.Image image;
        try {
            BufferedImage bufferedImg = ImageIO.read(imgFile);
            // canvas.getCurrentSlide().addImage(bufferedImg);
            image = ImageIO.read(imgFile);

            java.awt.Image scaledImage = scaleImage(image, 500, -1);
            // scale image to correct size

            // set image choice as new icon
            ImageIcon imgIcon = new ImageIcon(scaledImage);

            System.out.println(imgIcon.getIconHeight());
            System.out.println(imgIcon.getIconWidth());
            System.out.println(imgIcon.getDescription());

            
            JLabel imageLabel = new JLabel(imgIcon);


            JPanel imagePanel = new JPanel();

            imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            imagePanel.add(imageLabel);
            
            imagePanel.setBounds(250, 60, imgIcon.getIconWidth(), imgIcon.getIconHeight());
            Img img = new Img(bufferedImg,imagePanel);
            canvas.getCurrentSlide().addImage(img);

            // add to canvas + update
            canvas.getPanel().add(imagePanel);
            canvas.getPanel().revalidate();
            canvas.getPanel().repaint();

            System.out.println("Added Image");


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // scale image function
    private java.awt.Image scaleImage(java.awt.Image ogImg, int width, int height){
        return ogImg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
    }


    public void addShape(Canvas canvas, Component invoker){
        JPanel panel = canvas.getPanel();
        JPopupMenu shapeMenu = new JPopupMenu("Add Shape");
        JMenuItem rectangleItem = new JMenuItem("Rectangle");
        JMenuItem ballItem = new JMenuItem("Ball");
        JMenuItem strightLineItem = new JMenuItem("Stright line");
        JMenuItem triangleItem = new JMenuItem("Triangle");

        shapeMenu.add(rectangleItem);
        shapeMenu.add(ballItem);
//        shapeMenu.add(strightLineItem);
        shapeMenu.add(triangleItem);


        rectangleItem.addActionListener(e -> {
            RectangleShape rectangle = new RectangleShape(350,50,80,80,Color.RED);
            canvas.getCurrentSlide().addShape(rectangle);
            panel.add(rectangle);
            panel.revalidate();
            panel.repaint();
        });
        strightLineItem.addActionListener(e -> {
            System.out.println("pass stright line");
            // StraightLineShape strightLine = new StraightLineShape(350,50,80,80);
            // panel.add(strightLine);
            // panel.revalidate();
            // panel.repaint();
        });
        triangleItem.addActionListener(e -> {
            TriangleShape triangle = new TriangleShape(350,50,80,80,Color.GREEN);
            canvas.getCurrentSlide().addShape(triangle);

            panel.add(triangle);
            panel.revalidate();
            panel.repaint();
        });

        ballItem.addActionListener(e -> {
            BallShape ball = new BallShape(350,50,80,80,Color.BLUE);
            canvas.getCurrentSlide().addShape(ball);

            panel.add(ball);
            panel.revalidate();
            panel.repaint();
        });

        shapeMenu.show(invoker, 0, invoker.getHeight());
    }

    public void addTextBox(Canvas canvas){
        JPanel panel = canvas.getPanel();

        Font font = new Font("Arial", Font.BOLD, 16); // Customize the font

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        JTextPane textPane = new JTextPane();

        textBoxSetup(canvas,textPane);

        textPane.setFont(font);
        textPane.setEditable(true);
        // textPane.setDragEnabled(true);
        textPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textPane.setText("Click to add Text");
        textPane.setName("EMPTY");
        textPane.setVisible(true);
        textPane.setOpaque(false);

        textPanel.add(textPane, BorderLayout.CENTER);

        textPanel.setBounds(250, 50, 100, 100);

        panel.setLayout(null);

        TextBox textbox = new TextBox(textPane, textPanel);
        canvas.getCurrentSlide().addTextbox(textbox);

        panel.add(textPanel);
        panel.revalidate();
        panel.repaint();

        /*canvas.getPanel().add(textPane);
        canvas.add(canvas.getPanel());
        canvas.revalidate();
        canvas.repaint(); */


    }
    private void textBoxSetup(Canvas canvas,JTextPane text){
        text.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // documentChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // documentChanged();
            }

            private void documentChanged() {
                text.setName("NOTempty");

                // This method is called whenever the text in the JTextPane changes.
                System.out.println("TextPane content changed: " + text.getText());
                // You can add additional logic here if needed
            }
        });

        InputMap inputMap = text.getInputMap();
        ActionMap actionMap = text.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK), "boldAction");
        actionMap.put("boldAction", new StyledEditorKit.BoldAction());

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK), "italicAction");
        actionMap.put("italicAction", new StyledEditorKit.ItalicAction());

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK), "underlineAction");
        actionMap.put("underlineAction", new StyledEditorKit.UnderlineAction());

        text.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                canvas.setcurrentActiveTextBox(text);
                canvas.getcurrentActiveTextBox().setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if ("EMPTY".equals(canvas.getcurrentActiveTextBox().getName()) && "Click to add Text".equals(canvas.getcurrentActiveTextBox().getText())) {
                    canvas.getcurrentActiveTextBox().setText("");
                    
                }
                System.out.println("Focus gained to " + text.getText());
                // System.out.println("Focus gained to " + text.getHeight());
                // System.out.println("Focus gained to " + text.getWidth());


            }

            @Override
            public void focusLost(FocusEvent e) {
                if (canvas.getcurrentActiveTextBox() == text) {
                    if (canvas.getcurrentActiveTextBox().getText().isEmpty()) {
                        canvas.getcurrentActiveTextBox().setText("Click to add Text");
                        canvas.getcurrentActiveTextBox().setName("EMPTY");
                        canvas.getcurrentActiveTextBox().setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    } else {
                        canvas.getcurrentActiveTextBox().setName("NOTempty");
                        canvas.getcurrentActiveTextBox().setBorder(null);
                    }
                    canvas.setcurrentActiveTextBox(null);
                    System.out.println("Focus lost " + text.getText());
                }
            }
        });
    

        // textPane.setFont(font);
        // textPane.setEditable(true);
        // // textPane.setDragEnabled(true);
        // textPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        // textPane.setText("Click to add Text");
        // textPane.setName("EMPTY");
        // textPane.setVisible(true);
        // textPane.setOpaque(false);
        // canvas.getCurrentSlide().addTextpane(textPane);
        // canvas.getPanel().add(textPane);
        // canvas.add(canvas.getPanel());
        // canvas.revalidate();
        // canvas.repaint();


    }


}