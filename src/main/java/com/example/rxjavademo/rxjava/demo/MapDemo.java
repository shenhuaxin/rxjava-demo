package com.example.rxjavademo.rxjava.demo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MapDemo {

    public static void main(String[] args) {

        StudentModel.init();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<Student> students = StudentModel.getStudents();
//                for (Student student : students) {
//                    List<Student.Course> courseList = student.getCourseList();
//                    for (Student.Course course : courseList) {
//                        System.out.println(course);
//                    }
//                }
//            }
//        }).start();


        Observable.fromIterable(StudentModel.getStudents())
                .map(new Function<Student, List<Student.Course>>() {
                    @Override
                    public List<Student.Course> apply(@NonNull Student student) throws Exception {
                        return student.getCourseList();
                    }
                }).subscribe(new Consumer<List<Student.Course>>() {
            @Override
            public void accept(List<Student.Course> courseList) throws Exception {
                for (Student.Course course : courseList) {
                    System.out.println(course);
                }
            }
        });

        Observable.fromIterable(StudentModel.getStudents())
                .flatMap(new Function<Student, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Student student) throws Exception {
                        return Observable.fromIterable(student.getCourseList());
                    }
                }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });


    }


}
