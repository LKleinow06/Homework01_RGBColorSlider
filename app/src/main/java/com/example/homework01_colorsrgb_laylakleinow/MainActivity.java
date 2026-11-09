package com.example.homework01_colorsrgb_laylakleinow;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/*
Name: Layla Kleinow
Date: 11/8/26
Desc: RGB Slider/ Color Picker App
*/

public class MainActivity extends AppCompatActivity
{
    //GUI variables
    Button btn_j_save;

    TextView tv_j_red;
    TextView tv_j_green;
    TextView tv_j_blue;
    TextView tv_j_hex;

    SeekBar sb_j_red;
    SeekBar sb_j_green;
    SeekBar sb_j_blue;

    ListView lv_j_savedColors;

    //arraylist to store saved colors
    ArrayList<ColorInfo> listOfColors;
    //adapter for the listview
    ColorInfoAdapter colorAdapter;

    int red = 0;
    int green = 0;
    int blue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        connectGUI();

        //create arraylist
        listOfColors = new ArrayList<>();

        fillListView();

        //set up event functions
        redChangeEvent();
        greenChangeEvent();
        blueChangeEvent();
        saveButtonClickEvent();
        setOnClickListenerForListView();

        //display initial color
        updateColor();
    }

    private void connectGUI()
    {
        tv_j_red = findViewById(R.id.tv_j_red);
        tv_j_green = findViewById(R.id.tv_j_green);
        tv_j_blue = findViewById(R.id.tv_j_blue);
        tv_j_hex = findViewById(R.id.tv_j_hex);

        sb_j_red = findViewById(R.id.sb_j_red);
        sb_j_green = findViewById(R.id.sb_j_green);
        sb_j_blue = findViewById(R.id.sb_j_blue);

        btn_j_save = findViewById(R.id.btn_j_save);

        lv_j_savedColors = findViewById(R.id.lv_j_savedColors);
    }

    private void redChangeEvent()
    {
        sb_j_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                red = i;

                updateColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    private void greenChangeEvent()
    {
        sb_j_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                green = i;

                updateColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    private void blueChangeEvent()
    {
        sb_j_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                blue = i;

                updateColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    private void updateColor()
    {
        //update RGB labels
        tv_j_red.setText("Red: " + red);
        tv_j_green.setText("Green: " + green);
        tv_j_blue.setText("Blue: " + blue);

        //create the RGB color
        int currentColor = Color.rgb(red, green, blue);

        //convert RGB color into hexadecimal
        String hexColor = String.format("#%02X%02X%02X", red, green, blue);

        //update hexadecimal textview
        tv_j_hex.setText(hexColor);

        //change app background
        View mainLayout = findViewById(R.id.main);
        mainLayout.setBackgroundColor(currentColor);

        updateTextColor(currentColor);
    }

    private void updateTextColor(int currentColor)
    {
        //get RGB brightness values
        int redValue = Color.red(currentColor);
        int greenValue = Color.green(currentColor);
        int blueValue = Color.blue(currentColor);

        //calculate brightness
        //numbers come from standard-definition video perceived light values
        double brightness = (0.299 * redValue) + (0.587 * greenValue) + (0.114 * blueValue);

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

        tv_j_red.setTextColor(textColor);
        tv_j_green.setTextColor(textColor);
        tv_j_blue.setTextColor(textColor);
        tv_j_hex.setTextColor(textColor);

        //update save button text
        btn_j_save.setTextColor(textColor);
    }

    private void saveButtonClickEvent()
    {
        btn_j_save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //get current RGB values
                int savedRed = sb_j_red.getProgress();
                int savedGreen = sb_j_green.getProgress();
                int savedBlue = sb_j_blue.getProgress();

                //create a hexadecimal representation
                String savedHex = String.format("#%02X%02X%02X", savedRed, savedGreen, savedBlue);

                //create a colorinfo object
                ColorInfo color = new ColorInfo(savedRed, savedGreen, savedBlue, savedHex);

                //add color to arraylist
                listOfColors.add(color);

                colorAdapter.notifyDataSetChanged();

                clearSeekBars();
            }
        });
    }

    private void clearSeekBars()
    {
        sb_j_red.setProgress(0);
        sb_j_green.setProgress(0);
        sb_j_blue.setProgress(0);

        red = 0;
        green = 0;
        blue = 0;

        updateColor();
    }

    private void fillListView()
    {
        colorAdapter = new ColorInfoAdapter(this, listOfColors);

        lv_j_savedColors.setAdapter(colorAdapter);
    }

    private void setOnClickListenerForListView()
    {
        lv_j_savedColors.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        //get selected color from arraylist
                        ColorInfo selectedColor = listOfColors.get(i);

                        //restore RGB values
                        red = selectedColor.getRed();
                        green = selectedColor.getGreen();
                        blue = selectedColor.getBlue();

                        //update seekbars
                        sb_j_red.setProgress(red);
                        sb_j_green.setProgress(green);
                        sb_j_blue.setProgress(blue);

                        updateColor();
                    }
                }
        );
    }
}