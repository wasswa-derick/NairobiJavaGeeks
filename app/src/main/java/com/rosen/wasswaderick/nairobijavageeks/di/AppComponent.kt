package com.rosen.wasswaderick.nairobijavageeks.di

import com.rosen.wasswaderick.nairobijavageeks.view.MainActivity
import dagger.Component

/**
 * Created by Derick W on 14,February,2019
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */

@Component(modules = [ServiceModule::class])
interface AppComponent {

    fun inject(mainactivity: MainActivity)

}
