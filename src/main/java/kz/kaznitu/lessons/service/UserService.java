package kz.kaznitu.lessons.service;


import kz.kaznitu.lessons.mod.Role;
import kz.kaznitu.lessons.mod.User;
import kz.kaznitu.lessons.mod.Users;
import kz.kaznitu.lessons.repas.RoleRepository;
import kz.kaznitu.lessons.repas.UserRepository;
import kz.kaznitu.lessons.repas.UsersRepas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service("userService")
public class UserService {


    private UserRepository userRepository;
    private UsersRepas usersRepas;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UsersRepas usersRepas,
                       UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepas= usersRepas;
        this.userRepository= userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<Users> findUserByLogin(String login) {
        return (Optional<Users>) usersRepas.findByLogin(login);
    }
    public Optional<Users> findUserById(long id) {
        return (Optional<Users>) usersRepas.findById(id);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public Users saveUsers(Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        users.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        users.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return usersRepas.save(users);
    }

}