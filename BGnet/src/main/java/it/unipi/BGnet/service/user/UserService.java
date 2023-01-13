package it.unipi.BGnet.service.user;
import it.unipi.BGnet.model.User;
import it.unipi.BGnet.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service("mainUserService")
public class UserService {
    @Autowired
    UserRepository userRepo;
    public boolean addUser(User user) {
        return userRepo.addUser(user);
    }
    public User getUserByEmail(String email) {
        User user = new User();
        Optional<User> result = userRepo.getUserByEmail(email);
        if(result.isEmpty())
            return null;
        user.setEmail(result.get().getEmail());
        user.setUsername(result.get().getUsername());
        user.setPassword(result.get().getPassword());
        return user;
    }
    public User getUser(String username) {
        User user = new User();
        Optional<User> result = userRepo.getUserByUsername(username);
        if(result.isEmpty())
            return null;
        user.setUsername(result.get().getUsername());
        user.setPassword(result.get().getPassword());
        return user;
    }
}
