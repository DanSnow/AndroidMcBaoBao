package io.github.dansnow.mcbaobao.ui.main

import dagger.Component
import io.github.dansnow.mcbaobao.ApplicationComponent
import io.github.dansnow.mcbaobao.ui.base.ActivityComponent
import io.github.dansnow.mcbaobao.ui.ActivityScope

@ActivityScope
@Component(
  modules = arrayOf(MainModule::class),
  dependencies = arrayOf(ApplicationComponent::class)
)
interface MainComponent: ActivityComponent {
  fun injectTo(activity: MainActivity)
}
