package top.lyijian.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.lyijian.community.model.Question;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}
