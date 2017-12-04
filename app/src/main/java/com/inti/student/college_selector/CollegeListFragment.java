package com.inti.student.college_selector;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by WongYeeKhang on 15/11/2017.
 */

public class CollegeListFragment extends Fragment {


    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private CollegesDataSource datasource;

    private List<College> mColleges;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_college_list,container,false);

        final String name = getActivity().getIntent().getStringExtra("name");
        final String age = getActivity().getIntent().getStringExtra("age");
        final String course_type = getActivity().getIntent().getStringExtra("course_type");

        final TextView display_user_name = (TextView) view.findViewById(R.id.display_user_name);
        final TextView display_user_age = (TextView) view.findViewById(R.id.display_user_age);

        display_user_name.setText(name);
        display_user_age.setText(age);

        Log.d("name","myname"+name);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //updateUI();

        datasource = new CollegesDataSource(getActivity());
        datasource.open();

        //mColleges = datasource.getAllComments();
        mColleges = datasource.get_searchCollege(course_type);
        mAdapter = new CrimeAdapter(mColleges);

        mCrimeRecyclerView.setAdapter(mAdapter);

        return view;
    }



    private class CrimeHolder extends RecyclerView.ViewHolder{

        private LinearLayout list_linear;
        private TextView list_college_name,list_course_name,list_course_duration,list_course_requirement,list_course_requirement_grade;
        ImageView img1;

        public CrimeHolder(View itemView){
            super(itemView);

            list_linear = (LinearLayout) itemView.findViewById(R.id.list_linear);
            list_college_name = (TextView) itemView.findViewById(R.id.list_college_name);
            list_course_name = (TextView) itemView.findViewById(R.id.list_course_name);
            list_course_duration = (TextView) itemView.findViewById(R.id.list_course_duration);
//            list_course_requirement = (TextView) itemView.findViewById(R.id.list_course_requirement);
//            list_course_requirement_grade = (TextView) itemView.findViewById(R.id.list_course_requirement_grade);
            img1 = (ImageView) itemView.findViewById(R.id.img_view);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

            private List<College> mColleges;

            public CrimeAdapter(List<College>crimes){
                mColleges = crimes;
            }

            @Override
            public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType){
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view = layoutInflater.inflate(R.layout.list_item_college,parent,false);
                return new CrimeHolder(view);
            }

            @Override
            public void onBindViewHolder(CrimeHolder holder, int position) {
                final College college = mColleges.get(position);

                holder.list_college_name.setText(college.getCollege_name());
                holder.list_course_name.setText(college.getCourse_name());
                holder.list_course_duration.setText(college.getCourse_duration());
//                holder.list_course_requirement.setText(college.getCourse_requirement());
//                holder.list_course_requirement_grade.setText(college.getCourse_requirement_grade());

                Log.d("check3","college_name:"+college.getCollege_name());
                if(college.getCollege_name().equals("KDU Penang University College"))
                {
                    holder.img1.setImageResource(R.drawable.kdu);
                }

                else if(college.getCollege_name().equals("INTI International College Penang"))
                {
                    holder.img1.setImageResource(R.drawable.inti);
                }

                else if(college.getCollege_name().equals("SEGi College Penang"))
                {
                    holder.img1.setImageResource(R.drawable.segi);
                }

                else if(college.getCollege_name().equals("Tunku Abdul Rahman University College Penang"))
                {
                    holder.img1.setImageResource(R.drawable.tarc);
                }


                holder.list_linear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Dialog dialog = new Dialog(getContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_college_detail);

                        final TextView dialog_college_name = (TextView) dialog.findViewById(R.id.dialog_college_name);
                        final TextView dialog_coursename = (TextView) dialog.findViewById(R.id.dialog_course_name);
                        final TextView dialog_requirement = (TextView) dialog.findViewById(R.id.dialog_requirement);
                        final TextView dialog_requirement_grade = (TextView) dialog.findViewById(R.id.dialog_requirement_grade);
                        final TextView dialog_duration = (TextView) dialog.findViewById(R.id.dialog_duration);
                        final ImageView dialog_image = (ImageView) dialog.findViewById(R.id.dialog_image);


                        dialog_college_name.setText(college.getCollege_name());
                        dialog_coursename.setText(college.getCourse_name());
                        dialog_requirement.setText(college.getCourse_requirement());
                        dialog_requirement_grade.setText(college.getCourse_requirement_grade());
                        dialog_duration.setText(college.getCourse_duration());


                        if(college.getCollege_name().equals("KDU Penang University College"))
                        {
                            dialog_image.setImageResource(R.drawable.kdu2);
                        }

                        else if(college.getCollege_name().equals("INTI International College Penang"))
                        {
                            dialog_image.setImageResource(R.drawable.inti2);
                        }

                        else if(college.getCollege_name().equals("SEGi College Penang"))
                        {
                            dialog_image.setImageResource(R.drawable.segi2);
                        }

                        else if(college.getCollege_name().equals("Tunku Abdul Rahman University College Penang"))
                        {
                            dialog_image.setImageResource(R.drawable.tarc2);
                        }

                        //set the dialog width and height
                        dialog.getWindow().setLayout(
                                (int) (getScreenWidth(getActivity()) * 1.0), ViewGroup.LayoutParams.WRAP_CONTENT
                        );

                        dialog.show();
                    }
                });
            }

            @Override
            public int getItemCount(){
                return mColleges.size();
            }
    }

    private void updateUI(){
        CollegeLab collegeLab = CollegeLab.get(getActivity());
        List<College> colleges = collegeLab.getCrimes();

        mAdapter = new CrimeAdapter(colleges);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    public static int getScreenWidth(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }


}

