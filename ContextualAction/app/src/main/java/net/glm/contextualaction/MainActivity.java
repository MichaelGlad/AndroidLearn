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
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnLongClickListener {
    public static final String MY_LOGS = "My_Logs";
    String[] arrayAnimalsName;
    String[] arrayAnimalsMail;
    TypedArray arrayAnimalsImage;
    public Boolean isInActionMode =false;

    RecyclerView recyclerView;
    AnimalsRecyclerAdapter animalsRecyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    TextView counterText;

    ArrayList<Animal> animalsList = new ArrayList<>();
    ArrayList<Animal> selectedList = new ArrayList<>();
    ArrayList<Animal> fullAnimalsList = new ArrayList<>();

    ArrayList<Animal> deleteList = new ArrayList<>();
    int counter =0;

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

        counterText = (TextView) findViewById(R.id.counter_text);
        counterText.setVisibility(View.GONE);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        for(Animal animal:animalsList){
            fullAnimalsList.add(animal);
            selectedList.add(animal);
        }

        animalsRecyclerAdapter = new AnimalsRecyclerAdapter(selectedList,this);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete ) {

            //selectedList.addAll(fullAnimalsList);

            for (Animal animal : deleteList) {
                Log.d(MY_LOGS,"Now check the " + animal.getName());
                if (fullAnimalsList.contains(animal)) {
                    Log.d(MY_LOGS,"DELETE THE  " + animal.getName() + "From selected");
                    fullAnimalsList.remove(animal);
                }
            }
            selectedList.clear();
            selectedList.addAll(fullAnimalsList);
            backToMainMode();

        }

        if (item.getItemId() == android.R.id.home){
            selectedList.clear();
            selectedList.addAll(fullAnimalsList);
            backToMainMode();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        Log.d(MY_LOGS,"In Search with word  - " + newText);
        if (isInActionMode == true){
            return false;
        }

        selectedList.clear();
        if (newText.isEmpty() ){
            selectedList.addAll(fullAnimalsList);
            animalsRecyclerAdapter.notifyDataSetChanged();
            return  false;
        }

        newText = newText.toLowerCase();


        for (Animal animal : fullAnimalsList){
            String name = animal.getName();
            name =name.toLowerCase();

            if (name.contains(newText))
                selectedList.add(animal);
        }

       animalsRecyclerAdapter.notifyDataSetChanged();
        return false;
    }


    @Override
    public boolean onLongClick(View v) {
        isInActionMode = true;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_action_mode);
        counterText.setVisibility(View.VISIBLE);
        animalsRecyclerAdapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        return true;
    }

    @Override
    public void onBackPressed() {
        if (isInActionMode){
            fullAnimalsList.clear();
            selectedList.clear();
            for(Animal animal:animalsList){
                fullAnimalsList.add(animal);
                selectedList.add(animal);
            }

            backToMainMode();

        }else {
            super.onBackPressed();
        }
    }

    public void backToMainMode(){
        isInActionMode =false;
        counterText.setVisibility(View.GONE);
        toolbar.getMenu().clear();
        toolbar.inflateMenu((R.menu.menu_item));

        deleteList.clear();
        counter =0;

        MenuItem menuItem = toolbar.getMenu().findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        animalsRecyclerAdapter.notifyDataSetChanged();

    }

    public void prepareSelection (View view, int position){
        if (((CheckBox) view).isChecked()){
            deleteList.add(selectedList.get(position));
            counter++;
            updateCounter(counter);
        }else{
            deleteList.remove(selectedList.get(position));
            counter--;
            updateCounter(counter);

        }
    }

    public  void updateCounter (int counter){
        if (counter == 0){
            counterText.setText(" No item selected");
        }else {
            counterText.setText(counter + " items selescted");
        }
    }
}
