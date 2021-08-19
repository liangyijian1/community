package top.lyijian.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.lyijian.community.dto.AccessTokenDto;
import top.lyijian.community.dto.GiteeUser;
import top.lyijian.community.provider.GiteeProvider;


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

    @GetMapping("/callback")
    public String AuthorizeController(@RequestParam("code") String code){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setClient_id(client_id);
        accessTokenDto.setClient_secret(client_secret);
        accessTokenDto.setRedirect_uri(redirect_url);
        String accessToken = giteeProvider.getAccessToken(accessTokenDto);
        GiteeUser user = giteeProvider.getUser(accessToken);
        System.out.println(user);
        return "redirect:index";
    }

}
