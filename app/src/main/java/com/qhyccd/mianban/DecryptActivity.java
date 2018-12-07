package com.qhyccd.mianban;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.qhyccd.mianban.Encrypted;

public class DecryptActivity extends Activity
{
    public static Encrypted publicEncrypted;
    private ArrayAdapter<Integer> adapter;
    private Button bt_confirm;
    private Button bt_reset;
    private int encryptdays;
    private EditText et_PWD;
    private EditText et_faceidcode;
    private EditText et_randomcode;
    private Spinner spinner_encryptdays;

    @Override
    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(2130903040);
        this.et_randomcode = ((EditText)findViewById(2131034118));
        this.et_randomcode.setFocusable(true);
        this.et_faceidcode = ((EditText)findViewById(2131034115));
        this.et_PWD = ((EditText)findViewById(2131034124));
        this.bt_confirm = ((Button)findViewById(2131034121));
        this.bt_reset = ((Button)findViewById(2131034122));
        this.spinner_encryptdays = ((Spinner)findViewById(2131034117));
        publicEncrypted = new Encrypted();
        this.et_randomcode.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
            {
                DecryptActivity.this.et_randomcode.selectAll();
                DecryptActivity.this.et_PWD.setText("");
            }
        });
        this.adapter = new ArrayAdapter(this, 17367048, publicEncrypted.init());
        this.spinner_encryptdays.setAdapter(this.adapter);
        this.spinner_encryptdays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                DecryptActivity.this.et_PWD.setText("");
                paramAnonymousAdapterView.setVisibility(0);
                DecryptActivity.this.encryptdays = DecryptActivity.publicEncrypted.init()[paramAnonymousInt].intValue();
                DecryptActivity.publicEncrypted.setComboBox(paramAnonymousInt);
            }
            @Override
            public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
            {
            }
        });
        this.bt_confirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View paramAnonymousView)
            {
                String str1 = DecryptActivity.this.et_randomcode.getText().toString();
                String str2 = DecryptActivity.this.et_faceidcode.getText().toString();
                if (str1 != null)
                {
                    if (DecryptActivity.this.et_randomcode.getText().toString().length() < 6)
                    {
                        Toast.makeText(DecryptActivity.this.getApplicationContext(), "请输入6位随机码！", 0).show();
                        return;
                    }
                    if (str2 != null)
                    {
                        if (Integer.parseInt(str2) <= 0)
                        {
                            Toast.makeText(DecryptActivity.this.getApplicationContext(), "请输入面板识别码！", 0).show();
                            return;
                        }
                        DecryptActivity.this.et_PWD.setText(DecryptActivity.publicEncrypted.getPWD(str1, str2));
                        return;
                    }
                    Toast.makeText(DecryptActivity.this.getApplicationContext(), "请输入面板识别码！", 0).show();
                    return;
                }
                Toast.makeText(DecryptActivity.this.getApplicationContext(), "请输入6位随机码！", 0).show();
            }
        });
        this.bt_reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View paramAnonymousView)
            {
                DecryptActivity.publicEncrypted.init();
                DecryptActivity.this.et_faceidcode.setText("");
                DecryptActivity.this.et_PWD.setText("");
                DecryptActivity.this.et_randomcode.setText("");
            }
        });
    }
}