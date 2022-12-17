package com.app.movie.service;

import com.app.movie.dto.ResponseDto;
import com.app.movie.dto.ScoreDto;
import com.app.movie.entities.Client;
import com.app.movie.entities.Movie;
import com.app.movie.entities.Score;
import com.app.movie.repository.ClientRepository;
import com.app.movie.repository.MovieRepository;
import com.app.movie.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    private final String SCORE_REGISTERED="La calificación debe ser un número entre 1 y 5";
    private final String SCORE_SUCCESS="Calificación guardada correctamente";

    //private final String SCORE_ERROR="la película o el usuario no existe";
    //    private final String SCORE_UPDATE="Calificación actualizada correctamente";
//    private final String SCORE_UPDATE="La pelicula ya fue calificada";

    @Autowired
    ScoreRepository repository;

    @Autowired
    ClientService clientService;


    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ClientRepository clientRepository;
    public Iterable<Score> get() {
        Iterable<Score> response = repository.getAll();
        return response;
    }
    public Score check(String movieId,String authorization) {

        Score score = new Score();
        Optional<Movie> movie = movieRepository.findById(movieId);
        Optional<Client> client = clientService.getByCredential(authorization);
        if(movie.isPresent() && client.isPresent()){
            List<Score> scores = repository.findByMovieAndClient(movie.get().getId(),client.get().getId());
            if(scores.size()>0){
                score = scores.get(scores.size()-1);
            }
        }

        return score;
    }

    public ResponseDto created(Score request) {
        ResponseDto response = new ResponseDto();
        List<Score> scoresClientandMovie = repository.getByMoviesAndClient(request.getMovie().getName(), request.getClient().getEmail());

        if(request.getScore().intValue()<1 || request.getScore().intValue()>5){
            response.status=false;
            response.message=SCORE_REGISTERED;


        }else{
            repository.save(request);
            response.status=true;
            response.message=SCORE_SUCCESS;
            response.id= request.getId();
        }

// if (scoresClientandMovie.size()>0){
//         response.status=false;
//         response.message=SCORE_ERROR;

        return response;


    }

    public ResponseDto create(ScoreDto request, String authorization) {
        ResponseDto response = new ResponseDto();
        response.status=false;
        if(request.score<0 || request.score>5){
            response.message="La calificación enviada no está dentro de los valores esperados";
        }else{
            Score score = new Score();
            Optional<Movie> movie = movieRepository.findById(request.movieId);
            Optional<Client> client = clientService.getByCredential(authorization);
            if(movie.isPresent() && client.isPresent()){
                score.setState("activo");
                score.setScore(request.score);
                score.setMovie(movie.get());
                score.setClient(client.get());
                repository.save(score);
                response.status=true;
                response.message="Calificación guardada correctamente";
                response.id= score.getId();
            }
        }
        return response;
    }


    public ResponseDto update(Score score, String ScoreId) {

        ResponseDto response = new ResponseDto();
        Optional<Score> currentScore = repository.findById(ScoreId);
        if (!currentScore.isEmpty()) {
            Score scoreToUpdate = new Score();
            scoreToUpdate = currentScore.get();
            scoreToUpdate.setScore(score.getScore());
            repository.save(scoreToUpdate);
            response.status=true;
            response.message="Se actualizó correctamente";
            response.id=ScoreId;
        }else{
            response.status=false;
            response.message="No se logró la actualización";
        }
        return response;
    }


    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}

