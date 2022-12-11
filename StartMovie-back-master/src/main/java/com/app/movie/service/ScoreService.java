/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.movie.service;

import com.app.movie.dto.ResponseDto;
import com.app.movie.entities.Score;
import com.app.movie.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Iterable<Score> get() {
        Iterable<Score> response = repository.getAll();
        return response;
    }

    public ResponseDto create(Score request) {
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

    public Score update(Score score) {
        Score scoreToUpdate = new Score();

        Optional<Score> currentScore = repository.findById(score.getId());
        if (!currentScore.isEmpty()) {
            scoreToUpdate = score;
            scoreToUpdate=repository.save(scoreToUpdate);
        }
        return scoreToUpdate;

    }


    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}

