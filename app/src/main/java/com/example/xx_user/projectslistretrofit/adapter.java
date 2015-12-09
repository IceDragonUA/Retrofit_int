package com.example.xx_user.projectslistretrofit;

import android.app.Activity;
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

public class adapter extends ArrayAdapter<Project> {

    private Context context;
    private List<Project> projectList;

    public adapter(Context context, int resource, List<Project> objects) {
        super(context, resource, objects);
        this.context = context;
        this.projectList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item, parent, false);
        Project project = projectList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.name);
        tv.setText(project.getProjectName());
        ImageView img = (ImageView) view.findViewById(R.id.img);
        Picasso.with(getContext()).load(project.getProjectImageUrl()).resize(200,100).into(img);
        return view;
    }
}
