package com.example.metroapp.adopter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.metroapp.R;
import com.example.metroapp.StationsActivity;
import com.example.metroapp.models.Lines;

import java.util.List;

public class LinesAdopter extends RecyclerView.Adapter<LinesAdopter.LineVh> {


    List<Lines>linesList;
    Context context;
    public LinesAdopter(Context context,List<Lines>linesList){
        this.linesList=linesList;
        this.context=context;

    }


    @NonNull
    @Override
    public LineVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.lines_row,null);
        return new LineVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LineVh holder, int position) {

        Lines lines=linesList.get(position);

        holder.txt_title.setText(lines.getTitle());
        holder.eng_title.setText(lines.getEnglishTitle());


        switch (lines.getId()){


            case 1:

                holder.relativeLayout.setBackgroundResource(R.color.Red);
                break;

            case 2:

                holder.relativeLayout.setBackgroundResource(R.color.Blue);
                break;

            case 3:

                holder.relativeLayout.setBackgroundResource(R.color.Low_blue);
                break;

            case 4:

                holder.relativeLayout.setBackgroundResource(R.color.yellow);
                break;

            case 5:

                holder.relativeLayout.setBackgroundResource(R.color.orange);
                break;

            case 6:

                holder.relativeLayout.setBackgroundResource(R.color.green);
                break;

            case 7:

                holder.relativeLayout.setBackgroundResource(R.color.purple);
                break;


        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,StationsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",lines.getId());
                intent.putExtra("title",lines.getTitle());
                intent.putExtra("english",lines.getEnglishTitle());
                context.startActivity(intent);

            }
        });

    }







    @Override
    public int getItemCount() {
        return linesList.size();
    }

    class LineVh extends RecyclerView.ViewHolder {
        AppCompatTextView txt_title,eng_title;
        RelativeLayout relativeLayout;
        public LineVh(@NonNull View itemView) {
            super(itemView);

            txt_title=itemView.findViewById(R.id.txt_title);
            eng_title=itemView.findViewById(R.id.eng_title);
            relativeLayout=itemView.findViewById(R.id.relative);

        }
    }

}
