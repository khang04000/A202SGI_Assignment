package com.inti.student.college_selector;

import android.support.v4.app.Fragment;

/**
 * Created by WongYeeKhang on 15/11/2017.
 */

public class CollegeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CollegeListFragment();
    }
}
