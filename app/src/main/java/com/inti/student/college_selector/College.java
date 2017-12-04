package com.inti.student.college_selector;

/**
 * Created by WongYeeKhang on 1/11/2017.
 */

public class College {

    private String college_name;
    private String course_name;
    private String course_requirement;

    private String course_duration;
    public String getCourse_duration() {
        return course_duration;
    }

    public void setCourse_duration(String course_duration) {
        this.course_duration = course_duration;
    }

    private String course_requirement_grade;
    private long id;

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_requirement() {
        return course_requirement;
    }

    public void setCourse_requirement(String course_requirement) {
        this.course_requirement = course_requirement;
    }

    public String getCourse_requirement_grade() {
        return course_requirement_grade;
    }

    public void setCourse_requirement_grade(String course_requirement_grade) {
        this.course_requirement_grade = course_requirement_grade;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

}
