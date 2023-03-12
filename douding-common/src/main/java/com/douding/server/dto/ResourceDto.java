package com.douding.server.dto;


import java.util.List;

public class ResourceDto {

    /**
    * id
    */
    private String id;

    /**
    * 名称|菜单或按钮
    */
    private String name;

    /**
    * 页面|路由
    */
    private String page;

    /**
    * 请求|接口
    */
    private String request;

    @Override
    public String toString() {
        return "ResourceDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", page='" + page + '\'' +
                ", request='" + request + '\'' +
                ", parent='" + parent + '\'' +
                ", children=" + children +
                '}';
    }

    /**
    * 父id
    */
    private String parent;

    public List<ResourceDto> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceDto> children) {
        this.children = children;
    }

    private List<ResourceDto> children;

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getName() {
    return name;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getPage() {
    return page;
    }

    public void setPage(String page) {
    this.page = page;
    }

    public String getRequest() {
    return request;
    }

    public void setRequest(String request) {
    this.request = request;
    }

    public String getParent() {
    return parent;
    }

    public void setParent(String parent) {
    this.parent = parent;
    }


}