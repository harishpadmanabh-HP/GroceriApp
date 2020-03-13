package com.hp.groceriapp.Staff;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Utils.Utils;

import java.util.List;

public class OnboardActivity extends AppCompatActivity {

    private EditText staffId;
    private EditText staffPass;
    private MaterialButton proceed;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);
        initView();
        View rootView = findViewById(android.R.id.content);
        appPreferences = AppPreferences.getInstance(getApplicationContext(), getResources().getString(R.string.app_name));
//conecting all TextInputEditText as list
        final List<EditText> EditTexts = Utils.findViewsWithType(
                rootView, EditText.class);

        proceed.setOnClickListener(v -> {
            //checking null values for each edittesxt
            boolean noErrors = true;
            for (EditText editText : EditTexts) {
                //get strings from each edittext
                String editTextString = editText.getText().toString();
                if (editTextString.isEmpty()) {
                    editText.setError(("please fill this field"));
                    noErrors = false;
                } else {
                    editText.setError(null);
                }
            }
            if(noErrors){

            }
        });
    }

    private void initView() {
        staffId = findViewById(R.id.staff_id);
        staffPass = findViewById(R.id.staff_pass);
        proceed = findViewById(R.id.proceed);
    }
}
