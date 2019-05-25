package org.project.study.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void create(User user) {
        userRepository.save(user);
    }

    public User getUser(Long id) {
       return userRepository.findById(id).get();
    }
}
