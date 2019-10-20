package org.project.study.controller;

import org.project.study.exception.PasswordValidateException;
import org.project.study.model.User;
import org.project.study.model.UserAdditional;
import org.project.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public void create(@RequestBody User user) {
        userService.create(user);
    }

    @PostMapping("/update")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/me")
    public ResponseEntity<Object> getProfile(@AuthenticationPrincipal User user) {

        if (user == null) {
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/additional")
    public void updateAdditional(@RequestBody UserAdditional userAdditional) {
        userService.updateAdditional(userAdditional);
    }

    @GetMapping("/additional")
    public ResponseEntity<UserAdditional> getAdditional(@AuthenticationPrincipal User user) {

        if (user == null) {
            return ResponseEntity.ok(null);
        }

        UserAdditional userAdditional = userService.getUserAdditional(user.getId());

        return ResponseEntity.ok(userAdditional);
    }

    @PostMapping("/password")
    public ResponseEntity updatePassword(@AuthenticationPrincipal User user, @RequestBody Map<String, String> password) {
        try {
            userService.updatePassword(user.getId(), password);
            return ResponseEntity.ok().build();
        } catch (PasswordValidateException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping("leave")
    public void leaveUser(@AuthenticationPrincipal User user) {
        userService.leaveUser(user.getId());
    }

}
