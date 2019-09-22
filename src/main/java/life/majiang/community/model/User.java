package life.majiang.community.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 *   登录用户对象
 */
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String account_id;
    private String name;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;
    private String avatar_url;

//    配置一对多关系
    @OneToMany(targetEntity = Question.class,cascade = CascadeType.ALL,mappedBy = "user")
    @JsonIgnoreProperties(value = "user")
    @JsonIgnore
    @JSONField(serialize = false)
    private List<Question> questions;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account_id='" + account_id + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", gmt_create=" + gmt_create +
                ", gmt_modified=" + gmt_modified +
                ", avatar_url='" + avatar_url + '\'' +
                ", questions=" + questions +
                '}';
    }
}
