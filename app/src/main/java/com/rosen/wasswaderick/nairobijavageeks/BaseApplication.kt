package com.rosen.wasswaderick.nairobijavageeks

import android.app.Application
import com.rosen.wasswaderick.nairobijavageeks.di.AppComponent
import com.rosen.wasswaderick.nairobijavageeks.di.ServiceModule
import com.rosen.wasswaderick.nairobijavageeks.di.DaggerAppComponent


/**
 * Created by Derick W on 14,February,2019
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
class BaseApplication: Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = createApplicationComponent()

        if (BuildConfig.DEBUG) {
            // Maybe TimberPlant etc.
        }
    }

    fun getApplicationComponent(): AppComponent {
        return component
    }

    fun createApplicationComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .serviceModule(ServiceModule())
                .build();
    }

}
