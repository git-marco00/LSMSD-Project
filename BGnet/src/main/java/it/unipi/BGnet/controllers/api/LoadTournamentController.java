package it.unipi.BGnet.controllers.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unipi.BGnet.DTO.TournamentDTO;
import it.unipi.BGnet.Utilities.SessionVariables;
import it.unipi.BGnet.model.Tournament;
import it.unipi.BGnet.service.pages.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@SessionAttributes("sessionVariables")
public class LoadTournamentController {
    @Autowired
    TournamentService tournamentService;

    @GetMapping("/api/loadTournamentPage")
    public @ResponseBody String loadTournamentPage(Model model) {
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        List<TournamentDTO> tournamentDTOList = new ArrayList<>();
        for (Tournament t : tournamentService.getTournamentsByGamename(sv.gameToDisplay)) {
            TournamentDTO tournamentDTO = new TournamentDTO();
            tournamentDTO.setId(t.getId());
            tournamentDTO.setDate(t.getDate());
            tournamentDTO.setDuration(t.getDuration());
            tournamentDTO.setMaxPlayers(t.getMaxPlayers());
            tournamentDTO.setModalities(t.getModalities());
            tournamentDTO.setPlayersPerMatch(t.getPlayersPerMatch());
            tournamentDTO.setNumPartecipants(t.getPartecipants().size());
            tournamentDTO.setTournamentGame(t.getTournamentGame());
            tournamentDTO.setCreator(t.getCreator());
            tournamentDTO.setClosed(t.isClosed());
            tournamentDTO.setParticipating(false);
            for(String partecipant: t.getPartecipants()){
                if(partecipant.equals(sv.myself)){
                    tournamentDTO.setParticipating(true);
                    break;
                }
            }
            tournamentDTO.setIsCreator(t.getCreator().equals(sv.myself));
            tournamentDTOList.add(tournamentDTO);
        }
        Gson gson = new Gson();
        return gson.toJson(tournamentDTOList);
    }

    @GetMapping("/api/loadTournamentParticipants")
    public @ResponseBody String loadTournamentsPartecipants(Model model, @RequestParam(value="tid") int tid){
        List<String> listPartecipants = tournamentService.getPartecipantsByTournamentId(tid);
        Gson gson = new Gson();
        return gson.toJson(listPartecipants);
    }

    @GetMapping("/api/participateToTournament")
    public @ResponseBody boolean participateToTournament(Model model, @RequestParam(value="tid") int tid){
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        return tournamentService.addTournamentPartecipant(sv.myself, tid);
    }

    @GetMapping("/api/closeTournament")
    public @ResponseBody boolean closeTournament(Model model, @RequestParam(value="tid") int tid){
        return tournamentService.closeTournament(tid);
    }

    @GetMapping("/api/quitTournament")
    public @ResponseBody boolean quitTournament(Model model, @RequestParam(value="tid") int tid){
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        return tournamentService.removeTournamentPartecipant(sv.myself, tid);
    }

    @GetMapping("/api/createTournament")
    public @ResponseBody boolean createTournament(Model model, @RequestParam(value="date") String date,
                                                  @RequestParam(value="duration") String duration,
                                                  @RequestParam(value="maxPlayers") int maxPlayers,
                                                  @RequestParam(value="modalities") String modalities,
                                                  @RequestParam(value="playersPerMatch") String playersPerMatch){
        SessionVariables sv =(SessionVariables) model.getAttribute("sessionVariables");

        return tournamentService.addTournament(sv.myself, sv.gameToDisplay, date, duration, maxPlayers, modalities, playersPerMatch);
    }
}
