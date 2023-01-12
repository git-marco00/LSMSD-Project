package it.unipi.BGnet.repository;

import it.unipi.BGnet.models.User;
import it.unipi.BGnet.repository.mongoDB.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    private IUserRepository userMongo;

    // CRUD Methods
    // ----------------------------------------------------------------------------------------
    public boolean addUser(User user){
        boolean result = true;
        try{
            userMongo.save(user);
        } catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean deleteUser(User user) {
        boolean result = true;
        try {
            userMongo.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean existsUserByUsername(String username) {
        boolean result = true;
        try {
            userMongo.existsByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public User getUserByUsername(String username){
        User user = new User();
        try {
            user = userMongo.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
