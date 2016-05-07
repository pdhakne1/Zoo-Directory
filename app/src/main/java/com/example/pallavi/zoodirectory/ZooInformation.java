package com.example.pallavi.zoodirectory;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import android.graphics.Paint;

public class ZooInformation extends AppCompatActivity {

    private TextView contact;
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS=5;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_information);
        contact = (TextView) findViewById(R.id.zooContact);
        contact.setPaintFlags(contact.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(ZooInformation.this,"Contact No:"+contact.getText().toString(),Toast.LENGTH_LONG).show();
                phoneNumber = "tel:"+contact.getText().toString();

                if (ActivityCompat.checkSelfPermission(ZooInformation.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions(ZooInformation.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                    //return;
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_CALL,
                            Uri.parse(phoneNumber));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //if (ActivityCompat.checkSelfPermission(ZooInformation.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                //}
            }
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

            Intent intent = new Intent(ZooInformation.this,ZooInformation.class);
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
