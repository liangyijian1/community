package top.lyijian.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.lyijian.community.dto.PageDto;
import top.lyijian.community.dto.QuestionDto;
import top.lyijian.community.mapper.QuestionMapper;
import top.lyijian.community.mapper.UserMapper;
import top.lyijian.community.model.Question;
import top.lyijian.community.model.User;
import top.lyijian.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    Map<String, Object> map = new HashMap<>();
                    map.put("token",token);
                    List<User> users = userMapper.selectByMap(map);
                    User user = users.get(0);
                    if (user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        List<QuestionDto> questions = questionService.list(pageNo, PageDto.PAGE_SIZE,model);
        model.addAttribute("questions",questions);
        return "index";
    }
}
