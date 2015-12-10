package com.example.xx_user.projectslistretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.xx_user.projectslistretrofit.model.Project;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListAdapter extends ArrayAdapter<Project> {

    private final List<Project> projectList;


    public ListAdapter(Context context, int resource, List<Project> objects) {
        super(context, resource, objects);
        this.projectList = objects;
    }

    @Override
    public Project getItem(int position) {
        return projectList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Project project = getItem(position);

        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
            viewHolder = new ViewHolder(getContext(), convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.bindData(project);
        return convertView;
    }

    static final class ViewHolder {

        @Bind(R.id.name) TextView itemTitle;
        @Bind(R.id.img) ImageView itemCoverImage;
        @Bind(R.id.imageProgressBar) ProgressBar imageProgressBar;

        private final Picasso picassoInstance;

        private ViewHolder(Context context, View rootView) {
            picassoInstance = Picasso.with(context);
            ButterKnife.bind(this, rootView);
        }

        public void bindData(Project dataToBind) {
            itemTitle.setText(dataToBind.getProjectName());
            loadCoverImage(dataToBind.getProjectImage().getProjectImageUrl());
        }

        private void loadCoverImage(String coverUrl) {
            picassoInstance
                    .load(coverUrl)
                    .into(itemCoverImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            if (imageProgressBar.getVisibility() == View.VISIBLE) {
                                imageProgressBar.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onError() {
                            imageProgressBar.setVisibility(View.GONE);
                        }
                    });
        }


    }

}
