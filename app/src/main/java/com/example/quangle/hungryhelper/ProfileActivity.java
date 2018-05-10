package com.example.quangle.hungryhelper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Created by QuangLe on 5/9/2018.
 */

public class ProfileActivity  extends AppCompatActivity {

    public  TextView title;
    public static LinearLayout resImg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        TextView title = findViewById(R.id.profile_restaurantName);
        LinearLayout resImg = findViewById(R.id.profile_restaurantLayout);

        title = findViewById(R.id.profile_restaurantName);
        int index = MainActivity.s;
        if(index == 0) {
            title.setText(GetNeabyPlacesData.names.get(0));
            new ProfileActivity.DownloadImageTask((LinearLayout) findViewById(R.id.profile_restaurantLayout)).execute(photoUrl(GetNeabyPlacesData.photos.get(0)));
        }
        else
        {
            title.setText(GetNeabyPlacesData.names.get(index));
            new ProfileActivity.DownloadImageTask((LinearLayout) findViewById(R.id.profile_restaurantLayout)).execute(photoUrl(GetNeabyPlacesData.photos.get(index)));
        }
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
        LinearLayout bmImage;

        public DownloadImageTask(LinearLayout bmImage) {
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
            BitmapDrawable newImg = new BitmapDrawable(result);
            bmImage.setBackgroundDrawable(newImg);
            //bmImage.setScaleType(LinearLayout.ScaleType.FIT_XY);
        }
    }

    public void writeReview(View v)
    {
        startActivity(new Intent(this, ReviewActivity.class));
    }


}
