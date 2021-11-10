package com.w4eret1ckrtb1tch.homework

import android.app.Application
import com.w4eret1ckrtb1tch.homework.di.AppComponent
import com.w4eret1ckrtb1tch.homework.di.DaggerAppComponent


class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().context(this.applicationContext).build()
    }

}