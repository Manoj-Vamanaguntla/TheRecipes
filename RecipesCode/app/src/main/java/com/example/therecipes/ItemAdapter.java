package com.example.therecipes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private ArrayList<RecipeItem> recipeList;
    private List<RecipeItem> completeRecipeList;
    private Context context;

    public ItemAdapter(ArrayList<RecipeItem> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
        this.completeRecipeList = new ArrayList<>(recipeList);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView m1;
        public TextView m2;
        public TextView m3;
        public LinearLayout lt;

        public ItemViewHolder(View itemView) {
            super(itemView);
            m1 = itemView.findViewById(R.id.textView1);
            m2 = itemView.findViewById(R.id.textView2);
            m3 = itemView.findViewById(R.id.textView3);
            lt = itemView.findViewById(R.id.linearlayout);
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        RecipeItem currentItem = recipeList.get(position);
        final String recipeName = currentItem.getName();
        holder.m1.setText(recipeName);
        holder.m2.setText(currentItem.getIngredients());
        holder.m3.setText(currentItem.getProcess());

        holder.lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("context", String.valueOf(context));
                Parse.initialize(new Parse.Configuration.Builder(context)
                        .applicationId("T4jyEZhrzfcSJQQ9ANSxMI6BuNzcjzzX6h6sDbNV")
                        .clientKey("ksUIefM5sqiqUPY3r7bDMHIy4Od3phIOtFWuwEg8")
                        .server("https://parseapi.back4app.com/")
                        .build()
                );

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Recipe");
                query.whereEqualTo("RecipeName", recipeName);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null && objects != null && objects.size() > 0) {
                            ParseObject po = objects.get(0);
                            String ingredients = po.getString("Ingredients");
                            String process = po.getString("Process");

                            Intent intent = new Intent(context, RecipeCompleteDetailsActivity.class);
                            intent.putExtra("RecipeName", recipeName);
                            intent.putExtra("Ingredients", ingredients);
                            intent.putExtra("Process", process);
                            context.startActivity(intent);
                        } else {
                            Log.e("ItemAdapter", "Error fetching recipe details", e);
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
