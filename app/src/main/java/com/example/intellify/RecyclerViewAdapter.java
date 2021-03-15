package com.example.intellify;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Chat> ChatList;
    LayoutInflater layoutInflater;

    public RecyclerViewAdapter(Context context, List<Chat> chatList) {
        this.context = context;
        ChatList = chatList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat chat = ChatList.get(position);
        holder.name.setText(chat.getName());
        holder.message.setText(chat.getMessage());
        Picasso.get().load(chat.getImage()).into(holder.image);

        holder.itemView.setOnClickListener(v -> {
//            Toast.makeText(context, chat.getName(), Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.profile, null);

            ImageView image = view.findViewById(R.id.profileImage);
            TextView name = view.findViewById(R.id.profileName);

            Picasso.get().load(chat.getImage()).into(image);
            name.setText(chat.getName());

            builder.setView(view);

            builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return ChatList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView message;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameText);
            message = itemView.findViewById(R.id.messageText);
            image = itemView.findViewById(R.id.imageView);
        }
    }

    public void showAlertDialog(String image, String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

//        View view = getLayo
    }
}
