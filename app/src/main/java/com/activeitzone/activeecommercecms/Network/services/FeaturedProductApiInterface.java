package com.activeitzone.activeecommercecms.Network.services;

import com.activeitzone.activeecommercecms.Network.response.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeaturedProductApiInterface {
    @GET("categories/featured")
    Call<ProductResponse> getFeaturedPrdocuts();
}
