package cn.hnisi.wx.server.security;


import cn.hnisi.wx.server.person.model.Person;
import cn.hnisi.wx.server.security.model.User;
import org.springframework.beans.BeanUtils;

public abstract class BaseSecurityService implements SecurityService {

    abstract protected User queryUserInfo(String openid);

    /**
     * 注入人员信息，包括userid
     * @param user
     */
    protected void injectPerson(User user){

        String openid = user.getOpenid();

        User _user = queryUserInfo(openid);
        if(_user == null){
            return;
        }

        BeanUtils.copyProperties(_user,user,Person.class);
        user.setUserid(_user.getUserid());
    }
}
