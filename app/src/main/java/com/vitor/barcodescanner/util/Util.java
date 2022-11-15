package com.vitor.barcodescanner.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

public class Util {

    public static AlertDialog exibeAlertPadrao(Context context, String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        return builder.show();
    }

    public static AlertDialog exibeAlertPadrao(Context context, String titulo, String mensagem, String textoBotao, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        builder.setPositiveButton(textoBotao, onClickListener);
        return builder.show();
    }

    public static AlertDialog exibeAlertPergunta(Context context, String titulo, String mensagem, String textoPositiveButton, String textoNegativeButton, DialogInterface.OnClickListener positiveClickListener, DialogInterface.OnClickListener negativeClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        builder.setPositiveButton(textoPositiveButton, positiveClickListener);
        builder.setNegativeButton(textoNegativeButton, negativeClickListener);
        return builder.show();
    }

    public static void finalizaActivity(Activity activity, int iResultado) {
        activity.setResult(iResultado);
        activity.finish();
    }

    public static void finalizaActivity(Activity activity, int iResultado, Intent intent) {
        activity.setResult(iResultado, intent);
        activity.finish();
    }
}
