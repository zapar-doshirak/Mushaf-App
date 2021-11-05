package com.example.mushaf.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushaf.R;
import com.example.mushaf.models.Sures;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE = 1;
    private List<Object> listRecyclerItem;
    private ItemClickListener clickListener;


    public RecyclerAdapter(List<Object> list, ItemClickListener clickListener) {
        this.listRecyclerItem = list;
        this.clickListener = clickListener;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView number;
        private final TextView name;
        private final TextView place;
        private final TextView ayahsCount;
        private ItemClickListener itemClickListener;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            number = (TextView) itemView.findViewById(R.id.surahsNumberView);
            name = (TextView) itemView.findViewById(R.id.surahsNameView);
            place = (TextView) itemView.findViewById(R.id.surahPlaceView);
            ayahsCount = (TextView) itemView.findViewById(R.id.surahAyahsCountView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.list_item, parent, false
                );

                return new ItemViewHolder(layoutView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        Sures sures = (Sures) listRecyclerItem.get(position);

        switch (viewType){
            case TYPE:
            default:


                itemViewHolder.number.setText(""+sures.getNumber());
                itemViewHolder.name.setText(sures.getName());
                itemViewHolder.place.setText(sures.getPlace());
                itemViewHolder.ayahsCount.setText(""+sures.getAyahsCount());

        }

        //ClickListener для элемента списка сур
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(sures);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }

    //for onItemClickListener
    public interface ItemClickListener{
        void onItemClick(Sures sures);

        }
    }
