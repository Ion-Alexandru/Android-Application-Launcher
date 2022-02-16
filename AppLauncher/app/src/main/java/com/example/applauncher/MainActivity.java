package com.example.applauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView appsList;
    private EditText searchItem;
    private Button searchButton;

    List<App> apps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchItem = findViewById(R.id.searchItem);
        appsList = findViewById(R.id.appsList);
        searchButton = findViewById(R.id.search_button);

        appsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                App app = (App) parent.getItemAtPosition(position);

                Intent intent = getPackageManager().getLaunchIntentForPackage(app.info.activityInfo.packageName);

                if(intent == null){
                    Toast.makeText(getApplicationContext(), "You cannot launch this app!", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(intent);
                }
            }
        });

        setAppList(apps);
        MyAdapter adapter = new MyAdapter(MainActivity.this, apps);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apps.clear();
                String searchItemString = searchItem.getText().toString();

                Intent intent = new Intent(Intent.ACTION_MAIN, null);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);

                List<ResolveInfo> resolveAppInfos = getPackageManager().queryIntentActivities(intent, 0);

                for(ResolveInfo appInfo : resolveAppInfos) {
                    if((appInfo.activityInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0 && appInfo.loadLabel(getPackageManager()).toString().contains(searchItemString)){
                        App app = new App();
                        app.appName = appInfo.loadLabel(getPackageManager()).toString();
                        app.appIcon = appInfo.loadIcon(getPackageManager());
                        app.info = appInfo;
                        apps.add(app);
                        }
                    }
                if(apps.isEmpty()) {
                    Intent gpInent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q="+ searchItemString));
                    (MainActivity.this).startActivity(gpInent);
                    setAppList(apps);
                }
                adapter.notifyDataSetChanged();
            }
        });
        appsList.setAdapter(adapter);
    }

    public List<App> setAppList(List<App> apps){

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> resolveAppInfos = getPackageManager().queryIntentActivities(mainIntent, 0);

        for(ResolveInfo appInfo : resolveAppInfos){
            if((appInfo.activityInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                App app = new App();
                app.appName = appInfo.loadLabel(getPackageManager()).toString();
                app.appIcon = appInfo.loadIcon(getPackageManager());
                app.info = appInfo;
                apps.add(app);
            }
        }
        return apps;
    }
}

class App{
    String appName;
    Drawable appIcon;
    public ResolveInfo info;
}

