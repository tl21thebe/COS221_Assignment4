/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.northwindgui;

/**
 *
 * @author leroy
 */
import com.mycompany.northwindgui.data.NotificationDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NotificationsPanel extends JPanel {
    private JTextArea notificationArea;

    public NotificationsPanel() {
        setLayout(new BorderLayout());

        notificationArea = new JTextArea();
        notificationArea.setEditable(false);
        add(new JScrollPane(notificationArea), BorderLayout.CENTER);

        loadNotifications();
    }

    private void loadNotifications() {
        NotificationDAO notificationDAO = new NotificationDAO();
        List<String> notifications = notificationDAO.getAllNotifications();
        notificationArea.setText(String.join("\n", notifications));
    }
}
