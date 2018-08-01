package cn.hnisi.wx.server.person;

import cn.hnisi.wx.server.person.model.Person;
import cn.hnisi.wx.server.security.model.User;

import java.util.List;

public interface PersonService {

    /**
     * 绑定用户
     * @param user
     * @param person
     * @return
     */
    Person bind(User user, Person person);

    void unbind(String personid);

    List<Person> queryByUser(String userid);

    Person querySelfUser(User user);


}
