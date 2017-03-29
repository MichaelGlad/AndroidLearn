package net.glm.contextualaction;

import android.content.res.TypedArray;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static final String MY_LOGS = "My_Logs";
    String[] arrayAnimalsName;
    String[] arrayAnimalsMail;
    TypedArray arrayAnimalsImage;

    RecyclerView recyclerView;
    AnimalsRecyclerAdapter animalsRecyclerAdapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Animal> animalsList = new ArrayList<>();
    Animal animal;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        arrayAnimalsMail = getResources().getStringArray(R.array.animals_mail);
        arrayAnimalsName = getResources().getStringArray(R.array.animals_name);
        arrayAnimalsImage = getResources().obtainTypedArray(R.array.animals_images);

        for (int i = 0; i < arrayAnimalsName.length ; i++) {
            animal = new Animal(arrayAnimalsImage.getResourceId(i,-1),arrayAnimalsName[i],arrayAnimalsMail[i]);
            animalsList.add(animal);

        }

        arrayAnimalsImage.recycle();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        animalsRecyclerAdapter = new AnimalsRecyclerAdapter(animalsList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(animalsRecyclerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        //if (newText.isEmpty()) return false;

        newText = newText.toLowerCase();
        Log.d(MY_LOGS,"The newText is - " + newText);
        ArrayList<Animal> newList = new ArrayList<>();
        for (Animal animal : animalsList){
            String name = animal.getName();
            name =name.toLowerCase();
            Log.d(MY_LOGS," The name of animal is - " + name);
            if (name.contains(newText))
                newList.add(animal);
        }

        animalsRecyclerAdapter.setFilter(newList);
        return false;
    }


}
