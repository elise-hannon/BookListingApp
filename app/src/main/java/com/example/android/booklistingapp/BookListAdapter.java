package com.example.android.booklistingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;



public class BookListAdapter extends ArrayAdapter<Book> {

    public BookListAdapter(Activity context, List<Book> bookList) {
        super(context, 0, bookList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Book currentItem = getItem(position);

        TextView title = listItemView.findViewById(R.id.title_view);
        title.setText(currentItem.getTitle());

        TextView author = listItemView.findViewById(R.id.author_view);
        author.setText(currentItem.getAuthors().get(0));

        TextView description = listItemView.findViewById(R.id.description_view);
        description.setText(currentItem.getDescription());

        ImageView imageView = listItemView.findViewById(R.id.image_view);

        if (currentItem.hasImage()) {
            imageView.setVisibility(View.VISIBLE);
            Picasso.with(imageView.getContext()).load(currentItem.getImageUrl()).into(imageView);
        } else {
            imageView.setVisibility(View.VISIBLE);
            Picasso.with(imageView.getContext()).load(R.drawable.icon_picture_unavailable).fit().into(imageView);
        }
        return listItemView;
    }
}