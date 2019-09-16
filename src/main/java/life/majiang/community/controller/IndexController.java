package life.majiang.community.controller;


import life.majiang.community.dao.UserDao;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @Autowired
    UserDao userDao;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
       if(cookies!=null) {
           for (Cookie c : cookies
           ) {
               if (c.getName().equals("token")) {
                   String value = c.getValue();
                   User user = userDao.findByToken(value);
                   if (user != null) {
                       request.getSession().setAttribute("user", user);
                   }
                   break;

               }

           }
       }
        return "hello";
    }
}
