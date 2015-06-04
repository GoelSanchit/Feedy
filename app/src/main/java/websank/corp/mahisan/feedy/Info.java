package websank.corp.mahisan.feedy

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 22-03-2015.
 */
public class Info extends Activity {
    ArrayList<String> MyArrList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feeds);
        /*Intent intent = getIntent();
        String lin = null;
        String num = null;
        String ema = null;
        String add = null;
        if (null != intent) {
            lin = intent.getStringExtra("lin");
            num = intent.getStringExtra("num");
            ema = intent.getStringExtra("ema");
            add = intent.getStringExtra("add");


        }*/

        final ListView list = (ListView) findViewById(R.id.list);
        MyArrList = new ArrayList<String>();
        MyArrList.add("Address");
        MyArrList.add("Phone number");
        MyArrList.add("Email");
        MyArrList.add("Link");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Info.this,android.R.layout.simple_list_item_1,MyArrList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> myAdapter, View myView,

                                    int position, long mylng) {


                switch( position )
                {
                    case 0:  Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34"));
                            startActivity(intent);
                        break;
                    case 1:
                        String posted_by = "+919999999999";

                        String uri = "tel:" + posted_by.trim() ;
                        intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(uri));
                        startActivity(intent);

                        break;
                    case 2:  Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                            finish();
                            Log.i("Finished sending email...", "");
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(Info.this,
                                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:String url = "https://data.gov.in/catalog/cases-registered-under-cyber-crimes-motives";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                        break;

                }

            }

        });

    }
}
