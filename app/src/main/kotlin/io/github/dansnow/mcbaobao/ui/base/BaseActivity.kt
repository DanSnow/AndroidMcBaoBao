package io.github.dansnow.mcbaobao.ui.base

import kotlin.reflect.KClass
import kotlin.reflect.full.staticFunctions
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import io.github.dansnow.mcbaobao.KotlinBoilerplateApp
import io.github.dansnow.mcbaobao.KotlinBoilerplateApp.Companion.graph
import io.github.dansnow.mcbaobao.ApplicationComponent

abstract class BaseActivity: AppCompatActivity() {
    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        injectDepencies(KotlinBoilerplateApp.graph)
    }

    abstract fun injectDepencies(graph: ApplicationComponent)
}
