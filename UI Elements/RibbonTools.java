import javax.swing.*;
import javax.swing.text.StyledEditorKit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Ribbon Tools
 * 
 * @author Vanessa Rodrigues
 */

//a set of tools to do basic canvas editing for the user, in the form of several boxes at the top of the screen the user can click to use that tool
public class RibbonTools extends JPanel{

    ToolsInterface toolsInterface = new ToolsMethods();
    Color PRESETBLUE = new Color(152, 186, 213);
    Color PRESETLIGHTBLUE = new Color(178, 203, 222);
    Color PRESETlightestBLUE = new Color(198, 211, 227);
    Color PRESETlighestesBLUE = new Color(216, 225, 232);
    Color PRESETdarkBLUE = new Color(48, 70, 116);
    private CanvasManager canvasManager;
    private int textSize = 16;
    
    private static ImageIcon createImageIcon(String path) {
        URL imgUrl = frequentlyUsed.class.getResource(path);
        if (imgUrl != null) {
            return new ImageIcon(imgUrl);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static final int TOOL_GROUP_SPACING = 60;

    public void start(JFrame frame,CanvasManager canvasManager, SlideShowFrame slides){
        this.canvasManager = canvasManager;
        // ToolsInterface toolsInterface = new ToolsMethods();
        Canvas canvas = canvasManager.getCanvas();

        int ribbonWidth = 70;
        
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridBagLayout());
        toolbar.setBackground(PRESETBLUE);
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1.0;
        c.weighty = 2.0;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH;
        
        //the x axis value here has no functionality as it will be determined by the size of the jpanel divided into equal chunks
        toolbar.setPreferredSize(new Dimension(0,ribbonWidth));

        //flexibly adds all buttons to the toolbar
        int offset = 0;
        offset = addSlideTools(toolbar, c, slides, offset, true);
        offset = addTextTools(toolbar, c, canvas, offset, false);
        offset = addImageTools(toolbar, c, canvas, offset, false);
        offset = copyPasteTools(toolbar, c, canvas, offset, false);
        offset = shapeTools(toolbar, c, canvas, offset, false);
        offset = transformTools(toolbar, c, canvas, offset, false);

        //adds the toolbar aligned to the top of the page, with the pre-added buttons packed inside it
        frame.getContentPane().add(toolbar, BorderLayout.NORTH);
        
    }

    public ToolsInterface getInterface(){
        return toolsInterface;
    }

    int addTextTools(JPanel toolbar, GridBagConstraints c, Canvas canvas, int lastOffset, boolean isFirst){
        Icon textBoxIcon = createImageIcon("Icons/text3030.png");   
        JButton addTextButton = new JButton(textBoxIcon);
        if(isFirst){
            c.insets = new Insets(5, 5, 5, 5);
        }
        else{
            c.insets = new Insets(5, TOOL_GROUP_SPACING, 5, 5);
        }
        
        c.gridheight = 12;
        c.gridwidth = 12;
        c.gridx = 0 + lastOffset; 
        c.gridy = 0;

        lastOffset += 12;
        
        addTextButton.setBackground(PRESETLIGHTBLUE);
        addTextButton.setPreferredSize(new Dimension(30, 30));
        toolbar.add(addTextButton, c);
        addTextButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                toolsInterface.addTextBox(canvas);
                
            }
        }); 

        c.insets = new Insets(5, 5, 5, 5);
        JButton fontSizeUp = new JButton("A+");
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 2 + lastOffset;
        c.gridy = 0;
        
        fontSizeUp.setBackground(PRESETLIGHTBLUE);
        fontSizeUp.setForeground(PRESETdarkBLUE);
        toolbar.add(fontSizeUp, c);
        fontSizeUp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new StyledEditorKit.FontSizeAction("up", textSize += 2).actionPerformed(e);
                System.out.println(textSize);
            }
            
            }
        );
        
        JButton fontSizeDown = new JButton("A-");
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 2 + lastOffset;
        c.gridy = 2;
        
        fontSizeDown.setBackground(PRESETLIGHTBLUE); 
        fontSizeDown.setForeground(PRESETdarkBLUE);
        toolbar.add(fontSizeDown, c);
        fontSizeDown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new StyledEditorKit.FontSizeAction("down", textSize -= 2).actionPerformed(e);;
                System.out.println(textSize);
            }
            
            }
        );
        lastOffset += 2;

        JButton bold = new JButton("B");
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 2 + lastOffset;
        c.gridy = 0;
        bold.setBackground(PRESETLIGHTBLUE); 
        bold.setForeground(PRESETdarkBLUE);
        toolbar.add(bold, c);
        bold.addActionListener(new StyledEditorKit.BoldAction());

        JButton italics = new JButton("I");
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 2 + lastOffset;
        c.gridy = 2;

        lastOffset += 2;
        italics.setBackground(PRESETLIGHTBLUE);
        italics.setForeground(PRESETdarkBLUE);
        toolbar.add(italics, c);

        italics.addActionListener(new StyledEditorKit.ItalicAction());

        JButton underline = new JButton("U");
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 2 + lastOffset;
        c.gridy = 0;

        lastOffset += 2;
        underline.setBackground(PRESETLIGHTBLUE);
        underline.setForeground(PRESETdarkBLUE);
        toolbar.add(underline, c);

        underline.addActionListener(new StyledEditorKit.UnderlineAction()); 

        return lastOffset + 2;
    }

    int addImageTools(JPanel toolbar, GridBagConstraints c, Canvas canvas, int lastOffset, boolean isFirst){
        Icon imageIcon = createImageIcon("Icons/image3030.png");
        JButton addImageButton = new JButton(imageIcon);
        //add some left padding so that there is a space between the groups of tools
        if(isFirst){
            c.insets = new Insets(5, 5, 5, 5);
        }
        else{
            c.insets = new Insets(5, TOOL_GROUP_SPACING, 5, 5);
        }
        
        
        c.gridheight = 4;
        c.gridwidth = 4;
        c.gridx = 0 + lastOffset;c.gridy = 0;
        addImageButton.setBackground(PRESETLIGHTBLUE);
        addImageButton.setPreferredSize(new Dimension(30, 30));
        toolbar.add(addImageButton, c);
        addImageButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //add image
                toolsInterface.addImage(canvas);
            }
        }); 
        c.insets = new Insets(5, 5, 5, 5);

        return 6 + lastOffset;
    }

    int addSlideTools(JPanel toolbar, GridBagConstraints c, SlideShowFrame slides, int lastOffset, boolean isFirst){
        if(isFirst){
            c.insets = new Insets(5, 5, 5, 5);
        }
        else{
            c.insets = new Insets(5, TOOL_GROUP_SPACING, 5, 5);
        }
        
        JButton addSlide = new JButton("Add Slide");
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 2 + lastOffset;
        c.gridy = 0;
        addSlide.setBackground(PRESETLIGHTBLUE);
        addSlide.setForeground(PRESETdarkBLUE);
        toolbar.add(addSlide, c);
        addSlide.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Slide slide = new Slide();
                canvasManager.addSlide(slide);
            }
        }); 
        
        JButton deleteSlide = new JButton("Delete Slide");
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 2 + lastOffset;
        c.gridy = 2;
        deleteSlide.setBackground(PRESETLIGHTBLUE);
        deleteSlide.setForeground(PRESETdarkBLUE);
        toolbar.add(deleteSlide, c);
        deleteSlide.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                //delete slide
                canvasManager.deleteSlide();
            }
        }); 

        JButton nextSlide = new JButton("Next Slide");
        c.gridheight = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 4 + lastOffset;
        c.gridy = 0;
        nextSlide.setBackground(PRESETLIGHTBLUE);
        nextSlide.setForeground(PRESETdarkBLUE);
        toolbar.add(nextSlide, c);

        nextSlide.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                canvasManager.nextSlide();
            }
        }); 

        JButton lastSlide = new JButton("Prev Slide");
        c.gridheight = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 4 + lastOffset;
        c.gridy = 2;
        lastSlide.setBackground(PRESETLIGHTBLUE);
        lastSlide.setForeground(PRESETdarkBLUE);
        toolbar.add(lastSlide, c);

        lastSlide.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                canvasManager.previousSlide();
            }
        }); 


         

        c.insets = new Insets(5, 5, 5, 5);


        return 6 + lastOffset;
    }

    int copyPasteTools(JPanel toolbar, GridBagConstraints c, Canvas canvas, int lastOffset, boolean isFirst){
        if(isFirst){
            c.insets = new Insets(5, 5, 5, 5);
        }
        else{
            c.insets = new Insets(5, TOOL_GROUP_SPACING, 5, 5);
        }

        JButton copy = new JButton("Copy");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 2 + lastOffset;
        c.gridy = 0;

        copy.setBackground(PRESETLIGHTBLUE); 
        copy.setForeground(PRESETdarkBLUE);
        toolbar.add(copy, c);
        copy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //add the selected element to the cli
            }
        }); 

        JButton cut = new JButton("Cut");
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 2 + lastOffset;
        c.gridy = 2;
        cut.setBackground(PRESETLIGHTBLUE); 
        cut.setForeground(PRESETdarkBLUE);
        toolbar.add(cut,c);
        cut.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //delete the selected element and add it to the clipboard
            }
        }); 

        lastOffset += 2;
        

        JButton paste = new JButton("Paste");
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 4;
        c.gridwidth = 4;
        c.gridx = 2 + lastOffset;
        c.gridy = 0;
        paste.setBackground(PRESETLIGHTBLUE); 
        paste.setForeground(PRESETdarkBLUE);
        lastOffset += 2;

        toolbar.add(paste, c);
        paste.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //paste the element in to the slide
            }
        }); 

        return lastOffset;
    }

    int shapeTools(JPanel toolbar, GridBagConstraints c, Canvas canvas, int lastOffset, boolean isFirst){
        if(isFirst){
            c.insets = new Insets(5, 5, 5, 5);
        }
        else{
            c.insets = new Insets(5, TOOL_GROUP_SPACING, 5, 5);
        }

        ImageIcon shapeIcon = createImageIcon("/Icons/shape3030.png");
        c.gridheight = 4;
        c.gridwidth = 4;
        c.gridx = 4 + lastOffset;
        c.gridy = 0;

        JButton addShapeButton = new JButton(shapeIcon);
        lastOffset += 4;
        addShapeButton.setBackground(PRESETLIGHTBLUE); 
        addShapeButton.setPreferredSize(new Dimension(30, 30));
        toolbar.add(addShapeButton, c);
        addShapeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                toolsInterface.addShape(canvas, addShapeButton);
            }
        }); 

        return lastOffset;
    }

    int transformTools(JPanel toolbar, GridBagConstraints c, Canvas canvas, int lastOffset, boolean isFirst){
        if(isFirst){
            c.insets = new Insets(5, 5, 5, 5);
        }
        else{
            c.insets = new Insets(5, TOOL_GROUP_SPACING, 5, 5);
        }

        ImageIcon moveIcon = createImageIcon("/Icons/move3030.png");

        c.gridheight = 4;
        c.gridwidth = 4;
        c.gridx = 4 + lastOffset;
        c.gridy = 0;


        return lastOffset;
    }

}