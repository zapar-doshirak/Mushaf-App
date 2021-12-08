package com.example.mushaf.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushaf.R;
import com.example.mushaf.data.model.Sures;

import java.util.ArrayList;
import java.util.List;

public class SuresAdapter extends RecyclerView.Adapter<SuresAdapter.SuresHolder>{

    private static final int TYPE = 1;
    private List<Sures> suresList = new ArrayList<>();
    private final RecyclerViewOnClickListener clickListener;

    public SuresAdapter(RecyclerViewOnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static class SuresHolder extends RecyclerView.ViewHolder {

        private final TextView number;
        private final TextView name;
        private final TextView place;
        private final TextView ayahsCount;

        public SuresHolder(@NonNull View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.surahsNumberView);
            name = itemView.findViewById(R.id.surahsNameView);
            place = itemView.findViewById(R.id.surahPlaceView);
            ayahsCount = itemView.findViewById(R.id.surahAyahsCountView);
        }
    }

    @NonNull
    @Override
    public SuresAdapter.SuresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case TYPE:

            default:

                //наполняет RecyclerList разметкой list_item
                View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.sures_list_item, parent, false);

                return new SuresHolder(layoutView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SuresHolder holder, int position) {

        int viewType = getItemViewType(position);
        Sures surah = suresList.get(position);


        switch (viewType){
            case TYPE:
            default:

                holder.number.setText(""+surah.getNumber());
                holder.name.setText(surah.getName());
                holder.place.setText(surah.getPlace());
                holder.ayahsCount.setText(""+surah.getAyahsCount());

        }

        //ClickListener для элемента списка сур
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(surah);
            }
        });

    }

    @Override
    public int getItemCount() {
        return suresList.size();
    }

    //устанавливает список сур
    public void setSures(List<Sures> suresList){
        this.suresList = suresList;
        //todo: notifyDataSetChanged() заменить на более хорошее решение
        notifyDataSetChanged();
    }

    //интерфейс для onItemClickListener
    public interface RecyclerViewOnClickListener {
        void onItemClick(Sures surah);
        }
    }
