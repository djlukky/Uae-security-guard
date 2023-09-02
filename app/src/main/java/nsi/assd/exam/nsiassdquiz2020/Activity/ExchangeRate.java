package nsi.assd.exam.nsiassdquiz2020.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.google.gson.JsonObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.SortedList;
import nsi.assd.exam.nsiassdquiz2020.R;

public class ExchangeRate extends AppCompatActivity {
    Button button;
    EditText currencyToBeConverted;
    EditText currencyConverted;
    Spinner convertToDropdown;
    Spinner convertFromDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_rate);
        /*currencyConverted = (EditText) findViewById(R.id.currency_converted);
        currencyToBeConverted = (EditText) findViewById(R.id.currency_to_be_converted);
        convertToDropdown = (Spinner) findViewById(R.id.convert_to);
        convertFromDropdown = (Spinner) findViewById(R.id.convert_from);
        button = (Button) findViewById(R.id.button);

        //Adding Functionality
        String[] dropDownList = {"USD", "INR","EUR","NZD"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, dropDownList);
        convertToDropdown.setAdapter(adapter);
        convertFromDropdown.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertFromDropdown.getSelectedItem().toString());
               call.enqueue(new Callback<JsonObject>() {
                   @Override
                   public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                       JsonObject res = response.body();
                       JsonObject rates = res.getAsJsonObject("rates");
                       double currency = Double.valueOf(currencyToBeConverted.getText().toString());
                       double multiplier = Double.valueOf(rates.get(convertToDropdown.getSelectedItem().toString()).toString());
                       double result = currency * multiplier;
                       currencyConverted.setText(String.valueOf(result));
                   }

                   @Override
                   public void onFailure(Call<JsonObject> call, Throwable t) {

                   }
               });
            }
        });*/



    }
}

