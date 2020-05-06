package activitytest.example.asus.uiwidgettest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private EditText editText;
    private ProgressBar progressBar;
    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.edit_text);
        //imageView = (ImageView)findViewById(R.id.image_view);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
               // ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                //progressDialog.setTitle("This is ProgressDialog");
                //progressDialog.setMessage("Loading...");
                //progressDialog.setCancelable(true);
                //progressDialog.show();
                progressBar.setContentDescription("Loading...");
                progressBar.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
