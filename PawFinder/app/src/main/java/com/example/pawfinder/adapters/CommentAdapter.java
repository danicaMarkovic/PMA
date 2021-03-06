
package com.example.pawfinder.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pawfinder.R;
import com.example.pawfinder.model.Comment;
import com.example.pawfinder.model.Pet;
import com.example.pawfinder.tools.MockupComments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;


public class CommentAdapter extends BaseAdapter {
    private Activity activity;
    private List<Comment> comments;

    public CommentAdapter(Activity activity, List<Comment> comments) {
        this.activity = activity;
        this.comments = comments;
    }


    @Override
    public int getCount() {

        return this.comments.size();
    }


    @Override
    public Object getItem(int position) {
        return this.comments.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        Comment comment = this.comments.get(position);

        if (convertView == null)
            vi = activity.getLayoutInflater().inflate(R.layout.comment_layout, null);

        TextView name = (TextView) vi.findViewById(R.id.user_name);
        TextView commentMessage = (TextView) vi.findViewById(R.id.user_comment);
        TextView date = (TextView) vi.findViewById(R.id.date_comment);

        name.setText(comment.getUser().getEmail());
        commentMessage.setText(comment.getMessage());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(comment.getDate());
        date.setText(strDate);


        return vi;

    }

    public void updateResults(List<Comment> updatedComments) {
        comments = updatedComments;
        notifyDataSetChanged();
    }
}
