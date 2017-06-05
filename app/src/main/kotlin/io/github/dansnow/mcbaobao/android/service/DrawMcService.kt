package io.github.dansnow.mcbaobao.android.service

import android.app.IntentService
import android.content.Intent
import io.github.dansnow.mcbaobao.KotlinBoilerplateApp
import io.github.dansnow.mcbaobao.service.DrawService

class DrawMcService: IntentService("DrawMcService") {
  lateinit var drawService: DrawService
  override fun onCreate() {
    DaggerServiceComponent
      .builder()
      .applicationComponent(KotlinBoilerplateApp.graph)
      .build()
      .injectTo(this)
  }

  override fun onHandleIntent(intent: Intent) {
    drawService.draw()
  }
}
