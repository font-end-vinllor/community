package life.majiang.community;


import life.majiang.community.dao.UserDao;
import life.majiang.community.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    UserDao userDao;

    @Test
    @Transactional
    public void testUserSave(){
        User u = new User();
        u.setName("金文敏");
        u.setToken("123");

        userDao.save(u);
    }
}
