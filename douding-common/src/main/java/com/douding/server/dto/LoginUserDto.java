package com.douding.server.dto;


import java.util.HashSet;
import java.util.List;

public class LoginUserDto {

    /**
    * id
    */
    private String id;

    /**
    * 登陆名
    */
    private String loginName;

    /**
    * 昵称
    */
    private String name;

    public List<ResourceDto> getResources() {
        return resources;
    }

    public void setResources(List<ResourceDto> resources) {
        this.resources = resources;
    }

    //所有资源 用于前端界面权限控制
    private List<ResourceDto> resources;

    public HashSet<String> getRequests() {
        return requests;
    }

    public void setRequests(HashSet<String> requests) {
        this.requests = requests;
    }

    //所有资源中的请求 用户后端接口拦截
    private HashSet<String> requests;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 登陆凭证
     */
    private String token;


    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getLoginName() {
    return loginName;
    }

    public void setLoginName(String loginName) {
    this.loginName = loginName;
    }

    public String getName() {
    return name;
    }

    public void setName(String name) {
    this.name = name;
    }

    @Override
    public String toString() {
        return "LoginUserDto{" +
                "id='" + id + '\'' +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", resources=" + resources +
                ", requests=" + requests +
                ", token='" + token + '\'' +
                '}';
    }
}