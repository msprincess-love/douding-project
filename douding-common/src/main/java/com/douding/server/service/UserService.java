package com.douding.server.service;

import com.alibaba.fastjson.JSON;
import com.douding.server.domain.User;
import com.douding.server.domain.UserExample;
import com.douding.server.dto.LoginUserDto;
import com.douding.server.dto.ResourceDto;
import com.douding.server.dto.UserDto;
import com.douding.server.dto.PageDto;
import com.douding.server.exception.BusinessException;
import com.douding.server.exception.BusinessExceptionCode;
import com.douding.server.mapper.UserMapper;
import com.douding.server.mapper.my.MyUserMapper;
import com.douding.server.util.CopyUtil;
import com.douding.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MyUserMapper myUserMapper;

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        UserExample userExample = new UserExample();
        List<User> userList = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        pageDto.setTotal(pageInfo.getTotal());
        List<UserDto> userDtoList = CopyUtil.copyList(userList, UserDto.class);
        pageDto.setList(userDtoList);
    }

    public void save(UserDto userDto) {

        User user = CopyUtil.copy(userDto, User.class);

        //判断是新增 还是修改
        if (StringUtils.isEmpty(userDto.getId())) {
            this.insert(user);
        } else {
            this.update(user);
        }

    }

    //新增数据
    private void insert(User user) {


        user.setId(UuidUtil.getShortUuid());
        User udb = this.selectByLoginName(user.getLoginName());
        if(udb != null){
            //对用户名存在 自定异常进行全局捕获(ControllerExceptionHandler中) Aop实现 并且返回了 ResponseDto
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
        }
        userMapper.insert(user);
    }

    //更新数据
    private void update(User user) {
        //更新时 不更新密码 与前端保持一致
        user.setPassword(null);
        //Selective user 中有值在执行update 没有值则不执行更新操作
        userMapper.updateByPrimaryKeySelective(user);
    }

    public void delete(String id) {
        userMapper.deleteByPrimaryKey(id);
    }

    //查询用户名是否存在
    public User selectByLoginName(String loginName){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginNameEqualTo(loginName);
        List<User> users = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }else{
            return users.get(0);
        }
    }

    //更新密码
    public void savePassword(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        userMapper.updateByPrimaryKeySelective(user);
    }

    //登录
    /*
        只用用户名查找 因为用户名也是唯一的
        然后获得user数据后 对比密码是否一致
     */
    public LoginUserDto login(UserDto userDto){

        User user = this.selectByLoginName(userDto.getLoginName());
        if(user == null) {
            LOG.info("用户名不存在{}",userDto.getLoginName());
            //通过日志我们后台可以知道 到底是用户名不对 还是密码不对
            //但是给前端的信息 不要那么准确 防止探测
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }else {
            if (user.getPassword().equals(userDto.getPassword())) {
                /*
                 //不需要把用户的过多信息返回给前端 所以新增一个LoginUserDto 做数据过滤
                return CopyUtil.copy(user, LoginUserDto.class);
                 */
                //登录用户 获得对应的访问权限
                LoginUserDto loginUserDto = CopyUtil.copy(user, LoginUserDto.class);
                setAuth(loginUserDto);
                return loginUserDto;
            }else{
                LOG.info("密码不正确");
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }//end if
    }//end method

    /**
     * 为登录用户读取权限
     */
    private void setAuth(LoginUserDto loginUserDto) {
        List<ResourceDto> resourceDtoList = myUserMapper.findResources(loginUserDto.getId());
        loginUserDto.setResources(resourceDtoList);

        // 整理所有有权限的请求，用于接口拦截
        HashSet<String> requestSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(resourceDtoList)) {
            for (int i = 0, l = resourceDtoList.size(); i < l; i++) {
                ResourceDto resourceDto = resourceDtoList.get(i);
                String arrayString = resourceDto.getRequest();
                List<String> requestList = JSON.parseArray(arrayString, String.class);
                if (!CollectionUtils.isEmpty(requestList)) {
                    requestSet.addAll(requestList);
                }
            }
        }
        LOG.info("有权限的请求：{}", requestSet);
        loginUserDto.setRequests(requestSet);
    }

}//end class
