/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.movie.repository;


import com.app.movie.entities.Favorite;
import com.app.movie.entities.Movie;
import com.app.movie.interfaces.IFavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class FavoriteRepository {

    @Autowired
    IFavoriteRepository repository;

    public Iterable<Favorite> getAll(){
        return repository.findAll();
    }

    public Optional<Favorite> findById(String id){
        Optional<Favorite> response= repository.findById(id);
        return response;
    }

    public List<Favorite> getByMoviesAndClient(String name, String email){
        return repository.getScoreByMoviesAndClient(name, email);
        }
    public List<Favorite> findByMovieAndClient(String movieId,String clientId){
        List<Favorite> response= repository.getScoreByMovieAndClient(movieId,clientId);
        return response;
    }

    public Boolean existsById(String id){
        return repository.existsById(id);
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }

    public Favorite save(Favorite favorite){
        return repository.save(favorite);
    }



}
