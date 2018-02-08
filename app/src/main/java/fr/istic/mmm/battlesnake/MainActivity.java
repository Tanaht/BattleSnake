package fr.istic.mmm.battlesnake;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.istic.mmm.battlesnake.fragments.AboutFragment;
import fr.istic.mmm.battlesnake.fragments.HomeFragment;
import fr.istic.mmm.battlesnake.fragments.ProfileFragment;
import fr.istic.mmm.battlesnake.fragments.RankingFragment;

public class MainActivity extends AppCompatActivity implements
        HomeFragment.OnFragmentInteractionListener,
        AboutFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        RankingFragment.OnFragmentInteractionListener {
    private HomeFragment home;
    private String Tag = "fr.istic.mmm.battlesnake.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            home = new HomeFragment();
            fragmentTransaction.add(R.id.base_layout, new HomeFragment()).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
