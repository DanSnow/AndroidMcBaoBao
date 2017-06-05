package io.github.dansnow.mcbaobao

import android.app.Application
import android.app.AlarmManager
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.content.SharedPreferences
import android.telephony.TelephonyManager
import dagger.Module
import dagger.Provides
import timber.log.Timber
import com.f2prateek.rx.preferences2.RxSharedPreferences
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: KotlinBoilerplateApp) {

    @Provides @Singleton
    fun provideApplication(): Application = app

    @Provides @Singleton
    fun provideContext(): Context = app.baseContext

    @Provides @Singleton
    fun provideResources(): Resources = app.resources

    @Provides @Singleton
    fun provideLayoutInflater(@ApplicationQualifier context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    @Provides @Singleton
    fun provideTelephonyManager(context: Context): TelephonyManager =
        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    @Provides @Singleton
    fun provideAlarmManager(context: Context): AlarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    @Provides @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences = 
        context.getSharedPreferences(AppConstants.PREF_KEY, Context.MODE_PRIVATE)

    @Provides @Singleton
    fun provideRxSharedPreferences(
        sharedPref: SharedPreferences
    ): RxSharedPreferences = RxSharedPreferences.create(sharedPref)

    @Provides
    fun provideDebugTree(): Timber.DebugTree = Timber.DebugTree()
}
