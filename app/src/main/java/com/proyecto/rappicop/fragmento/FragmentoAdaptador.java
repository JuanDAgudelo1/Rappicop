package com.proyecto.rappicop.fragmento;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentoAdaptador extends FragmentStateAdapter {

    public FragmentoAdaptador(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new SegundoFragment();
            case 2:
                return new FavoritoNuevoFragment();
        }

        return new PrimerFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
