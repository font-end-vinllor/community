package life.majiang.community.controller;


import life.majiang.community.dao.QuestionDao;
import life.majiang.community.dao.UserDao;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    UserDao userDao;

    @Autowired
    QuestionDao questionDao;

    @GetMapping("/")
    public String index(HttpServletRequest request,Model model) {
        Cookie[] cookies = request.getCookies();
        User user;
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
        List<Question> questions = questionDao.findAll();
        model.addAttribute("questions",questions);
        return "hello";
    }
}
