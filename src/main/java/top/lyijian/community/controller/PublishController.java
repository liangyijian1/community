package top.lyijian.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.lyijian.community.mapper.QuestionMapper;
import top.lyijian.community.mapper.UserMapper;
import top.lyijian.community.model.Question;
import top.lyijian.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("description",description);
        model.addAttribute("title",title);
        model.addAttribute("tag",tag);
        if (title==null||"".equals(title)){
            model.addAttribute("publish_error","标题不能为空");
            return "publish";
        }
        if (description==null||"".equals(description)){
            model.addAttribute("publish_error","内容不能为空");
            return "publish";
        }
        Cookie[] cookies = request.getCookies();
        Question question = new Question();
        User user=null;
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    Map<String, Object> map = new HashMap<>();
                    map.put("token",token);
                    List<User> users = userMapper.selectByMap(map);
                    user = users.get(0);
                    break;
                }
            }
        }
        if (user==null){
            model.addAttribute("publish_error","请登录后再尝试");
            return "publish";
        }
        question.setCreator(user.getId());
        question.setDescription(description);
        question.setTag(tag);
        question.setTitle(title);
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());
        questionMapper.insert(question);
        return "redirect:/";
    }

}
