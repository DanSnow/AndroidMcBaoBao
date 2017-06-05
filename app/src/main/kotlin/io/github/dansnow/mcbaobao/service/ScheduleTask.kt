package io.github.dansnow.mcbaobao.service

import java.util.Calendar
import javax.inject.Inject
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import io.github.dansnow.mcbaobao.android.receiver.AlarmReceiver

class ScheduleTask @Inject constructor(val alarmManager: AlarmManager, val context: Context) {
  fun schedule() {
    val intent = Intent(context, AlarmReceiver::class.java)
    val alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
    val calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 5);
    alarmManager.setRepeating(
      AlarmManager.RTC_WAKEUP,
      calendar.getTimeInMillis(),
      AlarmManager.INTERVAL_DAY,
      alarmIntent
    )
  }
}
