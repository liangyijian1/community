package top.lyijian.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import top.lyijian.community.dto.QuestionDto;
import top.lyijian.community.mapper.QuestionMapper;
import top.lyijian.community.mapper.UserMapper;
import top.lyijian.community.model.Question;
import top.lyijian.community.model.User;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDto> list(){
        List<Question> questions = questionMapper.selectList(null);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    public List<QuestionDto> list(Integer pageNo,
                                  Integer pageSize,
                                  Model model){
        Page<Question> page = new Page<>(pageNo,pageSize);
        Page<Question> questionPage = questionMapper.selectPage(page, null);
        model.addAttribute("questionPage",questionPage);
        List<Question> questions = questionPage.getRecords();
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

}
