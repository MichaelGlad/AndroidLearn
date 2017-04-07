package net.glm.contextualaction;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 29/03/2017.
 */

public class AnimalsRecyclerAdapter extends RecyclerView.Adapter <AnimalsRecyclerAdapter.AnimalViewHolder> {
    List<Animal> animalArrayList = new ArrayList<>();
    MainActivity mainActivity;

    public AnimalsRecyclerAdapter (List<Animal> animalArrayList, MainActivity mainActivity){
        this.animalArrayList = animalArrayList;
        this.mainActivity = mainActivity;
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,parent,false);
        AnimalViewHolder animalViewHolder = new AnimalViewHolder(view,mainActivity);
        return animalViewHolder;
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        holder.animalImage.setImageResource(animalArrayList.get(position).getImage());
        holder.txtAnimalName.setText(animalArrayList.get(position).getName());
        holder.txtAnimalMail.setText(animalArrayList.get(position).getMail());
        if (!mainActivity.isInActionMode){
            holder.checkBox.setVisibility(View.GONE);

        }else {
            holder.checkBox.setChecked(false);
            holder.checkBox.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return animalArrayList.size();
    }

    public static class AnimalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView animalImage;
        TextView txtAnimalName,txtAnimalMail;
        CheckBox checkBox;
        MainActivity mainActivity;
        CardView cardView;

        public AnimalViewHolder(View itemView, MainActivity mainActivity) {


            super(itemView);
            animalImage = (ImageView) itemView.findViewById(R.id.image);
            txtAnimalName = (TextView) itemView.findViewById(R.id.txt_animals_name);
            txtAnimalMail = (TextView) itemView.findViewById(R.id.txt_animals_mail);
            checkBox = (CheckBox) itemView.findViewById(R.id.chkbox_recyclerView);
            checkBox.setOnClickListener(this);
            this.mainActivity = mainActivity;
            if (mainActivity != null) {
                cardView = (CardView) itemView.findViewById(R.id.card_view);
                cardView.setOnLongClickListener(mainActivity);
            }



        }


        @Override
        public void onClick(View v) {
            mainActivity.prepareSelection(v,getAdapterPosition());
        }
    }




}
