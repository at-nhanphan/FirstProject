package com.example.naunem.firstproject.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.example.naunem.firstproject.R;
import com.example.naunem.firstproject.activities.MyReceiverActivity;

/**
 * Created by naunem on 28/03/2017.
 */

public class MyReceiver extends BroadcastReceiver {

    private final String ACTION_PHONE_STATE = "android.intent.action.PHONE_STATE";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_PHONE_STATE)) {
            String phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            Toast.makeText(context, "Phone State: " + phoneState, Toast.LENGTH_SHORT).show();
            showNotification(context, phoneState);
        } else {
            showNotification(context, showMessage(context, intent));
        }
    }

    private void showNotification(Context context, String content) {
        String contentText = "mot con vit xoe ra 2 cai canh ......";
        Intent intent = new Intent(context, MyReceiverActivity.class);
        intent.putExtra("data", content);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notif = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("My notification")
                .setContentText(contentText)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notif.build());
    }

    private String showMessage(Context context, Intent intent) {
        Toast.makeText(context, "Hello .. có tin nhắn tới đó", Toast.LENGTH_LONG).show();
        String sms_extra = "pdus";
        Bundle bundle = intent.getExtras();
        Object[] objArr = (Object[]) bundle.get(sms_extra);
        String sms = "";
        if (objArr != null) {
            for (int i = 0; i < objArr.length; i++) {
                SmsMessage smsMsg = SmsMessage.createFromPdu((byte[]) objArr[i]);
                String body = smsMsg.getMessageBody();
                String address = smsMsg.getDisplayOriginatingAddress();
                sms += address + ":\n" + body + "\n";
            }
        }
        return sms;
    }
}
