package life.majiang.community.controller;


import life.majiang.community.dao.QuestionDao;
import life.majiang.community.dao.UserDao;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.service.IndexService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @Autowired
    UserDao userDao;

    @Autowired
    IndexService indexService;

    @GetMapping("/")
    public String index(HttpServletRequest request,Model model,
                         @RequestParam(name = "currentPage",defaultValue = "1") Integer currentPage) {
        Cookie[] cookies = request.getCookies();
        User user;
//        判断是否登录.如果登陆了获取cookie 展示个人昵称
        if (cookies != null && cookies.length != 0) {
            for (Cookie c : cookies
            ) {
                if (c.getName().equals("token")) {
                    String value = c.getValue();
                    user = userDao.findByToken(value);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;

                }

            }
        }

//       展示问题列表
        Page<Question> all = indexService.pageQuestion(currentPage);
       model.addAttribute("questions",all);
        return "hello";
    }
}
