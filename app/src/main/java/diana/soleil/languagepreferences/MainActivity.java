package diana.soleil.languagepreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String language, languageSwitched;
    TextView textView;
    SharedPreferences editor;


    public void languageSwitching (String s){
        editor.edit().putString("language", s).apply();

        textView.setText(s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_language, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        String name = editor.getString("language", "No name defined");
        switch (item.getItemId()) {
            case R.id.english:
                if (name.equals("English")) {
                    Toast.makeText(this, "Language is in English", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Language Changed to English", Toast.LENGTH_SHORT).show();
                  languageSwitching("English");
                }
                return true;

            case R.id.french:
                if (name.equals("French")) {

                    Toast.makeText(this, "Language is in French", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Language Changed to French ", Toast.LENGTH_SHORT).show();
                    languageSwitching("French");
                }
                return true;

            default:
                return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView2);
         editor = this.getSharedPreferences("diana.soleil.languagepreferences", Context.MODE_PRIVATE);
        language = editor.getString("language","Error");
        if (language.equals("Error")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.alert_dark_frame)
                    .setTitle("Language")
                    .setMessage("Which Language do you want to switch to")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            languageSwitching("English");
                        }
                    })
                    .setNegativeButton("French", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            languageSwitching("French");
                        }
                    }).show();

        } else {
            textView.setText(language);
        }

    }
}