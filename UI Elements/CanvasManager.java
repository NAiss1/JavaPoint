import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
/**
 * Canvas Manager
 * 
 * @author Nurmukhammed Aissauyt
 */


public class CanvasManager {
    private Canvas canvas;
    private SlideManager slideManager;
    private ArrayList<BufferedImage> previewList;

    public CanvasManager(JFrame frame, SlideShowFrame previews){
        slideManager = new SlideManager(canvas, previews);
        this.previewList = new ArrayList<>();
        canvas= new Canvas(frame);
    }

    public SlideManager getSlideManager(){
        return slideManager;
    }

    public void start() {
        Slide slide = new Slide();
        // RectangleShape r = new  RectangleShape(100,100, 20, 50, Color.RED);
        // slide.addShape(r);
        slideManager.addSlide(slide);
        canvas.setCurrentSlide(slide);
    }
    public Canvas getCanvas(){
        return canvas;
    }
    public void save(){
        slideManager.save();
        // canvas.getCurrentSlide().save();
        canvas.draw();
        

    }
    public void load(){
        // slideManager.deleteAllSlide();
    
        slideManager.load();
        canvas.setCurrentSlide(slideManager.getSlideAtIndex(0));

        canvas.loadImages();
        canvas.loadTextboxes();
        canvas.loadShapes();
    }

    public void nextSlide(){
        slideManager.nextSlide();
        canvas.setCurrentSlide(slideManager.getCurrentSlide());
        canvas.draw();
        System.out.println(slideManager.getCurrentSlideIndex());

    }public void previousSlide(){
        slideManager.previousSlide();
        canvas.setCurrentSlide(slideManager.getCurrentSlide());
        canvas.draw();
        System.out.println(slideManager.getCurrentSlideIndex());

    }

    public int getSlideCount(){
        return slideManager.getSlidesCount();

    }
    public void deleteSlide(){
        slideManager.deleteSlide(slideManager.getCurrentSlideIndex());
        if(slideManager.getSlidesCount() == 0){
            Slide slide = new Slide();
            addSlide(slide);
            nextSlide();
        }
        else{
            previousSlide();
            nextSlide();
        }

    }

    public ArrayList<BufferedImage> getAllPreviews(){
        return previewList;
    }




    public void addSlide(Slide slide){
        slide.setId(slideManager.getCurrentSlideIndex());
        slideManager.addSlide(slide);
        nextSlide();
        System.out.println(slideManager.getCurrentSlideIndex());
    }

}
