package com.vitor.barcodescanner.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vitor.barcodescanner.R;
import com.vitor.barcodescanner.constant.Const;
import com.vitor.barcodescanner.util.Util;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fabProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requisitaPermissoes();
        getReferences();

        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.ABRE_TELA_SCANNER) {
            if (resultCode == RESULT_OK) {
                Util.exibeAlertPadrao(this, null, data.getStringExtra(Const.RETORNO_TELA_SCANNER), getString(R.string.copiar),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                ClipData clip = ClipData.newPlainText(Const.RETORNO_TELA_SCANNER, data.getStringExtra(Const.RETORNO_TELA_SCANNER));
                                clipboard.setPrimaryClip(clip);
                                Toast.makeText(MainActivity.this, getString(R.string.msg_copiado), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
    }

    public void requisitaPermissoes() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, Const.REQUISICAO_PERMISSOES);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean bCarregar = true;

        switch (requestCode) {
            case Const.REQUISICAO_PERMISSOES:
                if (grantResults.length > 0) {
                    for (int iCont = 0; iCont < grantResults.length; iCont++) {
                        bCarregar &= (grantResults[iCont] == PackageManager.PERMISSION_GRANTED);
                    }
                }
                else {
                    bCarregar = false;
                }

                if (!bCarregar) {
                    Util.finalizaActivity(this, RESULT_CANCELED);
                    return;
                }
                fabProximo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, ScannerActivity.class);
                        startActivityForResult(intent, Const.ABRE_TELA_SCANNER);
                    }
                });
                break;
        }
    }

    public void getReferences() {
        fabProximo = findViewById(R.id.fabProximo);
        toolbar = findViewById(R.id.toolbar);
    }
}