package life.majiang.community.dao;

import life.majiang.community.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

     User findByToken(String token);

}
