package com.example.pallavi.zoodirectory;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.List;

public class ZooDirectory extends AppCompatActivity {

    private boolean proceed=true;
    private boolean waitForDialogValue=false;
    private Animal selectedAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_directory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        final List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Giraffe","giraffe.jpg","The giraffe is the tallest mammal in the world, " +
                "with even new-born babies being taller than most humans."));
        animalList.add(new Animal("Panda","panda.jpg","The giant panda’s black and white coat and prominent black eye " +
                "patches have made it one of the best known species, although it is among the shyest and rarest animals in the world."));
        animalList.add(new Animal("Gorilla","gorilla.jpg","Gorillas are ground-dwelling, predominantly herbivorous " +
                "apes that inhabit the forests of central Africa."));
        animalList.add(new Animal("Tiger","tiger.jpg","The tiger is the largest member of the felid (cat) family. " +
                "They sport long, thick reddish coats with white bellies and white and black tails. Their heads, bodies, " +
                "tails and limbs have narrow black, brown or gray stripes"));
        animalList.add(new Animal("Lion", "lion.jpg","Renowned for its majesty and nicknamed \"the king of the jungle,\" the " +
                "lion possesses both beauty and strength. Lions vary in color but typically sport light yellow-brown coats. " +
                "Mature male lions are unique among big cats due the thick brown or black manes that encircle their " +
                "necks and protect them while fighting."));
        final ListView listView = (ListView) findViewById(R.id.custom_list_view);
        listView.setAdapter(new CustomAdapter(this,R.layout.card_view,animalList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedAnimal = (Animal) listView.getAdapter().getItem(position);
                String animalName = selectedAnimal.getName();
                proceed=true;
                waitForDialogValue=false;
                if(position==listView.getAdapter().getCount()-1)
                {
                    waitForDialogValue=true;
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ZooDirectory.this);
                    alertDialogBuilder.setTitle("Warning!!");
                    alertDialogBuilder.setMessage("Scary Animal.Do you want to proceed? ");
                    alertDialogBuilder.setIcon(R.drawable.warning);
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ZooDirectory.this,AnimalDescription.class);
                            intent.putExtra("selectedAnimalObj", selectedAnimal);
                            startActivity(intent);
                        }
                    });
                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            proceed=false;
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else
                {

                        Intent intent = new Intent(ZooDirectory.this,AnimalDescription.class);
                        intent.putExtra("selectedAnimalObj", selectedAnimal);
                        startActivity(intent);

                }

            }

        });
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

            Intent intent = new Intent(ZooDirectory.this,ZooInformation.class);
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
