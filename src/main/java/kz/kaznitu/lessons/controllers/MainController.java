package kz.kaznitu.lessons.controllers;

import jssc.SerialPortEventListener;
import kz.kaznitu.lessons.mod.*;
import kz.kaznitu.lessons.mod.Timestamp;
import kz.kaznitu.lessons.repas.*;
import kz.kaznitu.lessons.service.UserService;
import kz.kaznitu.lessons.spm.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jssc.SerialPort;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




import javax.validation.Valid;

@Controller
public class MainController {
    listener listener = new listener();
    //Registration registration = new Registration();
    SKUD skud = new SKUD();

    //FootballController(){startListener();}


    @Autowired
    private RegistrationRepas registrationRepas;

    @Autowired
    private UsersRepas usersRepas;
    private long a;

    @Autowired
    private UserService userService;


    @Autowired
    private InstitutRepas institutRepas;
    @Autowired
    private CafedraRepas cafedraRepas;
    @Autowired
    private TimestampRepas timestampRepas;


    @RequestMapping(value = "/Korzina", method = RequestMethod.GET)
    public ModelAndView footballsList2(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        skud.pauseRead1();
        listener.pauseRead();
        listener.startListener();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> users = userService.findUserByLogin(auth.getName());
        modelAndView.addObject("userName1",  users.get().getSurname()+ " " + users.get().getName());
        model.addAttribute("users", usersRepas.findAll());
        modelAndView.setViewName("Korzina");
        return modelAndView;

    }

    /*@RequestMapping(value="/Registrations", method = RequestMethod.GET)
    public String footballsList3(Model model) {
        model.addAttribute("footballs", footballRepas.findAll());
        //startListener();
        return "registrations";
    }*/
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteAuthor(@RequestParam("id") long idd) {
        usersRepas.deleteById(idd);
        return new ModelAndView("redirect:/Korzina");
    }


    /////////

