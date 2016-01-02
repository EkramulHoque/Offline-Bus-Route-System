package com.example.ekram.imagemap4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static com.example.ekram.imagemap4.R.layout.map_layout;


public class MapActivity extends Activity  {
    private String[] values;
    private String[] routes;
    private String[] stops;


    private int mSourceId;
    private int mDstWidth;
    private int mDstHeight;
    private ListView mDrawerList;
    private SimpleDBAdapter mDbHelper;
    Canvas canvas;

    int x=320,y=75;
    Button btn;
    private DrawerLayout drawerLayout;
    private boolean firstResume = false;
    public EditText from,to;
    private String FROM,TOM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(map_layout);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        values = getResources().getStringArray(R.array.bus1_ShishuMela);
        routes = getResources().getStringArray(R.array.bus1_ShishuMela);
        stops = getResources().getStringArray(R.array.bus1_ShishuMela);

        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                 android.R.layout.simple_list_item_1, values));
        from=(EditText)findViewById(R.id.editText3);
        to=(EditText)findViewById(R.id.editText2);

        mDbHelper = new SimpleDBAdapter(MapActivity.this);
        mDbHelper.createDatabase();
        mDbHelper.open();



        Bundle xtras= getIntent().getExtras();
        if(xtras!= null) {
           //x =xtras.getFloat("X");
            //y =xtras.getFloat("Y");
            Toast.makeText(getApplicationContext(), x+""+y, Toast.LENGTH_LONG).show();

        }

        btn=(Button)findViewById(R.id.button2);
        mSourceId = R.drawable.map;
        mDstWidth = getResources().getDimensionPixelSize(R.dimen.destination_width);
        mDstHeight = getResources().getDimensionPixelSize(R.dimen.destination_height);



        Bitmap unscaledBitmap = decodeResource(getResources(), mSourceId,
                mDstWidth, mDstHeight, ScalingLogic.CROP);

        Bitmap scaledBitmap = createScaledBitmap(unscaledBitmap, mDstWidth,
                mDstHeight, ScalingLogic.CROP);
        unscaledBitmap.recycle();



        TouchImageView img = new TouchImageView(this);
        img.setImageBitmap(scaledBitmap);
        img.setMaxZoom(4f);



        RelativeLayout rl = (RelativeLayout)findViewById(R.id.relative);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rl.addView(img, params);






    }
    public void Drawer(View v) {
        Bitmap unscaledBitmap = decodeResource(getResources(), mSourceId,
                mDstWidth, mDstHeight, ScalingLogic.CROP);

        Bitmap scaledBitmap = createScaledBitmap(unscaledBitmap, mDstWidth,
                mDstHeight, ScalingLogic.CROP);
        unscaledBitmap.recycle();



        TouchImageView img = new TouchImageView(this);
        img.setImageBitmap(scaledBitmap);
        img.setMaxZoom(4f);

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.relative);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rl.addView(img, params);

        FROM = from.getText().toString();
        TOM = to.getText().toString();

        if(FROM.trim().length() >0 || TOM.trim().length()>0 ) {


            values = mDbHelper.betweenStops(FROM, TOM);
            routes = mDbHelper.OnlyRoute(FROM, TOM);
            if(values.length>0 && routes.length>0) {
                int r = Integer.parseInt(routes[0]);
                //Toast.makeText(getApplicationContext(), r, Toast.LENGTH_LONG).show();
                stops = mDbHelper.OnlyStop(FROM, TOM, r);
                values[values.length - 1] = "STOP NAMES:";

              values=combine(values,stops);

                mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, values));


                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


                }
                from.setText("");
                to.setText("");
                Resources res = getResources();
                Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.marker);


                for (int j = 0; j < stops.length; j++) {
                    if (stops[j].equals("Mirpur-12"))
                        canvas.drawBitmap(bitmap, 320f, 75f, new Paint());//Mirpur-12
                    if (stops[j].equals("Pallabi"))
                        canvas.drawBitmap(bitmap, 320, 145, new Paint());//Pallabi
                    if (stops[j].equals("Mirpur-10"))
                        canvas.drawBitmap(bitmap, 320, 200, new Paint());//Mirpur-10
                    if (stops[j].equals("Mirpur-2"))
                        canvas.drawBitmap(bitmap, 280, 200, new Paint());//Mirpur-2
                    if (stops[j].equals("Gabtoli"))
                        canvas.drawBitmap(bitmap, 95, 310, new Paint());//Gabtoli
                    if (stops[j].equals("Kalyanpur"))
                        canvas.drawBitmap(bitmap, 245, 375, new Paint());//kayanpur
                    if (stops[j].equals("Shyamoli"))
                        canvas.drawBitmap(bitmap, 260, 400, new Paint());//Shyamoli
                    if (stops[j].equals("Agargaon"))
                        canvas.drawBitmap(bitmap, 360, 420, new Paint());//Agar Gaon
                    if (stops[j].equals("College Gate"))
                        canvas.drawBitmap(bitmap, 260, 460, new Paint());//College Gate
                    if (stops[j].equals("Shishu Mela"))
                        canvas.drawBitmap(bitmap, 280, 430, new Paint());//Shishu mela
                    if (stops[j].equals("Shia Mosque"))
                        canvas.drawBitmap(bitmap, 210, 460, new Paint());//Shia Mosque
                    if (stops[j].equals("Asad Gate"))
                        canvas.drawBitmap(bitmap, 260, 530, new Paint());//Asad gate
                    if (stops[j].equals("Farmgate"))
                        canvas.drawBitmap(bitmap, 390, 530, new Paint());//Farm gate
                    if (stops[j].equals("Manik Mia"))
                        canvas.drawBitmap(bitmap, 320, 530, new Paint());//Manik Mia
                    if (stops[j].equals("BRTC Stop-2"))
                        canvas.drawBitmap(bitmap, 240, 530, new Paint());//BRTC 2
                    if (stops[j].equals("BRTC Stop-1"))
                        canvas.drawBitmap(bitmap, 200, 530, new Paint());//BRTC 1
                    if (stops[j].equals("Shankar"))
                        canvas.drawBitmap(bitmap, 210, 550, new Paint());//Shankar
                    if (stops[j].equals("Star Kabab"))
                        canvas.drawBitmap(bitmap, 220, 595, new Paint());//Star
                    if (stops[j].equals("Kalabagan"))
                        canvas.drawBitmap(bitmap, 290, 595, new Paint());//kalabagan
                    if (stops[j].equals("Jigatala"))
                        canvas.drawBitmap(bitmap, 220, 700, new Paint());//Jigatala
                    if (stops[j].equals("City College"))
                   //     canvas.drawBitmap(bitmap, 290, 630, new Paint());//city college
                    if (stops[j].equals("Dhanmondi-15"))
                     //   canvas.drawBitmap(bitmap, 220, 690, new Paint());//Dhanmondi-15
                    if (stops[j].equals("Mirpur-1"))
                        canvas.drawBitmap(bitmap, 220, 225, new Paint());//Mirpur-1
                    if (stops[j].equals("Ansar Camp"))
                        canvas.drawBitmap(bitmap, 220, 285, new Paint());//Ansar Camp
                    if (stops[j].equals("Technical"))
                        canvas.drawBitmap(bitmap, 200, 330, new Paint());//Technical
                    if (stops[j].equals("Shabagh"))
                    canvas.drawBitmap(bitmap, 410, 700, new Paint());//Shabagh
                    if (stops[j].equals("Banglamotor"))
                        canvas.drawBitmap(bitmap, 390, 620, new Paint());//Banglamotor


                }
            }
        }
        drawerLayout.openDrawer(Gravity.LEFT);
        mDrawerList.bringToFront();
    }
    public static String[] combine(String[] arg1, String[] arg2) {
        String[] result = new String[arg1.length + arg2.length];
        System.arraycopy(arg1, 0, result, 0, arg1.length);
        System.arraycopy(arg2, 0, result, arg1.length, arg2.length);
        return result;
    }
    protected void onResume() {
        super.onResume();

        if(firstResume) {
            drawerLayout.openDrawer(Gravity.LEFT);
        }

        firstResume = false;
    }

    public  Bitmap createScaledBitmap(Bitmap unscaledBitmap, int dstWidth, int dstHeight,
                                      ScalingLogic scalingLogic) {
        Rect srcRect = calculateSrcRect(unscaledBitmap.getWidth(), unscaledBitmap.getHeight(),
                dstWidth, dstHeight, scalingLogic);
        Rect dstRect = calculateDstRect(unscaledBitmap.getWidth(), unscaledBitmap.getHeight(),
                dstWidth, dstHeight, scalingLogic);

        Bitmap scaledBitmap = Bitmap.createBitmap(dstRect.width(), dstRect.height(),
                Bitmap.Config.ARGB_8888);

        canvas = new Canvas(scaledBitmap);
        canvas.drawBitmap(unscaledBitmap, srcRect, dstRect, new Paint(Paint.FILTER_BITMAP_FLAG));

        //Resources res = getResources();
        //Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.marker);
       // canvas.drawBitmap(bitmap, 390, 620, new Paint());//Shabagh


        return scaledBitmap;
    }





    public static enum ScalingLogic {
        CROP, FIT
    }



    public static int calculateSampleSize(int srcWidth, int srcHeight, int dstWidth, int dstHeight,
                                          ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.FIT) {
            final float srcAspect = (float)srcWidth / (float)srcHeight;
            final float dstAspect = (float)dstWidth / (float)dstHeight;

            if (srcAspect > dstAspect) {
                return srcWidth / dstWidth;
            } else {
                return srcHeight / dstHeight;
            }
        } else {
            final float srcAspect = (float)srcWidth / (float)srcHeight;
            final float dstAspect = (float)dstWidth / (float)dstHeight;

            if (srcAspect > dstAspect) {
                return srcHeight / dstHeight;
            } else {
                return srcWidth / dstWidth;
            }
        }
    }



    public static Rect calculateSrcRect(int srcWidth, int srcHeight, int dstWidth, int dstHeight,
                                        ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.CROP) {
            final float srcAspect = (float)srcWidth / (float)srcHeight;
            final float dstAspect = (float)dstWidth / (float)dstHeight;

            if (srcAspect > dstAspect) {
                final int srcRectWidth = (int)(srcHeight * dstAspect);
                final int srcRectLeft = (srcWidth - srcRectWidth) / 2;
                return new Rect(srcRectLeft, 0, srcRectLeft + srcRectWidth, srcHeight);
            } else {
                final int srcRectHeight = (int)(srcWidth / dstAspect);
                final int scrRectTop = (int)(srcHeight - srcRectHeight) / 2;
                return new Rect(0, scrRectTop, srcWidth, scrRectTop + srcRectHeight);
            }
        } else {
            return new Rect(0, 0, srcWidth, srcHeight);
        }
    }
    public static Bitmap decodeResource(Resources res, int resId, int dstWidth, int dstHeight,
                                        ScalingLogic scalingLogic) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth, options.outHeight, dstWidth,
                dstHeight, scalingLogic);
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(res, resId, options);

        return unscaledBitmap;
    }

    public static Rect calculateDstRect(int srcWidth, int srcHeight, int dstWidth, int dstHeight,
                                        ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.FIT) {
            final float srcAspect = (float)srcWidth / (float)srcHeight;
            final float dstAspect = (float)dstWidth / (float)dstHeight;

            if (srcAspect > dstAspect) {
                return new Rect(0, 0, dstWidth, (int)(dstWidth / srcAspect));
            } else {
                return new Rect(0, 0, (int)(dstHeight * srcAspect), dstHeight);
            }
        } else {
            return new Rect(0, 0, dstWidth, dstHeight);
        }
    }


}
