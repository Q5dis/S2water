package com.olcp2.s2water__bw;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainPageManager extends Fragment {

    private ViewPager viewPager;
    private MainPageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Layout을 inflate함
        View view = inflater.inflate(R.layout.fragment_main_page_manager, container, false);

        // ViewPager를 찾음
        viewPager = view.findViewById(R.id.viewPager);

        // FragmentManager와 함께 MainPageAdapter 설정
        adapter = new MainPageAdapter(getChildFragmentManager());

        // ViewPager에 adapter 설정
        viewPager.setAdapter(adapter);

        return view; // 설정 완료된 view 반환
    }

    // FragmentPagerAdapter 클래스 정의
    private class MainPageAdapter extends FragmentPagerAdapter {

        public MainPageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            // position에 따라 각 Fragment 반환
            switch (position) {
                case 0:
                    return new WaterCheckFragment(); // 첫 번째 페이지: SugarCheckFragment
                case 1:
                    return new SugarCheckFragment(); // 두 번째 페이지: WaterCheckFragment
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2; // 총 2개의 페이지
        }
    }
}
