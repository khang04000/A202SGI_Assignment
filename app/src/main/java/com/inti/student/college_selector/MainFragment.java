package com.inti.student.college_selector;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by WongYeeKhang on 30/11/2017.
 */

public class MainFragment extends Fragment {

    String education_level;
    private CollegesDataSource datasource;
    College college = null;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_main,container,false);



        datasource = new CollegesDataSource(getActivity());
        datasource.open();

        //Log.d("check12","data:"+datasource.checkRecord());
        if(datasource.checkRecord()==false){

            //Computing course
            datasource.insertRecord("KDU Penang University College","Bachelor of Software Engineering (Hons)","Computing & IT","Relevant Diploma","CGPA 2.5","2.5");
            datasource.insertRecord("SEGi College Penang","MSc (Information Technology) by Research","Computing & IT","Degree","CGPA 2.5","2");
            datasource.insertRecord("INTI International College Penang","Diploma in Information & Communications Technology","Computing & IT","SPM/ O Level","5 Credits","2");
            datasource.insertRecord("KDU Penang University College","Bachelor of Information Technology (Hons) in Internet Technology","Computing & IT","Relevant Diploma","CGPA 2.5","3");

            //Business course
            datasource.insertRecord("INTI International College Penang","Foundation in Business Information Technology","Business","SPM/ O Level","5 Credits","1");
            datasource.insertRecord("INTI International College Penang","Master of Business Administration (MBA) Global Business","Business","Bachelor's Degre","CGPA 2.5","1");
            datasource.insertRecord("SEGi College Penang","Bachelor of Information Systems (Hons) in Enterprise Information Systems","Business","Relevant Diploma","CGPA 2.0","2");
            datasource.insertRecord("SEGi College Penang","Bachelor of Arts (Hons) Accounting and Finance","Business","Diploma","CGPA 2.0","3");

            //Enginerring
            datasource.insertRecord("Tunku Abdul Rahman University College Penang","Diploma in Technology (Electronic Engineering)","Engineering","SPM","5 Credits","2");
            datasource.insertRecord("Tunku Abdul Rahman University College Penang","Bachelor of Engineering (Honours) Electrical and Electronics","Engineering","Relevant Diploma","CGPA 2.5","4");
            datasource.insertRecord("KDU Penang University College","BACHELOR OF ENGINEERING (HONS) IN MECHATRONICS ENGINEERING","Engineering","Relevant Diploma","CGPA 2.0","2");
            datasource.insertRecord("KDU Penang University College","DOCTOR OF PHILOSOPHY IN ENGINEERING (PHD)","Engineering","Master Degree","","3-6");

            //Hospitality & Culinary
            datasource.insertRecord("INTI International College Penang","Diploma in Hotel Management","Hospitality & Culinary Arts","SPM","3Cs","2");
            datasource.insertRecord("INTI International College Penang","Diploma in Culinary Arts","Hospitality & Culinary Arts","SPM","3Cs","2");
            datasource.insertRecord("SEGi College Penang","Bachelor of Hospitality Management (Hons)","Hospitality & Culinary Arts","Relevant Diploma","CGPA 2.5","3");
            datasource.insertRecord("SEGi College Penang","Diploma in Baking & Pastry Arts","Hospitality & Culinary Arts","SPM","3 Credits","2.5");
        }

        Button search_btn = (Button) v.findViewById(R.id.search_btn);
        final Button course_it = (Button) v.findViewById(R.id.course_it);
        final Button course_engineering = (Button) v.findViewById(R.id.course_engineering);
        final Button course_business = (Button) v.findViewById(R.id.course_business);
        final Button course_hospitality = (Button) v.findViewById(R.id.course_hospitality);
        final TextView course_selected = (TextView) v.findViewById(R.id.course_selected);


        course_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course_selected.setText("Computing & IT");
                course_it.setTextColor((Color.parseColor("#000000")));
                course_engineering.setTextColor((Color.parseColor("#808080")));
                course_business.setTextColor((Color.parseColor("#808080")));
                course_hospitality.setTextColor((Color.parseColor("#808080")));
            }
        });

        course_engineering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course_selected.setText("Engineering");
                course_engineering.setTextColor((Color.parseColor("#000000")));
                course_it.setTextColor((Color.parseColor("#808080")));
                course_business.setTextColor((Color.parseColor("#808080")));
                course_hospitality.setTextColor((Color.parseColor("#808080")));
            }
        });

        course_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course_selected.setText("Business");
                course_business.setTextColor((Color.parseColor("#000000")));
                course_engineering.setTextColor((Color.parseColor("#808080")));
                course_it.setTextColor((Color.parseColor("#808080")));
                course_hospitality.setTextColor((Color.parseColor("#808080")));
            }
        });

        course_hospitality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course_selected.setText("Hospitality & Culinary Arts");
                course_hospitality.setTextColor((Color.parseColor("#000000")));
                course_engineering.setTextColor((Color.parseColor("#808080")));
                course_business.setTextColor((Color.parseColor("#808080")));
                course_it.setTextColor((Color.parseColor("#808080")));
            }
        });



        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user_name =(EditText) getActivity().findViewById(R.id.user_name);
                EditText user_age =(EditText) getActivity().findViewById(R.id.user_age);

                String name = user_name.getText().toString().trim();
                String age = user_age.getText().toString().trim();
                String course_type = course_selected.getText().toString().trim();

                if(name.equals("") || age.equals("") || course_type.equals("")) {
                    if (name.equals("")) {
                        Toast.makeText(getActivity(), "Please insert name field", Toast.LENGTH_SHORT).show();
                    }

                    if (age.equals("")) {
                        Toast.makeText(getActivity(), "Please insert age field", Toast.LENGTH_SHORT).show();
                    }

                    if (course_type.equals("None")) {
                        Toast.makeText(getActivity(), "Please select course", Toast.LENGTH_SHORT).show();
                    }
                }

                else {
                    Intent mainIntent = new Intent(getActivity(), CollegeListActivity.class);
                    mainIntent.putExtra("name", name);
                    mainIntent.putExtra("age", age);
                    mainIntent.putExtra("course_type", course_type);

                    startActivity(mainIntent);
                }
            }
        });

        return v;
    }
}
