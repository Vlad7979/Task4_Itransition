package com.vladbadey.task4.controllers;

import com.vladbadey.task4.dao.UsersDAO;
import com.vladbadey.task4.models.Index;
import com.vladbadey.task4.models.Login;
import com.vladbadey.task4.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
public class GreetingController {

    private final UsersDAO usersDAO;
    private User currentUser = new User();
    private Index index;


    @Autowired
    public GreetingController(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @GetMapping("/showAll")
    public String index(Model model) {
        model.addAttribute("i", index.getI());
        model.addAttribute("users", usersDAO.showAll());
        return "list";
    }

    @GetMapping()
    public String login(@ModelAttribute("user") Login login) {
        return "login";
    }

    @PostMapping("/login")
    public String validLogin(@ModelAttribute("user") Login login) {
        User user = usersDAO.validateUser(login);
        if (user != null && user.getStatus()) {
            user.setLastLogIn(LocalDate.now());
            currentUser = user;
            return "redirect:/showAll";
        } else {
            return "redirect:/?error";
        }
    }

    @GetMapping("/signin")
    public String signin(@ModelAttribute("user") User user) {
        return "signin";
    }

    @PostMapping("/signin")
    public String save(@ModelAttribute("user") User user) {
        if (usersDAO.isUnique(user)) {
            usersDAO.save(user);
            currentUser = user;
            return "redirect:/showAll";
        } else {
            return "redirect:/signin?uniqueError";
        }
    }

    @PostMapping(value = "/action", params = "block")
    public String block(HttpServletRequest request) {
        String[] userID = request.getParameterValues("foo");
        for (String s : userID) {
            int userId = Integer.parseInt(s);
            User users = new User(userId);
            usersDAO.blockUser(users);
            if (users.getId() == currentUser.getId()) {
                return "redirect:/";
            }
        }
        return "redirect:/showAll";
    }

    @PostMapping(value = "/action", params = "unblock")
    public String unblock(HttpServletRequest request) {
        String[] userID = request.getParameterValues("foo");
        for (String s : userID) {
            int userId = Integer.parseInt(s);
            User users = new User(userId);
            usersDAO.unblockUser(users);
        }
        return "redirect:/showAll";
    }

    @PostMapping(value = "/action", params = "delete")
    public String delete(HttpServletRequest request) {
        String[] userID = request.getParameterValues("foo");
        for (String s : userID) {
            int userId = Integer.parseInt(s);
            User users = new User(userId);
            usersDAO.deleteUser(users);
            if (users.getId() == currentUser.getId()) {
                return "redirect:/";
            }
        }
        return "redirect:/showAll";
    }
}