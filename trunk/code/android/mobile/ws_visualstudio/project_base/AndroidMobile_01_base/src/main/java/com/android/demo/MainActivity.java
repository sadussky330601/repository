package com.android.demo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.demo.cons.Cons;
import com.android.demo.jni.JNISecurity;
import com.android.demo.util.Comutils;
import com.android.demo.util.FileUtils;
import com.android.demo.util.Logger;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileUtils.getInstance(this);
        Comutils.getInstance(this);
        Exception e = new Exception("test for exception...");
        Logger.eFile(e, "MainActivity", "test for exception");


        testGetDefualt();
    }

    public void testGetDefualt() {
        try {
            Log.d(Cons.TAG, "直接从JNI获取的 :" + new String(JNISecurity.getDefaultPartner(), "UTF-8").toString());
            Log.d(Cons.TAG, "直接从JNI获取的 :" + new String(JNISecurity.getDefaultSeller(), "UTF-8").toString());
            Log.d(Cons.TAG, "直接从JNI获取的 :" + new String(JNISecurity.getPublicKey(), "UTF-8").toString());
            Log.d(Cons.TAG, "直接从JNI获取的 :" + new String(JNISecurity.getPrivateKey(), "UTF-8").toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public interface IA {

    }

    public interface IB {

    }

    public interface IC {

    }

    public class CA {

    }

    private <T extends IA, IB> void test(T t) {

    }


    private <T extends IC & IA, IB> void test(T t) {

    }


    private <T extends CA & IA, IB> void test(T t) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
