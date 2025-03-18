package org.example.ui;

import javax.swing.*;
import java.awt.*;

public class ErrorPane {
    private ErrorPane() {}

    public static void invokeError(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(parent,
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }
}
