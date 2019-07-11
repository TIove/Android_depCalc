package com.example.igorok.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {

    private EditText mTextView;
    private EditText CountDate;
    private EditText CountPercent;

    String Type;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Switch Капитализация

        Switch switch2 = (Switch) findViewById(R.id.switch2);
        if (switch2 != null) {
            switch2.setOnCheckedChangeListener(this);
        }


//Слайдер 1

        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar4);
        seekBar.setOnSeekBarChangeListener(this);

        mTextView = (EditText) findViewById(R.id.editText10);
        mTextView.setText("20000");
//Cлайдер 2

        final SeekBar seekBar2 = (SeekBar)findViewById(R.id.seekBar);
        seekBar2.setOnSeekBarChangeListener(this);

        CountDate = (EditText) findViewById(R.id.editText11);
        CountDate.setText("1");
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress,boolean fromUser) {
                CountDate.setText(String.valueOf(seekBar2.getProgress()));
            }
        });
//Слайдер 3

        final SeekBar seekBar3 = (SeekBar)findViewById(R.id.seekBar3);
        seekBar3.setOnSeekBarChangeListener(this);

        CountPercent = (EditText) findViewById(R.id.editText12);
        CountPercent.setText("1");
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar3, int progress,boolean fromUser) {
                float progressD=(float) progress/2;
                CountPercent.setText(String.valueOf(progressD));
                //CountPercent.setText(String.valueOf(seekBar3.getProgress()));
            }
        });


        // Получаем экземпляр элемента Spinner
        final Spinner spinner = (Spinner)findViewById(R.id.spinner);

        final ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.animals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.animals);
                Type = choose[selectedItemPosition];

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final TextView textView = findViewById(R.id.textView2);
        final EditText Text1 = (EditText) findViewById(R.id.editText10);
        final EditText Text2 = (EditText) findViewById(R.id.editText11);
        final EditText Text3 = (EditText) findViewById(R.id.editText12);



        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                switch (Type) {
                    case "В конце срока":
                        double a, b, c, d;
                        try {
                            a = Double.parseDouble(Text1.getText().toString());
                            b = Double.parseDouble(Text2.getText().toString());
                            c = Double.parseDouble(Text3.getText().toString());
                        } catch (NumberFormatException e) {
                            a = 0;
                            b = 0;
                            c = 0;
                        }
                        d = a * b * c / 36500;
                        d = Math.rint(100.0 * d) / 100.0;
                        String S = Double.toString(d);
                        textView.setText(S);
                        break;
                    case "В конце месяца":

                        startActivity(intent);
                        break;
                    case "В день вклада":
                        startActivity(intent);
                        break;
                    default:

                        break;
                }

                }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        int n = seekBar.getProgress();
        if (n%50000 != 0){
            n = Math.round(n/50000)*50000;
        }
        seekBar.setProgress(n);
        mTextView.setText(String.valueOf(seekBar.getProgress()));
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            Toast.makeText(getApplicationContext(), "SET ON", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "SET OFF", Toast.LENGTH_SHORT).show();
        }
    }
}

