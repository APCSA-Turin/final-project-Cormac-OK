package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.imageio.ImageIO;

public class MyFrame extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new MyFrame();
    }

    private JPanel mainMenu = new JPanel(new GridLayout(2,2));
    private JButton browseRandomButton = new JButton("Browse Random");
    private JButton browseRecommendedButton = new JButton("Browse Recommended");
    private JButton likedPhotosButton = new JButton("View Liked Photos");
    private JButton statsButton = new JButton("View Statistics");

    private JPanel imageViewer = new JPanel(new BorderLayout());
    private JButton back = new JButton("Back");
    private JLabel imagePlaceHolder = new JLabel("IMAAAGE");

    private JPanel ratingPanel = new JPanel(new GridLayout(1,2));
    private JLabel ratingLabel = new JLabel("Enter a rating from 1-5:");
    private  JTextArea ratingSlot = new JTextArea("");
    private  JButton nextPhotoButton = new JButton("Next Photo");

    private Image image;


    private RandomImage randomImage = new RandomImage(2);
    private MarsPhoto currentPhoto;

    public MyFrame(){
        super("RoverPics");
        super.setSize(1600, 200);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new FlowLayout());

        mainMenu.add(browseRandomButton);
        mainMenu.add(browseRecommendedButton);
        mainMenu.add(likedPhotosButton);
        mainMenu.add(statsButton);

        ratingSlot.setPreferredSize(new Dimension(50, 8));
        ratingPanel.add(ratingLabel, 0);
        ratingPanel.add(ratingSlot, 1);

        imageViewer.add(back, BorderLayout.PAGE_START);
        imageViewer.add(imagePlaceHolder, BorderLayout.CENTER);
        imageViewer.add(ratingPanel, BorderLayout.LINE_START);
        imageViewer.add(nextPhotoButton, BorderLayout.LINE_END);

        imageViewer.setVisible(false);

        try{
            URL url = new URL("http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/00002/opgs/edr/ncam/NRA_397681398EDR_F0020000AUT_04096M_.JPG");
            // URL url = new URL("https://images.dog.ceo/breeds/terrier-american/pan-pan.jpg");
            image = ImageIO.read(url);
            imageViewer.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
        }
        catch(Exception e){
            System.out.println("Uh Oh");
        }
        


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
        System.out.println("NEw photo");
        try{
            currentPhoto.setRating(Integer.parseInt(ratingSlot.getText().trim()));
        }
        catch (Exception e){
            System.out.println("Invalid Rating");
        }
        currentPhoto = randomImage.randomImage();
        imagePlaceHolder.setText(currentPhoto.toString());
        System.out.println(currentPhoto);
    }

}
