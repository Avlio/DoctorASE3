package omadara.doctorappase3;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
public class GiatrosActivity extends Activity {

    private ListView list;
    private Button addEvent, deleteEvent, editEvent;
    private MyDatabase db;
    private CustomAdapterAdmin cAdapter;
    private static final String DATABASE_NAME = "softeng.db";
    private static final int DATABASE_VERSION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_giatros);

        db = new MyDatabase(this, DATABASE_NAME, null, DATABASE_VERSION);
        list = (ListView) findViewById(R.id.avList);
        add = (Button) findViewById(R.id.add);
        delete = (Button) findViewById(R.id.delete);
        edit = (Button) findViewById(R.id.edit);

        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        addEvent.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(GiatrosActivity.this, AddActivity.class);
                startActivity(i);
            }

        });

        editEvent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final ArrayList<String> names = new ArrayList<String>();
                for(int i = 0; i < list.getChildCount(); i++) {
                    AdminItem item = (AdminItem) list.getItemAtPosition(i);
                    if(item.getChecked())
                        names.add(list.getItemAtPosition(i).toString());
                }
                if(names.size() > 1)
                    Toast.makeText(GiatrosActivity.this, "Επιλέξτε μόνο ένα ραντεβού για επεξεργασία.", Toast.LENGTH_SHORT).show();
                else
                    try {
                        Intent i = new Intent(GiatrosActivity.this, AddActivity.class);
                        i.putExtra("name", names.get(0));
                        startActivity(i);
                    } catch(NullPointerException e) {
                        Toast.makeText(GiatrosActivity.this, "Δεν εχετέ επιλέξει κάποιο ραντεβού.", Toast.LENGTH_SHORT).show();
                    }
            }

        });

        deleteEvent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final ArrayList<String> names = new ArrayList<String>();
                for(int i = 0; i < list.getChildCount(); i++) {
                    AdminItem item = (AdminItem) list.getItemAtPosition(i);
                    if(item.getChecked())
                        names.add(item.getName());
                }

                if(names.size() > 0) {
                    new AlertDialog.Builder(GiatrosActivity.this)
                            .setTitle("Διαγραφή ραντεβού")
                            .setMessage("Είστε σίγουροι?")
                            .setPositiveButton("Ναι", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteAv(names);
                                    dialog.dismiss();
                                    cAdapter.notifyDataSetChanged();
                                    recreate();
                                }
                            })
                            .setNegativeButton("Όχι",  new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                } else {
                    Toast.makeText(GiatrosActivity.this, "Δεν εχετέ επιλέξει κάποια εκδήλωση.", Toast.LENGTH_SHORT).show();
                }
            }

        });

        Cursor c = db.getEventsAdmin();
        ArrayList<AdminItem> events = new ArrayList<AdminItem>();
        if(c.getCount()>0) {
            while(!c.isAfterLast()) {
                events.add(new AdminItem(c.getString(c.getColumnIndex("events")), false));
                c.moveToNext();
            }
            cAdapter = new CustomAdapterAdmin(this, R.layout.bridge_listview, events);
            list.setAdapter(cAdapter);
        } else {
            Toast.makeText(GiatrosActivity.this, "Δεν υπάρχει κάποιο ραντεβού", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_screen, menu);
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
