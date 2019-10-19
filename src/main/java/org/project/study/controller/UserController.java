package org.project.study.controller;

import org.project.study.model.User;
import org.project.study.model.UserAdditional;
import org.project.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
        System.err.println(userAdditional);
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

}
