package com.example.quangle.hungryhelper;

import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.google.android.gms.maps.GoogleMap;



import java.io.InputStream;
import java.util.ArrayList;
import java.util.Stack;

import static com.example.quangle.hungryhelper.Restaurant.generateRestaurant;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Restaurant> queue = new ArrayList<>();
    public static ArrayList<Restaurant> listOfRestaurants = new ArrayList<>();

    private ArrayList<String> choseName = new ArrayList<>();
    private ArrayList<String> choseAddress = new ArrayList<>();
    private ArrayList<String> chosePhoto = new ArrayList<>();

    public static TextView main;
    public static int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_restaurantPlaque);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);;
        main = findViewById(R.id.main_restaurantDescriptionTxt);
        changeName();

        int size = GetNeabyPlacesData.names.size();
        int j = 0;
        while(j < size)
        {
            listOfRestaurants.add(new Restaurant(GetNeabyPlacesData.names.get(j),GetNeabyPlacesData.address.get(j),GetNeabyPlacesData.photos.get(j)));
            j++;
        }

        ImageButton like =(ImageButton) findViewById(R.id.main_rightBtn);


        like.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    if (i<GetNeabyPlacesData.names.size()){
                        main.setText(GetNeabyPlacesData.names.get(i));

                        new DownloadImageTask((ImageView) findViewById(R.id.main_profileBtn)).execute(photoUrl(GetNeabyPlacesData.photos.get(i)));
                        choseName.add(GetNeabyPlacesData.names.get(i));
                        choseAddress.add(GetNeabyPlacesData.address.get(i));
                        chosePhoto.add(GetNeabyPlacesData.photos.get(i));
                        i++;
                    }
        }});

        ImageButton dislike = findViewById(R.id.main_leftBtn);
        dislike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (i<GetNeabyPlacesData.names.size()){
                    main.setText(GetNeabyPlacesData.names.get(i));

                    new DownloadImageTask((ImageView) findViewById(R.id.main_profileBtn)).execute(photoUrl(GetNeabyPlacesData.photos.get(i)));
                    i++;
                }

            }
            });

        ImageButton profile = findViewById(R.id.main_profileBtn);
        profile.setOnTouchListener(new GestureListener(MainActivity.this){
            @Override
            public void onSwipeRight()
            {
                if (i<GetNeabyPlacesData.names.size()){
                    main.setText(GetNeabyPlacesData.names.get(i));
                    new DownloadImageTask((ImageView) findViewById(R.id.main_profileBtn)).execute(photoUrl(GetNeabyPlacesData.photos.get(i)));

                    i++;
                }
            }
            public void onSwipeLeft()
            {
                if (i<GetNeabyPlacesData.names.size()){
                    main.setText(GetNeabyPlacesData.names.get(i));

                    new DownloadImageTask((ImageView) findViewById(R.id.main_profileBtn)).execute(photoUrl(GetNeabyPlacesData.photos.get(i)));
                    i++;
                }
            }
            @Override
            public void onTap()
            {
                    gotoProfile(findViewById(R.id.main_profileBtn));
            }
        });

        linearLayout.setOnTouchListener(new GestureListener(MainActivity.this)
        {
            @Override
            public void onSwipeRight()
            {
                if (i<GetNeabyPlacesData.names.size()){
                    main.setText(GetNeabyPlacesData.names.get(i));

                    new DownloadImageTask((ImageView) findViewById(R.id.main_profileBtn)).execute(photoUrl(GetNeabyPlacesData.photos.get(i)));
                    i++;
                }
            }
            public void onSwipeLeft()
            {
                if (i<GetNeabyPlacesData.names.size()){
                    main.setText(GetNeabyPlacesData.names.get(i));

                    new DownloadImageTask((ImageView) findViewById(R.id.main_profileBtn)).execute(photoUrl(GetNeabyPlacesData.photos.get(i)));
                    i++;
                }
            }
        });
        Button done = findViewById(R.id.main_donebutton);
        done.setOnClickListener(new View.OnClickListener() {
            int x = (int)(Math.random() * choseName.size());
            @Override
            public void onClick(View view) {

                    main.setText(choseName.get(x));
                    new DownloadImageTask((ImageView) findViewById(R.id.main_profileBtn)).execute(photoUrl(chosePhoto.get(x)));
            }
        });
    }

    public void gotoProfile(View v){
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(this,MainActivity.class));
        }  if (id == R.id.nav_Login) {
            startActivity(new Intent(this,LoginActivity.class));
        } else if (id == R.id.nav_Signup) {
            startActivity(new Intent(this,SignupActivity.class));
        } else if (id == R.id.nav_Favorite) {
            startActivity(new Intent(this,FavoriteActivity.class));
        } else if (id == R.id.nav_map) {
            startActivity(new Intent(this,MapActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
    private GoogleMap mMap;


    public void changeName()
    {
        Object dataTransfer[] = new Object[2];
        GetNeabyPlacesData getNearbyPlacesData = new GetNeabyPlacesData();
        String restaurant = "restaurant";
        String url = getUrl(33.786218, -118.110196, restaurant);
        dataTransfer[0] = mMap;
        dataTransfer[1] = url;

        getNearbyPlacesData.execute(dataTransfer);
    }

    private String getUrl(double latitude , double longitude , String nearbyPlace)
    {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+1000);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&keyword="+"restaurant");
        googlePlaceUrl.append("&key="+"AIzaSyB_2lXrSXS3PwKwk2LfeBz5v061sbVk_38");

        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }


    private String photoUrl(String p)
    {
        StringBuilder photoUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400");
        photoUrl.append("&photoreference="+p);
        photoUrl.append("&key="+"AIzaSyB_2lXrSXS3PwKwk2LfeBz5v061sbVk_38");

        Log.d("MapsActivity", "url = "+photoUrl.toString());
        return photoUrl.toString();
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            bmImage.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }
     public void changeProfile()
    {
     if(!listOfRestaurants.isEmpty())
     {
         TextView temp = findViewById(R.id.main_restaurantDescriptionTxt);
         main.setText(listOfRestaurants.get(0).getName());

         new DownloadImageTask((ImageView) findViewById(R.id.main_profileBtn)).execute(photoUrl(listOfRestaurants.get(0).getImage()));
     }
    }

}
