package com.example.quangle.hungryhelper;

import android.util.Log;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by QuangLe on 5/9/2018.
 */

public class Restaurant {
    public String name;
    public String address;
    public String distance;
    public int starRating;
    public String image;
    private static ArrayList<Restaurant> listOfRestaurants = new ArrayList<>();
    public Restaurant(String name, String address,String image){
        this.name = name;
        this.address = address;
        this.image = image;
    }

    public static void generateRestaurant()
    {
        int size = GetNeabyPlacesData.names.size();
        int i = 0;
        while(i < size)
        {
            listOfRestaurants.add(new Restaurant(GetNeabyPlacesData.names.get(i),GetNeabyPlacesData.address.get(i),GetNeabyPlacesData.photos.get(i)));
        }
    }

    public static Restaurant getRestaurant()
    {
        return listOfRestaurants.get(0);
    }

    public static ArrayList<Restaurant> getlistRestaurants()
    {
        return listOfRestaurants;
    }

    public String getName()
    {
        return this.name;
    }
    public String getAddress()
    {
        return this.address;
    }
    public String getImage()
    {
        return this.image;
    }

}
