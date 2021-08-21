package top.lyijian.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.lyijian.community.dto.AccessTokenDto;
import top.lyijian.community.dto.GiteeUser;
import top.lyijian.community.mapper.UserMapper;
import top.lyijian.community.model.User;
import top.lyijian.community.provider.GiteeProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;


@Controller
public class OAuthController {
    @Autowired
    private GiteeProvider giteeProvider;
    @Value("${gitee.client.id}")
    private String client_id;
    @Value("${gitee.client.secret}")
    private String client_secret;
    @Value("${gitee.redirect.url}")
    private String redirect_url;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String AuthorizeController(@RequestParam("code") String code,
                                      HttpServletRequest request,
                                      HttpServletResponse response){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setClient_id(client_id);
        accessTokenDto.setClient_secret(client_secret);
        accessTokenDto.setRedirect_uri(redirect_url);
        String accessToken = giteeProvider.getAccessToken(accessTokenDto);
        GiteeUser giteeUser = giteeProvider.getUser(accessToken);
        if (giteeUser!=null){
            User user = new User();
            user.setId(null);
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(giteeUser.getName());
            user.setAccountId(String.valueOf(giteeUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatar_url(giteeUser.getAvatar_url());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else {
            return "redirect:/";
        }


    }

}
