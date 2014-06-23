package com.rtrk.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rtrk.R;

public class FirstTabFragment extends Fragment {
    private TextView mTextView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        if (mTextView == null) {
            mTextView = (TextView) inflater.inflate(R.layout.tab_layout,
                    container, false);
            mTextView.setText(R.string.fragment_first);
        } else {
            ((ViewGroup) mTextView.getParent()).removeView(mTextView);
        }
        return mTextView;
    }
}