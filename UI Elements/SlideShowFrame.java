import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.Flow;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;  

/**
 * Slideshow Frame
 * 
 * @author Nurmukhammed Aissauyt, Vanessa Rodrigues
 */


public class SlideShowFrame extends JPanel {
    int currentSlide = 0;
    JFrame frame;
    JPanel slidePreviewPanel;
    ArrayList<SlidePanel> previews;
    CanvasManager cm;
    SlideManager sm;

    public void start(JFrame frame, CanvasManager cm) {
        this.cm = cm;
        this.frame = frame;
        this.sm = cm.getSlideManager();
        JPanel previewSidebar = new JPanel();
        slidePreviewPanel = new JPanel();
        slidePreviewPanel.setLayout(new BoxLayout(slidePreviewPanel, BoxLayout.Y_AXIS));
        previews = new ArrayList<SlidePanel>();

        


        // Create a button to be added at the bottom
        // JButton addslideButton = new JButton("+");
        // addslideButton.setForeground(Color.RED);
        
        //BoxLayout sideBarLayout = new BoxLayout(previewSidebar, BoxLayout.Y_AXIS);
        //previewSidebar.setLayout(sideBarLayout);


        
        

        // Make the main panel scrollable
        JScrollPane scrollPane = new JScrollPane(previewSidebar);
        scrollPane.repaint();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(slidePreviewPanel);
        scrollPane.setBackground(Color.gray);

        // Set the preferred size
        scrollPane.setPreferredSize(new Dimension(170, -1)); 
        scrollPane.add(previewSidebar);

        // Add the scroll pane to the frame on the left side
        //this works with previewSideBar but comes up empty with scrollPane which we need
        frame.getContentPane().add(scrollPane, BorderLayout.WEST);

    }

    //adds a slide to the side bar, returning the added slide to be tracked
    public void addSlide(){
        System.out.println("added slide");
        SlidePanel newSlide = new SlidePanel();
        newSlide.start(frame, previews.size());
        newSlide.setMaximumSize(new Dimension(190, 80));
        slidePreviewPanel.add(newSlide);
        previews.add(newSlide);
        System.out.println("testing 23");
        newSlide.updatePreviewImage(cm.getCanvas().getBufferedImage());   

        slidePreviewPanel.revalidate();
        slidePreviewPanel.repaint();
    }

    public void deleteSlide(int toDelete){
        slidePreviewPanel.remove(previews.get(toDelete));
        previews.remove(toDelete);

        for(int i = toDelete; i < previews.size(); i++){
            previews.get(i).updateSlideNumber(i);

        }
        this.repaint();
        this.revalidate();
        slidePreviewPanel.revalidate();
        slidePreviewPanel.repaint();
    }


    


}
