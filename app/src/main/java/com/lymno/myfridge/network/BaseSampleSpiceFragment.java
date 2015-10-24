package com.lymno.myfridge.network;

import android.support.v4.app.Fragment;

import com.octo.android.robospice.SpiceManager;

/**
 * Created by Andre on 24.10.2015.
 */
public class BaseSampleSpiceFragment extends Fragment {
    private SpiceManager spiceManager = new SpiceManager(SampleRetrofitSpiceService.class);

    @Override
    public void onStart() {
        spiceManager.start(getActivity());
        super.onStart();
    }

    @Override
    public void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }

}
