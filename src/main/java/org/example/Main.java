package org.example;

import org.example.ui.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }
}