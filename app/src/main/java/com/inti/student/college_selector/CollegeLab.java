package com.inti.student.college_selector;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WongYeeKhang on 8/11/2017.
 */

public class CollegeLab {
    private static CollegeLab sCollegeLab;

    private List <College> mCollege;

    public static CollegeLab get(Context context){
        if (sCollegeLab == null )
        {
            sCollegeLab = new CollegeLab(context);
        }

        return sCollegeLab;
    }

    private CollegeLab(Context context)
    {
        mCollege = new ArrayList<>();
        for(int i =0;i<110;i++)
        {
            College college = new College();
            mCollege.add(college);
        }
    }

    public List<College> getCrimes(){
        return mCollege;
    }

}
