package top.lyijian.community;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    @Test
    void test02() {
        // 两个参数：current的值默认是1，从1开始，不是0。size是每一页的条数。
        Page<Question> page = new Page<>(20,3);
        Page<Question> questionPage = questionMapper.selectPage(page, null);
        questionPage.getRecords().forEach(System.out::println);
        System.out.println("当前页：" + questionPage.getCurrent());
        System.out.println("总页数：" + questionPage.getPages());
        System.out.println("记录数：" + questionPage.getTotal());
        System.out.println("是否有上一页：" + questionPage.hasPrevious());
        System.out.println("是否有下一页：" + questionPage.hasNext());
    }


}
