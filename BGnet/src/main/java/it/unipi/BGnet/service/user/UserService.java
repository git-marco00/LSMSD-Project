package it.unipi.BGnet.service.user;

import it.unipi.BGnet.DTO.*;
import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.model.Tournament;
import it.unipi.BGnet.model.User;
import it.unipi.BGnet.repository.GameRepository;
import it.unipi.BGnet.repository.TournamentRepository;
import it.unipi.BGnet.repository.UserRepository;

import org.neo4j.driver.Record;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("mainUserService")
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepo;
    @Autowired
    GameRepository gameRepo;
    @Autowired
    TournamentRepository tournamentRepo;
    public boolean addUser(User user) {
        if(!userRepo.addUser(user))
            return false;

        return userRepo.createNewUserNeo4j(user.getUsername());
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
    public UserDTO loadProfile(String username, String myself) {
        Optional<User> result = userRepo.getUserByUsername(username);
        if(result.isEmpty())
            return null;
        logger.warn(Long.toString(System.currentTimeMillis()));
        // creating the DTO to show to the user
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(result.get().getUsername());
        userDTO.setPassword(result.get().getPassword());
        userDTO.setFirstName(result.get().getFirstName());
        userDTO.setLastName(result.get().getLastName());
        userDTO.setYearRegistered(result.get().getYearRegistered());
        userDTO.setEmail(result.get().getEmail());
        userDTO.setStateOfProvince(result.get().getStateOfProvince());
        userDTO.setCountry(result.get().getCountry());
        userDTO.setContinent(result.get().getContinent());
        userDTO.setImg(result.get().getImg());
        userDTO.setMostRecentPosts(result.get().getMostRecentPosts(), username);
        userDTO.setFollowers(userRepo.findFollowerNumberByUsername(username));
        if(myself != null){
            if(!myself.equals(username)) {
                List<InCommonGenericDTO> inCommonFollowers = userRepo.findInCommonFollowers(myself, username);
                logger.warn(Long.toString(System.currentTimeMillis()));
                List<Tournament> inCommonTournaments = tournamentRepo.getInCommonTournaments(myself, username);
                logger.warn(Long.toString(System.currentTimeMillis()));
                List<TournamentDTO> tournamentDTOList = new ArrayList<>();
                for (Tournament t : inCommonTournaments) {
                    TournamentDTO tDTO = new TournamentDTO();
                    tDTO.setDate(t.getDate());
                    tDTO.setTournamentGame(t.getTournamentGame());
                    tDTO.setClosed(t.isClosed());
                    tournamentDTOList.add(tDTO);
                }
                userDTO.setInCommonFollowers(inCommonFollowers);
                userDTO.setInCommonTournaments(tournamentDTOList);
            } else {
                List<Tournament> myTournaments = tournamentRepo.getTournamentsByUser(myself);
                logger.warn(Long.toString(System.currentTimeMillis()));
                List<TournamentDTO> tournamentDTOList = new ArrayList<>();
                for (Tournament t : myTournaments) {
                    TournamentDTO tDTO = new TournamentDTO();
                    tDTO.setDate(t.getDate());
                    tDTO.setTournamentGame(t.getTournamentGame());
                    tDTO.setClosed(t.isClosed());
                    tournamentDTOList.add(tDTO);
                }
                userDTO.setInCommonTournaments(tournamentDTOList);
            }

            logger.warn(Long.toString(System.currentTimeMillis()));
            userDTO.setFollowed(userRepo.isFollowed(myself, username));
            logger.warn(Long.toString(System.currentTimeMillis()));
        }

        return userDTO;
    }
    public List<GameDTO> getSuggestedGames(String username){
        List<GameDTO> suggestedGames = userRepo.getSuggestedGames(username);
        if(suggestedGames.size() < 4){
            List<GameDTO> famousGames = gameRepo.getFamousGames();
            while(suggestedGames.size() < 4){
                for(GameDTO fam: famousGames){
                    boolean found = false;
                    for(GameDTO sugg: suggestedGames){
                        if(sugg.getName().equals(fam.getName())){
                            found=true;
                            break;
                        }
                    }
                    if(!found){
                        suggestedGames.add(fam);
                    }
                }
            }
        }
        return suggestedGames;
    }
    public List<GameDTO> getMostFamousGames() {
        return gameRepo.getFamousGames();
    }
    public List<UserDTO> getSuggestedUsers(String username){
        List<UserDTO> suggestedUsers = userRepo.getSuggestedUserByTournaments(username);
        if(suggestedUsers == null || suggestedUsers.size()<4){
            suggestedUsers = userRepo.getSuggestedUserByFollower(username);
            if(suggestedUsers == null || suggestedUsers.size()<4){
                suggestedUsers = userRepo.getFamousUsers();
            }
        }
        return suggestedUsers;
    }
    public List<UserDTO> getMostFamousUsers() {
        return userRepo.getFamousUsers();
    }
    public boolean isAdmin(String username){
        return userRepo.checkAdmin(username);
    }
    public boolean followUser(String myself, String username) {
        return userRepo.followUserByUsername(myself, username);
    }
    public boolean unfollowUser(String myself, String username) {
        return userRepo.unfollowUserByUsername(myself, username);
    }
}
