package com.example.vinod.myapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.vinod.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding mBinder;
  private String mOrganisationName;
  private String mRepositoryName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);
    setSupportActionBar(mBinder.toolbar);
    initViews();
  }

  private void initViews() {
    mBinder.etOrganisationName.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void afterTextChanged(Editable editable) {
        mOrganisationName = editable.toString();
      }
    });
    mBinder.etRepositoryName.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void afterTextChanged(Editable editable) {
        mRepositoryName = editable.toString();
      }
    });
    mBinder.btFetchResults.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        callDisplayResultActivity();
      }
    });
  }

  private void callDisplayResultActivity() {
    Intent mIntent = new Intent(this, DisplayResultActivity.class);
    mIntent.putExtra("Organisation_Name", mOrganisationName);
    mIntent.putExtra("Repository_Name", mRepositoryName);
    startActivity(mIntent);
  }

}
