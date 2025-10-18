
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;


/**
 * Frequently Used Item Bar
 * 
 * @author Georgina Grewcock
 */

public class frequentlyUsed {
    public void start(JFrame parentFrame) {

        Color MINT = new Color(216, 225, 232); // custom color

        SwingUtilities.invokeLater(() -> {
            JFrame frequentlyUsedFrame = new JFrame("Frequently Used Tools");
            frequentlyUsedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frequentlyUsedFrame.setSize(300, 435);
            frequentlyUsedFrame.getContentPane().setBackground(new Color(152, 186, 213));
            frequentlyUsedFrame.setLocationRelativeTo(parentFrame);
            frequentlyUsedFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            frequentlyUsedFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            // makes sure user wants to disable feature
            frequentlyUsedFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int option = JOptionPane.showConfirmDialog(frequentlyUsedFrame,
                            "Closing this window will disable the Frequently Used Tools feature.\n Do you wish to continue?",
                            "Do you wish to continue?", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        // closes feature hotbar
                        frequentlyUsedFrame.dispose();
                    }
                }
            });

            // loads icons for buttons
            ImageIcon save = createImageIcon("Icons/save40.png");
            ImageIcon undo = createImageIcon("Icons/undo40.png");
            ImageIcon redo = createImageIcon("Icons/redo40.png");

            // tool buttons 1-8
            JButton tool1Button = createToolButton("Undo", MINT, Color.BLACK, undo);
            JButton tool2Button = createToolButton("Redo", MINT, Color.BLACK, redo);
            JButton tool3Button = createToolButton("Save", MINT, Color.BLACK, save);
            JButton tool4Button = createToolButton("Tool 4", MINT, Color.BLACK, save);
            JButton tool5Button = createToolButton("Tool 5", MINT, Color.BLACK, save);
            JButton tool6Button = createToolButton("Tool 6", MINT, Color.BLACK, save);
            JButton tool7Button = createToolButton("Tool 7", MINT, Color.BLACK, save);
            JButton tool8Button = createToolButton("Tool 8", MINT, Color.BLACK, save);

            // creates menu
            JPopupMenu popupMenu = createPopupMenu();

            frequentlyUsedFrame.add(tool1Button);
            frequentlyUsedFrame.add(tool2Button);
            frequentlyUsedFrame.add(tool3Button);
            frequentlyUsedFrame.add(tool4Button);
            frequentlyUsedFrame.add(tool5Button);
            frequentlyUsedFrame.add(tool6Button);
            frequentlyUsedFrame.add(tool7Button);
            frequentlyUsedFrame.add(tool8Button);

            frequentlyUsedFrame.setVisible(true);
        });
    }

    // if icon images can't be found - check it's in folder
    private static ImageIcon createImageIcon(String path) {
        URL imgUrl = frequentlyUsed.class.getResource(path);
        if (imgUrl != null) {
            return new ImageIcon(imgUrl);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private static JButton createToolButton(String toolName, Color backgroundColor, Color textColor, Icon ImageIcon) {
        JButton toolButton = new JButton(toolName);
        toolButton.setPreferredSize(new Dimension(120, 80)); 
        toolButton.setBackground(backgroundColor);
        toolButton.setForeground(textColor);
        toolButton.setIcon(ImageIcon);
        toolButton.setFocusPainted(false);

        toolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // performs action for the tool (eg. save etc)
                JOptionPane.showMessageDialog(null, toolName + " selected - TEST PASSED"); // will remove when all functions work
            }
        });
        return toolButton;
    }

    private static JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        for (int i = 4; i <= 8; i++) {
            JMenuItem toolItem = new JMenuItem("Tool " + i);
            int toolNumber = i;
            toolItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Tool " + toolNumber + " selected");
                }
            });
            popupMenu.add(toolItem);
        }

        return popupMenu;
    }
}