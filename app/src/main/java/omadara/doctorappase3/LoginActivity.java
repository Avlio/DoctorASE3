package omadara.doctorappase3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class LoginActivity extends Activity {

    private EditText username, password;
    private Button login, register;
    private MyDatabaseClient db;
    private RadioButton patient, doctor;
    private static final String DATABASE_NAME = "softeng.db";
    private static final int DATABASE_VERSION = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.signup);

        patient = (RadioButton) findViewById(R.id.patientRadioButton);
        doctor = (RadioButton) findViewById(R.id.doctorRadioButton);

        db = new MyDatabaseClient(this, DATABASE_NAME, null, DATABASE_VERSION);

        login.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty())
                    Toast.makeText(LoginActivity.this, "Συμπληρώστε όλα τα πεδία", Toast.LENGTH_SHORT).show();
                else {
                    try {
                        Cursor c = db.getUser(username.getText().toString(), password.getText().toString());
                        String userDB  = c.getString(c.getColumnIndex("username"));
                        if(username.getText().toString().equals(userDB)){
                            if(c.getString(c.getColumnIndex("type")).equals("doctor")) {
                                Intent i = new Intent(LoginActivity.this, GiatrosActivity.class);
                                startActivity(i);
                            } else {
                                Intent i = new Intent(LoginActivity.this, AsthenisActivity.class);
                                startActivity(i);
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Ο κωδικός δεν είναι σωστός", Toast.LENGTH_SHORT).show();
                        }
                    } catch(IndexOutOfBoundsException e) {
                        Toast.makeText(LoginActivity.this, "Το όνομα δεν είναι σωστό", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        register.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty())
                    Toast.makeText(LoginActivity.this, "Συμπληρώστε όλα τα πεδία", Toast.LENGTH_SHORT).show();
                else {
                    if(doctor.isChecked()) {
                        if(db.addUser(username.getText().toString(), password.getText().toString(), "doctor")) {
                            Intent i = new Intent(LoginActivity.this, GiatrosActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(LoginActivity.this, "Το όνομα χρήστη χρησιμοποιείται.", Toast.LENGTH_SHORT).show();
                        }
                    } else if(patient.isChecked()){
                        if(db.addUser(username.getText().toString(), password.getText().toString(), "patient")) {
                            Intent i = new Intent(LoginActivity.this, AsthenisActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(LoginActivity.this, "Το όνομα χρήστη χρησιμοποιείται.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Επιλέξτε κάποια κατηγορία.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

