package it.unipi.BGnet.service.user;

import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.model.User;
import it.unipi.BGnet.DTO.PostDTO;
import it.unipi.BGnet.DTO.UserDTO;
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
    public UserDTO getUserByEmail(String email) {
        Optional<User> result = userRepo.getUserByEmail(email);
        if(result.isEmpty())
            return null;
        return new UserDTO(result.get().getUsername(), result.get().getPassword());
    }
    public UserDTO getUser(String username) {
        Optional<User> result = userRepo.getUserByUsername(username);
        if(result.isEmpty())
            return null;
        return new UserDTO(result.get().getUsername(), result.get().getPassword());
    }
    public UserDTO loadProfile(String username) {
        Optional<User> result = userRepo.getUserByUsername(username);
        if(result.isEmpty())
            return null;
        UserDTO profile = new UserDTO(result.get().getUsername(), result.get().getFirstName(), result.get().getLastName(), result.get().getImg());
        profile.setMostRecentPosts(result.get().getMostRecentPosts());
        return profile;
    }
}
