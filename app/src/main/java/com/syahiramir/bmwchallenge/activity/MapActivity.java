package com.syahiramir.bmwchallenge.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.syahiramir.bmwchallenge.R;
import com.syahiramir.bmwchallenge.model.Location;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Syahir on 7/4/17.
 * map activity to show location data from intent with mapView
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
public class MapActivity extends AppCompatActivity {

    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nameTxt)
    TextView nameTxt;
    @BindView(R.id.addressTxt)
    TextView addressTxt;
    @BindView(R.id.longitudeTxt)
    TextView longitudeTxt;
    @BindView(R.id.latitudeTxt)
    TextView latitideTxt;
    @BindView(R.id.arrivalTimeTxt)
    TextView arrivalTimeTxt;
    private GoogleMap mGoogleMap;
    private Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        location = getIntent().getParcelableExtra("location");
        getSupportActionBar().setTitle(location.getName());
        nameTxt.setText(location.getName());
        addressTxt.setText(location.getAddress());
        longitudeTxt.setText(String.format("%s%s", getString(R.string.longitude), location.getLongitude()));
        latitideTxt.setText(String.format("%s%s", getString(R.string.latitude), location.getLatitude()));

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);
            Date startDate;
            Date endDate;
            startDate = Calendar.getInstance().getTime();
            endDate = format.parse(location.getArrivalTime());
            StringBuilder arriveTimeSb = new StringBuilder();
            DateTime startDateTime = new DateTime(startDate);
            DateTime endDateTime = new DateTime(endDate);
            int hours = Hours.hoursBetween(startDateTime, endDateTime).getHours() % 24;
            int minutes = Minutes.minutesBetween(startDateTime, endDateTime).getMinutes() % 60;
            if (hours != 0) arriveTimeSb.append(hours).append(getString(R.string.hour));
            if (minutes != 0) arriveTimeSb.append(minutes).append(getString(R.string.minutes));
            arrivalTimeTxt.setText(arriveTimeSb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng locationLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                mGoogleMap = googleMap;
                mGoogleMap.addMarker(new MarkerOptions().position(locationLatLng).title(location.getName()));
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(locationLatLng));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
