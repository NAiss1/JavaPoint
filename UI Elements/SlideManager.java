import java.io.ByteArrayOutputStream;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Slide Manager
 * 
 * @author Nurmukhammed Aissauyt
 */


public class SlideManager {
    private List<Slide> slides;
    private int currentSlideIndex;
    private Canvas canvas;
    private SlideShowFrame previews;

    public BufferedImage getCurrentCanvasImage(){
        try{
            BufferedImage canvasImage = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = canvasImage.createGraphics();
            canvas.paint(g2d);
            g2d.dispose();
            System.out.println("teseting");
            return canvasImage;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
            

        
    }

    public SlideManager(Canvas canvas, SlideShowFrame previews) {
        this.slides = new ArrayList<>();
        this.currentSlideIndex = 0; 
        this.canvas = canvas;
        this.previews = previews;
    }

    public void addSlide(Slide slide) {
        previews.addSlide();
        slides.add(slide);
    }

    public void deleteSlide(int index) {
        if (index >= 0 && index < slides.size()) {
            slides.remove(index);
            previews.deleteSlide(index);
            // Adjust the current slide index if necessary
            // if (currentSlideIndex >= slides.size()) {
            //     currentSlideIndex = Math.max(0, slides.size() - 1);
            // }
        }
    }
    public void deleteAllSlide(int max) {
        for(int i=0;i<max;i++){
            System.out.println(i);
            System.out.println(max);
            slides.remove(0);
            previews.deleteSlide(0);

            
 
        }
            
    }

    public Slide getSlideAtIndex(int index) {
        return slides.get(index);
    }

    public void nextSlide() {
        if (currentSlideIndex < slides.size() - 1) {
            currentSlideIndex++;
        }
        getCurrentSlide();
    }

    public void previousSlide() {
        if (currentSlideIndex > 0) {
            currentSlideIndex--;
        }
        getCurrentSlide();
    }

    public Slide getCurrentSlide() {
        if (!slides.isEmpty()) {
            return slides.get(currentSlideIndex);
        }
        return null; // Or return null
    }

    public int getCurrentSlideIndex() {
        return currentSlideIndex;
    }

    public int getSlidesCount() {
        return slides.size();
    }

    public void save() {
        
        try {
            StringBuilder xmlBuilder = new StringBuilder();
            xmlBuilder.append("<slides>\n");

            for (Slide slide : slides) {
                xmlBuilder.append("<slide>\n");
                List<Img> images = slide.getImages();
                // List<JTextPane> textspane = getCurrentSlide().getTextboxes();
                List<TextBox> textboxes = slide.getTextboxes();
                int id = slide.getId();
                List<Shapes> shapes =slide.getShapes();


            
        
                xmlBuilder.append("    <id>").append(id).append("</id>\n");
                xmlBuilder.append("    <Content>\n");

                for (TextBox text : textboxes) {
                    xmlBuilder.append("        <TextBox>\n");
                    xmlBuilder.append("             <x>").append(text.getX()).append("</x>\n");
                    xmlBuilder.append("             <y>").append(text.getY()).append("</y>\n");
                    xmlBuilder.append("             <height>").append(text.getH()).append("</height>\n");
                    xmlBuilder.append("             <width>").append(text.getW()).append("</width>\n");
                    xmlBuilder.append("             <text>").append(text.getText().getText()).append("</text>\n");
                    xmlBuilder.append("             <textpaneName>").append(text.getText().getName()).append("</textpaneName>\n");

                    StyledDocument doc = text.getText().getStyledDocument();
                    for (int i = 0; i < doc.getLength(); i++) {
                        String charText = doc.getText(i, 1);
                        AttributeSet attrs = doc.getCharacterElement(i).getAttributes();
        
                        xmlBuilder.append("                 <letter>\n");
                        xmlBuilder.append("                     <char>").append(charText).append("</char>\n");
                        xmlBuilder.append("                     <isBold>").append(StyleConstants.isBold(attrs)).append("</isBold>\n");
                        xmlBuilder.append("                     <isItalic>").append(StyleConstants.isItalic(attrs)).append("</isItalic>\n");
                        xmlBuilder.append("                     <isUnderline>").append(StyleConstants.isUnderline(attrs)).append("</isUnderline>\n");
                        xmlBuilder.append("                     <font>").append(StyleConstants.getFontFamily(attrs)).append("</font>\n");
                        xmlBuilder.append("                     <fontSize>").append(String.valueOf(StyleConstants.getFontSize(attrs))).append("</fontSize>\n");
                        xmlBuilder.append("                     <bgColor>").append(StyleConstants.getBackground(attrs)).append("</bgColor>\n");
                        xmlBuilder.append("                     <fgColor>").append(StyleConstants.getForeground(attrs)).append("</fgColor>\n");
                        xmlBuilder.append("                 </letter>\n");
                    
                    }
                        
                    xmlBuilder.append("        </TextBox>\n");
                }
                for (Img img : images) {
                    xmlBuilder.append("        <Image>\n");
                    xmlBuilder.append("             <IconHeight>").append(img.getH()).append("</IconHeight>\n");
                    xmlBuilder.append("             <IconWidth>").append(img.getW()).append("</IconWidth>\n");
                    xmlBuilder.append("             <x>").append(img.getXPosition()).append("</x>\n");
                    xmlBuilder.append("             <y>").append(img.getYPosition()).append("</y>\n");

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ImageIO.write(img.getImage(), "PNG", bos);
                    byte[] imageBytes = bos.toByteArray();
                    String imageString = Base64.getEncoder().encodeToString(imageBytes);
                    bos.close();
                    xmlBuilder.append("             <Data>").append(imageString).append("</Data>\n");
                    xmlBuilder.append("        </Image>\n");
                }
                for (Shapes shape : shapes) {
                    xmlBuilder.append("        <Shape>\n");
                    xmlBuilder.append("             <Name>").append(shape.getName()).append("</Name>\n");
                    xmlBuilder.append("             <Color>").append(shape.getColor()).append("</Color>\n");
                    xmlBuilder.append("             <x>").append(shape.getShapeX()).append("</x>\n");
                    xmlBuilder.append("             <y>").append(shape.getShapeY()).append("</y>\n");
                    xmlBuilder.append("             <h>").append(shape.getShapeH()).append("</h>\n");
                    xmlBuilder.append("             <w>").append(shape.getShapeW()).append("</w>\n");
                    xmlBuilder.append("        </Shape>\n");
                    
                }
                
                
                xmlBuilder.append("    </Content>\n");
                xmlBuilder.append("</slide>\n");

            }
            xmlBuilder.append("</slides>\n");



            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
            fileChooser.setFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(false);

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".xml")) {
                    filePath += ".xml";
                }

                try (FileWriter writer = new FileWriter(filePath)) {
                    writer.write(xmlBuilder.toString());
                    System.out.println("Saved to: " + filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving XML");
        }
        
    }

    public void load() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an XML file to load");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
        fileChooser.setFileFilter(filter);
        

        // images.clear();
        // textboxes.clear();
        int result = fileChooser.showOpenDialog(null);
        System.out.println("====");
        System.out.println(slides.size());
        System.out.println("====");
        deleteAllSlide(slides.size());

        if (result == JFileChooser.APPROVE_OPTION) {


            File selectedFile = fileChooser.getSelectedFile();
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(selectedFile);
                doc.getDocumentElement().normalize();


                NodeList slidNodeList = doc.getElementsByTagName("slide");
                for(int a=0; a < slidNodeList.getLength(); a++){
                    Slide slide = new Slide();

                    Node slideNode = slidNodeList.item(a);
                    if (slideNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element slideElement = (Element) slideNode;
    
                        NodeList nList = slideElement.getElementsByTagName("TextBox");
                        for (int i = 0; i < nList.getLength(); i++) {
                            Node nNode = nList.item(i);
                            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element eElement = (Element) nNode;
                                JTextPane textPane = new JTextPane();
                                String textpaneNamesString = eElement.getElementsByTagName("textpaneName").item(0).getTextContent();
                                int textwidth = Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent());
                                int textheight = Integer.parseInt(eElement.getElementsByTagName("height").item(0).getTextContent());
                                int textXPosition = Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());
                                int textYPosition = Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());
                                System.out.println(textXPosition);
                                System.out.println(textYPosition);

                                System.out.println(textheight);
                                System.out.println(textwidth);



                                System.out.println(textpaneNamesString);
                                textPane.setName(textpaneNamesString);
                                // textPane.setName(null);
                                if("NOTempty".equals(textpaneNamesString)){
                                    textBoxSetup(textPane);


                                
                                    StyledDocument styledDoc = textPane.getStyledDocument();
                                    
                                    NodeList letterNodeList = eElement.getElementsByTagName("letter");
                                    for (int j = 0; j < letterNodeList.getLength(); j++) {
                                        Node letterNode = letterNodeList.item(j);
                                        if (letterNode != null && letterNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element letterElement = (Element) letterNode;
                                            String fg = letterElement.getElementsByTagName("fgColor").item(0).getTextContent();
                                            String[] rgbValues = fg.replaceAll("[^0-9,]", "").split(",");
                                            // System.out.println(rgbValues);
                                            Color color = new Color(255, 255, 0);
                                            if (rgbValues.length == 3) {
                                                int red = Integer.parseInt(rgbValues[0]);
                                                int green = Integer.parseInt(rgbValues[1]);
                                                int blue = Integer.parseInt(rgbValues[2]);
                                                
                                                color = new Color(red, green, blue);

                                            } else {
                                                System.out.println("Invalid color string format.");
                                            }
                                            
                                            String charText = getSafeTextContent(letterElement, "char");
                                            boolean isItalic = Boolean.parseBoolean(getSafeTextContent(letterElement, "isItalic"));
                                            boolean isBold = Boolean.parseBoolean(getSafeTextContent(letterElement, "isBold"));
                                            boolean isUnderline = Boolean.parseBoolean(getSafeTextContent(letterElement, "isUnderline"));
                                            String fontName = getSafeTextContent(letterElement, "font");
                                            int fontSize = Integer.parseInt(getSafeTextContent(letterElement, "fontSize"));

                                    
                                            SimpleAttributeSet attrs = new SimpleAttributeSet();
                                            StyleConstants.setItalic(attrs, isItalic);
                                            StyleConstants.setBold(attrs, isBold);
                                            StyleConstants.setUnderline(attrs, isUnderline);
                                            StyleConstants.setFontFamily(attrs, fontName);
                                            StyleConstants.setFontSize(attrs, fontSize);
                                            StyleConstants.setForeground(attrs, color);
                            
                                            
                
                                            try {
                                                styledDoc.insertString(styledDoc.getLength(), charText, attrs);
                                            } catch (BadLocationException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }else{
                                    Font font = new Font("Arial", Font.BOLD, 16); // Customize the font
                                    textPane.setFont(font);
                                    textPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                                    textPane.setText("Click to add Text");
                                    textBoxSetup(textPane);
                                }
                                

                                JPanel textPanel = new JPanel(new BorderLayout());
                                textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                                textPanel.add(textPane, BorderLayout.CENTER);

                                textPanel.setBounds(textXPosition, textYPosition, textwidth, textheight);
                                TextBox textbox = new TextBox(textPane, textPanel);

                                slide.addTextbox(textbox);
                            }
                        }
                        // getCurrentSlide().setId(Integer.parseInt(doc.getDocumentElement().getAttribute("id")));
                        // System.out.println(textspane);


                        nList = slideElement.getElementsByTagName("Image");
                        for (int temp = 0; temp < nList.getLength(); temp++) {
                            Node node = nList.item(temp);

                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                Element imgElement = (Element) node;
                                String encodedImage = imgElement.getElementsByTagName("Data").item(0).getTextContent(); // Corrected line

                                if (encodedImage.isEmpty()) {
                                    System.out.println("Skipping empty Base64 string.");
                                    continue;
                                }
                                try {
                                    byte[] imageBytes = Base64.getDecoder().decode(encodedImage);
                                    BufferedImage newImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

                                    int xPosition = Integer.parseInt(imgElement.getElementsByTagName("x").item(0).getTextContent());
                                    int yPosition = Integer.parseInt(imgElement.getElementsByTagName("y").item(0).getTextContent());
                                    int height = Integer.parseInt(imgElement.getElementsByTagName("IconHeight").item(0).getTextContent());
                                    int width = Integer.parseInt(imgElement.getElementsByTagName("IconWidth").item(0).getTextContent());

                                    System.out.println(xPosition);
                                    System.out.println(yPosition);
                                    System.out.println(height);
                                    System.out.println(width);
                                    java.awt.Image scaledImage = newImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
                                    ImageIcon imgIcon = new ImageIcon(scaledImage);
                                    JLabel imageLabel = new JLabel(imgIcon);
                                    JPanel imagePanel = new JPanel();
                                    imagePanel.add(imageLabel);
                                    imagePanel.setBounds(xPosition, yPosition, width, height); 
                                    Img tempImg = new Img(newImage, imagePanel);

                                    slide.addImage(tempImg);
                                } catch (IllegalArgumentException e) {
                                    System.err.println("Error during Base64 decoding: " + e.getMessage());
                                } catch (IOException e) {
                                    System.err.println("Error reading the image: " + e.getMessage());
                                }
                            }
                        }


                        nList = slideElement.getElementsByTagName("Shape");
                        for (int temp = 0; temp < nList.getLength(); temp++) {
                            Node node = nList.item(temp);

                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                Element shapElement = (Element) node;
                                String shapeName = shapElement.getElementsByTagName("Name").item(0).getTextContent(); 
                                int xPosition = Integer.parseInt(shapElement.getElementsByTagName("x").item(0).getTextContent());
                                int yPosition = Integer.parseInt(shapElement.getElementsByTagName("y").item(0).getTextContent());
                                int height = Integer.parseInt(shapElement.getElementsByTagName("h").item(0).getTextContent());
                                int width = Integer.parseInt(shapElement.getElementsByTagName("w").item(0).getTextContent());
                                String fg = shapElement.getElementsByTagName("Color").item(0).getTextContent();
                                String[] rgbValues = fg.replaceAll("[^0-9,]", "").split(",");
                                // System.out.println(rgbValues);
                                Color color = new Color(255, 255, 0);
                                if (rgbValues.length == 3) {
                                    int red = Integer.parseInt(rgbValues[0]);
                                    int green = Integer.parseInt(rgbValues[1]);
                                    int blue = Integer.parseInt(rgbValues[2]);
                                    
                                    color = new Color(red, green, blue);

                                } else {
                                    System.out.println("Invalid color string format.");
                                }
                                


                                System.out.println(xPosition);
                                System.out.println(yPosition);
                                System.out.println(height);
                                System.out.println(width);
                                System.out.println(shapeName);
                                System.out.println("shapes create");
                                if("Rectangle".equals(shapeName)){
                                    RectangleShape rectangle = new RectangleShape(xPosition,yPosition,height,width,color);
                                    slide.addShape(rectangle);

                                }else if("Circle".equals(shapeName)){
                                    BallShape ball = new BallShape(xPosition,yPosition,height,width,color);
                                    slide.addShape(ball);


                                }else if("Triangle".equals(shapeName)){
                                    TriangleShape triangle = new TriangleShape(xPosition,yPosition,height,width,color);
                                    slide.addShape(triangle);
                                }
                            }
                        }
                    }
                    addSlide(slide);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private String getSafeTextContent(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0 && nodeList.item(0) != null) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }

    private void textBoxSetup(JTextPane text){
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

                System.out.println("TextPane content changed: " + text.getText());
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

        // if(text.getText().isEmpty()){
        //     text.setName("EMPTY");
        // }

        text.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                canvas.setcurrentActiveTextBox(text);
                // if(text.getText().isEmpty()){
                //     text.setName("EMPTY");
                // }
                System.out.println(text.getName());
                canvas.getcurrentActiveTextBox().setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if ("EMPTY".equals(canvas.getcurrentActiveTextBox().getName()) && "Click to add Text".equals(canvas.getcurrentActiveTextBox().getText())) {
                    canvas.getcurrentActiveTextBox().setText("");
                    
                }
                System.out.println("Focus gained to " + text.getText());
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (canvas.getcurrentActiveTextBox() == text) {
                    if(text.getText().isEmpty()){
                        text.setName("EMPTY");
                    }
                    if ("EMPTY".equals(canvas.getcurrentActiveTextBox().getName())) {
                        canvas.getcurrentActiveTextBox().setText("Click to add Text");
                        canvas.getcurrentActiveTextBox().setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    } 
                    if("NOTempty".equals(canvas.getcurrentActiveTextBox().getName())) {
                        canvas.getcurrentActiveTextBox().setBorder(null);
                    }
                    canvas.setcurrentActiveTextBox(null);
                    System.out.println("Focus lost " + text.getText());
                }
            }
        });

    }
    
}
