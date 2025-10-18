import javax.swing.*;
import java.awt.*;

/**
 * File Menu
 * 
 * @author Nurmukhammed Aissauyt
 */


public class FileMenu {

    Color PRESETBLUE = new Color(152, 186, 213);
    Color PRESETLIGHTBLUE = new Color(178, 203, 222);
    Color PRESETlightestBLUE = new Color(198, 211, 227);
    Color PRESETlighestesBLUE = new Color(216, 225, 232);
    Color PRESETdarkBLUE = new Color(48, 70, 116);

    public void start(JFrame frame,CanvasManager cManager) {


        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create a "File" menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem nextSlideItem = new JMenuItem("Next slide");
        JMenuItem previousSlideItem = new JMenuItem("Previous slide");
        JMenuItem exitItem = new JMenuItem("Exit");

        openItem.setForeground(PRESETdarkBLUE);
        saveItem.setForeground(PRESETdarkBLUE);
        exitItem.setForeground(PRESETdarkBLUE);
//        previousSlideItem.setForeground(PRESETdarkBLUE);
//        nextSlideItem.setForeground(PRESETdarkBLUE);
//
        openItem.setBackground(PRESETlightestBLUE);
        saveItem.setBackground(PRESETlightestBLUE);
        exitItem.setBackground(PRESETlightestBLUE);
//        previousSlideItem.setBackground(PRESETlightestBLUE);
//        nextSlideItem.setBackground(PRESETlightestBLUE);

        // Create a "Version Tree" menu
        JMenu VersionTreeMenu = new JMenu("Version Tree");
        JMenuItem openVersionTree = new JMenuItem("Open Version Tree");

        openVersionTree.setForeground(PRESETdarkBLUE);
        VersionTreeMenu.setBackground(PRESETLIGHTBLUE);
        openVersionTree.setBackground(PRESETlightestBLUE);
        

        // Create a "Frequently used tools" menu
        JMenu FreqMenu = new JMenu("Frequently Used Tools");
        JMenuItem openFreqTools = new JMenuItem("Open Frequently Used Tools");

        openFreqTools.setForeground(PRESETdarkBLUE);
        FreqMenu.setBackground(PRESETLIGHTBLUE);
        openFreqTools.setBackground(PRESETlightestBLUE);

        // Create a presentation menu
        JMenu PresentMenu = new JMenu("Presentation");
        JMenuItem present = new JMenuItem("Start Presentation");

        // Add action listeners for menu items
        openItem.addActionListener(e -> openfile(frame,cManager));
        saveItem.addActionListener(e -> savefile(frame,cManager));
//        nextSlideItem.addActionListener(e -> nextSlideItem(cManager));
//        previousSlideItem.addActionListener(e -> previousSlideItem(cManager));
        exitItem.addActionListener(e -> System.exit(0));

        openVersionTree.addActionListener(e -> openVersion(frame,cManager));
        openFreqTools.addActionListener(e -> openFreqTools(frame,cManager));

        present.addActionListener(e -> startPresentation(frame,cManager));

        // Add menu items to the "File" menu
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
//        fileMenu.add(nextSlideItem);
//        fileMenu.add(previousSlideItem);
        fileMenu.add(exitItem);

        VersionTreeMenu.add(openVersionTree);
        FreqMenu.add(openFreqTools);
        
        PresentMenu.add(present);

        // Add the "File" menu to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(VersionTreeMenu);
        menuBar.add(FreqMenu);
        menuBar.add(PresentMenu);

        menuBar.setBackground(PRESETLIGHTBLUE); // Change to whatever color you want


        // Set the menu bar for the frame
        frame.setJMenuBar(menuBar);

    }


    private void openfile(JFrame frame,CanvasManager canvasManager) {
        System.out.print("Open file was clicked\n");
        canvasManager.load();
        
    }

    private void savefile(JFrame frame,CanvasManager canvasManager) {
        canvasManager.save();
        System.out.print("save file was clicked\n");
    }

//    private void nextSlideItem(CanvasManager canvasManager) {
//        System.out.println("next");
//        canvasManager.nextSlide();
//
//
//    }private void previousSlideItem(CanvasManager canvasManager) {
//        System.out.println("pre");
//
//        canvasManager.previousSlide();
//
//
//    }

    private void openVersion(JFrame frame,CanvasManager canvasManager) {
        System.out.print("version control tree opened\n");
        VersionTree versionTreeFrame = new VersionTree();
        versionTreeFrame.start(frame);
    }

    private void openFreqTools(JFrame frame,CanvasManager canvasManager) {
        System.out.print("frequently used tools opened\n");
        frequentlyUsed frequentlyUsedFrame = new frequentlyUsed();
        frequentlyUsedFrame.start(frame);
    }

    private void startPresentation(JFrame frame, CanvasManager canvasManager) {
        System.out.print("presentation started");
        Present presentFrame = new Present();
        presentFrame.start(frame, canvasManager);
    }
    
}