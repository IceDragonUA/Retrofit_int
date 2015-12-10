package com.example.xx_user.projectslistretrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.xx_user.projectslistretrofit.model.Project;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListAdapterHolder> {

    private final Context context;
    private final List<Project> projectList;
    private final ICommand<Project> clickCommand;


    public ListAdapter(Context context, List<Project> projectList, ICommand<Project> clickCommand) {
        this.context = context;
        this.projectList = projectList;
        this.clickCommand = clickCommand;
    }

    @Override
    public ListAdapterHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ListAdapterHolder(context, rootView);
    }

    @Override
    public void onBindViewHolder(ListAdapterHolder projectListAdapterHolder, int position) {
        Project projectItem = getItem(position);
        projectListAdapterHolder.bind(projectItem, clickCommand);
    }

    public Project getItem(int position) {
        return projectList.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != projectList ? projectList.size() : 0);
    }

    static class ListAdapterHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title) TextView titleView;
        @Bind(R.id.thumbnail) ImageView thumbnailView;
        @Bind(R.id.imageProgressBar) ProgressBar imageProgressBar;

        private final Picasso picassoInstance;

        public ListAdapterHolder(Context context, View view) {
            super(view);
            picassoInstance = Picasso.with(context);
            ButterKnife.bind(this, view);
        }

        public void bind(final Project selectedProject, final ICommand<Project> projectClickCommand) {

            titleView.setText(selectedProject.getProjectName());

            picassoInstance
                    .load(selectedProject.getProjectImage().getProjectImageUrl())
                    .into(thumbnailView, new Callback() {
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


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    projectClickCommand.execute(selectedProject);
                }
            });
        }
    }
}
