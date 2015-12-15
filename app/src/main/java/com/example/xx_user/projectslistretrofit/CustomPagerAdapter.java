package com.example.xx_user.projectslistretrofit;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.xx_user.projectslistretrofit.model.Asset;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {

    private final Context context;
    private final List<Asset> assetList;
    private final LayoutInflater layoutInflater;

    public CustomPagerAdapter(Context context, List<Asset> assetList) {
        this.context = context;
        this.assetList = assetList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return assetList.size();
    }

    public Asset getItem(int position) {
        return assetList.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.pager_item, container, false);
        bind(itemView, getItem(position));
        container.addView(itemView);
        return itemView;
    }

    private void bind(View rootView, Asset asset) {
        ImageView itemCoverView = (ImageView) rootView.findViewById(R.id.slide);
        final ProgressBar itemProgressView = (ProgressBar) rootView.findViewById(R.id.pagerProgressBar);
        Picasso.with(context).load(asset.getAssetUrl()).into(itemCoverView, new Callback() {
            @Override
            public void onSuccess() {
                if (itemProgressView.getVisibility() == View.VISIBLE) {
                    itemProgressView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError() {
                itemProgressView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
