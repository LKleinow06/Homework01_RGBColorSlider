package com.example.homework01_colorsrgb_laylakleinow;

/*
Name: Layla Kleinow
Date: 11/8/26
Desc: RGB Slider/ Color Picker App
*/

public class ColorInfo
{
    //variables to store color information
    int red;
    int green;
    int blue;
    String hex;

    //default constructor
    public ColorInfo()
    {

    }

    //overloaded constructor
    public ColorInfo(int r, int g, int b, String h)
    {
        red = r;
        green = g;
        blue = b;
        hex = h;
    }

    //get the red value
    public int getRed()
    {
        return red;
    }

    //set the red value
    public void setRed(int red)
    {
        this.red = red;
    }

    //get the green value
    public int getGreen()
    {
        return green;
    }

    //set the green value
    public void setGreen(int green)
    {
        this.green = green;
    }

    //get the blue value
    public int getBlue()
    {
        return blue;
    }

    //set the blue value
    public void setBlue(int blue)
    {
        this.blue = blue;
    }

    //get the hexadecimal value
    public String getHex()
    {
        return hex;
    }

    //set the hexadecimal value
    public void setHex(String hex)
    {
        this.hex = hex;
    }
}