package com.douding.server.service;

import com.douding.server.domain.Test;
import com.douding.server.domain.TestExample;
import com.douding.server.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list(){



        TestExample testExample = new TestExample();
        testExample.createCriteria().andIdEqualTo("1");
        return testMapper.selectByExample(testExample);

    }
}
