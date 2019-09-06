package life.majiang.community.mapper;


import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
//  将登录用户保存在数据库
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified) " +
            "values(#{gmt_account_id},#{name},#{token},#{gmt_create},#{gmt_modified})")
    void insertUser(User user);


}
