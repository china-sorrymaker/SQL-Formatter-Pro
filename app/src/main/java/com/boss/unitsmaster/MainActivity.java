package com.boss.unitsmaster;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
// 关键：对齐 R 文件
import com.boss.unitsmaster.R;
// 关键：对齐 SQL 格式化库
import com.github.vertical_blank.sqlformatter.SqlFormatter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, initializationStatus -> {});
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        EditText etInput = findViewById(R.id.et_input_sql);
        Button btnFormat = findViewById(R.id.btn_format);
        TextView tvOutput = findViewById(R.id.tv_output_sql);

        btnFormat.setOnClickListener(v -> {
            String rawSql = etInput.getText().toString();
            if (rawSql.isEmpty()) {
                Toast.makeText(this, "Please enter SQL", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                // 执行格式化
                String result = SqlFormatter.format(rawSql);
                tvOutput.setText(result);
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                tvOutput.setText("Format Error");
            }
        });
    }
}
