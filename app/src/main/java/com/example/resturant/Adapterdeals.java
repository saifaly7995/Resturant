package com.example.resturant;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapterdeals  extends RecyclerView.Adapter<Adapterdeals.Holderforstore>   {

    //here we declare attributes for the values we get from database and set in recyclerview
    private List<ListItem> listItems;
    private List<ListItem> listItemsfull;
    private Context context;


    public Adapterdeals(List<ListItem> listItems, Context context) {
        this.listItems=listItems;
        listItemsfull= new ArrayList<>(listItems);
        this.context=context;


    }


    @NonNull
    @Override
    public Adapterdeals.Holderforstore onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.layoutfordeals,parent,false);



        return new Adapterdeals.Holderforstore(view);
    }





    @Override
    public void onBindViewHolder(@NonNull Adapterdeals.Holderforstore holder, int position) {


        final ListItem lt= listItems.get(position);


        holder.dealname.setText(lt.getName());
        holder.dealdesc.setText(lt.getDesc());
        holder.dealprice.setText(lt.getPrice());
        Picasso.get().load(lt.getUrl()).into(holder.imgViewdeal);

        holder.imgViewdeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, lt.getName()+" clicked", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(v.getContext(),Selecedwallpapers.class);
//                intent.putExtra("url",lt.getUrl());
//                v.getContext().startActivity(intent);

                Intent intent = new Intent(v.getContext(),ViewProduct.class);
                intent.putExtra("id",lt.getId());
                intent.putExtra("title",lt.getName());
                intent.putExtra("image",lt.getUrl());
                intent.putExtra("desc",lt.getDesc());
                intent.putExtra("price",lt.getPrice());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class Holderforstore extends RecyclerView.ViewHolder{


        public ImageView imgViewdeal;
        public TextView dealname,dealdesc,dealprice;


        public Holderforstore(@NonNull View itemView) {
            super(itemView);

            imgViewdeal=(ImageView)itemView.findViewById(R.id.img_deal);
            dealname=itemView.findViewById(R.id.tv_deal_name);
            dealdesc=itemView.findViewById(R.id.tv_deal_desc);
            dealprice=itemView.findViewById(R.id.tv_deal_price);


        }

    }


}



