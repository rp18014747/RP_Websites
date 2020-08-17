package sg.edu.rp.c346.id18014747.rpwebsites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spnCategory;
    Spinner spnPage;
    Button btnGo;
    ArrayList<String> alCategory;
    ArrayAdapter<String> aaCategory;
    ArrayList<String> alPage;
    ArrayAdapter<String> aaPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnCategory = findViewById(R.id.spinnerCategory);
        spnPage = findViewById(R.id.spinnerPage);
        btnGo = findViewById(R.id.buttonGo);

        //Category
        alCategory = new ArrayList<>();
        String[] strCategory = {"RP", "SOI"};
        alCategory.addAll(Arrays.asList(strCategory));
        aaCategory = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alCategory);
        spnCategory.setAdapter(aaCategory);

        //Page
        alPage = new ArrayList<>();
        String[] strPage = {"Homepage", "Student Life", "DMSD", "DIT"};
        alPage.addAll(Arrays.asList(strPage));
        aaPage = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alPage);
        spnPage.setAdapter(aaPage);

        // Dynamic
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        alPage.clear();
                        String[] strPageRP = {"Homepage", "Student Life"};
                        alPage.addAll(Arrays.asList(strPageRP));
                        aaPage.notifyDataSetChanged();
                        spnPage.setSelection(0);
                        break;
                    case 1:
                        alPage.clear();
                        String[] strPageSOI = {"DMSD", "DIT"};
                        alPage.addAll(Arrays.asList(strPageSOI));
                        aaPage.notifyDataSetChanged();
                        spnPage.setSelection(0);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[][] sites = {{"https://www.grab.com/sg/", "https://www.grab.com/"}, {"https://www.rp.edu.sg/soi/full-time-diplomas/details/r47", "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12"}};
                String url = sites[spnCategory.getSelectedItemPosition()][spnPage.getSelectedItemPosition()];
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putInt("category", spnCategory.getSelectedItemPosition());
        prefEdit.putInt("page", spnPage.getSelectedItemPosition());

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int category = prefs.getInt("category", 0);
        int page = prefs.getInt("page", 0);
        spnCategory.setSelection(category);
        spnPage.setSelection(page);
    }

}
