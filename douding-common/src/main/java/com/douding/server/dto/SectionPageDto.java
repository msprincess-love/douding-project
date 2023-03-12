package com.douding.server.dto;

public class SectionPageDto extends PageDto{
    @Override
    public String toString() {
        return "SectionPageDto{" +
                "page=" + page +
                ", size=" + size +
                ", total=" + total +
                ", list=" + list +
                ", courseId='" + courseId + '\'' +
                ", chapterId='" + chapterId + '\'' +
                '}';
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    private String courseId;
    private String chapterId;

}
