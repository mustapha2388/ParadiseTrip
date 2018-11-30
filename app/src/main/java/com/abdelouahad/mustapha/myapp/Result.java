package com.abdelouahad.mustapha.myapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

import static com.abdelouahad.mustapha.myapp.ChooseDateActivity.EXTRA_RETURN_DATE;
import static com.abdelouahad.mustapha.myapp.ChooseDateActivity.EXTRA_START_DATE;
import static com.abdelouahad.mustapha.myapp.MainActivity.EXTRA_COUNTRY;


public class Result extends AppCompatActivity {
    public static final String TAG="Result";

    Date min_compagny, max_compagny;   // assume these are set to something
    Date dateStart, dateReturn;          // the date in question
    String travelId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final ImageView img = findViewById(R.id.logo);

        travelId= getIntent().getStringExtra(EXTRA_COUNTRY);

        Log.d(TAG,"Oncreate: Started");
        final ListView mListView = findViewById(R.id.listView);
        final ArrayList<Compagny> compagniesList = new ArrayList<>();

        // Toast.makeText(Result.this, "BEFORE REQUEST FLIGHTS", Toast.LENGTH_LONG).show();

        final RequestFlights flights = new RequestFlights();
        String[] compagnies = {"AirFrance","EasyJet"};

        for(int indx=0;indx< compagnies.length;indx++) {


            flights.getData("FLIGHTS_AVAILABLE/ID_" + travelId ,compagnies[indx], new FirebaseCallback() {
                @Override
                public void onCallback() {

                    String imageBase64 = flights.getLogo();
                    String startD = getIntent().getStringExtra(EXTRA_START_DATE);
                    String returnD = getIntent().getStringExtra(EXTRA_RETURN_DATE);
                    String price = flights.getPrice();
                    String name_compagny = flights.getName_compagny();
                    Toast.makeText(Result.this, "Name Compagny : " + name_compagny, Toast.LENGTH_SHORT).show();

                    final Compagny compagny = new Compagny(name_compagny, price, "Economique", startD, returnD, img, imageBase64);
                    compagniesList.add(compagny);

                    final CompagnyListAdapter adapter = new CompagnyListAdapter(Result.this, R.layout.adapter_view_layout, compagniesList);


                    mListView.setAdapter(adapter);

                    mListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                Toast.makeText(Result.this, "You are login : ", Toast.LENGTH_SHORT).show();

                          /*  TextView start_date = view.findViewById(R.id.start_date);
                            TextView return_date = view.findViewById(R.id.return_date);
                            TextView price =  view.findViewById(R.id.price);*/
                           /* String tag_start_date = start_date.getText().toString();
                            String tag_return_date = return_date.getText().toString();
                            String tag_price = price.getText().toString();*/
                                String tag_name_compagny = compagniesList.get(position).getName();
                                String tag_logo_base64 = compagniesList.get(position).getImageBase64();
                                String tag_start_date = compagniesList.get(position).getStart_date();
                                String tag_return_date = compagniesList.get(position).getReturn_date();
                                String tag_price = compagniesList.get(position).getPrice();

                                Toast.makeText(Result.this, "You selected : ", Toast.LENGTH_SHORT).show();

                                // Write a message to the database
                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                String rootPathUsers = "/USERS/" + user.getUid() + "/MY_TRAVELS/ID_" + travelId + "/COMPAGNY/" + tag_name_compagny + "/";
                                ArrayList<DatabaseReference> myRef = new ArrayList<>();
                                myRef.add(database.getReference(rootPathUsers));
                                myRef.add(database.getReference(rootPathUsers + "PRICE"));
                                myRef.add(database.getReference(rootPathUsers + "START_DATE"));
                                myRef.add(database.getReference(rootPathUsers + "RETURN_DATE"));
                                myRef.add(database.getReference(rootPathUsers + "Logo_B64"));

                                for (DatabaseReference ref : myRef) {
                                    if (ref.getPath().toString().equals(rootPathUsers + "PRICE")) {
                                        ref.setValue(tag_price);
                                    } else if (ref.getPath().toString().equals(rootPathUsers + "START_DATE")) {
                                        ref.setValue(tag_start_date);
                                    } else if (ref.getPath().toString().equals(rootPathUsers + "RETURN_DATE")) {
                                        ref.setValue(tag_return_date);
                                    } else if (ref.getPath().toString().equals(rootPathUsers + "Logo_B64")) {
                                        ref.setValue(tag_logo_base64);
                                    }
                                }
                            }
                        }
                    });
                }
            });
        }
    }
    void dateAvailable(){
        if(dateStart.after(min_compagny) && dateReturn.before(max_compagny))
            Log.e("dateAvailable", "Between Date");

    }
}
