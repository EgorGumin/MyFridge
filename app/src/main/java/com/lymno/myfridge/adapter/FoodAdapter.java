package com.lymno.myfridge.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lymno.myfridge.Categories;
import com.lymno.myfridge.Measures;
import com.lymno.myfridge.MyDate;
import com.lymno.myfridge.R;
import com.lymno.myfridge.activity.FoodInfoActivity;
import com.lymno.myfridge.model.UserProduct;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private ArrayList<UserProduct> foodsData; // these are the things we want to display

    public FoodAdapter(ArrayList<UserProduct> foods) {
        this.foodsData = foods; //sorry for my English)))000))
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
        UserProduct foodData = foodsData.get(position);
        viewHolder.FoodName.setText("Test " + foodData.getName());
        Categories cat = Categories.get();
        viewHolder.tvDate.setText((new MyDate(foodData.getDate())).stayedTime().presentToString(""));


//        viewHolder.FoodEatBefore.setText(foodsDataPos.getDescription());
        //viewHolder.imgViewIcon.setImageResource(questsData[position].getImageUrl());
    }

    public void updateItems(ArrayList<UserProduct> items) {
        this.foodsData = items;
        notifyDataSetChanged();
    }


    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView FoodName;
        public TextView FoodEatLetfTime;
        public TextView tvDate;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            FoodName = (TextView) itemLayoutView.findViewById(R.id.food_adapter_name);
            FoodEatLetfTime = (TextView) itemLayoutView.findViewById(R.id.food_adapter_left_time);
            tvDate = (TextView) itemLayoutView.findViewById(R.id.textView4);
            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //TODO insert request activity
            Context context = view.getContext();
            Intent questInfoIntent = new Intent(context, FoodInfoActivity.class);
            UserProduct product = foodsData.get(getAdapterPosition());

            questInfoIntent.putExtra(FoodInfoActivity.INTENT_PRODUCT_ID, product.getId());
            questInfoIntent.putExtra(FoodInfoActivity.INTENT_NAME_STRING, (product.getName()));
            //questInfoIntent.putExtra(FoodInfoActivity.INTENT_CATEGORY_STRING, Categories.getItem(product.getCategory()));
            questInfoIntent.putExtra(FoodInfoActivity.INTENT_COUNT_INT, product.getQuantity());
            questInfoIntent.putExtra(FoodInfoActivity.INTENT_COUNT_IN_PACKET_INT, product.getQuantityByDefault());
            questInfoIntent.putExtra(FoodInfoActivity.INTENT_UNITS_STRING, Measures.getItem(product.getMeasure()));
            questInfoIntent.putExtra(FoodInfoActivity.INTENT_USE_LEFT_STRING,
                    (new MyDate(product.getDate())).stayedTime().presentToString("сьесть за: "));
            context.startActivity(questInfoIntent);
        }
    }

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return foodsData.size();
    }
}
