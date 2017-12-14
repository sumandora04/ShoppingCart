package com.notepoint4ugmail.shoppingcart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SUMAN SHEKHAR on 13-Dec-17.
 */

public class DisplayDatabaseItems extends BaseAdapter {

    ArrayList<String> item_name;
    ArrayList<Integer>item_price, item_quantity;
    Context context;
    LayoutInflater layoutInflater;
    ProductDatabase productDatabase = new ProductDatabase(context);

    int quantity =1;



    public DisplayDatabaseItems(Context applicationContext, ArrayList<String> item_name,
                        ArrayList<Integer> item_quantity, ArrayList<Integer> item_price) {
        this.context = applicationContext;
        this.item_name = item_name;
        this.item_quantity = item_quantity;
        this.item_price = item_price;
        layoutInflater = LayoutInflater.from(applicationContext);
    }

    @Override
    public int getCount() {
        return item_name.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.item_in_cart_cell, null);

        TextView view_name_text = view.findViewById(R.id.view_name_text);
        final TextView view_quantity_text = view.findViewById(R.id.text_quantity);
        TextView view_price_text = view.findViewById(R.id.view_price_text);

        view_name_text.setText(item_name.get(i));
//        view_quantity_text.setText(item_quantity.get(i));
//        view_price_text.setText(item_price.get(i));

        Button increment,decrement,remove;
        increment =view.findViewById(R.id.btn_increment);

//        Home home = new Home();
//        final String product_id=home.productId.get(i);

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Incremented", Toast.LENGTH_SHORT).show();

                quantity = quantity+1;

                view_quantity_text.setText(quantity);

            }
        });
        decrement =view.findViewById(R.id.btn_decrement);
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Decremented", Toast.LENGTH_SHORT).show();

                quantity = quantity-1;

                view_quantity_text.setText(quantity);

            }
        });

        view.findViewById(R.id.btn_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean updated = productDatabase.db_update("//This is the productId//",quantity);

                if (updated){
                    Toast.makeText(context, "Database Updated",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Unable to update database.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        remove =view.findViewById(R.id.btn_remove_item);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();

                boolean deleted = productDatabase.db_removeItem("//This is the productId//");

                if (deleted){
                    Toast.makeText(context, "Data deleted form database",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,
                            "Unable to delete data from database", Toast.LENGTH_SHORT).show();
                }
            }
        });




        return view;
    }

}
