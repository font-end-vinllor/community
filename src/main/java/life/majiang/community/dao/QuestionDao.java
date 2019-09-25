package life.majiang.community.dao;

import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionDao extends JpaRepository<Question,Long>, JpaSpecificationExecutor<Long> {

}
