package com.rosen.wasswaderick.nairobijavageeks.view;


import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Derick W on 25,September,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
public interface PresenterView {

    void fetchNairobiJavaGitHubUsers();

    ArrayList<JavaGeekGitHubUser> fetchLocalUsers();

    void insertUser(List<JavaGeekGitHubUser> javaGeekGitHubUser);

}
