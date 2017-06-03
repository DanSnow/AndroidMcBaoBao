package io.github.dansnow.mcbaobao.ui.login

import dagger.Module
import dagger.Provides
import android.support.v7.app.AppCompatActivity
import io.github.dansnow.mcbaobao.ui.base.ActivityModule
import io.github.dansnow.mcbaobao.service.LoginService

@Module
class LoginModule(activity: AppCompatActivity): ActivityModule(activity)
