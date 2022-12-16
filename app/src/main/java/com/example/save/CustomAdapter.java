package com.example.save;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList note_id, note_titre, note;
    private Activity activity;
    Animation translate;
    CustomAdapter(Activity activity, Context context, ArrayList note_id, ArrayList note_titre, ArrayList note){
        this.activity =activity;
        this.context = context;
        this.note_id = note_id;
        this.note_titre = note_titre;
        this.note = note;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter.MyViewHolder holder, final int position) {
        holder.note_id_txt.setText(String.valueOf(note_id.get(position)));
        holder.note_titre_txt.setText(String.valueOf(note_titre.get(position)));
        holder.note_txt.setText(String.valueOf(note.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(note_id.get(position)));
                intent.putExtra("titre", String.valueOf(note_titre.get(position)));
                intent.putExtra("note", String.valueOf(note.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return note_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView note_id_txt, note_titre_txt, note_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            note_id_txt = itemView.findViewById(R.id.note_id_txt);
            note_titre_txt = itemView.findViewById(R.id.note_titre_txt);
            note_txt = itemView.findViewById(R.id.note_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            translate = AnimationUtils.loadAnimation(context, R.anim.translate);
            mainLayout.setAnimation(translate);
        }
    }
}
