RGB Background Color Slider:

Program Description:

This program is an RGB color picker app
created with android studio and java. This program
allows users to create custom colors by adjusting
3 different seekbars representing red, green, and
blue. Each seekbar has a valid range of 0-255. The
user can save as many colors as they want by 
pressing the save color button. The user can also
select a previously saved color from the listview,
which will change the rgb sliders, background, and
hexadecimal value to match the selection.

Notes:

The ColorInfo class stores the RGB values and
hexadecimal representation of each saved color,
A custom ColorInfoAdapter is used to display
the saved colors in the ListView, Saved colors
remain available while the app is running, but
they are not permanently stored.

Things I struggled with:

I had many issues with Android Studio itself
because the IDE would frequently stop responding,
which led to a lot of issues and delays. Most of
these issues were fixed by adjusting some settings
and restarting the IDE a few times.