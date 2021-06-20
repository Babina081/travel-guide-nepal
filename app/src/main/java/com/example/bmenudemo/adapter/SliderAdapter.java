package com.example.bmenudemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bmenudemo.R;
import com.example.bmenudemo.models.ScreenItem;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    List<ScreenItem> mListScreen;

    public SliderAdapter(Context context) {
        this.context = context;}

    /*public SliderAdapter(Context context, List<ScreenItem> mListScreen) {
        this.context = context;
        this.mListScreen = mListScreen;
    }*/



    int image[] = {
            R.drawable.searchpalce,
            R.drawable.savelike3,
            R.drawable.locate,
            R.drawable.travel2
    };

    int headings[] =
            {
                R.string.heading1,
                R.string.heading2,
                R.string.heading3,
                R.string.heading4

            };

    int description[] = {
           R.string.desc1,
           R.string.desc2,
           R.string.desc3,
           R.string.desc4,
    };


    @Override
    public int getCount() {
        return headings.length;
    }

    /*@Override
    public int getCount() {
        return mListScreen.size();
    }*/

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView heading = view.findViewById(R.id.slider_heading);
        TextView desc = view.findViewById(R.id.slider_desc);

        imageView.setImageResource(image[position]);
        heading.setText(headings[position]);
        desc.setText(description[position]);

        container.addView(view);
        return view;
    }

    /*@NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView heading = view.findViewById(R.id.slider_heading);
        TextView desc = view.findViewById(R.id.slider_desc);

        imageView.setImageResource(mListScreen.get(position).getScreenImg());
        heading.setText(mListScreen.get(position).getTitle());
        desc.setText(mListScreen.get(position).getDescription());

        container.addView(view);
        return view;
    }*/

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
