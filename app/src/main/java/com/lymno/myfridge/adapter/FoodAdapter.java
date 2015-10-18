package com.lymno.myfridge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lymno.myfridge.R;
import com.lymno.myfridge.model.Food;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private ArrayList<Food> foodData; // these are the things we want to display

    public FoodAdapter(ArrayList<Food> foods) {
        this.foodData = foods; //sorry for my English)))000))
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_adapter, parent, false);

        // create ViewHolder
        return new ViewHolder(itemLayoutView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
Context context;
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        Food foodsDataPos = foodData.get(position);
        viewHolder.FoodName.setText(foodsDataPos.getName());
        viewHolder.FoodDescription.setText(foodsDataPos.getDescription());
        //viewHolder.imgViewIcon.setImageResource(questsData[position].getImageUrl());
    }

    public void updateItems (ArrayList<Food> items) {
        this.foodData = items;
        notifyDataSetChanged();
    }

    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView FoodName;
        public TextView FoodDescription;
        //public ImageView imgViewIcon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            FoodName = (TextView) itemLayoutView.findViewById(R.id.tvCardFoodName);
            FoodDescription = (TextView) itemLayoutView.findViewById(R.id.tvFoodDescription);
            //imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.item_icon);
            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            Context context = view.getContext();
//            Intent questInfoIntent = new Intent(context, QuestInfo.class);
//            questInfoIntent.putExtra("questId", questsData.get(getAdapterPosition()).getId());
//            questInfoIntent.putExtra("amountStages", questsData.get(getAdapterPosition()).getAmountStages());
//            questInfoIntent.putExtra("questDescription", questsData.get(getAdapterPosition()).getDescription());
//            questInfoIntent.putExtra("questLength", questsData.get(getAdapterPosition()).getLength());
//            questInfoIntent.putExtra("questName", questsData.get(getAdapterPosition()).getName());
//            context.startActivity(questInfoIntent);
        }
    }

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return foodData.size();
    }
}
