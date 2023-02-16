package onetech.onetech.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import onetech.onetech.entities.User;
import onetech.onetech.services.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public User add(@Valid @RequestBody User user) {
        return userService.add(user);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody User user) {
        userService.delete(user);
    }

    @GetMapping("/retrieveByEmail")
    public User retrieveByEmail(@RequestBody User user) {
        return userService.retrieveByEmail(user.getEmail());
    }

    @GetMapping("/retrieveAll")
    public List<User> retrieveAll() {
        return userService.retrieveAll();
    }

}
