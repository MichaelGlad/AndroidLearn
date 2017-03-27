package net.glm.actionmodetraining;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {



    Toolbar mainToolbar;
    Boolean isActionMode = false;
    TextView counterTextView;

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
        mainToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        counterTextView = (TextView) findViewById(R.id.txt_toolbar_counter);
        counterTextView.setVisibility(View.GONE);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main,menu);
        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        mainToolbar.getMenu().clear();
        mainToolbar.inflateMenu(R.menu.menu_action_mode);
        counterTextView.setVisibility(View.VISIBLE);
        isActionMode = true;
        adapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        return true;
    }
}