package io.github.dansnow.mcbaobao.android.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager

class AlarmReceiver: BroadcastReceiver() {
  override fun onReceive(context: Context, intent: Intent) {
    val am = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    am.setRingerMode(AudioManager.RINGER_MODE_SILENT)
  }
}
