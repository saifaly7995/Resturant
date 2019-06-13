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

public class Adapterpizza extends RecyclerView.Adapter<Adapterpizza.Holderforstore> {

    //here we declare attributes for the values we get from database and set in recyclerview
   private List<ListItem> listItems;
    private List<ListItem> listItemsfull;
    private Context context;



    public Adapterpizza(List<ListItem> listItems, Context context) {
        this.listItems=listItems;
        this.context=context;


    }



    @NonNull
    @Override
    public Adapterpizza.Holderforstore onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.layoutforpizza,parent,false);



        return new Adapterpizza.Holderforstore(view);
    }




    @Override
    public void onBindViewHolder(@NonNull Adapterpizza.Holderforstore holder, int position) {


        final ListItem lt= listItems.get(position);
        //this is click listner for opening new activity in which user can view the details of clicked product
        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onClick(View v, int position) {

            }
        });

        holder.ffname.setText(lt.getName());
        holder.ffdesc.setText(lt.getDesc());
        holder.ffprice.setText(lt.getPrice());
        Picasso.get().load(lt.getUrl()).into(holder.imgViewff);

        holder.imgViewff.setOnClickListener(new View.OnClickListener() {
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




    public class Holderforstore extends RecyclerView.ViewHolder implements  View.OnClickListener {


        public ImageView imgViewff;
        public TextView ffname,ffdesc,ffprice;
        private ItemClickListner itemClickListner;

        public Holderforstore(@NonNull View itemView) {
            super(itemView);

            imgViewff=(ImageView)itemView.findViewById(R.id.img_pizza);
            ffname=itemView.findViewById(R.id.tv_pizza_name);
            ffdesc=itemView.findViewById(R.id.tv_pizza_desc);
            ffprice=itemView.findViewById(R.id.tv_pizza_price);


        }

        public void setItemClickListner(ItemClickListner itemClickListner){
            this.itemClickListner=itemClickListner;
        }


        @Override
        public void onClick(View v) {
            itemClickListner.onClick(v,getAdapterPosition());
        }
    }


}
