package top.lyijian.community;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.lyijian.community.dto.QuestionDto;
import top.lyijian.community.mapper.QuestionMapper;
import top.lyijian.community.mapper.UserMapper;
import top.lyijian.community.model.Question;
import top.lyijian.community.model.User;

import java.util.List;


@MapperScan("top.lyijian.community.mapper")
@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    @Test
    void TestQuestionMapper(){
        Question question = questionMapper.selectById(1);
        QuestionDto questionDto = new QuestionDto();
        System.out.println(question);
    }

}
