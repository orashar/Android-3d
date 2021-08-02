package com.bennyplo.android_mooc_graphics_3d;

/*import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

    SeekBar rotateBar;
    TextView rotateText;

    SeekBar radiusBar, innerRadiusBar;
    MyView myView;

    SeekBar ptBar;
    TextView textPt;
    final static int MIN_PT = 3;

    RadioButton optLayerTypeNone, optLayerTypeSoftware, optLayerTypeHardware;
    TextView textLayerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radiusBar = (SeekBar) findViewById(R.id.radiusbar);
        innerRadiusBar = (SeekBar)findViewById(R.id.innerradiusbar);
        myView = (MyView) findViewById(R.id.myview);
        float defaultRatio = (float) (radiusBar.getProgress())
                / (float) (radiusBar.getMax());
        myView.setShapeRadiusRatio(defaultRatio);
        float defaultInnerRatio = (float) (innerRadiusBar.getProgress())
                / (float) (innerRadiusBar.getMax());
        myView.setShapeInnerRadiusRatio(defaultInnerRatio);

        radiusBar.setOnSeekBarChangeListener(radiusBarOnSeekBarChangeListener);
        innerRadiusBar.setOnSeekBarChangeListener(innerRadiusBarOnSeekBarChangeListener);

        textPt = (TextView)findViewById(R.id.pttext);
        ptBar = (SeekBar)findViewById(R.id.ptbar);
        ptBar.setOnSeekBarChangeListener(ptBarOnSeekBarChangeListener);

        optLayerTypeNone = (RadioButton)findViewById(R.id.typeNone);
        optLayerTypeSoftware = (RadioButton)findViewById(R.id.typeSoftware);
        optLayerTypeHardware = (RadioButton)findViewById(R.id.typeHardware);
        textLayerInfo = (TextView)findViewById(R.id.typeinfo);

        myView.passElements(textLayerInfo);
        myView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        optLayerTypeNone.setOnCheckedChangeListener(optLayerTypeOnCheckedChangeListener);
        optLayerTypeSoftware.setOnCheckedChangeListener(optLayerTypeOnCheckedChangeListener);
        optLayerTypeHardware.setOnCheckedChangeListener(optLayerTypeOnCheckedChangeListener);

        rotateText = (TextView)findViewById(R.id.rottext);
        rotateBar = (SeekBar)findViewById(R.id.rotatebar);
        rotateBar.setOnSeekBarChangeListener(rotateBarOnSeekBarChangeListener);
        myView.setRotation(0);  //set default rotate degree
    };

    OnCheckedChangeListener optLayerTypeOnCheckedChangeListener =
            new OnCheckedChangeListener(){

                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    if(optLayerTypeNone.isChecked()){
                        myView.setLayerType(View.LAYER_TYPE_NONE, null);
                    }else if(optLayerTypeSoftware.isChecked()){
                        myView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                    }else{
                        myView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                    }

                    myView.invalidate();
                }};

    OnSeekBarChangeListener radiusBarOnSeekBarChangeListener =
            new OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    float ratio = (float) (radiusBar.getProgress())
                            / (float) (radiusBar.getMax());
                    myView.setShapeRadiusRatio(ratio);
                    myView.invalidate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}

            };

    OnSeekBarChangeListener innerRadiusBarOnSeekBarChangeListener =
            new OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    float ratio = (float) (innerRadiusBar.getProgress())
                            / (float) (innerRadiusBar.getMax());
                    myView.setShapeInnerRadiusRatio(ratio);
                    myView.invalidate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}

            };

    OnSeekBarChangeListener ptBarOnSeekBarChangeListener =
            new OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    int pt = progress + MIN_PT;
                    textPt.setText("number of point in polygon: " + String.valueOf(pt));
                    myView.setNumberOfPoint(pt);
                    myView.invalidate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}

            };

    OnSeekBarChangeListener rotateBarOnSeekBarChangeListener =
            new OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    int degree = progress-180;
                    rotateText.setText("rotate : " + degree + " degree");
                    myView.setShapeRotate(degree);
                    myView.invalidate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
            };

}*/


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MyView mMyView=null;//a custom view for drawing
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//hide the title bar
        mMyView=new MyView(this);
        setContentView(mMyView);

    }
}

