package com.enggemy22.cap.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enggemy22.cap.R;
import com.enggemy22.cap.models.University;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    List<University> mlist;
    Context context;
    OnItemClickListner listner;

    public ItemAdapter(List<University> mlist, Context context, OnItemClickListner listner) {
        this.mlist = mlist;
        this.context = context;
        this.listner = listner;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        University university = mlist.get(position);
        holder.mtxtUniversity.setText(university.getName());
        holder.mGpa.setText(university.getGpa());
        holder.mSatScore.setText(university.getSat_score_range());
        holder.mAccetRate.setText(university.getAcceptance_rate());
        holder.mFinanialAid.setText(university.getFinanial_aid());
        holder.mActRange.setText(university.getEst_score_range());
        holder.mAverageFess.setText(university.getAverage_fees());
        holder.mCountry.setText(university.getCountry());
        holder.mglobal_Qs_rank.setText(university.getQs_ranking());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView mtxtUniversity;
        TextView mGpa;
        TextView mSatScore;
        TextView mFinanialAid;
        TextView mAccetRate;
        TextView mActRange;
        TextView mAverageFess;
        TextView mCountry;
        TextView mglobal_Qs_rank;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mtxtUniversity = itemView.findViewById(R.id.univerName);
            mGpa = itemView.findViewById(R.id.Gpa);
            mSatScore = itemView.findViewById(R.id.sat_range);
            mFinanialAid = itemView.findViewById(R.id.firnainal_Aid);
            mAccetRate = itemView.findViewById(R.id.Accept_rate);
            mActRange = itemView.findViewById(R.id.Act_scoreRange);
            mAverageFess = itemView.findViewById(R.id.Average_Tutition_fees);
            mCountry = itemView.findViewById(R.id.Country);
            mglobal_Qs_rank = itemView.findViewById(R.id.global_Qs_rank);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos!=RecyclerView.NO_POSITION){
                        if(listner!=null){
                            listner.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListner {
        void onItemClick(int position);
    }

}
