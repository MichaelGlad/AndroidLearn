package net.glm.gsontraning1.Data;

import java.util.List;

/**
 * Created by Michael on 07/01/2018.
 */

public class RestaurantWithMenu {
    String name;
    Address address;
    List<MenuItem> menu;

    public RestaurantWithMenu(String name, Address address, List<MenuItem> menu) {
        this.name = name;
        this.address = address;
        this.menu = menu;
    }
}
