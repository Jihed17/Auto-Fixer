package org.jihed.autofixer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewUserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DataBaseHelperClass databaseHelperClass = new DataBaseHelperClass(this);
        List<UserModelClass> userModelClasses = databaseHelperClass.getUserList();

        if (userModelClasses.size() > 0){
            UserAdapterClass useradapterclass = new UserAdapterClass(userModelClasses,ViewUserActivity.this);
            recyclerView.setAdapter(useradapterclass);
        }else {
            Toast.makeText(this, "There is no employee in the database", Toast.LENGTH_SHORT).show();
        }




    }
}