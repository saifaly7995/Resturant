//package com.example.resturant;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.squareup.picasso.Picasso;
//
//import java.util.List;
//
//import static com.example.resturant.MainActivity.lastid;
//
//public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Holderforstore>  {
//
//    //here we declare attributes for the values we get from database and set in recyclerview
//    private List<ModelHistory> listItems;
//
//
//    private Context context;
//
//
//    public HistoryAdapter(List<ModelHistory> listItems, Context context) {
//        this.listItems=listItems;
//        this.context=context;
//
//
//    }
//
//
//    @NonNull
//    @Override
//    public HistoryAdapter.Holderforstore onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
//        View view=inflater.inflate(R.layout.row_for_cart,parent,false);
//
//
//
//        return new HistoryAdapter.Holderforstore(view);
//    }
//
//
//
//
//
//
//    @Override
//    public void onBindViewHolder(@NonNull final HistoryAdapter.Holderforstore holder, int position) {
//
//
//        final ModelHistory lt= listItems.get(position);
//
//
//        holder.fftotalprice.setText(lt.getId()+"/=");
//        holder.ffname.setText(lt.getId());
//        holder.ffquantity.setText("Qt. "+lt.getDesc());
//        holder.ffprice.setText(lt.getPrice()+"/=");
//        Picasso.get().load(lt.getUrl()).into(holder.imgViewff);
//
//        holder.btndel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, lt.getItemid()+"", Toast.LENGTH_SHORT).show();
//                String url ="https://projectresturent.000webhostapp.com/itemdelcart.php?item_id="+lt.getItemid()+"&customer_id="+lastid;
//                // Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                // Display the first 500 characters of the response string.
//                                Intent intent = new Intent(context,Cart.class);
//                                intent.putExtra("price",response.trim());
//                                context.startActivity(intent);
//
//
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//                // Instantiate the RequestQueue.
//                RequestQueue queue = Volley.newRequestQueue(context);
//                queue.add(stringRequest);
//
//            }
//        });
//
//
//        holder.imgViewff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, lt.getName()+" clicked", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(v.getContext(),Selecedwallpapers.class);
////                intent.putExtra("url",lt.getUrl());
////                v.getContext().startActivity(intent);
//
//
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return listItems.size();
//    }
//
//    public class Holderforstore extends RecyclerView.ViewHolder{
//
//
//
//        public TextView ffname,ffquantity,ffprice,fftotalprice;
//
//
//        public Holderforstore(@NonNull View itemView) {
//            super(itemView);
//
//
//            imgViewff=(ImageView)itemView.findViewById(R.id.img_cart);
//            ffname=itemView.findViewById(R.id.tv_cart_name);
//            ffquantity=itemView.findViewById(R.id.tv_cart_item_quantityprice);
//            fftotalprice=itemView.findViewById(R.id.tv_total_item_price);
//            ffprice=itemView.findViewById(R.id.tv_price);
//
//
//        }
//
//    }
//
//
//
