package it.unipi.BGnet.service.pages;

import it.unipi.BGnet.model.Tournament;
import it.unipi.BGnet.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mainTournamentService")
public class TournamentService {
    TournamentRepository tournamentRepository = new TournamentRepository();
    public boolean addTournament(String creator, String gamename, String date, String duration, int maxPlayers, String modalities, String playersPerMatch){
        return tournamentRepository.createTournament(maxPlayers, date, modalities, playersPerMatch, duration, gamename, creator);
    }

    public boolean closeTournament(String tournamentId){
        return tournamentRepository.closeTournament(tournamentId);
    }

    public boolean addTournamentPartecipant(String username, String tournamentId){
        return tournamentRepository.addTournamentPartecipant(username, tournamentId);
    }

    public List<Tournament> getTournamentsByGamename(String gamename){
        List<Tournament> tournamentList = tournamentRepository.getTournamentsByGamename(gamename);
        for (Tournament t: tournamentList){
            t.setPartecipants(tournamentRepository.getPartecipantsByTournamentId(t.getId()));
            t.setCreator(tournamentRepository.getCreatorByTournamentId(t.getId()));
        }
        return tournamentList;
    }

    public List<Tournament> getInCommonTournaments(String userA, String userB){
        List<Tournament> tournamentList = tournamentRepository.getInCommonTournaments(userA, userB);
        for (Tournament t: tournamentList){
            t.setPartecipants(tournamentRepository.getPartecipantsByTournamentId(t.getId()));
            t.setCreator(tournamentRepository.getCreatorByTournamentId(t.getId()));
            t.setTournamentGame(tournamentRepository.getGameByTournamentId(t.getId()));
        }
        return tournamentList;
    }

    public List<Tournament> getTournamentsByUser(String username){
        List<Tournament> tournamentList = tournamentRepository.getTournamentsByUser(username);
        for (Tournament t: tournamentList){
            t.setPartecipants(tournamentRepository.getPartecipantsByTournamentId(t.getId()));
            t.setCreator(tournamentRepository.getCreatorByTournamentId(t.getId()));
            t.setTournamentGame(tournamentRepository.getGameByTournamentId(t.getId()));
        }
        return tournamentList;
    }

    public boolean isParticipating(String username, String tournamentId){
        return tournamentRepository.isParticipating(username, tournamentId);
    }

    public boolean isCreator(String username, String tournamentId){
        return tournamentRepository.isCreator(username, tournamentId);
    }
}
