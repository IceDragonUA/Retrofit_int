package com.example.xx_user.projectslistretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xx_user.projectslistretrofit.model.Project;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListAdapter extends ArrayAdapter<Project> {

    private List<Project> projectList;

    @Bind(R.id.name) TextView tv;
    @Bind(R.id.img) ImageView img;

    public ListAdapter(Context context, int resource, List<Project> objects) {
        super(context, resource, objects);
        this.projectList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Project project = projectList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        ButterKnife.bind(this, convertView);

        tv.setText(project.getProjectName());
        Picasso.with(getContext()).load(project.getProjectImage().getProjectImageUrl()).resize(200,100).into(img);

        return convertView;
    }
}
