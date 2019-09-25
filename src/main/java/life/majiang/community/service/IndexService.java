package life.majiang.community.service;

import life.majiang.community.dao.QuestionDao;
import life.majiang.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    @Autowired
    QuestionDao questionDao;

    /**
     *
     */
    public Page<Question> pageQuestion(int currentPage) {
        Page<Question> all = questionDao.findAll(PageRequest.of(currentPage, 2));
        return all;
    }
}
