import java.io.IOException;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.FileWriter;
import javax.swing.text.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.event.DocumentListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.event.DocumentEvent;

import java.awt.image.BufferedImage;


/**
 * Slide
 * 
 * @author Nurmukhammed Aissauyt
 */


public class Slide {
    private List<Img> images;
    private List<JTextPane> textspane;
    private List<TextBox> textboxes;
    private int id;
    private List<Shapes> shapes; 
    

    // Constructor
    public Slide() {
        this.images = new ArrayList<>();
        this.textboxes = new ArrayList<>();
        this.textspane = new ArrayList<>();
        this.shapes = new ArrayList<>();

    }                             

    // Add an image to the slide
    public void addImage(Img image) {
        images.add(image);
    }

    public void addShape(Shapes shape) {
        shapes.add(shape);
    }
    public void removeShape(Shapes shape) {
        shapes.remove(shape);
    }
    public List<Shapes> getShapes() {
        return shapes;
    }


    // Add a textbox to the slide
    public void addTextbox(TextBox textbox) {
        textboxes.add(textbox);
    }

    // Remove an image from the slide
    public void removeImage(Img image) {
        images.remove(image);
    }

    // Remove a textbox from the slide
    public void removeTextbox(TextBox textbox) {
        textboxes.remove(textbox);
    }

    // Add a textbox to the slide
    public void addTextpane(JTextPane textpane) {
        textspane.add(textpane);
    }

    // Remove an image from the slide
    public void removeTextpane(JTextPane textpane) {
        textspane.remove(textpane);
    }

    // Get all images
    public List<Img> getImages() {
        return images;
    }
    // Get all textboxes
    public List<TextBox> getTextboxes() {
        return textboxes;
    }

    public List<JTextPane> getTextpPanes() {
        return textspane;
    }
    public void setId(int newid) {
        // Logic to set id
        id = newid;
    }

    // load from xml file
    
    // public void save() {
    //     try {
    //         System.out.println(textspane);
    //         StringBuilder xmlBuilder = new StringBuilder();
    //         xmlBuilder.append("<slide>\n");
        
    //         xmlBuilder.append("    <id>").append(this.id).append("</id>\n");
    //         xmlBuilder.append("    <Content>\n");

    //         for (TextBox text : textboxes) {
    //             xmlBuilder.append("        <TextBox>\n");
    //             xmlBuilder.append("             <x>").append(text.getX()).append("</x>\n");
    //             xmlBuilder.append("             <y>").append(text.getY()).append("</y>\n");
    //             xmlBuilder.append("             <height>").append(text.getH()).append("</height>\n");
    //             xmlBuilder.append("             <width>").append(text.getW()).append("</width>\n");
    //             xmlBuilder.append("             <text>").append(text.getText().getText()).append("</text>\n");
    //             xmlBuilder.append("             <textpaneName>").append(text.getText().getName()).append("</textpaneName>\n");

    //             StyledDocument doc = text.getText().getStyledDocument();
    //             for (int i = 0; i < doc.getLength(); i++) {
    //                 String charText = doc.getText(i, 1);
    //                 AttributeSet attrs = doc.getCharacterElement(i).getAttributes();
      
    //                 xmlBuilder.append("                 <letter>\n");
    //                 xmlBuilder.append("                     <char>").append(charText).append("</char>\n");
    //                 xmlBuilder.append("                     <isBold>").append(StyleConstants.isBold(attrs)).append("</isBold>\n");
    //                 xmlBuilder.append("                     <isItalic>").append(StyleConstants.isItalic(attrs)).append("</isItalic>\n");
    //                 xmlBuilder.append("                     <isUnderline>").append(StyleConstants.isUnderline(attrs)).append("</isUnderline>\n");
    //                 xmlBuilder.append("                     <font>").append(StyleConstants.getFontFamily(attrs)).append("</font>\n");
    //                 xmlBuilder.append("                     <fontSize>").append(String.valueOf(StyleConstants.getFontSize(attrs))).append("</fontSize>\n");
    //                 xmlBuilder.append("                     <bgColor>").append(StyleConstants.getBackground(attrs)).append("</bgColor>\n");
    //                 xmlBuilder.append("                     <fgColor>").append(StyleConstants.getForeground(attrs)).append("</fgColor>\n");
    //                 xmlBuilder.append("                 </letter>\n");
                
    //             }
                    
    //             xmlBuilder.append("        </TextBox>\n");
    //         }
    //         for (Img img : images) {
    //             xmlBuilder.append("        <Image>\n");
    //             xmlBuilder.append("             <IconHeight>").append(img.getH()).append("</IconHeight>\n");
    //             xmlBuilder.append("             <IconWidth>").append(img.getW()).append("</IconWidth>\n");
    //             xmlBuilder.append("             <x>").append(img.getXPosition()).append("</x>\n");
    //             xmlBuilder.append("             <y>").append(img.getYPosition()).append("</y>\n");

