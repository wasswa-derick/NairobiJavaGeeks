package com.rosen.wasswaderick.nairobijavageeks.di

import com.rosen.wasswaderick.nairobijavageeks.service.GitHubUserAPI
import com.rosen.wasswaderick.nairobijavageeks.service.RetrofitGitHubService
import dagger.Module
import dagger.Provides

/**
 * Created by Derick W on 14,February,2019
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
@Module
class ServiceModule {

    @Provides
    fun provideGitHubUserAPI(): GitHubUserAPI {
        return RetrofitGitHubService.getGitHubUserAPI()
    }

}
