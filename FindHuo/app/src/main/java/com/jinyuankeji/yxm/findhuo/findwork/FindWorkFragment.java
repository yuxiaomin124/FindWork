package com.jinyuankeji.yxm.findhuo.findwork;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseFragment;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.FindWorkDeclareNewAdapter;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.FindWorkDeclareNewBean;
import com.jinyuankeji.yxm.findhuo.findwork.declare_new.declare_new_detail.FindWorkNewDetailActivity;
import com.jinyuankeji.yxm.findhuo.findwork.hot.FindWorkHotAdapter;
import com.jinyuankeji.yxm.findhuo.findwork.hot.FindWorkHotBean;
import com.jinyuankeji.yxm.findhuo.findwork.hot.hot_detail.FindWorkHotTypeDetailActivity;
import com.jinyuankeji.yxm.findhuo.lottery.LotteryViewPagerAdapter;
import com.jinyuankeji.yxm.findhuo.tools.SVG;
import com.jinyuankeji.yxm.findhuo.tools.SVL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxiaomin on 2016/12/19 0019.
 */

public class FindWorkFragment extends BaseFragment {
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    private ViewPager viewPagerBanner;

    private LinearLayout llTip;
    private LotteryViewPagerAdapter myAdapter;
    private ArrayList<Integer> images;
    private Handler handler;
    private boolean flag = true;
    private boolean mm = true;
    private ImageView[] tips;

    private ScrollView scrollView;

    private FindWorkDeclareNewAdapter mNewAdapter;
    private FindWorkDeclareNewBean mNewBean;
    private List<FindWorkDeclareNewBean> mNewBeanList;
    private SVL mLv;

    private FindWorkHotAdapter mHotAdapter;
    private List<FindWorkHotBean> mHotBeanList;
    private SVG mGv;

    private String str[] = {"技工", "促销导购", "钟点工", "家教", "翻译", "送货", "心理咨询", "其他"};

    private TextView tvFindWork,tvDoWork;


    @Override
    protected int initLayout() {
        return R.layout.fragment_findwork;
    }

    @Override
    protected void initView() {
        spinner = (Spinner) getView().findViewById(R.id.spinner_findwork);
        viewPagerBanner = (ViewPager) getView().findViewById(R.id.view_pager_findworke);
        llTip = (LinearLayout) getView().findViewById(R.id.ll_findworke);
        scrollView = (ScrollView) getView().findViewById(R.id.sv_findworke);
        mLv = (SVL) getView().findViewById(R.id.lv_findwrke_decalre_new);
        mGv = (SVG) getView().findViewById(R.id.gv_findworke_hot);
        tvFindWork = (TextView) getView().findViewById(R.id.tv_findwork_find);
        tvDoWork = (TextView) getView().findViewById(R.id.tv_findwork_do);

    }

    @Override
    protected void initData() {
        scrollView.scrollTo(0, 0);
        //数据
        data_list = new ArrayList<String>();
        data_list.add("北京");
        data_list.add("上海");
        data_list.add("广州");
        data_list.add("深圳");
        //适配器
        arr_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);

        myAdapter = new LotteryViewPagerAdapter(getActivity());
        images = new ArrayList<>();
        images.add(0, R.mipmap.ic_launcher);
        images.add(1, R.mipmap.ic_launcher);
        images.add(2, R.mipmap.ic_launcher);
        initViewPager();


        mNewAdapter = new FindWorkDeclareNewAdapter(getActivity());
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
                Intent intent = new Intent(getActivity(), FindWorkNewDetailActivity.class);
                startActivity(intent);
            }
        });

        mHotAdapter = new FindWorkHotAdapter(getActivity());
        mHotBeanList = new ArrayList<>();
        FindWorkHotBean hotBean = new FindWorkHotBean();
        for (int i = 0; i < str.length; i++) {
            hotBean.setName(str[i]);
            Log.d("FindWorkFragment", hotBean.getName());
            mHotBeanList.add(hotBean);

        }

        mHotAdapter.setDatas(mHotBeanList);
        mGv.setAdapter(mHotAdapter);
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), FindWorkHotTypeDetailActivity.class);
                startActivity(intent);
            }
        });

        tvDoWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDoWork.setBackgroundResource(R.mipmap.btn_pay_selected3x);
                tvFindWork.setBackgroundResource(R.drawable.shap_findwork_select);
                tvDoWork.setTextColor(Color.WHITE);
                tvFindWork.setTextColor(Color.BLACK);
            }
        });

        tvFindWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvFindWork.setBackgroundResource(R.mipmap.btn_pay_selected3x);
                tvDoWork.setBackgroundResource(R.drawable.shap_findwork_select);
                tvDoWork.setTextColor(Color.BLACK);
                tvFindWork.setTextColor(Color.WHITE);
            }
        });
    }


    private void initViewPager() {
        myAdapter.setImages(images);
        myAdapter.setViewPager(viewPagerBanner);
        viewPagerBanner.setAdapter(myAdapter);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                viewPagerBanner.setCurrentItem(viewPagerBanner.getCurrentItem() + 1);
                return false;
            }
        });


        if (mm) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (flag) {

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        handler.sendEmptyMessage(0);

                    }
                }
            }).start();

            mm = false;
        }

        tips = new ImageView[images.size()];

        for (int i = 0; i < images.size(); i++) {

            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(10, 10));

            tips[i] = imageView;

            if (i == 0) {
                imageView.setImageResource(R.mipmap.fri3x);
            } else {
                imageView.setImageResource(R.mipmap.sec3x);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);

            layoutParams.leftMargin = 7;
            layoutParams.rightMargin = 7;

            llTip.addView(imageView, layoutParams);
        }

        myAdapter.setTips(tips);
    }
}
