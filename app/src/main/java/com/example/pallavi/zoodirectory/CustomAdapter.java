package com.example.pallavi.zoodirectory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by pallavi on 2/1/16.
 */
public class CustomAdapter extends ArrayAdapter<Animal> {

    private final List<Animal> animalList;
    static class ViewHolder {
        TextView textView;
        ImageView imageView;

    }

    public CustomAdapter(Context context,int resource,List<Animal> animals) {
        super(context,resource,animals);
        this.animalList= animals;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Animal animal = animalList.get(position);

        View myView;
        ViewHolder holder;

        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            myView = inflater.inflate(R.layout.card_view,null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) myView.findViewById(R.id.rowImage);
            holder.textView = (TextView) myView.findViewById(R.id.rowText);
            myView.setTag(holder);

        }
        else {
            myView = convertView;
            holder = (ViewHolder) myView.getTag();
        }

        holder.textView.setText(animal.getName());
        try{
            InputStream inputStream = getContext().getAssets().open(animal.getImage());
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            holder.imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myView;
    }


    }
