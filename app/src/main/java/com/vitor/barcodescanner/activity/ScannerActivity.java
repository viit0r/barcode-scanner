package com.vitor.barcodescanner.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.vitor.barcodescanner.R;
import com.vitor.barcodescanner.constant.Const;
import com.vitor.barcodescanner.util.Util;

public class ScannerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        try {
            iniciarScanner();
        } catch (Exception err) {
            Util.exibeAlertPadrao(this, getString(R.string.msg_atencao), err.getMessage());
        }
    }

    private void iniciarScanner() throws Exception
    {
        IntentIntegrator intentIntegrator = null;
        intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt(getString(R.string.msg_escaneie));
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        try {
            if (intentResult != null) {
                if (intentResult.getContents() != null) {
                    Intent intent = new Intent(ScannerActivity.this, MainActivity.class);
                    intent.putExtra(Const.RETORNO_TELA_SCANNER, intentResult.getContents());
                    Util.finalizaActivity(this, RESULT_OK, intent);
                } else {
                    Util.finalizaActivity(this, RESULT_CANCELED);
                }
            } else {
                Util.finalizaActivity(this, RESULT_CANCELED);
            }
        } catch (Exception err) {
            Util.exibeAlertPadrao(this, getString(R.string.msg_atencao), err.getMessage());
        }
    }
}