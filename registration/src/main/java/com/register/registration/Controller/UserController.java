package com.register.registration.Controller;
import com.register.registration.Service.ServiceImpl.UserService;
import com.register.registration.entity.User;
import com.register.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);

    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User savedUser = userService.createUser(newUser);
        return ResponseEntity.ok(savedUser);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
            return userService.updateUser(id, updatedUser);
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
            return userService.deleteUser(id);
    }
        @GetMapping("/findbyname/{name}")
        public User findByName(@PathVariable String name)
        {
            return userService.getUserByName(name);
        }

        //SIGN-IN:
        @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody User signInRequest) {
        long mobile = signInRequest.getMobile();
        String password = signInRequest.getPassword();

        // Call the service method to check the credentials
        boolean signInSuccess = userService.signIn(mobile, password);

        if (signInSuccess) {
            return ResponseEntity.ok("Sign-in successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}

