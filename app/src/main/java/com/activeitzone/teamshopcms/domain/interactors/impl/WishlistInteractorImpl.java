package com.activeitzone.teamshopcms.domain.interactors.impl;

import android.util.Log;

import com.activeitzone.teamshopcms.Network.ApiClient;
import com.activeitzone.teamshopcms.Network.response.WishlistResponse;
import com.activeitzone.teamshopcms.Network.services.WishlistApiInterface;
import com.activeitzone.teamshopcms.domain.executor.Executor;
import com.activeitzone.teamshopcms.domain.executor.MainThread;
import com.activeitzone.teamshopcms.domain.interactors.WishlistInteractor;
import com.activeitzone.teamshopcms.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishlistInteractorImpl extends AbstractInteractor {
    private WishlistInteractor.CallBack mCallback;
    private WishlistApiInterface apiService;
    private int user_id;
    private String token;

    public WishlistInteractorImpl(Executor threadExecutor, MainThread mainThread, WishlistInteractor.CallBack callBack, int id, String token) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.user_id = id;
        this.token = "Bearer "+token;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(WishlistApiInterface.class);
        Call<WishlistResponse> call = apiService.getWishlistItems(token,"wishlists/"+user_id);

        call.enqueue(new Callback<WishlistResponse>() {
            @Override
            public void onResponse(Call<WishlistResponse> call, Response<WishlistResponse> response) {
                try {
                    mCallback.onWishlistLodaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<WishlistResponse> call, Throwable t) {
                mCallback.onWishlistError();
            }
        });

    }
}
