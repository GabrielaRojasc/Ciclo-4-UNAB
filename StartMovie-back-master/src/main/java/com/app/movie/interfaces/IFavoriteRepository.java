/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.movie.interfaces;

import com.app.movie.entities.Favorite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IFavoriteRepository extends MongoRepository<Favorite, String> {
    @Query(value= "{moviesname  : ?0, clientemail : ?1}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    List<Favorite> getScoreByMoviesAndClient(String name, String email);

   @Query(value= "{$and:[{'movie.id' : ?0},{'client.id':?1}]}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
   List<Favorite> getScoreByMovieAndClient(String movieId,String clientId);
}
