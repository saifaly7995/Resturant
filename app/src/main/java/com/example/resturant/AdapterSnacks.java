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

public class AdapterSnacks  extends RecyclerView.Adapter<AdapterSnacks.Holderforstore>  {

    //here we declare attributes for the values we get from database and set in recyclerview
    private List<ListItem> listItems;
    private List<ListItem> listItemsfull;
    private Context context;


    public AdapterSnacks(List<ListItem> listItems, Context context) {
        this.listItems=listItems;
        listItemsfull= new ArrayList<>(listItems);
        this.context=context;


    }


    @NonNull
    @Override
    public AdapterSnacks.Holderforstore onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.layoutforsnacks,parent,false);



        return new AdapterSnacks.Holderforstore(view);
    }




    @Override
    public void onBindViewHolder(@NonNull AdapterSnacks.Holderforstore holder, int position) {


        final ListItem lt= listItems.get(position);


        holder.snackname.setText(lt.getName());
        holder.snackdesc.setText(lt.getDesc());
        holder.snackprice.setText(lt.getPrice());
        Picasso.get().load(lt.getUrl()).into(holder.imgViewsnack);

        holder.imgViewsnack.setOnClickListener(new View.OnClickListener() {
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


        public ImageView imgViewsnack;
        public TextView snackname,snackdesc,snackprice;


        public Holderforstore(@NonNull View itemView) {
            super(itemView);

            imgViewsnack =(ImageView)itemView.findViewById(R.id.img_snack);
            snackname=itemView.findViewById(R.id.tv_snack_name);
            snackdesc=itemView.findViewById(R.id.tv_snack_desc);
            snackprice=itemView.findViewById(R.id.tv_snack_price);


        }

    }


}



