package com.rosen.wasswaderick.nairobijavageeks.presenter;


import com.rosen.wasswaderick.nairobijavageeks.model.User;
import com.rosen.wasswaderick.nairobijavageeks.service.RetrofitGitHubService;
import com.rosen.wasswaderick.nairobijavageeks.view.UserDetailView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


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
public class UserDetailPresenterTest {

    @Mock
    private RetrofitGitHubService retrofitGitHubService;

    @InjectMocks
    private UserDetailPresenter userDetailPresenter;

    @Mock
    private UserDetailView userDetailView;

    @Mock
    private Call<User> gUserResponseCall;

    @Mock
    private User user;

    @Captor
    private ArgumentCaptor<Callback<User>> callbackArgumentCaptor;

    @Mock
    Response<User> response;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        // get the presenter reference and bind with view for testing
        userDetailPresenter = new UserDetailPresenter(userDetailView);

    }

    @Test
    public void searchUsers() throws Exception{

        //real object call to presentDataFromApi();
        userDetailPresenter.fetchUserDetails("test user");

    }

    @Test
    public void testSuccessfulResponse(){
        userDetailPresenter.fetchUserDetails("test user");
        User user = new User("test name", "company", "blog", "location", "bio", "23", "24", "111");
        when(response.body()).thenReturn(user);
    }


    private User getUser() {
        User user = new User();
        user.setBio("test bio");
        user.setBlog("test blog");
        user.setCompany("test company");
        user.setFollowers("23");
        user.setFollowing("23");
        user.setName("test user");
        user.setPublicRepos("3");

        return user;
    }


}
