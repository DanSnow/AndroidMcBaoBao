package io.github.dansnow.mcbaobao.android.service

import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import android.content.Context
import io.github.dansnow.mcbaobao.ui.base.ActivityModule
import io.github.dansnow.mcbaobao.service.LoginService

@Module
class ServiceModule(val context: Context) {
  @Provides @Singleton
  fun provideContext() = context
}
