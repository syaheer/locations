package com.syahiramir.bmwchallenge.rest;

import com.syahiramir.bmwchallenge.model.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Syahir on 7/3/17.
 * class for API caller using Retrofit
 */

public interface ApiInterface {

    @GET("Locations")
    Call<List<Location>> getLocations();
}
