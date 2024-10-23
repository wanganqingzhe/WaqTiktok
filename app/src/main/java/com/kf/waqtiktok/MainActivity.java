package com.kf.waqtiktok;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kf.waqtiktok.databinding.ActivityMainBinding;
import com.kf.waqtiktok.mvvm.base.BaseActivity;
import com.kf.waqtiktok.mvvm.base.BaseViewActivity;
import com.kf.waqtiktok.mvvm.fragment.FriendsFragment;
import com.kf.waqtiktok.mvvm.fragment.HomeFragment;
import com.kf.waqtiktok.mvvm.fragment.MessageFragment;
import com.kf.waqtiktok.mvvm.fragment.MineFragment;

import java.util.ArrayList;

public class MainActivity extends BaseViewActivity<ActivityMainBinding> {


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        //给vp 添加4个 Fragment
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new FriendsFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MineFragment());
        //给  vp 设置适配器
        binding.vp.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });

        //给 vp2添加底部联动
        binding.vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                switch (position){
                    case 0:
                        binding.nav.setSelectedItemId(R.id.home);
                        break;

                    case 1:
                        binding.nav.setSelectedItemId(R.id.friend);
                        break;
                    case 2:
                        binding.nav.setSelectedItemId(R.id.message);
                        break;
                    case 3:
                        binding.nav.setSelectedItemId(R.id.mine);
                        break;


                }

            }
        });
        //设置  底部  Bottom
        binding.nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId()==R.id.home){
                    binding.vp.setCurrentItem(0);
                }
                if (item.getItemId()==R.id.friend){

                    binding.vp.setCurrentItem(1);
                }
                if (item.getItemId()==R.id.publish){
                    ToastUtils.showShort("点击发布");
                }
                if (item.getItemId()==R.id.message){
                    binding.vp.setCurrentItem(2);
                }
                if (item.getItemId()==R.id.mine){
                    binding.vp.setCurrentItem(3);
                }

                return true;
            }
        });










    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }
}