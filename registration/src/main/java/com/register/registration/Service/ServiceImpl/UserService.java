package com.register.registration.Service.ServiceImpl;
import com.register.registration.entity.User;
import com.register.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setMobile(updatedUser.getMobile());
            return userRepository.save(existingUser);
        }
        return null;
    }
    public ResponseEntity<String> deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }

        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body("Deleted Successfully");
    }

    public  User getUserByName(String name)
    {
        return userRepository.findUserByusername(name);
    }

    //SIGN-IN:
        public boolean signIn(long mobile, String password) {
            // Implement your logic to check if the mobile and password match
            // For simplicity, let's assume you have a method in the repository to check this
            User user = userRepository.findByMobileAndPassword(mobile, password);

            return user != null;
        }
    }

