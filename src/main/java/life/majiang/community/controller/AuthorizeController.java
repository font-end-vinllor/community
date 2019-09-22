package life.majiang.community.controller;


import life.majiang.community.dao.UserDao;
import life.majiang.community.dto.AccessTokenDto;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
    public class AuthorizeController {

    @Autowired
    private UserDao dao;

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${redirect_uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response){
        AccessTokenDto dto = new AccessTokenDto();
        dto.setCode(code);
        dto.setState(state);
        dto.setClient_id(client_id);
        dto.setClient_secret(client_secret);
        dto.setRedirect_uri(redirect_uri);
        String accessToken = githubProvider.getAccessToken(dto);
        GithubUser user = githubProvider.getUser(accessToken);
       if(user != null){
           response.addCookie(new Cookie("token",accessToken));
//           将user保存在数据库
           User u = new User();
           u.setAvatar_url(user.getAvatar_url());
           u.setAccount_id(UUID.randomUUID().toString());
           u.setToken(accessToken);
           u.setName(user.getName());
           u.setGmt_create(System.currentTimeMillis());
           u.setGmt_modified(u.getGmt_create());
          dao.save(u);
           return "redirect:/";
       }else{
           //重新登录
           return "redirect:/";
       }

    }
}
