
package com.example;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.image.BufferedImage;
import com.example.ImageObject;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI{
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Data Viewer");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JLabel titleLabel = new JLabel("Enter city name:", SwingConstants.CENTER);
        JTextField inputField = new JTextField(); //input text 
        JButton fetchButton = new JButton("Fetch Data");//a button fetches data when pressed
        JTextArea outputArea = new JTextArea();//where the fetched data will output 

        RandomImage rand = new RandomImage(2);

        try{
            ImageIcon originalIcon = new ImageIcon(new URL(rand.randomImage().getURL()));
            Image scaledImage = originalIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
            JLabel displayField = new JLabel(new ImageIcon(scaledImage));
            displayField.setHorizontalAlignment(JLabel.CENTER);
            frame.add(displayField);
        }
        catch(Exception e){}

        outputArea.setEditable(false);

        // Layout setup
        JPanel panel = new JPanel(new GridLayout(4, 1)); //create the JPanel object
        //Jpanel is like a tray that you put things on and then you put the whole tray into your window
        //this panel holds a title, input field, button, and output area
        panel.add(titleLabel);
        panel.add(inputField);
        panel.add(fetchButton);
        panel.add(outputArea);

        //We have added components to our panel, then we add the PANEL to our FRAME
        frame.add(panel); 



        // Button behavior
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
