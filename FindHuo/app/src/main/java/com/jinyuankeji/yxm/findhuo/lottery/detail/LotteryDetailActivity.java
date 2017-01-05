package com.jinyuankeji.yxm.findhuo.lottery.detail;

import android.content.Intent;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;

/**
 * Created by  yxiaomin on 2016/12/20 0020.
 */
public class LotteryDetailActivity extends BaseActivity {
    private RelativeLayout rvPay;
    private ImageView back;


    @Override
    protected int initLayout() {
        return R.layout.activity_lottery_detail;
    }

    @Override
    protected void initView() {
        rvPay = (RelativeLayout) findViewById(R.id.rv_lottery_detail_pay);
        back = (ImageView) findViewById(R.id.lottery_detail_more_back);
    }

    @Override
    protected void initData() {
        rvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LotteryDetailActivity.this, PayExtraActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
