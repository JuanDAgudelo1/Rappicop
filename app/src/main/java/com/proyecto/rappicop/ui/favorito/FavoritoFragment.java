package com.proyecto.rappicop.ui.favorito;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.fragmento.FragmentoAdaptador;

public class FavoritoFragment extends Fragment {


    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FragmentoAdaptador fragmentoAdaptador;

    private FragmentActivity myContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_favorito,container, false);

        tabLayout = root.findViewById(R.id.tab_layout);
        viewPager2 = root.findViewById(R.id.view_pager2);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        fragmentoAdaptador = new FragmentoAdaptador(fm,getLifecycle());

        viewPager2.setAdapter(fragmentoAdaptador);

        tabLayout.addTab(tabLayout.newTab().setText("Destacados"));
        tabLayout.addTab(tabLayout.newTab().setText("Populares"));
        tabLayout.addTab(tabLayout.newTab().setText("Nuevo"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        return root;
    }


}