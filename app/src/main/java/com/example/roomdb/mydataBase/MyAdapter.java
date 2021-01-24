package com.example.roomdb.mydataBase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdb.MainActivity;
import com.example.roomdb.NewNote;
import com.example.roomdb.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolderAdapter>{

    public List<EntityClass> notes ;
    Context context;

    public MyAdapter(Context context,List<EntityClass> mnotes){
        this.context=context;
        this.notes=mnotes;

    }

    public MyAdapter(){

    }

    @NonNull
    @Override
    public ViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderAdapter(LayoutInflater.from(parent.getContext()).inflate(R.layout.card,null,false)) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderAdapter holder, final int position) {

        if (notes != null) {
            EntityClass note = notes.get(position);
            holder.card_name.setText(note.get_NOTE());
            holder.date.setText(note.get_DATE());
        }

        else {

            holder.card_name.setText("No Notes");
            holder.date.setText("...");
        }
      holder.delete.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              EntityClass n=notes.get(holder.getAdapterPosition());

              final int itemid =n.get_ID();
              final AlertDialog dialog = new AlertDialog.Builder(context)
                      .setTitle("Delete")
                      .setMessage(" Are you sure to delete note ")
                      //default colorAccent
                      .setPositiveButton("Yes", null)
                      .setNegativeButton("No", null)
                      .show();
             Button positive_btn = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
              positive_btn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      NoteRoomDb.getInstance(context.getApplicationContext()).noteDao().deleteById(itemid);
                     notes.remove(itemid);

                     new MyAdapter().notifyItemRemoved(position);
                      new MyAdapter().notifyDataSetChanged();
                      dialog.dismiss();
                  }

              });
   holder.edit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        EntityClass n=notes.get(holder.getAdapterPosition());
        final int itemid =n.get_ID();
        String mnote = n.get_NOTE();
        String mdate = n.get_DATE();
       NoteRoomDb.getInstance(context.getApplicationContext()).noteDao().update(new EntityClass(mnote,mdate));
       Intent intent = new Intent(context.getApplicationContext(), NewNote.class);
        intent.putExtra("mynote",mnote);
       context.getApplicationContext().startActivity(intent);

    }
});



          }
      });

    }





    @Override
    public int getItemCount() {
        return  notes.size();
    }


    public class ViewHolderAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView card_name;
        TextView date;
        ImageView delete,edit;

        public ViewHolderAdapter(@NonNull View itemView) {

            super(itemView);
            card_name = itemView.findViewById(R.id.card_name);
            date = itemView.findViewById(R.id.card_date);
            delete=itemView.findViewById(R.id.card_del);
            edit=itemView.findViewById(R.id.card_edt);

        }


        @Override
        public void onClick(View v) {

        }
    }


    }
