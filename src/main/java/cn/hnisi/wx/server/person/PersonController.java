package cn.hnisi.wx.server.person;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.server.person.model.Person;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.validateface.ValidateFaceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class PersonController {

    @Resource
    private PersonService personService;

    @Resource
    private ValidateFaceService validateFaceService;

    @RequestMapping("/api/frontend/user/bind")
    public ResponseEntity<Person> bind(String idcard, String name, User user, HttpServletRequest request){
        //验证人脸和参保人用户是否一致
        validateFaceService.validateToken(request,idcard,name);

        //人脸校验通过，构造Person
        Person person = new Person();
        person.setIdcard(idcard);
        person.setName(name);

        Person resultPerson = personService.bind(user,person);

        return new ResponseEntity<>(resultPerson);
    }

    @RequestMapping("/api/frontend/user/unbind")
    public ResponseEntity unbind(String personid, User user, HttpServletRequest request){
        //验证人脸和注册用户是否一致
        validateFaceService.validateToken(request,user.getIdcard(),user.getName());

        //不允许删除本人的人员信息
        if(user.getPersonid().equals(personid)){
            throw new AppException(ResponseStatus.DATA_VALIDATE_EXCEPTION,"不允许删除本人的人员信息，请使用用户注销接口");
        }

        //人脸验证通过，删除参保人
        personService.unbind(personid);
        return new ResponseEntity();
    }

    @RequestMapping("/api/frontend/user/person/query")
    public ResponseEntity<List<Person>> query(User user){
        List<Person> persons = personService.queryByUser(user.getUserid());
        return new ResponseEntity<>(persons);
    }

}
