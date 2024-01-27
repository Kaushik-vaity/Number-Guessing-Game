package com.codtechitsolutions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGame extends JFrame {

    private int numberToGuess;
    private int attemptsLeft;

    private JTextField guessField;
    private JLabel resultLabel;
    private JButton submitButton;

    public NumberGuessingGame() {
        // Set up the JFrame
        super("Number Guessing Game");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize variables
        numberToGuess = (int) (Math.random() * 100) + 1; // Random number between 1 and 100
        attemptsLeft = 3; // You can adjust the number of attempts

        // Create components
        guessField = new JTextField(10);
        resultLabel = new JLabel("Enter a number between 1 to 100:");
        submitButton = new JButton("Submit");

        // Set up layout
        setLayout(new GridLayout(3, 1));
        add(resultLabel);
        add(guessField);
        add(submitButton);

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }
    
    // used to check the guess 
    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());

            if (userGuess == numberToGuess) {
                resultLabel.setText("Hurray! Your answer is correct. You won the game.");
                disableInput();
            } else if ((userGuess - numberToGuess) < -10) {
                resultLabel.setText("Your guess is too low. Please try again."+"     "+"Attempts left: "+(attemptsLeft-1));
            } else if ((userGuess - numberToGuess) > 10) {
                resultLabel.setText("Your guess is too high. Please try again."+"     "+"Attempts left: "+(attemptsLeft-1));
            } else if ((userGuess - numberToGuess) >= -10 && (userGuess - numberToGuess) <= 10) {
                resultLabel.setText("Your guess is really close. Please try again."+"     "+"Attempts left: "+(attemptsLeft-1));
            }
            attemptsLeft--;
            if (attemptsLeft == 0) {
                resultLabel.setText("You lost the game. Better luck next time.");
                disableInput();
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a number.");
        }
    }
    
    // used to disable the input field and button once game is over
    private void disableInput() {
        guessField.setEnabled(false);
        submitButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}
