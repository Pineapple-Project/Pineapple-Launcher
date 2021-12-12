package fr.pineapplemc.launcher.main;

import fr.pineapplemc.launcher.PineappleLauncher;
import javafx.application.Application;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("javafx.application.Application");
            Application.launch(PineappleLauncher.class, args);
        }catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "JavaFX error: " + e.getMessage() + " was not found.",
                    "Library Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
