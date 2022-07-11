package com.elvis.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

    public class MainActivity extends AppCompatActivity {

        //Declaring the widgets
        Button button;
        ImageView imageView;
        static final int CAM_REQUEST = 1; //request code


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            button = findViewById(R.id.button);
            imageView = findViewById(R.id.image_view);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //intent object for starting the camera app
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //Creating  file by calling the getfile method that returns a file object
                    File file = getFile();

                    //pass the file location of the image into the intent object
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(intent, CAM_REQUEST);
                }
            });
        }

        //creating a folder on the external storage where the image will be stored
        //This Method returns a file object
        private File getFile()
        {
            //Creating a folder inside the external storage
            //The name of the folder is "MYCAMERA"
            File camerafolder = new File("sdcard/mycamera");

            //check if the folder exists or not
            //if the folder is does not exist, the code below creates, else it is skipped
            if (!camerafolder.exists()) {
                camerafolder.mkdir();
            }

            //saving the image file in the folder of the external storage
            File camera_file = new File(camerafolder, "the_image.jpg");

            // a camera file is returned
            return camera_file;
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            //getting the image from the directory and putting it into the imageview
            //The below path points to the image in the directory
            String path = "sdcard/mycamera/the_image.jpg";

            //The image from the directory is mapped on the imageview
            imageView.setImageDrawable(Drawable.createFromPath(path));
        }
    }