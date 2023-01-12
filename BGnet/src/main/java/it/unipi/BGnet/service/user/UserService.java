package it.unipi.BGnet.service.user;
import it.unipi.BGnet.models.User;
import it.unipi.BGnet.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service("mainUserService")
public class UserService {
    @Autowired
    UserRepository userRepo;
    public boolean existsUser(String username) {
        return userRepo.existsUserByUsername(username);
    }
    public User getUser(String username) {
        return (User) userRepo.getUserByUsername(username);
    }
}
