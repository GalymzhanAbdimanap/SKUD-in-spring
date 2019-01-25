package kz.kaznitu.lessons.controllers;

import kz.kaznitu.lessons.mod.User;
import kz.kaznitu.lessons.mod.Users;
import kz.kaznitu.lessons.service.UserService;
import kz.kaznitu.lessons.spm.listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes("users")
public class LoginController {

    kz.kaznitu.lessons.spm.listener listener = new listener();

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        System.out.println(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView home1() {
        ModelAndView model = new ModelAndView();
        //model.addAttribute("footballs", footballRepas.findAll());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> users = userService.findUserByLogin(auth.getName());
        System.out.println(auth.getName() + users.get().getName());
        //Optional<Users> users = (Optional<Users>) userService.findUserByLogin(auth.getName());
        model.addObject("userName1",  users.get().getSurname()+ " " + users.get().getName());
        listener.pauseRead();
        model.setViewName("main_admin");
        //return "main_admin" ;
        return model;
    }

}