    //             ByteArrayOutputStream bos = new ByteArrayOutputStream();
    //             ImageIO.write(img.getImage(), "PNG", bos);
    //             byte[] imageBytes = bos.toByteArray();
    //             String imageString = Base64.getEncoder().encodeToString(imageBytes);
    //             bos.close();
    //             xmlBuilder.append("             <Data>").append(imageString).append("</Data>\n");
    //             xmlBuilder.append("        </Image>\n");
    //         }
    //         for (Shapes shape : shapes) {
    //             xmlBuilder.append("        <Shape>\n");
    //             xmlBuilder.append("             <Name>").append(shape.getName()).append("</Name>\n");
    //             xmlBuilder.append("             <Color>").append(shape.getColor()).append("</Color>\n");
    //             xmlBuilder.append("             <x>").append(shape.getShapeX()).append("</x>\n");
    //             xmlBuilder.append("             <y>").append(shape.getShapeY()).append("</y>\n");
    //             xmlBuilder.append("             <h>").append(shape.getShapeH()).append("</h>\n");
    //             xmlBuilder.append("             <w>").append(shape.getShapeW()).append("</w>\n");
    //             xmlBuilder.append("        </Shape>\n");
                
    //         }
            
            
    //         xmlBuilder.append("    </Content>\n");
    //         xmlBuilder.append("</slide>");


    //         JFileChooser fileChooser = new JFileChooser();
    //         fileChooser.setDialogTitle("Specify a file to save");
            
    //         FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
    //         fileChooser.setFileFilter(filter);
    //         fileChooser.setAcceptAllFileFilterUsed(false);

    //         int userSelection = fileChooser.showSaveDialog(null);

    //         if (userSelection == JFileChooser.APPROVE_OPTION) {
    //             String filePath = fileChooser.getSelectedFile().getAbsolutePath();
    //             if (!filePath.toLowerCase().endsWith(".xml")) {
    //                 filePath += ".xml";
    //             }

    //             try (FileWriter writer = new FileWriter(filePath)) {
    //                 writer.write(xmlBuilder.toString());
    //                 System.out.println("Saved to: " + filePath);
    //             } catch (IOException e) {
    //                 e.printStackTrace();
    //             }
    //         }
        
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         JOptionPane.showMessageDialog(null, "Error saving XML");
    //     }
        
    // }
    

    public int getId() {
        // Logic to get id
        return id;
    }

    // private void textBoxSetup(JTextPane text){
    //     text.getDocument().addDocumentListener(new DocumentListener() {
    //         @Override
    //         public void insertUpdate(DocumentEvent e) {
    //             documentChanged();
    //         }

    //         @Override
    //         public void removeUpdate(DocumentEvent e) {
    //             // documentChanged();
    //         }

    //         @Override
    //         public void changedUpdate(DocumentEvent e) {
    //             // documentChanged();
    //         }

    //         private void documentChanged() {
                
    //             text.setName("NOTempty");

    //             System.out.println("TextPane content changed: " + text.getText());
    //         }
    //     });

    //     InputMap inputMap = text.getInputMap();
    //     ActionMap actionMap = text.getActionMap();

    //     inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK), "boldAction");
    //     actionMap.put("boldAction", new StyledEditorKit.BoldAction());

    //     inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK), "italicAction");
    //     actionMap.put("italicAction", new StyledEditorKit.ItalicAction());

    //     inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK), "underlineAction");
    //     actionMap.put("underlineAction", new StyledEditorKit.UnderlineAction());

    //     // if(text.getText().isEmpty()){
    //     //     text.setName("EMPTY");
    //     // }

    //     text.addFocusListener(new FocusListener() {
    //         @Override
    //         public void focusGained(FocusEvent e) {
    //             canvas.setcurrentActiveTextBox(text);
    //             // if(text.getText().isEmpty()){
    //             //     text.setName("EMPTY");
    //             // }
    //             System.out.println(text.getName());
    //             canvas.getcurrentActiveTextBox().setBorder(BorderFactory.createLineBorder(Color.BLACK));
    //             if ("EMPTY".equals(canvas.getcurrentActiveTextBox().getName()) && "Click to add Text".equals(canvas.getcurrentActiveTextBox().getText())) {
    //                 canvas.getcurrentActiveTextBox().setText("");
                    
    //             }
    //             System.out.println("Focus gained to " + text.getText());
    //         }

    //         @Override
    //         public void focusLost(FocusEvent e) {
    //             if (canvas.getcurrentActiveTextBox() == text) {
    //                 if(text.getText().isEmpty()){
    //                     text.setName("EMPTY");
    //                 }
    //                 if ("EMPTY".equals(canvas.getcurrentActiveTextBox().getName())) {
    //                     canvas.getcurrentActiveTextBox().setText("Click to add Text");
    //                     canvas.getcurrentActiveTextBox().setBorder(BorderFactory.createLineBorder(Color.GRAY));
    //                 } 
    //                 if("NOTempty".equals(canvas.getcurrentActiveTextBox().getName())) {
    //                     canvas.getcurrentActiveTextBox().setBorder(null);
    //                 }
    //                 canvas.setcurrentActiveTextBox(null);
    //                 System.out.println("Focus lost " + text.getText());
    //             }
    //         }
    //     });

    // }



}

