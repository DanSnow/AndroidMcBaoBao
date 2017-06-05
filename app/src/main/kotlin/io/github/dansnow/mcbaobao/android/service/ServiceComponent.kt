package io.github.dansnow.mcbaobao.android.service

import javax.inject.Singleton
import dagger.Component
import io.github.dansnow.mcbaobao.ApplicationComponent

@ServiceScope
@Component(
  modules = arrayOf(ServiceModule::class),
  dependencies = arrayOf(ApplicationComponent::class)
)
interface ServiceComponent {
  fun injectTo(service: DrawMcService)
}

