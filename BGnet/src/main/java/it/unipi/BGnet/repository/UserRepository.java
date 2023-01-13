package it.unipi.BGnet.repository;

import it.unipi.BGnet.model.User;
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

    public Optional<User> getUserByEmail(String email){
        Optional<User> user = Optional.empty();
        try {
            user = userMongo.findByEmail(email);
            System.out.println("\t\tUserRepository::getUserByEmail(String email) >> " + user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Optional<User> getUserByUsername(String username){
        Optional<User> user = Optional.empty();
        try {
            user = userMongo.findByUsername(username);
            System.out.println("\t\tUserRepository::getUserByUsername(String username) >> " + user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
