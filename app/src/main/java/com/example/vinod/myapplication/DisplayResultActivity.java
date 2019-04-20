package com.example.vinod.myapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.vinod.myapplication.databinding.ActivityDisplayResultBinding;
import com.example.vinod.myapplication.model.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayResultActivity extends AppCompatActivity {

  private ActivityDisplayResultBinding mBinder;
  private String mOrganisationName;
  private String mRepositoryName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinder = DataBindingUtil.setContentView(this, R.layout.activity_display_result);
    setSupportActionBar(mBinder.toolbar);
    if (null != getIntent()) {
      mOrganisationName = getIntent().getStringExtra("Organisation_Name");
      mRepositoryName = getIntent().getStringExtra("Repository_Name");
    }
    initApiCall();
  }

  public void initApiCall() {
    mBinder.progressBarCyclic.setVisibility(View.VISIBLE);
    final RecyclerView recyclerView = findViewById(R.id.rv_list);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    Call<List<DataModel>> call =
        apiService.getClosedIssues(mOrganisationName, mRepositoryName, "closed");
    call.enqueue(new Callback<List<DataModel>>() {
      @Override
      public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
        mBinder.progressBarCyclic.setVisibility(View.GONE);
        System.out.println("" + response.toString());
        Response<List<DataModel>> DataModelList = response;
        System.out.println("" + response.toString());
        recyclerView.setAdapter(new RecyclerViewAdapter(DataModelList.body(),
            R.layout.list_item_data, getApplicationContext()));
      }

      @Override
      public void onFailure(Call<List<DataModel>> call, Throwable t) {
        // Log error here since request failed
        Log.e("", t.toString());
        Toast.makeText(DisplayResultActivity.this, "Something went wrong", Toast.LENGTH_SHORT)
            .show();
        mBinder.progressBarCyclic.setVisibility(View.GONE);
      }
    });
  }
}

