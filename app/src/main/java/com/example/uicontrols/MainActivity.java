package com.example.uicontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import static com.example.uicontrols.R.*;

public class MainActivity extends AppCompatActivity {

    StringBuilder stringBuilder;
    EditText name;
    RadioGroup radioGroup;
    RadioButton gender;
    CheckBox eng, mar, hindi;
    ToggleButton hostel;
    SeekBar age;
    int getAge;
    RatingBar ratingBar;
    ListView listView;
    TextView textView;
    String[] listItem;
    String City;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);


        stringBuilder = new StringBuilder();
        name = (EditText) findViewById(R.id.GetName);
        radioGroup = (RadioGroup) findViewById(id.radioGroup);
        eng = (CheckBox) findViewById(id.English);
        hindi = (CheckBox) findViewById(id.Hindi);
        mar = (CheckBox) findViewById(id.Marathi);
        hostel = (ToggleButton) findViewById(id.toggleButton);
        age = (SeekBar) findViewById(id.seekBar);
        age.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getAge = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ratingBar = (RatingBar) findViewById(id.ratingBar);
        listView = (ListView) findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.textView);
        listItem = getResources().getStringArray(array.city);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                City = adapter.getItem(position);

            }
        });
        submit = (Button) findViewById(id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringBuilder.delete(0, stringBuilder.length());
                stringBuilder.append("Entered Details:\n");
                stringBuilder.append("Name: ").append(name.getText()).append("\n");
                int selected_gender = radioGroup.getCheckedRadioButtonId();
                gender = findViewById(selected_gender);
                stringBuilder.append("Gender: ").append(gender.getText()).append("\n");
                String languages = "";
                if (eng.isChecked()) languages += eng.getText() + " ";
                if (hindi.isChecked()) languages += hindi.getText() + " ";
                if (mar.isChecked()) languages += mar.getText();
                stringBuilder.append("Can Speak: ").append(languages).append("\n");
                stringBuilder.append("Do you Need Hostel: ").append(hostel.getText()).append("\n");
                stringBuilder.append("Age: ").append(getAge).append("\n");
                stringBuilder.append("Your Rating: ").append(ratingBar.getRating()).append("\n");
                stringBuilder.append("City: ").append(City).append("\n");
                Toast toast = Toast.makeText(getApplicationContext(), stringBuilder.toString(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}