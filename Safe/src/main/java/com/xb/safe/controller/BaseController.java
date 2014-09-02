package com.xb.safe.controller;

import com.xb.danilov.personal.client.PersonalClient;
import com.xb.danilov.personal.dto.UserItem;
import com.xb.kurilo.LogFactory;
import com.xb.safe.dto.Department;
import com.xb.safe.dto.User;
import com.xb.safe.service.DaoService;
import com.xb.safe.service.ExcelService;
import com.xb.safe.service.ReportService;
import com.xb.safe.util.Replacer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class BaseController {

    @Autowired
    protected ExcelService excelService;
    @Autowired
    protected DaoService daoService;
    @Autowired
    protected ReportService reportService;
    @Autowired
    protected PersonalClient pc;
    private static final Replacer replacer = new Replacer();
    private static Map<String, Integer> loginMap;
    protected Logger logger;
    protected Logger loggerExeption;

    @PostConstruct
    private void PostConstruct() {
        loginMap = daoService.getLoginMap();
        logger = LogFactory.getLogger(this.getClass().getSimpleName());
        loggerExeption = LogFactory.getLogger(this.getClass().getSimpleName() + "_ERROR");
    }

    protected void updateLoginMap() {
        loginMap = daoService.getLoginMap();
    }

    protected String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    protected Collection getRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    protected Integer getDepartmentId() {
        return loginMap.get(getLogin());
    }

    protected String getCurrentDate() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date today = Calendar.getInstance().getTime();
        String currentDate = df.format(today);
        return currentDate;
    }

    protected String getUserFullName() {
        StringBuilder sb = new StringBuilder();
        try {
            UserItem person = pc.getUser(getLogin());

            sb.append(person.getSurname()).append(" ");
            sb.append(person.getName().substring(0, 1)).append(".");
            sb.append(person.getPatronymic().trim().substring(0, 1)).append(".");
        } catch (Exception ignored) {
//TODO
        }
        return sb.toString();
    }

    protected Date getFormatEndDate(Date dateEnd) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateEnd);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        return dateEnd = cal.getTime();
    }

    protected Date getFormatStartDate(Date startEnd) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startEnd);
        cal.set(Calendar.HOUR_OF_DAY, 8);
        return startEnd = cal.getTime();
    }

    protected List<User> getRequestedUserList(String data) throws Exception {
        if (replacer.isFullEngString(data)) {
            data = replacer.convertEnglishToUkr(data);
        }
        List<UserItem> userItemList = pc.getUserBySurname(data);
        List<User> userList = new ArrayList<>();
        for (UserItem uItem : userItemList) {
            User user = new User();
            user.setLogin(uItem.getLogin());
            user.setName(uItem.getSurname() + " " + uItem.getName() + " " + uItem.getPatronymic());
            userList.add(user);
        }
        return userList;
    }

    protected Department getDepartmentById() {
        Department dept = daoService.getDepartmentById(getDepartmentId());
        return dept;
    }

}
