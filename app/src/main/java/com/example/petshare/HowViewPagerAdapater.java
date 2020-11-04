package com.example.petshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class HowViewPagerAdapater extends PagerAdapter {

    Context ctx;

    public HowViewPagerAdapater(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_how_to_adopt, container,false);

        ImageView circle1 = view.findViewById(R.id.circle1);
        ImageView circle2 = view.findViewById(R.id.circle2);
        ImageView circle3 = view.findViewById(R.id.circle3);
        ImageView circle4 = view.findViewById(R.id.circle4);

        TextView title = view.findViewById(R.id.txtTitle);
        TextView description = view.findViewById(R.id.how_txtDescription1);

        Button btnNext = view.findViewById(R.id.how_btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                howToAdopt.viewPager.setCurrentItem(position+1);
            }
        });

        switch (position){
            case 0:
                circle1.setImageResource(R.drawable.selected);
                circle2.setImageResource(R.drawable.unselected);
                circle3.setImageResource(R.drawable.unselected);
                circle4.setImageResource(R.drawable.unselected);


                description.setText("Step 1:\n Choose a pet.");
                break;
            case 1:
                circle1.setImageResource(R.drawable.unselected);
                circle2.setImageResource(R.drawable.selected);
                circle3.setImageResource(R.drawable.unselected);
                circle4.setImageResource(R.drawable.unselected);


                description.setText("Step 2:\n Book an appointment \n" +
                        "for meeting the pet.");
                break;
            case 2:
                circle1.setImageResource(R.drawable.unselected);
                circle2.setImageResource(R.drawable.unselected);
                circle3.setImageResource(R.drawable.selected);
                circle4.setImageResource(R.drawable.unselected);


                description.setText("Step 3:\n Background check.");
                break;
            case 3:
                circle1.setImageResource(R.drawable.unselected);
                circle2.setImageResource(R.drawable.unselected);
                circle3.setImageResource(R.drawable.unselected);
                circle4.setImageResource(R.drawable.selected);


                description.setText("Step 4:\n Take care of it.");
                btnNext.setText("Get Started");
                break;

        }

        container.addView(view);
        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
