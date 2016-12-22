package com.jinyuankeji.yxm.findhuo.findwork.hot.hot_detail;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseActivity;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.FindWorkDeclareNewBean;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.declare_new_detail.FindWorkNewDetailActivity;
import com.jinyuankeji.yxm.findhuo.tools.SVL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  yxiaomin on 2016/12/21 0021.
 */

public class FindWorkHotTypeDetailActivity extends BaseActivity {
    private FindWorkHotTypeDetailAdapter mNewAdapter;
    private FindWorkDeclareNewBean mNewBean;
    private List<FindWorkDeclareNewBean> mNewBeanList;
    private SVL mLv;
    private TextView back;

    @Override
    protected int initLayout() {
        return R.layout.activity_findwork_hot_type_detail;
    }

    @Override
    protected void initView() {
mLv = (SVL) findViewById(R.id.lv_findwork_hot_type_detail);
        back = (TextView) findViewById(R.id.tv_findwork_hot_type_detail_back);
    }

    @Override
    protected void initData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mNewAdapter = new FindWorkHotTypeDetailAdapter(this);
        mNewBeanList = new ArrayList<>();
        FindWorkDeclareNewBean newBean = new FindWorkDeclareNewBean();
        for (int i = 0; i < 50; i++) {
            newBean.setRange("大东区");
            newBean.setName("张三");
            newBean.setJob("保洁");
            newBean.setPrice("1000");
            newBean.setImg(R.mipmap.ic_launcher);
            mNewBeanList.add(newBean);
        }
        mNewAdapter.setDatas(mNewBeanList);
        mLv.setAdapter(mNewAdapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FindWorkHotTypeDetailActivity.this, FindWorkNewDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
