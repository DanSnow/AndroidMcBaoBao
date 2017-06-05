package io.github.dansnow.mcbaobao

import dagger.Component
import javax.inject.Singleton
import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.github.dansnow.mcbaobao.network.NetworkModule
import io.github.dansnow.mcbaobao.data.remote.ApiModule
import io.github.dansnow.mcbaobao.service.ServiceModule

import io.github.dansnow.mcbaobao.network.NetworkInteractor
import io.github.dansnow.mcbaobao.data.remote.McApiService

import io.github.dansnow.mcbaobao.ui.main.MainModule
import io.github.dansnow.mcbaobao.ui.main.MainComponent
import io.github.dansnow.mcbaobao.ui.login.LoginModule
import io.github.dansnow.mcbaobao.ui.login.LoginComponent

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        NetworkModule::class,
        ApiModule::class,
        ServiceModule::class
))
interface ApplicationComponent {

    // Injectors
    fun injectTo(app: KotlinBoilerplateApp)

    // Expose
    fun telephonyManager(): TelephonyManager
    fun rxSharedPreferences(): RxSharedPreferences
    fun mcApiService(): McApiService
    fun connectivityManager(): ConnectivityManager
    fun networkInteractor(): NetworkInteractor
}
