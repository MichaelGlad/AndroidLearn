package net.glm.recyclerviewtraining2;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Animal> animalsArrayList = new ArrayList<>();
    Animal oneAnimal;

    String[] animalsName,animalsMail;
    TypedArray images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        animalsName = getResources().getStringArray(R.array.animals_name);
        animalsMail = getResources().getStringArray(R.array.animals_mail);
        images = getResources().obtainTypedArray(R.array.animals_images);

        for (int i = 0; i < animalsName.length ; i++) {
            oneAnimal = new Animal(images.getResourceId(i,-1),animalsName[i],animalsMail[i]);
            animalsArrayList.add(oneAnimal);


        }
        images.recycle();

        adapter = new AnimalsRecyclerAdapter(animalsArrayList,this);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
