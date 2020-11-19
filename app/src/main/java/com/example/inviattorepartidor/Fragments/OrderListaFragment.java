package com.example.inviattorepartidor.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.inviattorepartidor.Common.Common;
import com.example.inviattorepartidor.Model.SolicitudModel;
import com.example.inviattorepartidor.R;
import com.example.inviattorepartidor.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;


public class OrderListaFragment extends Fragment {

    //Inicializacion de variables
    public RecyclerView rvOrden;
    public RecyclerView.LayoutManager layoutManager;

    //Agregar layout de menu
    private MaterialEditText edtCategoryName;
    private FButton btnSelectCategory;
    private FButton btnUploadCategory;

    private FrameLayout flOrderLista;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    FirebaseRecyclerAdapter<SolicitudModel, OrderViewHolder> adapter;
    //private IAPIService mService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_lista, container, false);

        flOrderLista = view.findViewById(R.id.flOrderLista);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("OrdersNeedShip");

        rvOrden = view.findViewById(R.id.rvOrden);
        rvOrden.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rvOrden.setLayoutManager(layoutManager);

        //Inicializar servicio
        //mService = Common.getFCMClient();
        loadAllOrderNeedShip(Common.currentRepartidorModel.getPhone());

        return view;
    }//onCreateView

    private void loadAllOrderNeedShip(String phone) {

        DatabaseReference orderInChildOfShipper = databaseReference.child(phone);

        FirebaseRecyclerOptions<SolicitudModel> listOrders = new FirebaseRecyclerOptions.Builder<SolicitudModel>()
                .setQuery(orderInChildOfShipper, SolicitudModel.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<SolicitudModel, OrderViewHolder>(listOrders) {
            @Override
            protected void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int position, @NonNull SolicitudModel solicitudModel) {
                orderViewHolder.lblOrderItemName.setText(adapter.getRef(position).getKey());
                orderViewHolder.lblOrderItemStatus.setText(Common.convertCodeToStatus(solicitudModel.getStatus()));
                orderViewHolder.lblOrderItemAddress.setText(solicitudModel.getAddress());
                orderViewHolder.lblOrderItemPhone.setText(solicitudModel.getPhone());
                orderViewHolder.lblOrderItemDate.setText(solicitudModel.getDate());

                orderViewHolder.btnOrderIniciar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "No disponible", Toast.LENGTH_LONG).show();
                    }//onClick
                });

            }//onBindViewHolder

            @NonNull
            @Override
            public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemview = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.order_item, parent, false);
                return new OrderViewHolder(itemview);
            }//onCreateViewHolder
        };
        adapter.startListening();
        adapter.notifyDataSetChanged();
        rvOrden.setAdapter(adapter);
    }//loadAllOrderNeedShip

    @Override
    public void onResume() {
        super.onResume();
        loadAllOrderNeedShip(Common.currentRepartidorModel.getPhone());
    }//onResume

    @Override
    public void onStop() {
        if (adapter != null)
            adapter.stopListening();
        super.onStop();
    }//onStop

    private void deleteCategory(String key, SolicitudModel item) {
        //databaseReference.child(key).removeValue();
        //Toast.makeText(getActivity(), "¡Elemento eliminado!", Toast.LENGTH_LONG).show();
        Toast.makeText(getActivity(), "¡No se puede eliminar la orden!", Toast.LENGTH_LONG).show();
    }//deleteCategory

    /*
    private void showUpdateDialog(final String key, final SolicitudModel item) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Actualizar Orden");
        //alertDialog.setMessage("Selecciona estado");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.update_order_layout, null);
        statusSpinner = (MaterialSpinner) view.findViewById(R.id.statusSpinner);
        shippedSpinner = (MaterialSpinner) view.findViewById(R.id.shippedSpinner);
        statusSpinner.setItems("Recibida", "En Camino", "Enviada");

        //Cargar todos los vendedores
        final List<String> shippedList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Shipped")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot shipperSnapShot : dataSnapshot.getChildren())
                            shippedList.add(shipperSnapShot.getKey());
                        shippedSpinner.setItems(shippedList);
                    }//onDataChange

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }//onCancelled
                });

        alertDialog.setView(view);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                item.setStatus(String.valueOf(statusSpinner.getSelectedIndex()));

                if (item.getStatus().equals("2")) {
                    //Copiar Item a la Tabla OrderNeedShip
                    FirebaseDatabase.getInstance().getReference("OrdersNeedShip")
                            .child(shippedSpinner.getItems().get(shippedSpinner.getSelectedIndex()).toString())
                            .child(key)
                            .setValue(item);

                    databaseReference.child(key).setValue(item);
                    sendOrderStatusToUser(key, item);
                    sendOrderShipRequestToShipper(shippedSpinner.getItems().get(shippedSpinner.getSelectedIndex()).toString(),item);
                    adapter.notifyDataSetChanged();
                } else {
                    databaseReference.child(key).setValue(item);
                    sendOrderStatusToUser(key, item);
                    adapter.notifyDataSetChanged();
                }//else
            }//onClick
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }//onClick
        });
        alertDialog.show();
    }//showUpdateDialog
 */

/*
    private void sendOrderShipRequestToShipper(String shipperPhone, SolicitudModel item) {
        DatabaseReference tokens = firebaseDatabase.getReference("Tokens");
        tokens.child(shipperPhone)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {

                            Token token = postSnapShot.getValue(Token.class);
                            Notification notification = new Notification("Inviatto Restaurant", "Tienes una nueva orden");
                            Sender content = new Sender(token.getToken(), notification);

                            mService.sendNotification(content)
                                    .enqueue(new Callback<MyResponse>() {
                                        @Override
                                        public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                            if (response.body().success == 1) {
                                                Toast.makeText(getActivity(), "Enviada para repartidor", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getActivity(), "Ocurrio un error al enviar la orden", Toast.LENGTH_SHORT).show();
                                            }//else
                                        }//onResponse

                                        @Override
                                        public void onFailure(Call<MyResponse> call, Throwable t) {
                                            Log.e("ERROR", t.getMessage());
                                        }//onFailure
                                    });
                        }//for
                    }//onDataChange

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }//onCancelled
                });
    }
 */

/*
    private void sendOrderStatusToUser(final String key, SolicitudModel item) {
        DatabaseReference tokens = firebaseDatabase.getReference("Tokens");
        tokens.orderByKey().equalTo(item.getPhone())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {

                            Token token = postSnapShot.getValue(Token.class);
                            Notification notification = new Notification("Inviatto Restaurant", "Su Orden " + key + " fue actualizada");
                            Sender content = new Sender(token.getToken(), notification);

                            mService.sendNotification(content)
                                    .enqueue(new Callback<MyResponse>() {
                                        @Override
                                        public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                            if (response.body().success == 1) {
                                                Toast.makeText(getActivity(), "La orden fue actializada", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getActivity(), "Ocurrio un error al actualizar la orden", Toast.LENGTH_SHORT).show();
                                            }//else
                                        }//onResponse

                                        @Override
                                        public void onFailure(Call<MyResponse> call, Throwable t) {
                                            Log.e("ERROR", t.getMessage());
                                        }//onFailure
                                    });
                        }//for
                    }//onDataChange

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }//onCancelled
                });
    }//sendOrderStatusToUser
 */

}//OrderListaFragment