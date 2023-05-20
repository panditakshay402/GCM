package com.example.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String token) {
        // Save the new FCM token to the Firebase Realtime Database
        // or perform any other necessary operations
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        // Handle incoming FCM messages here
        // Extract the necessary information from the RemoteMessage object
        // and create a notification to display to the user
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();

            // Customize the notification as per your requirements
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setAutoCancel(true);

            // Create an intent to open the app when the notification is tapped
            Intent intent;
            intent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            // Display the notification
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, builder.build());
        }
    }
}
