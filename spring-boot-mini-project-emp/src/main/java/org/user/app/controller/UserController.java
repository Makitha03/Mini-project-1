package org.user.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.server.ResponseStatusException;
import org.user.app.model.User;
import org.user.app.service.UserService;

import jakarta.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Display user list
    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", this.userService.getUsers());
        return "index";
    }

    // Show form to add a new user
    @GetMapping("/user")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    // Process adding a user
    @PostMapping("/process")
    public String addUserProcess(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        this.userService.addUser(user);
        return "redirect:/users";
    }

    // View a user by ID
    @GetMapping("/view/{id}")
    public String viewUser(@PathVariable("id") long id, Model model) {
        Optional<User> user = this.userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    // Delete a user by ID
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        Optional<User> user = this.userService.getUserById(id);
        if (user.isPresent()) {
            this.userService.deleteUserById(id);
            return "redirect:/users";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    // Show form to update a user
    @GetMapping("/update/{id}")
    public ModelAndView showUpdateUserForm(@PathVariable("id") long id) {
        Optional<User> user = this.userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView();
        if (user.isPresent()) {
            modelAndView.setViewName("update-user");
            modelAndView.addObject("user", user.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return modelAndView;
    }

    
}
