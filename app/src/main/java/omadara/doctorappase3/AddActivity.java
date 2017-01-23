package omadara.doctorappase3;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static omadara.doctorappase3.R.id.date;
import static omadara.doctorappase3.R.id.theaterRB;

public class AddActivity extends Activity {

    private Button confirm;
    private EditText name;
    private RadioButton xeirRB, epRB;
    private DatePicker dPicker;
    private MyDatabase db;
    private static final String DATABASE_NAME = "softeng.db";
    private static final int DATABASE_VERSION = 1;
    private String avname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add);

        Intent i = getIntent();
        try {
            avname = i.getStringExtra("name");
            Log.d("av name", avname);
        } catch(NullPointerException e) {

        }

        db = new MyDatabase(this, DATABASE_NAME, null, DATABASE_VERSION);

        confirm = (Button) findViewById(R.id.confirm);
        name = (EditText) findViewById(R.id.avname);
        dPicker = (DatePicker) findViewById(R.id.date);

        xeirRB = (RadioButton) findViewById(R.id.xeirRB);
        epRB = (RadioButton) findViewById(R.id.epRB);

        confirm.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String date = dPicker.getDayOfMonth() + "/" + dPicker.getMonth() + "/" + dPicker.getYear();
                if(avname==null)
                    if(xeirRB.isChecked()) {
                        db.addAvXeirourgeio(name.getText().toString(), "hour", date, true, "xeirourgeio");
                        Intent i = new Intent(AddActivity.this, GiatrosActivity.class);
                        startActivity(i);
                    } else if(epRB.isChecked()) {
                        db.addAvEpiskepsi(name.getText().toString(), "hour", date, true);
                        Intent i = new Intent(AddActivity.this, GiatrosActivity.class);
                        startActivity(i);
                    } else
                        Toast.makeText(AddActivity.this, "Συμπληρώστε όλα τα στοιχεία", Toast.LENGTH_SHORT).show();
                else {
                    if(xeirRB.isChecked()) {
                        db.updateAvXeirourgeio(name.getText().toString(), "hour", date, true, "xeirourgeio");
                        Intent i = new Intent(AddActivity.this, GiatrosActivity.class);
                        startActivity(i);
                    } else if(epRB.isChecked()) {
                        db.updateAvEpiskepsi(name.getText().toString(), "hour", date, true);
                        Intent i = new Intent(AddActivity.this, GiatrosActivity.class);
                        startActivity(i);
                    } else
                        Toast.makeText(AddActivity.this, "Συμπληρώστε όλα τα στοιχεία", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_event_screen, menu);
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
