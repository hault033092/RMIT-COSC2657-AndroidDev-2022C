package com.example.myapplication.Model;

import com.example.myapplication.Entity.Item;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ItemsDatabase {

    public static List<Item> retrieveAllSearchItems() {
        List<Item> itemsList = new ArrayList<>();

        int[] images = {R.drawable.ram, R.drawable.ram, R.drawable.ram, R.drawable.ram, R.drawable.ram, R.drawable.ram,
                R.drawable.razor, R.drawable.razor, R.drawable.razor, R.drawable.razor, R.drawable.razor, R.drawable.razor,
                R.drawable.razor,R.drawable.razor
        };

        String[] names = {"Physical RAM", "Physical RAM", "Physical RAM", "Physical RAM", "Physical RAM", "Physical RAM",
                "Razor Viper", "Razor Basilisk", "Razor Deathadder", "Razor Naga", "Razor Orochi", "Razor Pro Click",
                "MSI GeForce VGA", "MSI GeForce VGA"
        };

        String[] specifications = {"(4GB)", "(8GB)", "(16GB)", "(32GB)", "(64GB)", "(128GB)",
                "(V2)", "(V2)", "(V2)", "(V2)", "(V2)", "(V2)","RTX 4070 Ti SUPRIM x12G","RTX 4070 Ti GAMING x12G"};

        String[] descriptions = {"Lorem ipsum ", "Lorem ipsum", "Lorem ipsum", "Lorem ipsum", "Lorem ipsum", "Lorem ipsum",
                "Lorem ipsum", "Lorem ipsum", "Lorem ipsum", "Lorem ipsum", "Lorem ipsum", "Lorem ipsum",
                "Lorem ipsum","Lorem ipsum"};

        float[] prices = {49.99f, 59.99f, 69.99f, 79.99f, 89.99f, 99.99f,
                109.99f, 119.99f, 129.99f, 139.99f, 149.99f, 159.99f,
                29.00f, 35.00f
        };

        for (int i = 0; i < names.length; i++) {
            Item item = new Item(images[i], names[i], specifications[i], descriptions[i], prices[i]);
            itemsList.add(item);
        }

        return itemsList;
    }

}
