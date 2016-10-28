package com.project.android.medicineremider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.android.medicineremider.data.MedicineContract;

public class MedicineEditor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button refillMedicine;
    private EditText stockEdit;
    private TextView StockText_view;

    /** EditText field to enter the Medicine Type */
    private Spinner mTypeMedicineSpinner;

    private int mtype = MedicineContract.MedicineEntry.TABLET_TABLET ;
    private int mcategory = MedicineContract.MedicineEntry.TABLET ;

    /**
     *Variables for customizer the spinner Type Medicine
     */
     int[] TypeCategories ={R.string.category_tablet,R.string.category_capsule,R.string.category_injection,
            R.string.category_drops,R.string.category_drinkable,R.string.category_anticonceptive,
            R.string.category_cream,R.string.category_powder,R.string.category_remaining};

     int[] iconSpinner = {R.drawable.ic_icon_tablet, R.drawable.ic_icon_capsule, R.drawable.ic_icon_injection,
            R.drawable.ic_icon_drops, R.drawable.ic_icon_drinkable, R.drawable.ic_icon_anticonceptive_pills,
            R.drawable.ic_icon_cream, R.drawable.ic_icon_powder, R.drawable.ic_icon_remaining};

    Button treatmentButton;

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), TypeCategories[position], Toast.LENGTH_LONG).show();

        //String selection = getParent()

        /*if (!TextUtils.isEmpty(selection)) {
            if (selection.equals(getString(R.string.category_tablet))) {
                mtype = MedicineContract.MedicineEntry.TABLET;
            } else if (selection.equals(getString(R.string.category_capsule))) {
                mtype = MedicineContract.MedicineEntry.CAPSULE;
            } else if (selection.equals(getString(R.string.category_injection))) {
                mtype = MedicineContract.MedicineEntry.INJECTION;
            } else if (selection.equals(getString(R.string.category_drinkable))) {
                mtype = MedicineContract.MedicineEntry.DRINKABLE;
            } else if (selection.equals(getString(R.string.category_anticonceptive))) {
                mtype = MedicineContract.MedicineEntry.ANTICONCEPTIVE;
            } else if (selection.equals(getString(R.string.category_inhaler))) {
                mtype = MedicineContract.MedicineEntry.INHALER;
            } else if (selection.equals(getString(R.string.category_powder))) {
                mtype = MedicineContract.MedicineEntry.POWDER;
            } else if (selection.equals(getString(R.string.category_cream))) {
                mtype = MedicineContract.MedicineEntry.CREAM;
            } else if (selection.equals(getString(R.string.category_drops))) {
                mtype = MedicineContract.MedicineEntry.DROPS;
            } else {
                mtype = MedicineContract.MedicineEntry.REMAINING;
            }
        }*/
    }

    // Because AdapterView is an abstract class, onNothingSelected must be defined
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        mtype = MedicineContract.MedicineEntry.TABLET;
    }

    public void setupSpinner() {


        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.categoty_spinner);
        spin.setOnItemSelectedListener(this);

        SpinnerAdapter customAdapter = new SpinnerAdapter(getApplicationContext(), iconSpinner, TypeCategories);
        spin.setAdapter(customAdapter);

    }


    private void setupSpinnerType() {

                mTypeMedicineSpinner = (Spinner) findViewById(R.id.spinner);

                // Create adapter for spinner. The list options are from the String array it will use
                // the spinner will use the default layout
                ArrayAdapter TypeMedicineSpinnerAdapter = ArrayAdapter.createFromResource(this,
                        R.array.array_tablet_options, android.R.layout.simple_spinner_item);

                // Specify dropdown layout style - simple list view with 1 item per line
                TypeMedicineSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

                // Apply the adapter to the spinner
                mTypeMedicineSpinner.setAdapter(TypeMedicineSpinnerAdapter);

                // Set the integer mSelected to the constant values
                mTypeMedicineSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selection = (String) parent.getItemAtPosition(position);
                        if (!TextUtils.isEmpty(selection)) {
                            if (selection.equals(getString(R.string.type_tablet))) {
                                mtype = MedicineContract.MedicineEntry.TABLET_TABLET;
                            } else if (selection.equals(getString(R.string.type_12table))) {
                                mtype = MedicineContract.MedicineEntry.TABLET_MTABLE;
                            } else {
                                mtype = MedicineContract.MedicineEntry.TABLET_MG;
                            }
                        }
                    }

                    // Because AdapterView is an abstract class, onNothingSelected must be defined
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        mtype = MedicineContract.MedicineEntry.TABLET_TABLET;
                    }

                });
            }

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);


                setContentView(R.layout.medicine_editor);

                treatmentButton = (Button) findViewById(R.id.treatment_button);

                treatmentButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplication(), "Click", Toast.LENGTH_SHORT).show();
                    }
                });

                setupSpinner();
                setupSpinnerType();

                refillMedicine = (Button) findViewById(R.id.refill_button);
                stockEdit = (EditText) findViewById(R.id.stock_editText);
                StockText_view = (TextView) findViewById(R.id.stock_Textview);

                refillMedicine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        refill();
                    }
                });
            }


            public void refill() {

                String stockRefill = stockEdit.getText().toString().trim();
                String StockTotal = StockText_view.getText().toString().trim();

                //  if (!stockRefill.isEmpty()){
                int addMedicine = Integer.parseInt(stockRefill);
                //  }

                // if (!StockTotal.isEmpty()){
                int stock = Integer.parseInt(StockTotal);
                //  }

                stock = stock + addMedicine;

                StockText_view.setText(String.valueOf(stock));


            }
        }




