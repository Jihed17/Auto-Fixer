package org.jihed.autofixer;

import static android.os.Build.VERSION_CODES.R;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_name = findViewById(R.id.Edittext_name),editText_email;
    Button button_add,button_view;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState, Context Context) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_email = findViewById(R.id.edittext_email);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = editText_name.getText().toString();
                String stringEmail = editText_email.getText().toString();

                if (stringName.length() <=0 || stringEmail.length() <=0){
                    Toast.makeText(MainActivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                }else {
                    DataBaseHelperClass databaseHelperClass = new DataBaseHelperClass(MainActivity.this);
                    UserModelClass userModelClass = new UserModelClass(stringName,stringEmail);
                    databaseHelperClass.addUser(userModelClass);
                    Toast.makeText(MainActivity.this, "Add User Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });


        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelperClass database= new DataBaseHelperClass(Context) ;

                Intent intent = new Intent(MainActivity.this,ViewUserActivity.class);
                startActivity(intent);
            }
        });


    }
}