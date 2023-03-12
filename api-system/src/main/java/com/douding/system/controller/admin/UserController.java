package com.douding.system.controller.admin;




import com.douding.server.dto.*;
import com.douding.server.service.UserService;
import com.douding.server.util.UuidUtil;
import com.douding.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSON;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    //给了日志用的
    public  static final String BUSINESS_NAME ="用户";

    @Resource
    private UserService userService;

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/list")
    public ResponseDto list(PageDto pageDto){

        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        userService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody UserDto userDto){

        //对用户密码进行MD5加密 1.前端无加密 2.前端已加密 不管哪种情况都进行后台再加密
        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        //field.length?c freemarker语法 c表示把数字转成字符串
                    ValidatorUtil.require(userDto.getLoginName(), "登陆名");
                    ValidatorUtil.length(userDto.getLoginName(), "登陆名", 1, 50);
                    ValidatorUtil.length(userDto.getName(), "昵称", 1, 50);
                    ValidatorUtil.require(userDto.getPassword(), "密码");
        ResponseDto<UserDto> responseDto = new ResponseDto<>();
        userService.save(userDto);
        responseDto.setContent(userDto);
        return responseDto;
    }
//    更新密码
    @PostMapping("/save-password")
    public ResponseDto savePassword(@RequestBody UserDto userDto){


        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));

        ValidatorUtil.require(userDto.getPassword(), "密码");

        ResponseDto<UserDto> responseDto = new ResponseDto<>();
        userService.savePassword(userDto);
        responseDto.setContent(userDto);
        return responseDto;
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){

        ResponseDto<UserDto> responseDto = new ResponseDto<>();
        userService.delete(id);
        return responseDto;
    }


    @PostMapping("/login")
    public ResponseDto login(@RequestBody UserDto userDto,HttpServletRequest request){


        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));

        ValidatorUtil.require(userDto.getPassword(), "密码");

        ResponseDto<LoginUserDto> responseDto = new ResponseDto<>();

        /*
        // 根据验证码token去获取缓存中的验证码，和用户输入的验证码是否一致
        String imageCode = (String) redisTemplate.opsForValue().get(userDto.getImageCodeToken());
        LOG.info("从redis中获取到的验证码：{}", imageCode);
        if (StringUtils.isEmpty(imageCode)) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码已过期");
            LOG.info("用户登录失败，验证码已过期");
            return responseDto;
        }
        if (!imageCode.toLowerCase().equals(userDto.getImageCode().toLowerCase())) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码不对");
            LOG.info("用户登录失败，验证码不对");
            return responseDto;
        } else {
            // 验证通过后，移除验证码
            redisTemplate.delete(userDto.getImageCodeToken());
        }
        */

        LoginUserDto loginUserDto = userService.login(userDto);


        //使用token令牌的方式绑定一个用户
        //生成一个唯一 令牌
        //从新绑定token的值 此前图片验证码的token在验证码正确后已经被清空
        String token = UuidUtil.getShortUuid(); //生成一个唯一 令牌
        loginUserDto.setToken(token);
        stringRedisTemplate.opsForValue().set(token, JSON.toJSONString(loginUserDto), 3600, TimeUnit.SECONDS);
        LOG.info("用户登陆成功{}",loginUserDto);
        responseDto.setContent(loginUserDto);
        return responseDto;
    }

//    退出登录状态 删除会话信息
    @GetMapping("/logout/{token}")
    public ResponseDto logout(@PathVariable String token){

        ResponseDto responseDto = new ResponseDto();
        stringRedisTemplate.delete(token);
        LOG.info("从redis中删除token{}",token);
        return responseDto;
    }

}//end class