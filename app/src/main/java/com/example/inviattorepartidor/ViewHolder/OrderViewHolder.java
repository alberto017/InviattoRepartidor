package com.example.inviattorepartidor.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.inviattorepartidor.Interface.IItemClickListener;
import com.example.inviattorepartidor.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import info.hoang8f.widget.FButton;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    public TextView lblOrderItemName;
    public TextView lblOrderItemStatus;
    public TextView lblOrderItemAddress;
    public TextView lblOrderItemPhone;
    public TextView lblOrderItemHour;
    public TextView lblOrderItemDate;
    public FButton btnOrderIniciar;
    private IItemClickListener iItemClickListener;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        lblOrderItemName = itemView.findViewById(R.id.lblOrderItemName);
        lblOrderItemStatus = itemView.findViewById(R.id.lblOrderItemStatus);
        lblOrderItemAddress = itemView.findViewById(R.id.lblOrderItemAddress);
        lblOrderItemPhone = itemView.findViewById(R.id.lblOrderItemPhone);
        lblOrderItemDate = itemView.findViewById(R.id.lblOrderItemDate);
        btnOrderIniciar = itemView.findViewById(R.id.btnOrderIniciar);

    }//OrderViewHolder
}
