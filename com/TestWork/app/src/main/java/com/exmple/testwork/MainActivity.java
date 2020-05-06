package com.exmple.testwork;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public DrawerLayout drawerLayout;
    private Button navButton;
    public static final int TAKE_PHOTO = 1;
    private CircleImageView circleImageView;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Button button0 = (Button)findViewById(R.id.button_0);
        //button0.setOnClickListener(this);

       // Button button1 = (Button)findViewById(R.id.button_1);
        //button1.setOnClickListener(this);
        //Button button2 = (Button)findViewById(R.id.button_2);
        //button2.setOnClickListener(this);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //navButton = (Button)findViewById(R.id.nav_button);

        NavigationView  navView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navView.getHeaderView(0);
        TextView textView = (TextView)headerLayout.findViewById(R.id.logname);
        Intent intent = getIntent();
        String logname = intent.getStringExtra("logname");
        textView.setText("hello "+logname);
        drawerLayout.openDrawer(GravityCompat.START);//将显示出来


        circleImageView = (CircleImageView)headerLayout.findViewById(R.id.icon_image);

        circleImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                File outputImage = new File(getExternalCacheDir(),"test.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(MainActivity.this, "com.exmple.testwork.fileprovider", outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }

// 启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });



    }



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.remove){
            Toast.makeText(MainActivity.this,"您点击了删除选项",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override

    public void onClick(View view) {

            switch (view.getId()){

                case R.id.button_1:
                    Toast.makeText(MainActivity.this,"本书是一本针对所有层次的Python读者而作的Python入门书。",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,PdfActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_2:
                    Toast.makeText(MainActivity.this,"本书内容易于理解，而且读起来生动有趣，是编程和Python初学者不可多得的优秀教程。",Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;

            }


        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
// 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        circleImageView.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }




}


/*
public class MainActivity extends AppCompatActivity{
    private List<Book> bookList = new ArrayList<>();
    TextView textView;
    public DrawerLayout drawerLayout;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.internet);
        textView.setTypeface(Typeface.createFromAsset(MainActivity.this.getAssets(), "fonts/1.ttf"));


        initBooks(); // 初始化水果数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        BookAdapter adapter = new BookAdapter(bookList);
        recyclerView.setAdapter(adapter);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView  navView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navView.getHeaderView(0);
        TextView textView = (TextView)headerLayout.findViewById(R.id.logname);
        Intent intent = getIntent();
        String logname = intent.getStringExtra("logname");
        textView.setText("welcome "+logname);
        drawerLayout.openDrawer(GravityCompat.START);




    }

    private void initBooks() {
        Book apple = new Book("Apple", R.drawable.img1);
        bookList.add(apple);
        Book banana = new Book("Banana", R.drawable.img2);
        bookList.add(banana);
        Book orange = new Book("Orange", R.drawable.img3);
        bookList.add(orange);
        Book watermelon = new Book("Watermelon", R.drawable.img4);
        bookList.add(watermelon);
        Book pear = new Book("Pear", R.drawable.img5);
        bookList.add(pear);
        Book grape = new Book("Grape", R.drawable.img6);
        bookList.add(grape);
        Book pineapple = new Book("Pineapple", R.drawable.img7);
        bookList.add(pineapple);
        Book strawberry = new Book("Strawberry", R.drawable.img8);
        bookList.add(strawberry);

    }






}
*/