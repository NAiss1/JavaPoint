import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.DefaultCaret;
import java.util.Date;

/**
 * Version Control Tree
 * 
 * @author Georgina Grewcock
 */

 //doesn't appear on screen

public class VersionTree {

    Color PRESETBLUE = new Color(152, 186, 213);
    Color PRESETLIGHTBLUE = new Color(178, 203, 222);
    Color PRESETlightestBLUE = new Color(198, 211, 227);
    Color PRESETlighestesBLUE = new Color(216, 225, 232);
    Color PRESETdarkBLUE = new Color(48, 70, 116);

    private String lastEditor = "Unknown";
    private Date lastEditTime;
    
    public void start(JFrame parentFrame) {

        SwingUtilities.invokeLater(() -> {
            JFrame versionTreeFrame = new JFrame("Version Control Tree");
            versionTreeFrame.setSize(300, 435);
            versionTreeFrame.setLocationRelativeTo(parentFrame);
            versionTreeFrame.getContentPane().setBackground(new Color(152, 186, 213));
            versionTreeFrame.setLayout(new BorderLayout());

            JTextArea terminalTextArea = new JTextArea(8, 20);
            terminalTextArea.setBackground(Color.BLACK);
            terminalTextArea.setForeground(Color.WHITE);
            terminalTextArea.setCaretColor(Color.WHITE);
            terminalTextArea.setEditable(true);

            terminalTextArea.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        String command = terminalTextArea.getText().trim();
                        if (command.equals("/help")) {
                            showHelpCommands(terminalTextArea);
                        }
                    }
                }
            });

            terminalTextArea.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        String command = terminalTextArea.getText().trim();
                        if (command.equals("/exit")) {
                        versionTreeFrame.dispose(); 
                    }
                }
            }
        });

        terminalTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String command = terminalTextArea.getText().trim();
                    if (command.equals("/save")) {
                        lastEditor = System.getProperty("user.name");
                        lastEditTime = new Date();
                        updateLastSavedLabel(versionTreeFrame);
                        //canvasManager.save();
                     }
                 }
            }
        });

            DefaultCaret caret = (DefaultCaret)terminalTextArea.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

            JScrollPane scrollPane = new JScrollPane(terminalTextArea);

            versionTreeFrame.add(scrollPane, BorderLayout.SOUTH);

            JLabel lastSavedLabel = new JLabel("PowerPoint Last Edited by: " + lastEditor + " at " + lastEditTime);
            lastSavedLabel.setHorizontalAlignment(SwingConstants.LEFT);
            lastSavedLabel.setForeground(PRESETdarkBLUE);
            versionTreeFrame.add(lastSavedLabel, BorderLayout.NORTH);


            versionTreeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            //makes sure user wants to disable feature
            versionTreeFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int option = JOptionPane.showConfirmDialog(versionTreeFrame,
                            "Closing this window will disable the Version Control Tree feature.\n Do you wish to continue?",
                            "Do you wish to continue?", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        versionTreeFrame.dispose();
                    }
                }
            });

            
            JPopupMenu popupMenu = createPopupMenu();
            
            versionTreeFrame.setVisible(true);
        });
    }

    private static JPopupMenu createPopupMenu() {
        return new JPopupMenu();
    }

    private void showHelpCommands(JTextArea terminalTextArea) {
        terminalTextArea.append("\nList of commands:\n");
        terminalTextArea.append("/help - Show list of commands\n");
        terminalTextArea.append("/open - Open a file\n");
        terminalTextArea.append("/save - Save current changes\n");
        terminalTextArea.append("/exit - Exit the Version Tree\n");
    }

        //method to update the last saved label with new information
    private void updateLastSavedLabel(JFrame versionTreeFrame) {
        JLabel lastSavedLabel = new JLabel("PowerPoint Last Edited by: " + lastEditor + " at " + lastEditTime);
        lastSavedLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lastSavedLabel.setForeground(PRESETdarkBLUE);
        versionTreeFrame.getContentPane().remove(0); //remove the previous label
        versionTreeFrame.add(lastSavedLabel, BorderLayout.NORTH); //updated label
        versionTreeFrame.revalidate(); //reflect the changes
    }
}
