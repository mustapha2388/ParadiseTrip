package com.abdelouahad.mustapha.myapp;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestFlights {

    private String price;
    private String logo;
    private String start_date;
    private String return_date;
    private String id;


    public RequestFlights(){

    }

    public RequestFlights(String id){
        this.id=id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected void getData(String rootPath, final FirebaseCallback firebaseCallback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(rootPath);
        String value="";

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value;

                for (DataSnapshot destination: dataSnapshot.getChildren()) {
                    for (DataSnapshot compagny_flight: destination.getChildren()) {
                        for (DataSnapshot data : compagny_flight.getChildren()) {
                            if(data.getKey().equals("price")) {
                                value = String.valueOf(data.getValue());
                                setPrice(value);
                            }else if(data.getKey().equals("Logo")){
                                value = String.valueOf(data.getValue());
                                setLogo(value);
                            }
                        }
                    }
                }
                firebaseCallback.onCallback();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
