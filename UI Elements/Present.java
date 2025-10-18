import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Flow;

public class Present {
    CanvasManager canvas;
    JFrame frame;

    public void ImageDisplay(BufferedImage image) {
        JFrame presentFrame= new JFrame("Presentation");
        presentFrame.setTitle("Presentation");
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //presentFrame.setSize(screenSize.width, screenSize.height);
        presentFrame.setSize(image.getWidth(), image.getHeight());

        //buttons for presentation
        JButton prevSlide = new JButton();
        //prevSlide.setPreferredSize(new Dimension(120, 80)); 
        prevSlide.setText("Previous Slide");
        prevSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.previousSlide();
                presentFrame.dispose();
                start(frame, canvas);
            }
        });
        

        JButton nextSlide = new JButton();
        //nextSlide.setPreferredSize(new Dimension(120, 80)); 
        nextSlide.setText("Next Slide");
        nextSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.nextSlide();
                presentFrame.dispose();
                start(frame, canvas);
            }
        });
        
        JPanel buttonPanel = new JPanel();

        JPanel imgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };

        JPanel container = new JPanel(new BorderLayout());

        buttonPanel.add(prevSlide);
        buttonPanel.add(nextSlide);
        buttonPanel.setPreferredSize(new Dimension(100,50));

        container.add(buttonPanel, BorderLayout.NORTH);
        container.add(imgPanel, BorderLayout.CENTER);

        presentFrame.add(container);
        presentFrame.setVisible(true);

        //get next slide
        //redo start
        //repaint
    }
     
    public void start(JFrame Frame, CanvasManager canvasManager){
        this.canvas = canvasManager;
        this.frame = Frame;

        Canvas canvas = canvasManager.getCanvas();

        BufferedImage canvasImage = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = canvasImage.createGraphics();
        canvas.paint(g2d);
        g2d.dispose();

        // Then display it in a separate window
        ImageDisplay(canvasImage);
    }




}


