package org.project.study.service;

import org.project.study.exception.PasswordValidateException;
import org.project.study.model.UserAdditional;
import org.project.study.repository.UserAdditionalRepository;
import org.project.study.repository.UserRepository;
import org.project.study.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAdditionalRepository userAdditionalRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public void create(User user) {
        String pwd = user.getPwd();
        user.setPwd(this.pwdEncode(pwd));
        userRepository.save(user);
    }

    @Transactional
    public void update(User user) {
        User defaultUser = userRepository.findById(user.getId()).get();
        user.setPwd(defaultUser.getPwd());
        userRepository.save(user);
    }

    public void updateAdditional(UserAdditional userAdditional) {
        userAdditionalRepository.save(userAdditional);
    }

    @Transactional
    public void updatePassword(Long userId, Map<String, String> password) throws PasswordValidateException {

        User user = userRepository.findById(userId).get();
        if (!passwordEncoder.matches(password.getOrDefault("old", ""), user.getPassword())) {
            throw new PasswordValidateException();
        }
        user.setPwd(this.pwdEncode(password.get("new")));
        userRepository.save(user);

    }

    @Transactional
    public void leaveUser(Long userId) {
        userRepository.updateUserActive(userId);
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id).get();
        user.setPwd(null);
        return user;
    }

    public UserAdditional getUserAdditional(Long id) {
        return userAdditionalRepository.findByUserId(id);
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

    public boolean isPwdMatch(String p, String p2) {
        return passwordEncoder.matches(p, p2);
    }
}
