package org.project.study.service;

import org.project.study.repository.UserRepository;
import org.project.study.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void create(User user) {
        String pwd = user.getPwd();
        user.setPwd(pwdEncode(pwd));
        userRepository.save(user);
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id).get();
        user.setPwd(null);
        return user;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByIdAndEmail(Long id, String email) {
        return userRepository.findByIdAndEmail(id, email);
    }

    private String pwdEncode(String pwd) {
        String encode = passwordEncoder.encode(pwd);
        return encode;
    }
}
