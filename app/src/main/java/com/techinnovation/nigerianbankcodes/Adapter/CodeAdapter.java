package com.techinnovation.nigerianbankcodes.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techinnovation.nigerianbankcodes.Model.CodeModel;
import com.techinnovation.nigerianbankcodes.R;

import java.util.ArrayList;

public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.viewHolder> {
    ArrayList<CodeModel> mylist; // Change variable name to mylist
    Context context;

    // Fix the constructor to initialize the mylist field
    public CodeAdapter(Context context, ArrayList<CodeModel> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carddesign_sims, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CodeModel codeModel = mylist.get(position);
        holder.textView.setText(codeModel.getTitle());
        holder.textView1.setText(codeModel.getCode());
        holder.imageView.setImageResource(codeModel.getImage());

        // Set onClick listeners
        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Copy code to clipboard
                copyToClipboard(codeModel.getCode());
                Toast.makeText(context, "Code copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open dial pad with the code
                dialCode(codeModel.getCode());
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Share the code
                shareCode(codeModel.getCode());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    // Helper method to copy code to clipboard
    private void copyToClipboard(String code) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Code", code);
        clipboard.setPrimaryClip(clip);
    }

    // Helper method to open dial pad with the code
    // Helper method to open dial pad with the code
    private void dialCode(String code) {
        // You can implement the logic to open the dial pad with the code here
        // For example, you can use Intent.ACTION_DIAL
        // Note: Ensure you have the necessary permissions in your manifest file

        // Encode the code to handle special characters like #
        String encodedCode = Uri.encode(code);

        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:" + encodedCode));
        context.startActivity(dialIntent);
    }


    // Helper method to share the code
    private void shareCode(String code) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, code);
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, null));
    }
    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView1;
        ImageView imageView, copy, share, call;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.titl);
            textView1 = itemView.findViewById(R.id.code);
            imageView = itemView.findViewById(R.id.cardimage);
            call = itemView.findViewById(R.id.call);
            copy = itemView.findViewById(R.id.copy);
            share = itemView.findViewById(R.id.share);
        }
    }
}
