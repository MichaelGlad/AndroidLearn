package net.glm.actionmodetraining;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Michael on 18/03/2017.
 */

public class AnimalsRecyclerAdapter extends RecyclerView.Adapter <AnimalsRecyclerAdapter.AnimalViewHolder> {
    ArrayList<Animal> animalArrayList = new ArrayList<>();
    Context context;
    MainActivity mainActivity;


    public AnimalsRecyclerAdapter(ArrayList<Animal> animalArrayList, Context context) {
        this.animalArrayList = animalArrayList;
        this.context = context;
        mainActivity = (MainActivity) context;

    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,parent,false);
        AnimalViewHolder animalViewHolder = new AnimalViewHolder(view,mainActivity);
        return animalViewHolder;
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        holder.image.setImageResource(animalArrayList.get(position).getImage());
        holder.animalsName.setText(animalArrayList.get(position).getName());
        holder.animalsMail.setText(animalArrayList.get(position).getMail());
        if (!mainActivity.isActionMode){
            holder.checkBox.setVisibility(View.GONE);
        }else {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);
        }



    }

    @Override
    public int getItemCount() {
        return animalArrayList.size();
    }

    public static class AnimalViewHolder extends RecyclerView.ViewHolder  {
        ImageView image;
        TextView animalsName,animalsMail;
        CheckBox checkBox;
        MainActivity mainActivity;
        CardView cardView;

        public AnimalViewHolder(View itemView,MainActivity mainActivity) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            animalsName = (TextView) itemView.findViewById(R.id.animalsName);
            animalsMail = (TextView) itemView.findViewById(R.id.animalsMail);
            checkBox = (CheckBox) itemView.findViewById(R.id.check_list_item);
            this.mainActivity = mainActivity;

            cardView = (CardView) itemView.findViewById(R.id.card_view);
            cardView.setOnLongClickListener(mainActivity);
        }




    }
}
