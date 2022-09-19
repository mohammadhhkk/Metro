package com.example.metroapp.adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.metroapp.R;
import com.example.metroapp.models.Stations;

import java.util.List;

public class StationsAdopter extends RecyclerView.Adapter<StationsAdopter.StationsVh> {

    Context context;
    List<Stations> stationsList;
    public StationsAdopter(Context context,List<Stations>stationsList){
        this.context=context;
        this.stationsList=stationsList;

    }

    @NonNull
    @Override
    public StationsVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.stations_row,null);
        return new StationsVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationsVh holder, int position) {

        Stations stations=stationsList.get(position);

        holder.txt_titleStation.setText(stations.getTitle());
        holder.txt_englishTitle_Station.setText(stations.getEnglishTitle());

        switch (Integer.parseInt(stations.getLineId())){


            case 1:

                holder.rele_stations.setBackgroundResource(R.color.Red);
                break;

            case 2:

                holder.rele_stations.setBackgroundResource(R.color.Blue);
                break;

            case 3:

                holder.rele_stations.setBackgroundResource(R.color.Low_blue);
                break;

            case 4:

                holder.rele_stations.setBackgroundResource(R.color.yellow);
                break;

            case 5:

                holder.rele_stations.setBackgroundResource(R.color.orange);
                break;

            case 6:

                holder.rele_stations.setBackgroundResource(R.color.green);
                break;

            case 7:

                holder.rele_stations.setBackgroundResource(R.color.purple);
                break;


        }


    }

    @Override
    public int getItemCount() {
        return stationsList.size();
    }

    class StationsVh extends RecyclerView.ViewHolder {

        AppCompatTextView txt_titleStation,txt_englishTitle_Station;
        RelativeLayout rele_stations;

        public StationsVh(@NonNull View itemView) {
            super(itemView);

        txt_titleStation=itemView.findViewById(R.id.txt_titleStation);
        txt_englishTitle_Station=itemView.findViewById(R.id.txt_englishTitle_Station);
        rele_stations=itemView.findViewById(R.id.rele_stations);

        }
    }


}
