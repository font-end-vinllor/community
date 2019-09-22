package life.majiang.community.controller;


import life.majiang.community.dao.QuestionDao;
import life.majiang.community.dao.UserDao;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    UserDao userDao;

    @Autowired
    QuestionDao questionDao;

    /**
     * 跳转到发布文章页面
     *
     * @return
     */
    @GetMapping
    public String publish() {
        return "publish";
    }

    /**
     * 发布文章
     *
     * @return
     */
    @PostMapping
    public String goPublish(@RequestParam(name = "title", required = false) String title,
                            @RequestParam(name = "content", required = false) String content,
                            @RequestParam(name = "tags", required = false) String tags,
                            Model model,
                            HttpServletRequest request) {


        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies
            ) {
                if (cookie.getName().equals("token")) {
                    String value = cookie.getValue();
                    user = userDao.findByToken(value);
                    break;
                }
            }
        }
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            //        将内容回显到页面
            model.addAttribute("title", title);
            model.addAttribute("content", content);
            model.addAttribute("tags", tags);
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setTags(tags);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModify(question.getGmtCreate());
        question.setUser(user);
        questionDao.save(question);

        return "redirect:/";
    }
}
