package com.jinyuankeji.yxm.findhuo.lottery;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseFragment;

import com.jinyuankeji.yxm.findhuo.lottery.detail.LotteryDetailActivity;
import com.jinyuankeji.yxm.findhuo.lottery.more.MoreActivity;
import com.jinyuankeji.yxm.findhuo.tools.MyRVOnClickListener;
import com.jinyuankeji.yxm.findhuo.tools.SVR;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yxiaomin on 2016/12/19 0019.
 */

public class LotteryFragment extends BaseFragment {
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    private ViewPager viewPagerBanner;
    private SVR svAndRV;
    private TextView tvMore;
    private LinearLayout llTip;
    private LotteryViewPagerAdapter myAdapter;
    private ArrayList<Integer> images;
    private Handler handler;
    private boolean flag = true;
    private boolean mm = true;
    private ImageView[] tips;

    private LotteryStationRVAdapter rvAdapter;
    private LotteryStationBean stationBean;
    private ArrayList<LotteryStationBean> beanArrayList;
    private ScrollView scrollView;


    @Override
    protected int initLayout() {
        return R.layout.fragment_lottery;
    }

    @Override
    protected void initView() {
        spinner = (Spinner) getView().findViewById(R.id.spinner_lottery);
        viewPagerBanner = (ViewPager) getView().findViewById(R.id.view_pager_lottery);
        svAndRV = (SVR) getView().findViewById(R.id.rv_lottery);
        tvMore = (TextView) getView().findViewById(R.id.tv_lottery_tv_more);
        llTip = (LinearLayout) getView().findViewById(R.id.ll_lottery);
        scrollView = (ScrollView) getView().findViewById(R.id.sv_lottery);

    }

    @Override
    protected void initData() {
        scrollView.scrollTo(0,0);
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
        images.add(0, R.mipmap.btn_pay_selected3x);
        images.add(1, R.mipmap.ic_launcher);
        images.add(2, R.mipmap.btn_pay_selected3x);
        initViewPager();

        rvAdapter = new LotteryStationRVAdapter(getActivity());
        beanArrayList = new ArrayList<>();
        svAndRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));
        LotteryStationBean bean = new LotteryStationBean();
        for (int i = 0; i < 50; i++) {
            bean.setAddr("大东区");
            bean.setNamr("张三");
            bean.setImg(R.mipmap.btn_pay_selected3x);
            beanArrayList.add(bean);
        }
        rvAdapter.setStationBeanList(beanArrayList);
        svAndRV.setAdapter(rvAdapter);
        rvAdapter.setMyRvListener(new MyRVOnClickListener() {
            @Override
            public void onClick(int position) {
               Intent intent = new Intent(getActivity(),LotteryDetailActivity.class);
                startActivity(intent);

            }
        });

        Log.d("卡卡卡卡卡", "tvMore:" + tvMore);
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "dianji", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MoreActivity.class);
               startActivity(intent);
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
