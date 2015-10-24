package com.lymno.myfridge.barcode_scanner;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewGroup;

import com.google.zxing.Result;
import com.lymno.myfridge.R;
import com.lymno.myfridge.activity.ProductAdd;
import com.lymno.myfridge.fragments.TwoButtonsFragment;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScannerFragmentActivity extends ActionBarActivity {

    @Bind(R.id.bar_code_sliding_layout)
    protected SlidingUpPanelLayout mSlidingUpPanelLayout;

    @Bind(R.id.container_for_fragments)
    protected ViewGroup mContainerForFragments;

    @Override
    public void onBackPressed() {
        if (mSlidingUpPanelLayout.getPanelState()!= SlidingUpPanelLayout.PanelState.COLLAPSED){
            closeFragment();
            return;
        }
        super.onBackPressed();
    }

    public void handleResult(Result rawResult) {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {}
        Intent intent = new Intent(this, ProductAdd.class);
        intent.putExtra("barcode", rawResult.getText());
        startActivity(intent);
    }

    public void openFragment(Fragment fragment){
        mSlidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_for_fragments, fragment)
                .commit();
    }

    public void closeFragment(){
        mSlidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_for_fragments,new TwoButtonsFragment())
                .commit();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.barcode_scanner);
        ButterKnife.bind(this);
        mSlidingUpPanelLayout.setTouchEnabled(false);
        closeFragment();
    }
}