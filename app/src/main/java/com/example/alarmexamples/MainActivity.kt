package com.example.alarmexamples

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

//        initElapsedRealtimeAlarm(pendingIntent, alarmManager)
//        initRTCAlarm(pendingIntent, alarmManager)
        initRepeatingAlarm(pendingIntent, alarmManager)
    }

    private fun initElapsedRealtimeAlarm(pendingIntent: PendingIntent, alarmManager: AlarmManager) {
        alarmManager.set(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + 60000,
            pendingIntent
        )
    }

    private fun initRTCAlarm(pendingIntent: PendingIntent, alarmManager: AlarmManager) {
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(System.currentTimeMillis())
        calendar.set(Calendar.HOUR_OF_DAY, 22)
        calendar.set(Calendar.MINUTE, 51)

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    private fun initRepeatingAlarm(pendingIntent: PendingIntent, alarmManager: AlarmManager) {
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(System.currentTimeMillis())
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 31)

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
            calendar.getTimeInMillis(),
            60000,
            pendingIntent);
    }
}
