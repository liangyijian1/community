package top.lyijian.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.lyijian.community.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
