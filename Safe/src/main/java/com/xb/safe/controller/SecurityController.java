package com.xb.safe.controller;

import com.xb.safe.dto.Data;
import com.xb.safe.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SecurityController extends BaseController {

    @RequestMapping(method = RequestMethod.POST, value = "/getUsersForAuthorisation")
    @ResponseBody
    public List<User> getUsers(@RequestBody Data data) throws Exception {
        return getRequestedUserList(data.getData());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/loginfailed")
    public String loginfailed(Model model) {
        model.addAttribute("loginFailed", "Неверный логин или пароль!");
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public String logout() {
        
        return "login";
    }
}