    @RequestMapping(value = "/registrations", method = RequestMethod.GET)
    public String clubsAdd(Model model) {

        model.addAttribute("registration", new Registration());
        //model.addAttribute("users", new Users());

        //Registration registration1 = listener.fetchById(registration.GN);
        //model.addAttribute("registration1", registration1);
        //System.out.println(registration.GN + "GN");
        return "registrations";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String clubsAdd1(Model model) {

        //model.addAttribute("registration", new Registration());
        model.addAttribute("user", new Users());
        model.addAttribute("users", usersRepas.findAll());

        return "users";
    }

    @RequestMapping(value = "/registrations", method = RequestMethod.POST)
    public String clubsAdd(@ModelAttribute("registration") @Valid Registration registration, BindingResult result) {

        if (result.hasErrors()) {
            return "registrations";
        }
        registrationRepas.save(registration);
        return "redirect:/registrations";
        //return "redirect:/clubs";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String clubsAdd1(@ModelAttribute("users") @Valid Users users, BindingResult result) {

        if (result.hasErrors()) {
            return "users";
        }
        usersRepas.save(users);
        return "redirect:/users";
        //return "redirect:/clubs";
    }
    /*@RequestMapping(value="/bar", method = RequestMethod.GET)
    public String footballsList43(Model model) {
        model.addAttribute("footballs", footballRepas.findAll());
        return "main_admin";
    }*/


    @RequestMapping(value="/history_admin", method = RequestMethod.GET)
    public ModelAndView footballsList435(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> users = userService.findUserByLogin(auth.getName());
        modelAndView.addObject("userName1",  users.get().getSurname()+ " " + users.get().getName());
       // model.addAttribute("footballs", footballRepas.findAll());
        modelAndView.setViewName("history_admin");
        return modelAndView;
    }


    @RequestMapping(value="/users_db", method = RequestMethod.GET)
    public ModelAndView footballsList436(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        //model.addAttribute("footballs", footballRepas.findAll());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> users = userService.findUserByLogin(auth.getName());
        modelAndView.addObject("userName1",  users.get().getSurname()+ " " + users.get().getName());
        model.addAttribute("users", usersRepas.findAll());
        modelAndView.setViewName("users_db");
        return modelAndView;
    }

    @RequestMapping(value="/find_db", method = RequestMethod.GET)
    public ModelAndView findDB(Model model) {
        ModelAndView modelAndView  = new ModelAndView();
        //model.addAttribute("footballs", footballRepas.findAll());

        model.addAttribute("users", usersRepas.findAll());
        model.addAttribute("user", new Users());
        model.addAttribute("timestamp", new Timestamp());
        model.addAttribute("instituts", institutRepas.findAll());
        model.addAttribute("cafedras", cafedraRepas.findAll());
        model.addAttribute("times", timestampRepas.findAll());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> users = userService.findUserByLogin(auth.getName());
        modelAndView.addObject("userName1",  users.get().getSurname()+ " " + users.get().getName());
        modelAndView.setViewName("find_db");

        return modelAndView;
    }

    ////////////////////////123123123n  запускаем скуд
    @RequestMapping(value="/add_lessons", method = RequestMethod.GET)
    public String clubsList(Model model) {
        model.addAttribute("registrations", registrationRepas.findAll());
        skud.startListener1();
        return "BookingList";
    }

    @RequestMapping(value = "/drop", method = RequestMethod.GET)
    public ModelAndView deleteAuthor1(@RequestParam("id") Long id) {
        registrationRepas.deleteById(id);
        return new ModelAndView("redirect:/BookingList");
    }

/*
    @RequestMapping(value = "/clubs", method = RequestMethod.POST)
    public String clubsAdd(@RequestParam String description, @RequestParam String email, @RequestParam String label, @RequestParam String description2, Model model) {
        Registration newClub = new Registration();
        newClub.setDescription(description);
        newClub.setEmail(email);
        newClub.setLabel(label);
        newClub.setDescription2(description2);
        registrationRepas.save(newClub);

        model.addAttribute("Registration", newClub);
        model.addAttribute("clubs", registrationRepas.findAll());
        return "redirect:/clubs" ;
    }
*/





/////////////////////////////

    @GetMapping("/all2")
    public String allUsers2(Model model) {
        List<Users> users = (List<Users>) usersRepas.findAll();
        model.addAttribute("users", users);
        return "users";
    }


    @PostMapping("/editUser")
    public String editUser(@ModelAttribute Users users) {
        Users users1 = new Users();
        users1.setId_user(a);
        users1.setCard(users.getCard());
        users1.setSurname(users.getSurname());
        users1.setName(users.getName());
        users1.setInst(users.getInst());
        users1.setCaf(users.getCaf());
        // users1.setCaf(users.getLogin());
        //users1.setCaf(users.getPassword());
        //users1.setEmail(author.getEmail());
        usersRepas.save(users1);
        return "redirect:/Korzina";
    }

  /*  @PostMapping("/editUser")
    public String editUser(Users users, Map<String, Object> model){
        Optional<Users> userFromDb = usersRepas.findByLogin(users.getLogin());
        if(userFromDb != null){
            model.put("message", "User exists");
            return "editUser";
        }
        users.setActive(true);
        users.setRoles(Collections.singleton(Role.USER));
        usersRepas.save(users);
        return "redirect:/Korzina";
    }*/

    @RequestMapping(value = "/editUser",method = RequestMethod.GET)
    public ModelAndView editUser(Model model,@RequestParam("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        a=id;
        Optional<Users> users = (Optional <Users>) usersRepas.findById(id);
        Optional<Institut> institut = (Optional <Institut>) institutRepas.findById(id);
        Optional<Cafedra> cafedra = (Optional <Cafedra>) cafedraRepas.findById(id);
        model.addAttribute("instituts", institutRepas.findAll());
        model.addAttribute("cafedras", cafedraRepas.findAll());
        model.addAttribute("user",users);
        model.addAttribute("institut",institut);
        model.addAttribute("cafedra",cafedra);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> users1 = userService.findUserByLogin(auth.getName());
        modelAndView.addObject("userName1",  users1.get().getSurname()+ " " + users1.get().getName());

        return new ModelAndView("smp");
    }


    @RequestMapping("/editUser")
    public String showForm2(Model model){
        model.addAttribute("user",new Users());
        model.addAttribute("user",new Institut());
        model.addAttribute("user",new Cafedra());

        return "smp";
    }



    /*@RequestMapping(value = "/find_db",method = RequestMethod.GET)
    public ModelAndView find_history(Model model,@RequestParam("id") Long id){
        Optional<Users> users = (Optional <Users>) usersRepas.findById(id);
        model.addAttribute("user",users);
        return new ModelAndView("find_db");
    }*/
////////////////////////////
/*
    @RequestMapping("/myFormPage")
    public String myFormPage(
            Model model,
            @ModelAttribute("users") Users users) {
        List<Users> users12 = new ArrayList<>();
        users12.add(new Users());
        users12.add(new Users());
        model.addAttribute("user", users12);
        return "myFormPage";
    }

    @RequestMapping("/editUser")
    public String search(
            @ModelAttribute("selectedOption") SearchOption selectedOption) {
        System.out.println(selectedOption.getOption());
        return "smp";
    }*/



}