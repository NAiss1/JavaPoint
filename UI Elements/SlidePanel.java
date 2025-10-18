import java.awt.Dimension;
import java.awt.Image;
import javax.swing.*;


public class SlidePanel extends JPanel{
    public JTextPane numberText;
    public JPanel imagePanel;
    public JLabel previewImage;
    public void start(JFrame frame, int number){
        numberText = new JTextPane();
        imagePanel = new JPanel();
        previewImage = new JLabel("empty icon");
        
        numberText.setText(Integer.toString(number));
        numberText.setEditable(false);

        numberText.setOpaque(false);

        imagePanel.setPreferredSize(new Dimension(100, 70));
        imagePanel.setBorder(BorderFactory.createBevelBorder(1));

        //imagePanel.add(previewImage);


        this.add(numberText);
        this.add(imagePanel);
        this.setSize(new Dimension(190, 80));

    }

    public void updateSlideNumber(int i){
        numberText.setText(Integer.toString(i));
    }

    public void updatePreviewImage(Image img){
        if(img == null){
            return;
        }
        Image scaledImage = scaleImage(img, 100, 70);
        ImageIcon imgIcon = new ImageIcon(scaledImage);

        // JLabel imageLabel = new JLabel();

        previewImage.setIcon(imgIcon);
        // previewImage = imageLabel;

        // imagePanel.add(previewImage);

        previewImage.revalidate();
        previewImage.repaint();
    }

    private java.awt.Image scaleImage(java.awt.Image ogImg, int width, int height){
        return ogImg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
    }
}
