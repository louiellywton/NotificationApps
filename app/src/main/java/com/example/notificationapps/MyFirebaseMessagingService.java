package com.example.notificationapps;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.List;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static List<RemoteMessage.Notification> notifications = new ArrayList<>();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // Handle the incoming FCM message here
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();

            // Store the received notification
            notifications.add(remoteMessage.getNotification());

            // Show the notification
            NotificationUtils.showNotification(this, title, message);

            // Customize the handling of the notification data as per your requirements
            // For example, you can update the UI, play a sound, etc.
        }
    }

    @Override
    public void onNewToken(String token) {
        // Handle the generation of a new FCM token here
        // You can send the new token to your server or perform any additional logic
    }

    public static List<RemoteMessage.Notification> getNotifications() {
        return notifications;
    }
}
