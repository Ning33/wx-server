package cn.hnisi.wx.server.person.impl;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.exception.DataValidationException;
import cn.hnisi.wx.core.io.ResponseStatus;
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
        List<Person> personList = personDAO.queryByUserid(user.getUserid());
        for(Person _person : personList){
            if(_person.getIdcard().equals(person.getIdcard())){
                throw new AppException(ResponseStatus.DATA_VALIDATE_EXCEPTION,"参保人已添加");
            }
        }

        //生成personid
        if(StringUtils.isEmpty(person.getPersonid())){
            person.setPersonid(GuidUtil.generate());
        }

        //TODO
        // 查询社保信息





        //插入人员信息表
        person.setUserid(user.getUserid());
        personDAO.insert(person);

        return person;
    }

    @Override
    public void unbind(String personid) {
        personDAO.delete(personid);
    }

    @Override
    public List<Person> queryByUser(String userid) {
        return personDAO.queryByUserid(userid);
    }

    @Override
    public Person querySelfUser(User user) {
        List<Person> personList = queryByUser(user.getUserid());
        for(Person person : personList){
            if(person.getIdcard().equals(user.getIdcard())){
                return person;
            }
        }
        throw new DataValidationException("查找不到当前用户对应的人员信息");
    }
}
