package com.example;


import javax.swing.*;

 
public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My first JFrame");
        frame.setSize(600, 600);  
        ImageIcon image1 = new ImageIcon("JavaAPIProject\\src\\main\\java\\com\\example\\dog.jpg");
        frame.add(new JLabel(image1));
        frame.pack();
        frame.setVisible(true);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }
    
}