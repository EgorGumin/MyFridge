package com.lymno.myfridge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lymno.myfridge.R;
import com.lymno.myfridge.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private ArrayList<Recipe> recipeData; // these are the things we want to display

    public RecipeAdapter(ArrayList<Recipe> recipes) {
        this.recipeData = recipes;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_adapter, parent, false);

        // create ViewHolder
        return new ViewHolder(itemLayoutView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
Context context;
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        Recipe recipeDataPos = recipeData.get(position);

        viewHolder.RecipeName.setText(recipeDataPos.getName());
        viewHolder.RecipeDescription.setText(recipeDataPos.getDescription());
        //viewHolder.RecipeFoodList.setText(recipeDataPos.getId());

        Picasso.with( viewHolder.imageView.getContext()).load(recipeDataPos.getImageUrl()).error(R.drawable.rec1).into(viewHolder.imageView);
//        viewHolder.imageView.setImageResource(R.drawable.rec1);
//        Drawable drawable=viewHolder.imageView.getContext().getResources().getDrawable(R.drawable.rec1);
//        drawable.setBounds(0,0,viewHolder.imageView.getWidth(), viewHolder.imageView.getHeight());
//                viewHolder.imageView.setImageDrawable(drawable);
    }

    public void updateItems (ArrayList<Recipe> items) {
        this.recipeData = items;
        notifyDataSetChanged();
    }

    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView RecipeName;
        public TextView RecipeDescription;
        public TextView RecipeFoodList;
        public ImageView imageView;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            RecipeName = (TextView) itemLayoutView.findViewById(R.id.tvCardRecipeName);
            RecipeDescription = (TextView) itemLayoutView.findViewById(R.id.tvRecipeDescription);
            RecipeFoodList = (TextView)itemLayoutView.findViewById(R.id.tvRecipeFoodList);
            imageView = (ImageView) itemLayoutView.findViewById(R.id.imageView);
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
        return recipeData.size();
    }
}
