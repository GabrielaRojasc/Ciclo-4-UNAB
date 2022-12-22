package com.app.movie.dto;

import com.app.movie.entities.Category;
import com.app.movie.entities.Staff;

import java.util.List;

public class FavoriteDto {
    public String state;
    public String movieId;
    public String movieName;
    public String movieTrailer;
    public String movieImagen;
    public String movieDescipcion;
    public List<Category> movieCategory;
    public List<Staff> moviestaffList;


}
