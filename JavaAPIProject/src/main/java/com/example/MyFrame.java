package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/** The Frame in which the entire GUI happens */
public class MyFrame extends JFrame implements ActionListener {

    /** The Panel on which everything is displayed */
    private JPanel imageViewer = new JPanel(new BorderLayout());

    /** The Label that displays the current photo */
    private JLabel imgLabel = new JLabel(new ImageIcon());
    /** Contains the label and the text box for entering a photo's rating */
    private JPanel ratingPanel = new JPanel(new GridLayout(2,1));
    /** The Label for where to enter your rating*/
    private JLabel ratingLabel = new JLabel("Enter a rating from 1-5:");
    /** The text box for entering a rating */
    private  JTextArea ratingSlot = new JTextArea("");

    /** The panel that holds the next photo and save photo buttons */
    private JPanel rightPanel = new JPanel(new GridLayout(2, 1));
    /** The button to advance to the next photo*/
    private  JButton nextPhotoButton = new JButton("Next Photo");
    /** The button to save the current photo*/
    private  JButton saveButton = new JButton("Save Photo");

    /** Displays various statistics about the use session*/
    private JLabel statsLabel = new JLabel("Average rating: N/A   |   Photos saved: 0");

    /** The sum of all ratings given this session*/
    private int ratingSum = 0;
    /** The number of ratings given this session*/
    private int ratingCount = 0;
    /** The number of photos saved this session*/
    private int saveCount = 0;

    /** Used to get random images and track what images have been gotten so far*/
    private RandomImage randomImage = new RandomImage(2);
    /** The photo that is currently being displayed*/
    private MarsPhoto currentPhoto;

    public MyFrame(){

        // Sets up frame
        super("RoverPics");
        super.setSize(1275, 1000);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets up the ratingPanel which will be added to the main panel
        ratingPanel.add(ratingLabel, 0);
        ratingPanel.add(ratingSlot, 1);
        imageViewer.add(ratingPanel, BorderLayout.LINE_START);

        // Sets up the right-hand side panel to be added to the main panel
        rightPanel.add(nextPhotoButton);
        rightPanel.add(saveButton);
        imageViewer.add(rightPanel, BorderLayout.LINE_END);

        // Sets up the text displaying the user's stats
        statsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageViewer.add(statsLabel, BorderLayout.PAGE_END);

        imageViewer.add(imgLabel, BorderLayout.CENTER);

        // Sets up action listeners
        nextPhotoButton.addActionListener(this);
        saveButton.addActionListener(this);

        // Adds main panel to the Frame
        super.add(imageViewer);
        // Makes the main panel visible
        imageViewer.setVisible(true);
        // Makes the Frame visible
        super.setVisible(true);

        // Gets a photo to display
        newPhoto();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Next Photo")){
            newPhoto();
        }
        else if (e.getActionCommand().equals("Save Photo")){
            savePhoto();
        }
    }

    /** Saves a photo to the Favorites folder*/
    public void savePhoto(){
        try{
            // Saves the photo to the correct location
            RandomImage.downloadUsingStream(currentPhoto.getURL(), "JavaAPIProject//src//main//java//com//example//Favorites//" + currentPhoto.getId() + ".jpg");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        // Updates the user's statistics
        saveCount ++;
        updateStats();
        // Disables the button to stop the user from saving the same image multiple times
        saveButton.setEnabled(false);

    }

    /** Gets and displays a new photo*/
    public void newPhoto(){
        try{
            int rating = Integer.parseInt(ratingSlot.getText().trim());
            if(rating >= 1 && rating <= 5){
                currentPhoto.setRating(rating);
                ratingSum += rating;
                ratingCount ++;
                updateStats();
            }
        }
        catch (Exception e){
            System.out.println("Invalid Rating");
        }

        try{
            File imageFile = new File(currentPhoto.getPath());
            imageFile.delete();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        ratingSlot.setText("");

        saveButton.setEnabled(true);

        currentPhoto = randomImage.randomImage();

        imgLabel.setIcon(new ImageIcon(currentPhoto.getPath()));

        updateStats();
    }

    /** Updates the text displaying the users statistics if any values have changed*/
    private void updateStats(){
        String text = "Average rating: ";
        if(ratingCount == 0){
            text += " N/A";
        }
        else{
            text += Math.round((10.0 * ratingSum)/ratingCount) / 10.0;
        }
        text += "   |   Photos saved: " + saveCount;


        statsLabel.setText(text);
    }


    public static void main(String[] args) {
        new MyFrame();
    }


}
