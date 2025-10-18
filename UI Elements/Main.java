import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.*;

/**
 * Main
 * 
 * @author ""
 */

//creates Swing window and allows moving the other classes around for editing
//this should instantiate all of the UI elements and show them
public class Main {

    static Color PRESETBLUE = new Color(152, 186, 213);
    Color PRESETLIGHTBLUE = new Color(178, 203, 222);
    static Color PRESETlightestBLUE = new Color(198, 211, 227);
    Color PRESETlighestesBLUE = new Color(216, 225, 232);
    Color PRESETdarkBLUE = new Color(48, 70, 116);
   
    public static void main(String args[]) {
        JFrame frame = new JFrame("PowerPoint");
        frame.setSize(1280, 720);
        frame.setBackground(PRESETBLUE);
        frame.setLayout(new BorderLayout()); // Set layout

        Toolbar tb = new Toolbar();
        SlideShowFrame sf = new SlideShowFrame();
        CanvasManager canvasManager = new CanvasManager(frame, sf);
        
        RibbonTools rt = new RibbonTools();
        FileMenu fileMenu = new FileMenu();
//        speakerNotes speak = new speakerNotes();

  


        
        frame.add(canvasManager.getCanvas(), BorderLayout.CENTER); // Add canvas in the center



        sf.start(frame, canvasManager);

        canvasManager.start();
        tb.start(frame,canvasManager);
        rt.start(frame,canvasManager, sf);
        fileMenu.start(frame, canvasManager); 
//        speak.start(frame, canvasManager);


        



        frame.pack();
        frame.setVisible(true);
        frame.setSize(1280, 720);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

