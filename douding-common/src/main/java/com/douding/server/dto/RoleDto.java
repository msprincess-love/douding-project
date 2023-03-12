package com.douding.server.dto;


import java.util.List;

public class RoleDto {

    /**
     * id
     */
    private String id;

    public List<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    private List<String> resourceIds;

    private List<String> userIds;

    @Override
    public String toString() {
        return "RoleDto{" +
                "id='" + id + '\'' +
                ", resourceIds=" + resourceIds +
                ", userIds=" + userIds +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    /**
     * 角色
     */
    private String name;

    /**
     * 描述
     */
    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}