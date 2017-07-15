package com.loubinfeng.www.classloaderdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String apkPath = getExternalCacheDir().getAbsolutePath() + "/bundle.apk";
        Log.i("info", "apkPath=" + apkPath);
        loadApk(apkPath);
    }

    private void loadApk(String apkPath) {
        File optFile = getDir("opt", MODE_PRIVATE);
        Log.i("info", "optFile=" + optFile);
        DexClassLoader dexClassLoader = new DexClassLoader(apkPath, optFile.getAbsolutePath(), null, getClassLoader());
        try {
            Class clz = dexClassLoader.loadClass("com.loubinfeng.www.boundle.Printer");
            if (clz != null) {
                Object instance = clz.newInstance();
                Method method = clz.getMethod("print");
                method.invoke(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
