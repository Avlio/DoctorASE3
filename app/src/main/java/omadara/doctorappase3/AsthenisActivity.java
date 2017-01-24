package omadara.doctorappase3;

import java.util.ArrayList;
import java.util.Date;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

public class AsthenisActivity extends Activity {

    private Button confirm;
    private ListView list;
    private MyDatabaseClient db;
    private ArrayList<Availables> avsList = new ArrayList<Availables>();
    private CustomAdapter cAdapter;
    private static final String DATABASE_NAME = "softeng.db";
    private static final int DATABASE_VERSION = 1;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asthenis);

        db = new MyDatabaseClient(this, DATABASE_NAME, null, DATABASE_VERSION);

        confirm = (Button) findViewById(R.id.confirm);
        list = (ListView) findViewById(R.id.avList);
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                getAvs();
            }

        });

        thread.start();

        cAdapter = new CustomAdapter(this, R.layout.bridge_listview, avsList);
        list.setAdapter(cAdapter);
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Availables av = (Availables) arg0.getItemAtPosition(arg2);
                if(av.getReservation())
                    av.setReservation(false);
                else
                    av.setReservation(true);
                cAdapter.notifyDataSetChanged();
            }

        });

    }

    confirm.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            for(int i = 0; i < list.getChildCount(); i++) {
                Availables av = (Availables) list.getItemAtPosition(i);
                if(av.getReservation())
                    db.setReservation(av.getName(), "true");
                else
                    db.setReservation(av.getName(), "false");
            }

            cAdapter.notifyDataSetChanged();
            recreate();
        }

    });
}

    @SuppressWarnings("deprecation")
    public void getAvs() {
        Cursor c = db.getEventsAdmin();
        while(!c.isAfterLast()) {
            String date = c.getString(c.getColumnIndex("date"));
            String[] dateParts = date.split("\\/");
            Date eventDate = new Date();
            eventDate.setDate(Integer.parseInt(dateParts[0]));
            eventDate.setMonth(Integer.parseInt(dateParts[1]));
            eventDate.setYear(Integer.parseInt(dateParts[2]));
            Date todayDate = new Date();
            if(!todayDate.after(eventDate)) {
                ArrayList<String> name = new ArrayList<String>();
                name.add(c.getString(c.getColumnIndex("avs")));
                db.deleteAvs(name);
            }else {
                if(c.getString(c.getColumnIndex("type")).equals("sports")) {
                    Xeirourgeio x1 = new Xeirourgeio();
                    x1.setName(c.getString(c.getColumnIndex("events")));
                    x1.setDate(c.getString(c.getColumnIndex("date")));
                    x1.setDifficulty(c.getString(c.getColumnIndex("difficulty")));
                    x1.setDate(c.getString(c.getColumnIndex("hour")));
                    if(c.getString(c.getColumnIndex("status")).equals("true"))
                        x1.setReservation(true);
                    else
                        x1.setReservation(false);
                    avsList.add(x1);
                } else {
                    Episkepsi e1= new Episkepsi();
                    e1.setName(c.getString(c.getColumnIndex("avs")));
                    e1.setDate(c.getString(c.getColumnIndex("date")));
                    e1.setHour(c.getString(c.getColumnIndex("hour")));
                    if(c.getString(c.getColumnIndex("status")).equals("true"))
                        e1.setReservation(true);
                    else
                        e1.setReservation(false);
                    avsList.add(e1);
                }
            }
            c.moveToNext();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



}
