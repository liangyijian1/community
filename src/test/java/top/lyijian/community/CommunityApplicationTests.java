package top.lyijian.community;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.lyijian.community.mapper.UserMapper;
import top.lyijian.community.model.User;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@MapperScan("top.lyijian.community.mapper")
@SpringBootTest
class CommunityApplicationTests {


    @Test
    void TestUserMapper(){

    }

}
