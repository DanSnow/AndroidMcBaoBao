package io.github.dansnow.mcbaobao.network

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.Binds
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides @Singleton
    fun provideConnectivityManager(context: Context): ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides @Singleton
    fun provideNetworkInteractor(networkInteractor: NetworkInteractorImpl): NetworkInteractor = networkInteractor
}
