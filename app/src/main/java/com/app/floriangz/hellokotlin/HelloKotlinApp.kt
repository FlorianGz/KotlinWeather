package com.app.floriangz.hellokotlin

import android.app.Application
import android.content.Context
import android.location.Location
import com.patloew.rxlocation.RxLocation
import io.reactivex.Maybe
import io.realm.Realm

internal class HelloKotlinApp : Application() {

    init {
        instance = this
    }

    companion object {
        var instance: HelloKotlinApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

        fun getUserLocation() : Maybe<Location> {
            return RxLocation(applicationContext()).location().lastLocation()
        }
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}


