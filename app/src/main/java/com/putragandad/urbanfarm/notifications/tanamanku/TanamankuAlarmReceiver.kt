package com.putragandad.urbanfarm.notifications.tanamanku

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.putragandad.urbanfarm.MainActivity
import com.putragandad.urbanfarm.R

class TanamankuAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val i = Intent(context, MainActivity::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_MUTABLE)

        val customText = intent?.getStringExtra("customText") ?: "Default Text"

        val builder = NotificationCompat.Builder(context!!, "urbanFarmNotificationAlarm")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("UrbanFarm")
            .setContentText(customText)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123, builder.build())
    }
}