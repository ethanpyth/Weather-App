package org.esisalama.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button submitButton;
    private EditText latField;
    private EditText lonField;
    private TextView productText;
    private TextView initText;
    private TextView dataseriesTimepoint;
    private TextView dataseriesTransparency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        submitButtonAction();
    }

    private void initComponents(){
        submitButton = findViewById(R.id.submitButton);
        latField = findViewById(R.id.latField);
        lonField = findViewById(R.id.lonField);
        initText = findViewById(R.id.initText);
        productText = findViewById(R.id.productText);
        dataseriesTimepoint = findViewById(R.id.dataseriesTimepoint);
        dataseriesTransparency = findViewById(R.id.dataseriesTransparency);
    }

    private void getRequest(String lon, String lat){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.7timer.info/bin/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService weatherService = retrofit.create(WeatherService.class);
        Call<Meteo> callback = weatherService.getWeather(
                lon,
                lat,
                0,
                "metric",
                "json",
                0
        );

        callback.enqueue(
                new Callback<Meteo>() {
                    @Override
                    public void onResponse(Call<Meteo> call, Response<Meteo> response) {
                        if (response.isSuccessful()){
                            Meteo meteo = response.body();
                            assert meteo != null;
                            productText.setText("Product : " + meteo.getProduct());
                            initText.setText("Init : " + meteo.getInit());
                            for (Dataserie ds: meteo.getDataseries()) {
                                Dataserie dataserie = new Dataserie(ds.getTimepoint(), ds.getTransparency());
                                dataseriesTimepoint.setText("Timepoint : " + Integer.toString(dataserie.getTimepoint()));
                                dataseriesTransparency.setText("Transparency : " + Integer.toString(dataserie.getTransparency()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Meteo> call, Throwable t) {
                        Toast.makeText(
                                MainActivity.this,
                                "Erreur : " + t.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );
    }

    private void submitButtonAction(){
        submitButton.setOnClickListener(
                v -> {
            String lon = lonField.getText().toString();
            String lat = latField.getText().toString();
            if (!lon.isEmpty() && !lat.isEmpty()){
                getRequest(lon, lat);
            }else{
                Toast.makeText(this, "Les deux champs ne doivent pas etre vides", Toast.LENGTH_SHORT).show();
            }
        });
    }
}