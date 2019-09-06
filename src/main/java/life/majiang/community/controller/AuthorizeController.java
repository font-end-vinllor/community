package life.majiang.community.controller;


import life.majiang.community.dto.AccessTokenDto;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
     private  UserMapper mapper;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${redirect_uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request){
        AccessTokenDto dto = new AccessTokenDto();
        dto.setCode(code);
        dto.setState(state);
        dto.setClient_id("2c83d2aa748fdeb32230");
        dto.setClient_secret("932bfbae5870eeec5d85d318496692aca11f316f");
        dto.setRedirect_uri("http://localhost:8887/callback");
        String accessToken = githubProvider.getAccessToken(dto);
        GithubUser user = githubProvider.getUser(accessToken);
       if(user != null){
           //写cookie 和session
           request.getSession().setAttribute("user",user);
//           将user保存在数据库
           User u = new User();
           u.setAccount_id(UUID.randomUUID().toString());
           u.setName(user.getName());
           u.setToken(accessToken);
           u.setGmt_create(System.currentTimeMillis());
           u.setGmt_modified(u.getGmt_create());

          mapper.insertUser(u);
           return "redirect:/";
       }else{
           //重新登录
           return "redirect:/";
       }

    }
}
