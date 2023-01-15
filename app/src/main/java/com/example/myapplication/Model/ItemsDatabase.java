package com.example.myapplication.Model;

import com.example.myapplication.Entity.SearchItem;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ItemsDatabase {

    public static List<SearchItem> retrieveAllSearchItems() {
        List<SearchItem> itemsList = new ArrayList<>();

        int[] images = {R.drawable.ram, R.drawable.ram, R.drawable.ram, R.drawable.ram, R.drawable.ram, R.drawable.ram,
                R.drawable.razor, R.drawable.razor, R.drawable.razor, R.drawable.razor, R.drawable.razor, R.drawable.razor
        };

        String[] names = {"4GB Physical RAM", "8GB Physical RAM", "16GB Physical RAM", "32GB Physical RAM", "64GB Physical RAM", "128GB Physical RAM",
                "Razor Viper V2", "Razor Basilisk V2", "Razor Deathadder V2", "Razor Naga V2", "Razor Orochi V2", "Razor Pro Click V2"
        };

        float[] prices = {49.99f, 59.99f, 69.99f, 79.99f, 89.99f, 99.99f,
                109.99f, 119.99f, 129.99f, 139.99f, 149.99f, 159.99f
        };

        for (int i = 0; i < names.length; i++) {
            SearchItem item = new SearchItem(images[i], names[i], prices[i]);
            itemsList.add(item);
        }

        return itemsList;
    }

}
