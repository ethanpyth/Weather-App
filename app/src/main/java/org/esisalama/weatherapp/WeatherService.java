package org.esisalama.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("astro.php")

    Call<Meteo> getWeather(
            @Query("lon") String lon,
            @Query("lat") String lat,
            @Query("ac") int ac,
            @Query("unit") String unit,
            @Query("output") String json,
            @Query("tzshift") int tzshift
    );
}
