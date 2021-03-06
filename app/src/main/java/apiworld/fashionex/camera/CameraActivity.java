package apiworld.fashionex.camera;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import apiworld.fashionex.R;
import apiworld.fashionex.favorite.FavoriteActivity;
import apiworld.fashionex.smartcart.SmartCartActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CameraActivity extends AppCompatActivity {

    /* Declare activity variables here */
    private static final String TAG = "CameraActivity";
    private Context context;

    private Camera camera;
    private CameraPreview preview;

    private RelativeLayout frame;
    private RelativeLayout overlay;
    private TextView coordinates;

    private ImageButton plus_button;
    private ImageButton favorite_button;
    private ImageButton smartcart_button;

    /* Declare activity variables above */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = this.getApplicationContext();
        setContentView(R.layout.activity_camera);

        //instantiate the view variables here
        this.coordinates = (TextView) this.findViewById(R.id.coordinates);
        this.overlay = (RelativeLayout) this.findViewById(R.id.overlay);
        this.smartcart_button = (ImageButton) this.findViewById(R.id.smartcart_icon);
        this.favorite_button = (ImageButton) this.findViewById(R.id.favorite_icon);
        this.plus_button = (ImageButton) this.findViewById(R.id.plus_icon);
        this.smartcart_button.setOnClickListener(click_listener);
        this.favorite_button.setOnClickListener(click_listener);
        this.plus_button.setOnClickListener(click_listener);

        //begin camera logic
        boolean hasCamera = checkCameraHardware(this.context);
        Log.d(TAG, "Phone has camera: " + hasCamera);

        //if no camera, throw error message (prevents crashing)
        if(!hasCamera) {
            Toast.makeText(context, "We need your camera to get you all ready!", Toast.LENGTH_LONG).show();
            return;
        }

        Log.d(TAG, "No of cameras" +  Camera.getNumberOfCameras());
        // Create an instance of Camera
        this.camera = this.getCameraInstance();
        Log.d(TAG, "Camera Instance obtained: " + ((this.camera != null) ? true : false));

        // Create our Preview view and set it as the content of our activity.
        this.preview = new CameraPreview(this, this.camera);
        this.frame = (RelativeLayout) findViewById(R.id.camera_preview);
        this.frame.addView(this.preview);

        this.overlay.setOnTouchListener(new SwipeTouchListener(this) {

            public void onSwipeTop() {
                Toast.makeText(context, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(context, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(context, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(context, "bottom", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.camera == null) {
            this.camera = getCameraInstance();
            this.preview = new CameraPreview(this, this.camera);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        Log.d(TAG, "x: " + event.getX());
        Log.d(TAG, "y: " + event.getY());

        this.coordinates.setText("x: " + event.getX() + " y: " + event.getY());

        return true;
    }

    private View.OnClickListener click_listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Intent intent = null;

            switch(v.getId()) {
                case R.id.favorite_icon:
                    intent = new Intent(context, FavoriteActivity.class);
                    break;
                case R.id.smartcart_icon:
                    intent = new Intent(context, SmartCartActivity.class);
                    break;
                case R.id.plus_icon:
                    Toast.makeText(context, "Items added to cart!", Toast.LENGTH_LONG).show();
                    break;
            }

            if(intent != null) { startActivity(intent); }
        }
    };

    /* Add activity functions here */
    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(0); // attempt to get a Camera instance
            c.setDisplayOrientation(90);
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /* Add activity functions above */
}
