package com.example.vinod.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinod.myapplication.model.DataModel;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder> {

  private List<DataModel> mDataModelList;
  private int rowLayout;
  private Context context;


  public static class DataViewHolder extends RecyclerView.ViewHolder {
    LinearLayout dataLayout;
    TextView tvPrNumber;
    TextView title;
    TextView tvUser;
    TextView tvPathUrl;


    public DataViewHolder(View v) {
      super(v);
      dataLayout = (LinearLayout) v.findViewById(R.id.data_layout);
      title = v.findViewById(R.id.tv_title);
      tvPrNumber = (TextView) v.findViewById(R.id.tv_pr_number);
      tvUser = (TextView) v.findViewById(R.id.tv_user);
      tvPathUrl = (TextView) v.findViewById(R.id.tv_patch_url);
    }
  }

  public RecyclerViewAdapter(List<DataModel> movies, int rowLayout, Context context) {
    this.mDataModelList = movies;
    this.rowLayout = rowLayout;
    this.context = context;
  }

  @Override
  public RecyclerViewAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
    return new DataViewHolder(view);
  }


  @Override
  public void onBindViewHolder(final DataViewHolder holder, final int position) {
    holder.tvPrNumber.setText("PR Number : " + mDataModelList.get(position).getNumber());
    holder.title.setText("Title : " + mDataModelList.get(position).getTitle());
    holder.tvUser.setText("User : " + mDataModelList.get(position).getUser().getLogin());
    holder.tvPathUrl.setText("Patch Url : " + mDataModelList.get(position).getRepositoryUrl());
    holder.dataLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (mDataModelList.get(position).getState().equals("closed")) {
          Toast.makeText(holder.tvPathUrl.getContext(), "This issue is closed", Toast.LENGTH_SHORT)
              .show();
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return mDataModelList.size();
  }
}
