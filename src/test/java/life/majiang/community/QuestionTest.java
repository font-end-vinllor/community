package life.majiang.community;


import life.majiang.community.dao.QuestionDao;
import life.majiang.community.model.Question;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionTest {

    @Autowired
    QuestionDao questionDao;


    @Test
    public void testPage(){
        Page<Question> all = questionDao.findAll(PageRequest.of(1, 2));
        Pageable pageable = all.getPageable();
        System.out.println("pageNumber"+pageable.getPageNumber()+"\nfirstPageNumber"+pageable.first().getPageNumber()+
                "\noffset "+pageable.getOffset()+"\npageSize"+pageable.getPageSize()+"\nhasPrevious"+pageable.hasPrevious()
        );

    }
}
