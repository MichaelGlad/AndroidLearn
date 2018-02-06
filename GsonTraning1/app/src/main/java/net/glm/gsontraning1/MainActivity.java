package net.glm.gsontraning1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import net.glm.gsontraning1.Data.Address;
import net.glm.gsontraning1.Data.MenuItem;
import net.glm.gsontraning1.Data.RestaurantWithMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final String MY_LOGS = "MY_LOGS";
    Address restaurantAddress;
    RestaurantWithMenu mRestaurant,mSecondRestaurant;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantAddress = new Address("First","42A","Modiin");
        List<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem("Steak",80.00f));
        menu.add(new MenuItem("Salad",30.50f));
        menu.add(new MenuItem("Chicken wings",40.00f));

        mRestaurant = new RestaurantWithMenu("Meat Rest",restaurantAddress,menu);

        Gson mGson = new Gson();
        String myGsonString = mGson.toJson(mRestaurant);
        Log.d(MY_LOGS,"The JSON String is - " + myGsonString);
        String myGsonString2 = "{\"address\":{\"HouseNumber\":\"25\",\"city\":\"Modiin\",\"street\":\"Second\"}," +
                "\"menu\":[{\"description\":\"Spaghetti\",\"price\":55.0},{\"description\":\"Salad\",\"price\":30.5},{\"description\":\"Juce\",\"price\":15.0}]," +
                "\"name\":\"Spaghetti Rest\"}";

        String myGsonString3 = "{'address':{'HouseNumber':'25','city':'Modiin','street':'Second'}," +
                "'menu':[{'description':'Spaghetti','price':55.0},{'description':'Salad','price':30.5},{'description':'Juce','price':15.0}]," +
                "\"name\":\"Spaghetti Rest\"}";

        mSecondRestaurant = mGson.fromJson(myGsonString2,RestaurantWithMenu.class);
        Gson mGson2 = new Gson();
        mRestaurant = mGson2.fromJson(myGsonString3,RestaurantWithMenu.class);
        mGson = mGson2;









    }
}
