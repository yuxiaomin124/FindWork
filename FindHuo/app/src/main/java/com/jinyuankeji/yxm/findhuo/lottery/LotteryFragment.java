package com.jinyuankeji.yxm.findhuo.lottery;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class LotteryFragment extends BaseFragment {
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;


    @Override
    protected int initLayout() {
        return R.layout.fragment_lottery;
    }

    @Override
    protected void initView() {
        spinner = (Spinner) getView().findViewById(R.id.spinner_lottery);

    }

    @Override
    protected void initData() {
        //数据
        data_list = new ArrayList<String>();
        data_list.add("北京");
        data_list.add("上海");
        data_list.add("广州");
        data_list.add("深圳");

        //适配器
        arr_adapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);

    }
}
