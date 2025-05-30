package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyFrame extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new MyFrame();
    }

    private JPanel mainMenu = new JPanel(new GridLayout(3,2));
    private JButton browseRandomButton = new JButton("Browse Random");
    private JButton browseRecommendedButton = new JButton("Browse Recommended");
    private JButton historyButton = new JButton("View History");
    private JButton statsButton = new JButton("View Statistics");

    private JPanel imageViewer = new JPanel(new BorderLayout());
    private JButton back = new JButton("Back");

    private JLabel imgLabel = new JLabel(new ImageIcon());

    private JPanel ratingPanel = new JPanel(new GridLayout(2,1));
    private JLabel ratingLabel = new JLabel("Enter a rating from 1-5:");
    private  JTextArea ratingSlot = new JTextArea("");
    private  JButton nextPhotoButton = new JButton("Next Photo");

    private JPanel historyViewer = new JPanel(new BorderLayout());
    private JPanel bottomBar = new JPanel(new GridLayout(1,2));
    private JButton goBackButton = new JButton("<<");
    private JButton goForwardButton = new JButton(">>");
    private JLabel historyImgLabel = new JLabel(new ImageIcon());
    private JLabel rating = new JLabel(); 

    

    private RandomImage randomImage = new RandomImage(2);
    private MarsPhoto currentPhoto;

    private ArrayList<MarsPhoto> history = randomImage.getHistory();

    public MyFrame(){
        super("RoverPics");
        super.setSize(1600, 800);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainMenu.setSize(super.getSize());
        mainMenu.add(browseRandomButton);
        mainMenu.add(browseRecommendedButton);
        mainMenu.add(historyButton);
        mainMenu.add(statsButton);

        bottomBar.add(goBackButton);
        bottomBar.add(goForwardButton);
        historyViewer.add(bottomBar, BorderLayout.PAGE_END);
        historyViewer.add(rating);
        historyViewer.add(back);

        ratingSlot.setPreferredSize(new Dimension(50, 8));
        ratingPanel.add(ratingLabel, 0);
        ratingPanel.add(ratingSlot, 1);

        imageViewer.add(back, BorderLayout.PAGE_START);
        imageViewer.add(ratingPanel, BorderLayout.LINE_START);
        imageViewer.add(nextPhotoButton, BorderLayout.LINE_END);
        imageViewer.add(imgLabel, BorderLayout.CENTER);





        back.addActionListener(this);
        browseRandomButton.addActionListener(this);
        browseRecommendedButton.addActionListener(this);
        historyButton.addActionListener(this);
        statsButton.addActionListener(this);
        nextPhotoButton.addActionListener(this);
        historyButton.addActionListener(this);

        // super.add(mainMenu);
        // super.add(imageViewer);
        // super.add(historyViewer);

        // imageViewer.setVisible(false);
        // historyViewer.setVisible(false);

        super.setVisible(true);

        super.setContentPane(mainMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Back")) {
            imageViewer.setVisible(false);
            historyViewer.setVisible(false);
            mainMenu.setVisible(true);
        }
        else if(e.getActionCommand().equals("Browse Random")){
            mainMenu.setVisible(false);
            imageViewer.setVisible(true);
            newPhoto();
        }
        else if(e.getActionCommand().equals("Next Photo")){
            newPhoto();
        }
        else if(e.getActionCommand().equals("View History")){
            mainMenu.setVisible(false);
            historyViewer.setVisible(true);
            historyPhoto(0);
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

    
        imgLabel.setIcon(new ImageIcon(currentPhoto.getPath()));
        
    }

    public void historyPhoto(int index){
        historyImgLabel.setIcon(new ImageIcon(history.get(index).getPath()));
    }

}
