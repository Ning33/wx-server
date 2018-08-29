package cn.hnisi.wx.server.person.dao;

import cn.hnisi.wx.server.person.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonDAO {

    void insert(Person person);

    List<Person> queryByUserId(@Param("userId") String userId);

    void delete(@Param("personId") String personId);

    Person querySelf(@Param("userId") String userId);

    Person queryByPersonId(@Param("personId") String personId);

}
