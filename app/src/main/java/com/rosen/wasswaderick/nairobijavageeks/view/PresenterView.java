package com.rosen.wasswaderick.nairobijavageeks.view;

import com.rosen.wasswaderick.nairobijavageeks.model.User;

/**
 * Created by Derick W on 25,September,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
public interface PresenterView {

    void fetchNairobiJavaGitHubUsers();

    User fetchUserDetails(String username);

}