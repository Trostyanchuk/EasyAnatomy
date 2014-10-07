package com.example.EasyAnatomy.atlas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.example.EasyAnatomy.R;

public class AtlasActivity extends Activity {

    //public static boolean landscape = false;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private static boolean alreadyCreated = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        int[] images = new int[]{R.drawable.human_organs, R.drawable.skeleton, R.drawable.circulatory_system};
        //int[] images = new int[]{R.drawable.human_organs_map, R.drawable.skeleton_map, R.drawable.circulatory_system_map};

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new ViewPagerAdapter(AtlasActivity.this, images);
        viewPager.setAdapter(pagerAdapter);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //onLandscapeOrientationNotificate(getResources().getConfiguration());

        View checkBoxView = View.inflate(this, R.layout.checkbox_view, null);
        CheckBox checkBox = (CheckBox) checkBoxView.findViewById(R.id.checkbox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                alreadyCreated = true;
            }
        });

        if(!alreadyCreated) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AtlasActivity.this);
            alertDialogBuilder.setMessage(R.string.atlas_alert_message)
                    .setCancelable(false)
                    .setView(checkBoxView)
                    .setPositiveButton("Oк", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //alreadyCreated = false;
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    /** @Override
    public void onConfigurationChanged(Configuration newConfig) {

        onLandscapeOrientationNotificate(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    private void onLandscapeOrientationNotificate(Configuration configuration) {

        View checkBoxView = View.inflate(this, R.layout.checkbox_view, null);
        CheckBox checkBox = (CheckBox) checkBoxView.findViewById(R.id.checkbox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                landscape = true;
            }
        });

        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE && !landscape && !alreadyCreated) {
            alreadyCreated = true;
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AtlasActivity.this);
            alertDialogBuilder.setMessage(R.string.atlas_alert_message)
                    .setCancelable(false)
                    .setView(checkBoxView)
                    .setPositiveButton("Oк", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alreadyCreated = false;
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }    */
}
