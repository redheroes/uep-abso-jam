package pl.redexperts.uepabsojam;

import android.graphics.Color;
import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import pl.redexperts.uepabsojam.fragments.FragmentJams;


public class MainActivity extends MaterialNavigationDrawer {


    @Override
    public void init(Bundle bundle) {

        MaterialAccount account = new MaterialAccount(this.getResources(),"Nazwa","nazwa@gmail.com",R.mipmap.user,R.mipmap.mat2);
        this.addAccount(account);

        // create sections
        this.addSection(newSection("Lista Jamów", new FragmentJams()));
        this.addSection(newSection("Section 2",new TestFragment()));
        this.addSection(newSection("Section 3", R.mipmap.ic_android_grey600_24dp,new TestFragment()).setSectionColor(Color.parseColor("#9c27b0")));
        this.addSection(newSection("Section",R.mipmap.ic_android_grey600_24dp,new TestFragment()).setSectionColor(Color.parseColor("#03a9f4")));

    }
}
