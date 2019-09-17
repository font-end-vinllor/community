package life.majiang.community.dao;

import life.majiang.community.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionDao extends JpaRepository<Question,Long>, JpaSpecificationExecutor<Long> {
}
