
package com.example;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI{
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("RoverPics");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JLabel titleLabel = new JLabel("Enter city name:", SwingConstants.CENTER);
        JTextField inputField = new JTextField(); //input text

        JButton browseRandomButton = new JButton("Browse Random");//a button fetches data when pressed
        JButton browseRecommendedButton = new JButton("Browse Recommended");
        JButton likedPhotosButton = new JButton("View Liked Photos");
        JButton statsButton = new JButton("View Liked Photos");

        JTextArea outputArea = new JTextArea();//where the fetched data will output

        RandomImage rand = new RandomImage(2);
        String address = rand.randomImage().getURL();

        JLabel imgLabel = new JLabel();
        try {
            BufferedImage img = ImageIO.read(new URL("https://images.dog.ceo/breeds/lhasa/n02098413_387.jpg"));
            System.out.println(address);
            System.out.println(img);

            imgLabel.setIcon(new ImageIcon(img));
//            imgLabel.setIcon(new javax.swing.ImageIcon(new URL(address)));
        }
        catch(Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();

        }

        outputArea.setEditable(false);

        // Layout setup
        JPanel mainMenu = new JPanel(new GridLayout(2, 2)); //create the JPanel object
        //Jpanel is like a tray that you put things on and then you put the whole tray into your window
        //this panel holds a title, input field, button, and output area
        mainMenu.add(browseRandomButton);
        mainMenu.add(browseRecommendedButton);
        mainMenu.add(likedPhotosButton);
        mainMenu.add(statsButton);

        //We have added components to our panel, then we add the PANEL to our FRAME
        frame.add(mainMenu);



        // Button behavior
        browseRandomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                String city = inputField.getText().trim(); //city is in reference to my example
                if (!city.isEmpty()) {
                    // Placeholder for data — replace with real API call 
                    String result = "You searched for: " + city;
                    outputArea.setText(result);
                } else {
                    outputArea.setText("Please enter a city.");
                }
            }
        });

        frame.setVisible(true);
    }
}
