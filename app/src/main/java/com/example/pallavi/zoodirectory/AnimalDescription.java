package com.example.pallavi.zoodirectory;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class AnimalDescription extends AppCompatActivity {

    private TextView animalName;
    private ImageView animalImg;
    private TextView animalDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_description);

        Intent intent = getIntent();
        Animal selectedAnimal = (Animal) intent.getSerializableExtra("selectedAnimalObj");

        animalName = (TextView) findViewById(R.id.animalName);
        animalName.setText(selectedAnimal.getName());
        animalDesc = (TextView) findViewById(R.id.animalDescription);
        animalDesc.setText(selectedAnimal.getDescription());
        animalImg = (ImageView) findViewById(R.id.animalImage);
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open(selectedAnimal.getImage());
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            animalImg.setImageDrawable(drawable);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zoo_directory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(AnimalDescription.this,ZooInformation.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_uninstall) {

            Uri AppToDelete = Uri.parse("package:com.example.pallavi.zoodirectory");
            Intent RemoveApp = new Intent(Intent.ACTION_DELETE, AppToDelete);
            startActivity(RemoveApp);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
