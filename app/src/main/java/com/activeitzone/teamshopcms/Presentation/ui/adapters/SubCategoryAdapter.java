package com.activeitzone.teamshopcms.Presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.activeitzone.teamshopcms.Models.SubCategory;
import com.activeitzone.teamshopcms.R;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    private Context context;
    private List<SubCategory> subCategories;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public SubCategoryAdapter(Context context, List<SubCategory> subCategories) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.subCategories = subCategories;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.sub_category_list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(subCategories.get(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return subCategories.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.subcategory_name);
            recyclerView = itemView.findViewById(R.id.subsubcategory_list);
        }

        public void bind(final SubCategory subCategory) {
            textView.setText(subCategory.getName());
            LinearLayoutManager linearLayoutManager
                    = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            if (subCategory.getSubSubCategories().getData() == null) {

            } else {
                SubSubCategoryAdapter adapter = new SubSubCategoryAdapter(context, subCategory.getSubSubCategories().getData());
                recyclerView.setAdapter(adapter);
            }
        }
    }
}
