import javax.swing.*;

/**
 * Toolbar
 * 
 * @author Nurmukhammed Aissauyt
 */


public class Toolbar extends JPanel{

    public void start(JFrame frame,CanvasManager cm) {
        FileMenu fm = new FileMenu();
        fm.start(frame,cm);

    }
}