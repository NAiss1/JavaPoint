import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
    
/**
 * Speaker's Notes
 * 
 * @author Georgina Grewcock
 */

public class speakerNotes extends JFrame {
    
    public speakerNotes() {
        // Constructor
    }

    public void start(JFrame frame, CanvasManager canvasManager) {
        setTitle("Speaker's Notes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea notesTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(notesTextArea);
        add(scrollPane, BorderLayout.EAST);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String notes = notesTextArea.getText();
                //here you can save the notes to a file or do whatever you want with them
                System.out.println("Speaker's notes saved: " + notes);
                JOptionPane.showMessageDialog(speakerNotes.this, "Speaker's notes saved!");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}


