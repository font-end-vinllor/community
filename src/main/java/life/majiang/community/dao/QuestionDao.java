package life.majiang.community.dao;

import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question,Long>, JpaSpecificationExecutor<Long> {


//    级联查询 查询问题以及将其对应的提问题的人查询出
}
