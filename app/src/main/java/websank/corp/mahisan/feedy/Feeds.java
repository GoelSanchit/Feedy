package websank.corp.mahisan.feedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
public class Feeds extends Activity {
   // ProgressDialog progress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feeds);
        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }



        // listView1

        final ListView lisView1 = (ListView)findViewById(R.id.list);



/** JSON return
 052.
 *  [{"MemberID":"1","Name":"Weerachai","Tel":"0819876107"},
 053.
 * {"MemberID":"2","Name":"Win","Tel":"021978032"},
 054.
 * {"MemberID":"3","Name":"Eak","Tel":"0876543210"}]
 055.
 */

        String url = "http://feedyapi.herokuapp.com/";

        try {

            JSONArray data = new JSONArray(getJSONUrl(url));

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();

            HashMap<String, String> map;

            for(int i = 0; i < data.length(); i++){

                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();

                map.put("tit", c.getString("title"));

                map.put("con", c.getString("person"));

               /* map.put("lin", c.getString("link"));

                map.put("num", c.getString("number"));

                map.put("ema", c.getString("email"));

                map.put("add", c.getString("address"));*/




                MyArrList.add(map);

            }

            SimpleAdapter sAdap;

            sAdap = new SimpleAdapter(Feeds.this, MyArrList, R.layout.feed_item,

                    new String[] {"tit", "con"}, new int[] {R.id.title, R.id.desc});

            lisView1.setAdapter(sAdap);

            final AlertDialog.Builder viewDetail = new AlertDialog.Builder(this);

// OnClick Item

            lisView1.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView<?> myAdapter, View myView,

                                        int position, long mylng) {

                    //  String sMemberID = MyArrList.get(position).get("tit")

                    //        .toString();

                   /* String lin = MyArrList.get(position).get("lin")

                            .toString();
                    String num = MyArrList.get(position).get("num")

                            .toString();
                    String ema = MyArrList.get(position).get("ema")

                            .toString();
                    String add = MyArrList.get(position).get("add")

                            .toString();*/

                    Intent i = new Intent(Feeds.this,Info.class);
                    //i.putExtra("link",lin);
                    //i.putExtra("number",num);
                    //i.putExtra("email",ema);
                    //i.putExtra("address",add);
                    startActivity(i);

                }

            });


        } catch (JSONException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        }

    }

    public String getJSONUrl(String url) {

        StringBuilder str = new StringBuilder();

        HttpClient client = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url);

        try {

            HttpResponse response = client.execute(httpGet);

            StatusLine statusLine = response.getStatusLine();

            int statusCode = statusLine.getStatusCode();

            if (statusCode == 200) { // Download OK

                HttpEntity entity = response.getEntity();

                InputStream content = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(content));

                String line;

                while ((line = reader.readLine()) != null) {

                    str.append(line);

                }

            } else {

                Log.e("Log", "Failed to download result..");

            }

        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return str.toString();

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my, menu);

        return true;

    }



}