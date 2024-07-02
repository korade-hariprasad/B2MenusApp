package sumagoscope.madipt.b2menusapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnPopup;
    Button btnContext;
    Button btnDialog;
    PopupMenu popupMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        btnPopup=findViewById(R.id.btnPopup);
        btnContext=findViewById(R.id.btnContext);
        btnDialog=findViewById(R.id.btnDialog);
        registerForContextMenu(btnContext);
        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                popupMenu=new PopupMenu(MainActivity.this,btnPopup);
                popupMenu.inflate(R.menu.popup_menu);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    popupMenu.setForceShowIcon(true);
                }
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getItemId()==R.id.action_home)
                        {
                            Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        }
                        if(item.getItemId()==R.id.action_extra)
                        {
                            Toast.makeText(MainActivity.this, "Extra", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                popupMenu.show();

            }
        });

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAlertDialog();
            }
        });

    }

    private void showAlertDialog() {

        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this)
                .setTitle("Title Goes Here")
                .setMessage("Message Text Here")
                .setIcon(R.drawable.baseline_logout_24)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.action_home)
        {
            Intent intent=new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_home)
        {
            Intent intent=new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        return true;
    }
}