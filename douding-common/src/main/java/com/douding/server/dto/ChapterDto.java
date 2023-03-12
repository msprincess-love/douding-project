package com.douding.server.dto;
/*
    dommain 与 dto的区别
    1.domain 中的实体类 是与数据库表 字段一一对应 通过mybeatis自动生成的 不要修改,也不允许修改,就是自动生成什么样,就让他一直保持什么样
    2.dto中的 实体类与domain中的对应, dto层可以根据程序员自己意愿增加修改字段数据,方便业务逻辑
 */
public class ChapterDto {
    private String id;

    private String courseId;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courseId=").append(courseId);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}