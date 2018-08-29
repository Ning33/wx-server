package cn.hnisi.wx.server.person.impl;

import cn.hnisi.wx.core.exception.DataValidationException;
import cn.hnisi.wx.core.utils.GuidUtil;
import cn.hnisi.wx.server.person.PersonService;
import cn.hnisi.wx.server.person.dao.PersonDAO;
import cn.hnisi.wx.server.person.model.Person;
import cn.hnisi.wx.server.security.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonDAO personDAO;

    @Override
    public Person bind(User user, Person person) {

        //校验是否重复添加
        List<Person> personList = personDAO.queryByUserId(user.getUserId());
        for(Person _person : personList){
            if(_person.getIdcard().equals(person.getIdcard())){
                throw new DataValidationException("参保人已添加");
            }
        }

        //生成personId
        if(StringUtils.isEmpty(person.getPersonId())){
            person.setPersonId(GuidUtil.generate());
        }

        //TODO
        // 查询社保信息





        //插入人员信息表
        person.setUserId(user.getUserId());
        personDAO.insert(person);

        return person;
    }

    @Override
    public void unbind(String personId) {
        personDAO.delete(personId);
    }

    @Override
    public List<Person> queryByUser(String userId) {
        return personDAO.queryByUserId(userId);
    }

    @Override
    public Person querySelf(String userId) {
        Person person = personDAO.querySelf(userId);
        if(person == null){
            throw new DataValidationException("查找不到当前用户对应的人员信息");
        }
        return person;
    }

    @Override
    public Person queryByPersonId(String personId) {
        Person person = personDAO.queryByPersonId(personId);
        if(person == null){
            throw new DataValidationException("查找不到对应的人员信息");
        }
        return person;
    }
}
