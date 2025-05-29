package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;

public class MyFrame extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new MyFrame();
    }

    private JPanel mainMenu = new JPanel(new GridLayout(3,2));
    private JButton browseRandomButton = new JButton("Browse Random");
    private JButton browseRecommendedButton = new JButton("Browse Recommended");
    private JButton likedPhotosButton = new JButton("View Liked Photos");
    private JButton statsButton = new JButton("View Statistics");

    private JPanel imageViewer = new JPanel(new BorderLayout());
    private JButton back = new JButton("Back");

    private ImageIcon imageIcon = new ImageIcon();
    private JLabel imgLabel = new JLabel(imageIcon);

    private JPanel ratingPanel = new JPanel(new GridLayout(2,1));
    private JLabel ratingLabel = new JLabel("Enter a rating from 1-5:");
    private  JTextArea ratingSlot = new JTextArea("");
    private  JButton nextPhotoButton = new JButton("Next Photo");


    private Image image;


    private RandomImage randomImage = new RandomImage(2);
    private MarsPhoto currentPhoto;

    public MyFrame(){
        super("RoverPics");
        super.setSize(1600, 800);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainMenu.setSize(super.getSize());
        mainMenu.add(browseRandomButton);
        mainMenu.add(browseRecommendedButton);
        mainMenu.add(likedPhotosButton);
        mainMenu.add(statsButton);
        mainMenu.add(imgLabel);

        ratingSlot.setPreferredSize(new Dimension(50, 8));
        ratingPanel.add(ratingLabel, 0);
        ratingPanel.add(ratingSlot, 1);

        imageViewer.add(back, BorderLayout.PAGE_START);
        imageViewer.add(ratingPanel, BorderLayout.LINE_START);
        imageViewer.add(nextPhotoButton, BorderLayout.LINE_END);
        imageViewer.add(imgLabel, BorderLayout.CENTER);

        imageViewer.setVisible(false);



        back.addActionListener(this);
        browseRandomButton.addActionListener(this);
        browseRecommendedButton.addActionListener(this);
        likedPhotosButton.addActionListener(this);
        statsButton.addActionListener(this);
        nextPhotoButton.addActionListener(this);

        super.add(mainMenu);
        super.add(imageViewer);

        System.out.println(mainMenu.isVisible());
        System.out.println(mainMenu.getComponent(0));

        super.setVisible(true);
        super.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Back")) {
            imageViewer.setVisible(false);
            mainMenu.setVisible(true);
            mainMenu.repaint();
        }
        else if(e.getActionCommand().equals("Browse Random")){
            mainMenu.setVisible(false);
            imageViewer.setVisible(true);
            imageViewer.repaint();
        }
        else if(e.getActionCommand().equals("Next Photo")){
            System.out.println("Next");
            newPhoto();
        }
    }

    public void newPhoto(){
        try{
            currentPhoto.setRating(Integer.parseInt(ratingSlot.getText().trim()));
        }
        catch (Exception e){
            System.out.println("Invalid Rating");
        }
        currentPhoto = randomImage.randomImage();

    
        //"JavaAPIProject\\src\\main\\java\\com\\example\\dog.jpg"
        imgLabel.setIcon(new ImageIcon(currentPhoto.getPath()));
        
        System.out.println("image:" + image);
        System.out.println("image label:" + imgLabel);
        System.out.println(currentPhoto);
    }

}
