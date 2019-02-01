package com.markpaveszka.pavlosdrinkinggame;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    private BarChart barChart;
    private BarChart barChart2;
    private Button saveAsIMGBtn;
    private Button shareOnFacebookBtn;
    private Button skipBtn;
    private int time;
    private final int WRITE_EXTERAL_STORAGE_CODE=1;
    private boolean isTwo;
    private ShareDialog shareDialog;
    private CallbackManager callbackManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int[] scores = this.getIntent().getIntArrayExtra("scores");
        ArrayList<String> names = this.getIntent().getStringArrayListExtra("names");
        shareDialog = new ShareDialog(this);
        callbackManager = CallbackManager.Factory.create();
        if(scores.length<9)
        {
            setContentView(R.layout.activity_chart);
            isTwo=false;

            shareOnFacebookBtn= (Button) findViewById(R.id.shareOnFBBtn);
            saveAsIMGBtn = (Button) findViewById(R.id.saveAsImgBtn);
            skipBtn =(Button) findViewById(R.id.skipBtn);
            barChart =(BarChart) findViewById(R.id.barChart);
            barChart.getDescription().setEnabled(false);

            setChartValues(scores, names, barChart);
            barChart.setFitBars(true);

            skipBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToMainScreen = new Intent(ChartActivity.this, MainActivity.class);
                    startActivity(goToMainScreen);
                    finish();
                }
            });

            saveAsIMGBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    time= (int)System.currentTimeMillis();
                    checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,WRITE_EXTERAL_STORAGE_CODE );
                    Intent goToMainScreen = new Intent(ChartActivity.this, MainActivity.class);
                    startActivity(goToMainScreen);
                    finish();
                }
            });

            shareOnFacebookBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                        @Override
                        public void onSuccess(Sharer.Result result) {
                            Toast.makeText(ChartActivity.this, "Share successful", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancel() {
                            Toast.makeText(ChartActivity.this, "Share cancelled", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(FacebookException e) {
                            Toast.makeText(ChartActivity.this, "Share unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Bitmap bitmap = barChart.getChartBitmap();
                    SharePhoto sharePhoto = new SharePhoto.Builder()
                            .setBitmap(bitmap)
                            .build();

                    if(ShareDialog.canShow(SharePhotoContent.class))
                    {
                        SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder()
                                .addPhoto(sharePhoto)
                                .build();
                        shareDialog.show(sharePhotoContent);
                    }
                }
            });
        }
        else
        {
            isTwo=true;

            setContentView(R.layout.multip_chart_layout);
            shareOnFacebookBtn= (Button) findViewById(R.id.shareOnFBMultipBtn);
            saveAsIMGBtn = (Button) findViewById(R.id.saveAsImgMultipBtn);
            skipBtn =(Button) findViewById(R.id.skipMultipBtn2);

            skipBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToMainScreen = new Intent(ChartActivity.this, MainActivity.class);
                    startActivity(goToMainScreen);
                    finish();
                }
            });
            int[] firstHalf;
            ArrayList<String> firstHalfNames = new ArrayList<>();

            if(scores.length%2==1)
            {
                firstHalf= new int[scores.length/2+1];
            }
            else
            {
                firstHalf= new int[scores.length/2];
            }
            int[] secondHalf = new int[scores.length/2];
            ArrayList<String> secondHalfNames = new ArrayList<>();
            for (int i =0; i<firstHalf.length; i++)
            {
                firstHalf[i]= scores[i];
                firstHalfNames.add(names.get(i));
            }
            int secondHalfIndex=0;
            for (int i =firstHalf.length; i< scores.length;i++)
            {
                secondHalf[secondHalfIndex]=scores[i];
                secondHalfNames.add(names.get(i));
                secondHalfIndex++;
            }

            barChart =(BarChart) findViewById(R.id.barChart1);
            barChart2 = (BarChart) findViewById(R.id.barChart2);
            barChart.getDescription().setEnabled(false);
            barChart2.getDescription().setEnabled(false);

            setChartValues(firstHalf, firstHalfNames, barChart);
            barChart.setFitBars(true);

            setChartValues(secondHalf, secondHalfNames, barChart2);
            barChart.setFitBars(true);
            saveAsIMGBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    time= (int)System.currentTimeMillis();
                    checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,WRITE_EXTERAL_STORAGE_CODE );
                }
            });

            shareOnFacebookBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                        @Override
                        public void onSuccess(Sharer.Result result) {
                            Toast.makeText(ChartActivity.this, "Share successful", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancel() {
                            Toast.makeText(ChartActivity.this, "Share cancelled", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(FacebookException e) {
                            Toast.makeText(ChartActivity.this, "Share unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Bitmap bitmap = barChart.getChartBitmap();
                    Bitmap bitmap2 = barChart2.getChartBitmap();
                    SharePhoto sharePhoto = new SharePhoto.Builder()
                            .setBitmap(bitmap)
                            .build();
                    SharePhoto sharePhoto1 = new SharePhoto.Builder()
                            .setBitmap(bitmap2)
                            .build();

                    List<SharePhoto> photos = new ArrayList<>();
                    photos.add(sharePhoto);
                    photos.add(sharePhoto1);
                    if(ShareDialog.canShow(SharePhotoContent.class))
                    {
                        SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder()
                                .addPhotos(photos)
                                .build();


                        shareDialog.show(sharePhotoContent);
                    }

                    Intent goToMainScreen = new Intent(ChartActivity.this, MainActivity.class);
                    startActivity(goToMainScreen);
                    finish();
                }
            });

        }



    }

    private void setChartValues(int[] barGraphValues, final ArrayList<String> playerNames,
                                BarChart chart)
    {
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i=0;i<barGraphValues.length; i++)
        {

            entries.add(new BarEntry(i,barGraphValues[i]));
        }

        BarDataSet set = new BarDataSet(entries, "Players");
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        set.setDrawValues(true);


        BarData data = new BarData(set);



        XAxis xAxis = chart.getXAxis();
        YAxis left = chart.getAxisLeft();
        YAxis right = chart.getAxisRight();
        left.setDrawGridLines(false);
        right.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(playerNames));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.setScaleEnabled(true);
        chart.setDoubleTapToZoomEnabled(false);
        xAxis.setTextSize(20f);
        chart.setDrawBorders(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(270);
        chart.setData(data);
        chart.invalidate();
        chart.animateY(500);

    }
    private void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

            //File write logic here
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
        else
        {
            if (isTwo)
            {
                barChart.saveToGallery("pavlo"+time, 720);
                barChart2.saveToGallery("pavlo2"+time,720);
            }
            else
            {
                barChart.saveToGallery("pavlo"+time, 720);
            }

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERAL_STORAGE_CODE:

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (isTwo)
                    {
                        barChart.saveToGallery("pavlo"+time, 720);
                        barChart2.saveToGallery("pavlo2"+time,720);
                    }
                    else
                    {
                        barChart.saveToGallery("pavlo"+time, 720);
                    }

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(ChartActivity.this, "Permission denied to write your External storage", Toast.LENGTH_SHORT).show();
                }
                return;


            // other 'case' lines to check for other
            // permissions this app might request
        }//SWITCH
    }
}
