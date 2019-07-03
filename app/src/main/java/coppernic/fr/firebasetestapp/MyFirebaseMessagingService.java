package coppernic.fr.firebasetestapp;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(MainActivity.TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(MainActivity.TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use WorkManager.
                Log.d(MainActivity.TAG, "schedule message : " + remoteMessage.toString());
            } else {
                // Handle message within 10 seconds

                Log.d(MainActivity.TAG, "handle Now message : " + remoteMessage.toString());
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(MainActivity.TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            if(null != MainActivity.sNotificationHandler) {
                MainActivity.sNotificationHandler.obtainMessage(MainActivity.GET_NOTIFICATION, remoteMessage.getNotification().getBody()).sendToTarget();
            }
        }

    }
}
