package cn.hnisi.wx.server.person.dao;

import cn.hnisi.wx.server.person.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonDAO {

    void insert(Person person);

    List<Person> queryByUserid(String userid);

    void delete(@Param("personid") String personid);

    Person querySelf(@Param("userid") String userid);

    Person queryByPersonid(@Param("personid") String personid);

}
