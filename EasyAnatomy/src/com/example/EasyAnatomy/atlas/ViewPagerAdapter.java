package com.example.EasyAnatomy.atlas;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.EasyAnatomy.R;
import com.example.EasyAnatomy.util.AtlasUtil;

public class ViewPagerAdapter extends PagerAdapter implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final String DEBUG_TAG = "Gestures";
    private Context context;
    private int[] images;
    private LayoutInflater inflater;
    private GestureDetector mDetector;
    private int currentImageId;

    public static int map = R.drawable.human_organs;

    public ViewPagerAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        this.mDetector = new GestureDetector(context, this);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == ((RelativeLayout) o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        map = images[position];
        ImageView imageView;
        TextView left, right;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.albom, container, false);

        left = (TextView) itemView.findViewById(R.id.left);
        right = (TextView) itemView.findViewById(R.id.right);
        if(images[position] == R.drawable.human_organs) {
            left.setVisibility(View.INVISIBLE);
            right.setText(R.string.albom_skeleton_rigth);
        } else if (images[position] == R.drawable.skeleton) {
            left.setText(R.string.albom_organs);
            right.setText(R.string.albom_circ_system);
        } else if(images[position] == R.drawable.circulatory_system) {
            left.setText(R.string.albom_skeleton_left);
            right.setVisibility(View.INVISIBLE);
        }
        imageView = (ImageView) itemView.findViewById(R.id.imgDisplay);
        imageView.setImageResource(images[position]);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                mDetector.onTouchEvent(event);
                return false;
            }
        });

        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
         currentImageId = images[position];
    }

    @Override
    public boolean onDown(MotionEvent event) {

        String text = "X - "+ event.getX() + " Y- " + event.getY();
        //Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        createActivityForCurrentPoint(event.getX(), event.getY());

        Log.d(DEBUG_TAG, "onDown: " + event.toString());
        return true;
    }

    private void createActivityForCurrentPoint(float eventX, float eventY) {
        int[] resources = new int[]{0, 0, 0};

        if(currentImageId == images[0]) {
            resources = AtlasUtil.getCustomResourcesFromOrgansMap(eventX, eventY);
        } else if (currentImageId == images[1]) {
            resources = AtlasUtil.getCustomResourcesFromBonesMap(eventX, eventY);
        } else if (currentImageId == images[2]) {
            resources = AtlasUtil.getCustomResourcesFromCirculatorySystemMap(eventX, eventY);
        }
        int imageResource = resources[0];
        int stringResource = resources[1];
        int titleResource = resources[2];

        if(imageResource != 0 && stringResource != 0 && titleResource != 0) {
            Intent infoActivity = new Intent(context, InfoActivity.class);
            infoActivity.putExtra("imageResource", imageResource);
            infoActivity.putExtra("stringResource", stringResource);
            infoActivity.putExtra("titleResource", titleResource);
            context.startActivity(infoActivity);
        }
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        float beginX = e1.getX();
        float beginY = e1.getY();

        float endX = e2.getX();
        float endY = e2.getY();

        if (Math.abs(beginX - endX) > Math.abs(beginY - endY)) {
            Log.d(DEBUG_TAG + "Scroll", " scrolled");
            if (beginX < endX) {
                Log.d(DEBUG_TAG + "Scroll", " show left");
            } else {
                Log.d(DEBUG_TAG + "Scroll", " show right");
            }
        }
        Log.d(DEBUG_TAG, "onScroll: " + e1.toString() + e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {

        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }
}
