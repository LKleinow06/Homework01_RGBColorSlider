package com.example.homework01_colorsrgb_laylakleinow;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/*
Name: Layla Kleinow
Date: 11/8/26
Desc: RGB Slider/ Color Picker App
*/

public class ColorInfoAdapter extends BaseAdapter
{
    Context context;
    ArrayList<ColorInfo> listOfColors;

    //constructor
    public ColorInfoAdapter(Context c, ArrayList<ColorInfo> ls)
    {
        context = c;
        listOfColors = ls;
    }

    //returns the number of colors in the arraylist
    @Override
    public int getCount()
    {
        return listOfColors.size();
    }

    //returns the colorinfo object at the given position
    @Override
    public Object getItem(int i)
    {
        return listOfColors.get(i);
    }

    //returns the position of the selected item
    @Override
    public long getItemId(int i)
    {
        return i;
    }

    //creates the visual appearance of each listview row
    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            //convert the xml layout into a java view
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);

            //create a new row using color_info_cell.xml
            view = mInflater.inflate(R.layout.color_info_cell, null);
        }

        //connect GUI elements from the custom cell
        TextView tv_hex = view.findViewById(R.id.tv_j_cell_hex);
        TextView tv_rgb = view.findViewById(R.id.tv_j_cell_rgb);

        //get the colorinfo object for this position
        ColorInfo color = listOfColors.get(i);

        //display hexadecimal and RGB values
        tv_hex.setText(color.getHex());

        tv_rgb.setText("Red: " + color.getRed() + "  Green: " + color.getGreen() + "  Blue: " + color.getBlue());

        //change the cell background to match the saved color
        int backgroundColor = Color.rgb(color.getRed(), color.getGreen(), color.getBlue());

        view.setBackgroundColor(backgroundColor);

        //calculate brightness
        //numbers come from standard-definition video perceived light values
        double brightness = (0.299 * color.getRed()) + (0.587 * color.getGreen()) + (0.114 * color.getBlue());

        int textColor;

        //if brightness is below 128 then use white text, otherwise use black
        //128 is about midway between 0 and 255
        if (brightness < 128)
        {
            textColor = Color.WHITE;
        }
        else
        {
            textColor = Color.BLACK;
        }

        tv_hex.setTextColor(textColor);
        tv_rgb.setTextColor(textColor);

        //return the completed row
        return view;
    }
}