package com.rosen.wasswaderick.nairobijavageeks.presenter;

import com.rosen.wasswaderick.nairobijavageeks.model.GitHubUsers;
import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;
import com.rosen.wasswaderick.nairobijavageeks.model.User;
import com.rosen.wasswaderick.nairobijavageeks.service.RetrofitGitHubService;
import com.rosen.wasswaderick.nairobijavageeks.view.GitHubUserView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.when;

/**
 * Created by Derick W on 03,October,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
@RunWith(JUnit4.class)
public class GitHubUserPresenterTest {


    @Mock
    private RetrofitGitHubService retrofitGitHubService;

    @InjectMocks
    private GitHubUserPresenter gitHubUserPresenter;

    @Mock
    private GitHubUserView gitHubUserView;

    @Mock
    private Call<GitHubUsers> gUserResponseCall;

    @Mock
    private GitHubUsers gitHubUser;

    @Captor
    private ArgumentCaptor<Callback<GitHubUsers>> callbackArgumentCaptor;

    @Mock
    Response<GitHubUsers> response;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        // get the presenter reference and bind with view for testing
        gitHubUserPresenter = new GitHubUserPresenter(gitHubUserView);

    }

    @Test
    public void searchUsers() throws Exception{

        //real object call to presentDataFromApi();
        gitHubUserPresenter.fetchNairobiJavaGitHubUsers();

    }

    @Test
    public void testSuccessfulResponse(){
        //real object call to presentDataFromApi();

        gitHubUserPresenter.fetchNairobiJavaGitHubUsers();

        User user = new User("test name", "company", "blog", "location", "bio", "23", "24", "111");
        JavaGeekGitHubUser javaGeekGitHubUser = new JavaGeekGitHubUser( "IMAGE", "USERNAME",  "COMPANY", "URL", "HTML URL", user);

        ArrayList<JavaGeekGitHubUser> arrayList = new ArrayList<>();
        arrayList.add(javaGeekGitHubUser);

        GitHubUsers gitHubUsers = new GitHubUsers();
        gitHubUsers.setDevelopers("90");
        gitHubUsers.setUsers(arrayList);

        when(response.body()).thenReturn(gitHubUsers);
    }


}
