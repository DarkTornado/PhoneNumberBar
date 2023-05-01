package com.darktornado.library;

/*
 * PhoneNumberBar.java
 * © 2023 Dark Tornado, All rights reserved.
 * 라이선스 아직 미정
 */

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class PhoneNumberBar extends LinearLayout {

    private final Context ctx;
    private final TextView current, left, right;
    private final SeekBar bar;

    public PhoneNumberBar(Context context) {
        super(context);
        ctx = context;
        current = new TextView(ctx);
        left = new TextView(ctx);
        right = new TextView(ctx);
        bar = new SeekBar(ctx);
        init();
    }

    private void init() {
        setOrientation(1);
        LinearLayout layout = new LinearLayout(ctx);
        layout.setLayoutParams(new LayoutParams(-1, -2));
        layout.setWeightSum(2);

        current.setText("0100000000");
        current.setTextSize(17);
        current.setGravity(Gravity.CENTER);
        int pad = dip2px(10);
        current.setPadding(pad, pad, pad, pad);
        addView(current);

        bar.setMax(99999999);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                current.setText("01" + zero(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        addView(bar);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2, 1);
        int mar = dip2px(5);
        params.setMargins(mar, mar, mar, mar);
        left.setText("01000000000");
        left.setTextSize(14);
        left.setLayoutParams(params);
        left.setGravity(Gravity.LEFT);
        right.setText("01099999999");
        right.setTextSize(14);
        right.setLayoutParams(params);
        right.setGravity(Gravity.RIGHT);

        layout.addView(left);
        layout.addView(right);
        addView(layout);
    }

    private String zero(int number) {
        int leng = String.valueOf(number).length();
        StringBuilder result = new StringBuilder();
        for (int n = 0; n < 9 - leng; n++) {
            result.append("0");
        }
        return result.append(number).toString();
    }

    public void enableLegacyPhoneNumber(boolean enable) {
        if (enable) {
            right.setText("01999999999");
            bar.setMax(999999999);
        } else {
            right.setText("01099999999");
            bar.setMax(99999999);
        }
        bar.setProgress(0);
        current.setText("0100000000");
    }

    public String getPhoneNumber() {
        return current.getText().toString();
    }

    public void setTextSize(float size) {
        current.setTextSize(size);
    }

    public void setTextColor(int color) {
        current.setTextColor(color);
    }

    public void setBottomTextSize(float size) {
        left.setTextSize(size);
        right.setTextSize(size);
    }

    public void setBottomTextColor(int color) {
        left.setTextColor(color);
        right.setTextColor(color);
    }

    private int dip2px(int dips) {
        return (int) Math.ceil(dips * this.getResources().getDisplayMetrics().density);
    }

}
