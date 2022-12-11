/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.movie.interfaces;

import com.app.movie.entities.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IClientRepository extends MongoRepository<Client, String> {
    @Query(value = "{email : ?0}")//SQL Equivalent : SELECT * FROM Client select * from Client where email=?
    List<Client> getClientsByEmail(String email);
    
}
