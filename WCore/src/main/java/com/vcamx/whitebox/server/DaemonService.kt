package com.vcamx.whitebox.server

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.vcamx.whitebox.utils.compat.BuildCompat

object DaemonService : Service() {
    val TAG: String = "DaemonService"
    private val NOTIFY_ID = (System.currentTimeMillis() / 1000).toInt()
    override fun onBind(intent: Intent): IBinder? { return null }

    override fun onCreate() {
        super.onCreate()
        initNotificationManager()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val innerIntent = Intent(this, DaemonInnerService::class.java)
        startService(innerIntent)
        if (BuildCompat.isOreo()) {
            showNotification()
        }
        return START_STICKY
    }

    private fun showNotification() {
//        Intent activity = new Intent(getApplicationContext(), MainActivity.class);
//        activity.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
//                0, activity, PendingIntent.FLAG_UPDATE_CURRENT);

        val builder = NotificationCompat.Builder(applicationContext, "$packageName.blackbox")
            .setPriority(NotificationCompat.PRIORITY_MAX)
        startForeground(NOTIFY_ID, builder.build())
    }

    private fun initNotificationManager() {
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val CHANNEL_ONE_ID = "$packageName.blackbox"
        val CHANNEL_ONE_NAME = "blackbox"
        if (BuildCompat.isOreo()) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ONE_ID,
                CHANNEL_ONE_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                enableLights(true)
                lightColor = Color.RED
                setShowBadge(true)
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            }
            nm.createNotificationChannel(notificationChannel)
        }
    }
    class DaemonInnerService : Service() {
        override fun onCreate() {
            Log.i(TAG, "DaemonInnerService -> onCreate")
            super.onCreate()
        }

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            Log.i(TAG, "DaemonInnerService -> onStartCommand")
            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.cancel(NOTIFY_ID)
            stopSelf()
            return super.onStartCommand(intent, flags, startId)
        }

        override fun onBind(intent: Intent?): IBinder? {
            return null
        }

        override fun onDestroy() {
            Log.i(TAG, "DaemonInnerService -> onDestroy")
            super.onDestroy()
        }
    }

}