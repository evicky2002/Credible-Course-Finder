package com.example.crediblecoursefinder;

import androidx.annotation.NonNull;

public class CourseViewModel {
    String title;
    Double ratings;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public CourseViewModel(String title, Double ratings) {
        this.title = title;
        this.ratings = ratings;
    }

    @NonNull
    @Override
    public String toString() {
        return "Title : "+title+",\n"+"ratings : "+ratings+",\n";
    }
}
