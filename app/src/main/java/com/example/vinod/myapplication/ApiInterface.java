package com.example.vinod.myapplication;

import com.example.vinod.myapplication.model.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
  // https://api.github.com/repos/prestodb/presto/issues?state=closed
  @GET("{prestodb}/{presto}/issues?state=closed")
  Call<List<DataModel>> getClosedIssues(@Path("prestodb") String prestodb,
      @Path("presto") String presto, @Query("state") String state);

  /*
   * @GET("prestodb/presto/issues?state=closed") Call<List<DataModel>> getClosedIssues();
   */
}
