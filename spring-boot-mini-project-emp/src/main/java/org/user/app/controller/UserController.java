package org.user.app.controller;

import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.user.app.model.User;
import org.user.app.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public String userList(Model model )
    {
    	
    	model.addAttribute("users", this.userService.getUsers());
        return "index";
    }

    @GetMapping("/user")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/process")
    public String addUserProcess(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        this.userService.addUser(user);
        return "redirect:users";
        
    }
    
    
    @GetMapping("/view/{id}")
   	public String viewUser(@PathVariable("id") long id,Model model)
   	{
   		model.addAttribute("user", this.userService.getUserById(id).get());
   		return "user";
   	}
       
    @RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id)
	{
		this.userService.deleteUserById(id);
		return "redirect:/users";
	}
    
    @GetMapping("/update/{id}")
   	public ModelAndView showUpdateUserForm(@PathVariable("id")long id)
   	{
   		Optional<User> user=this.userService.getUserById(id);
   		ModelAndView modelAndView=new ModelAndView();
   		if(user.isPresent())
   		{
   			
   			modelAndView.setViewName("update-user");
   			modelAndView.addObject("user", this.userService.getUserById(id).get());
   			return modelAndView;
   		}
   		else
   		{
   			modelAndView.setViewName("index");
   			return modelAndView;
   		}
}
}