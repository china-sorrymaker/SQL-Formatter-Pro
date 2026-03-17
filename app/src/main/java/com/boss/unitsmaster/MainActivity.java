package com.boss.unitsmaster;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// 广告相关
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

// SQL 格式化相关
import com.github.verticalblank.sqlformatter.SqlFormatter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- 1. 广告逻辑 (完璧归赵) ---
        MobileAds.initialize(this, initializationStatus -> {});
        AdView mAdView = findViewById(R.id.adView);
        if (mAdView != null) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        // --- 2. SQL 逻辑 (核心功能) ---
        EditText etInput = findViewById(R.id.et_input_sql);
        Button btnFormat = findViewById(R.id.btn_format);
        TextView tvOutput = findViewById(R.id.tv_output_sql);

        btnFormat.setOnClickListener(v -> {
            String rawSql = etInput.getText().toString().trim();
            if (rawSql.isEmpty()) {
                // 调用 strings.xml 里的 msg_empty
                Toast.makeText(this, getString(R.string.msg_empty), Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                String result = SqlFormatter.format(rawSql);
                tvOutput.setText(result);
                // 成功提示也用多语言
                Toast.makeText(this, getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                tvOutput.setText(getString(R.string.msg_error));
            }
        });
    }
}