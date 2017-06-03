package io.github.dansnow.mcbaobao.data.network

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.Binds
// import io.github.dansnow.mcbaobao.ApplicationQualifier
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides @Singleton
    fun provideConnectivityManager(context: Context): ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides @Singleton
    fun provideNetworkInteractor(networkInteractor: NetworkInteractorImpl): NetworkInteractor = networkInteractor
}
