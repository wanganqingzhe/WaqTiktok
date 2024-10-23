package com.kf.waqtiktok.mvvm.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.kf.waqtiktok.R;
import com.kf.waqtiktok.databinding.FragmentHomeBinding;
import com.kf.waqtiktok.mvvm.base.BaseViewFragment;
import com.kf.waqtiktok.mvvm.entity.AboutEntity;
import com.kf.waqtiktok.mvvm.fragment.home.ConcernFragment;
import com.kf.waqtiktok.mvvm.fragment.home.MallFragment;
import com.kf.waqtiktok.mvvm.recommend.RecommendFragment;

import java.util.ArrayList;


/**
   * 作者： 王安卿
   * 日期： 2024/8/20 17:46
   * 功能：  首页面
   */



public class HomeFragment extends BaseViewFragment<FragmentHomeBinding> {


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new ConcernFragment());
        fragments.add(new MallFragment());



        //天机适配器
        binding.vp.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return  fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });

        //设置关联
        binding.vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.tab.setCurrentTab(position);
            }
        });

        ArrayList<CustomTabEntity> list = new ArrayList<>();
        list.add(new AboutEntity("推荐"));
        list.add(new AboutEntity("关注"));
        list.add(new AboutEntity("商场"));
        //设置值
        binding.tab.setTabData(list);

        //设置关联
        binding.tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                binding.vp.setCurrentItem(position);

            }

            @Override
            public void onTabReselect(int position) {

            }
        });







    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home;
    }
}