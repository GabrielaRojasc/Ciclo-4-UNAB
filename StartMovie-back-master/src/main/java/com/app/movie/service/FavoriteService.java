package com.app.movie.service;

import com.app.movie.dto.FavoriteDto;
import com.app.movie.dto.ReportClientDto;
import com.app.movie.dto.ResponseDto;
import com.app.movie.entities.*;
import com.app.movie.repository.ClientRepository;
import com.app.movie.repository.FavoriteRepository;
import com.app.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    private final String Favorite_REGISTERED = "Movie no guardada";
    private final String Favorite_SUCCESS = "Movie guardada correctamente";


    @Autowired
    FavoriteRepository repository;

    @Autowired
    ClientService clientService;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ClientRepository clientRepository;

    public Iterable<Favorite> getAll() {
        Iterable<Favorite> response = repository.getAll();
        return response;
    }

    public Favorite check(String movieId, String authorization) {

        Favorite MyFavorites = new Favorite();
        Optional<Movie> movie = movieRepository.findById(movieId);
        Optional<Client> client = clientService.getByCredential(authorization);
        if (movie.isPresent() && client.isPresent()) {
            List<Favorite> MyFavoritess = repository.findByMovieAndClient(movie.get().getId(), client.get().getId());
            if (MyFavoritess.size() > 0) {
                MyFavorites = MyFavoritess.get(MyFavoritess.size() - 1);
            }
        }

        return MyFavorites;
    }


    public ResponseDto create(Favorite request) {
        Favorite newFavorite = repository.save(request);
        ResponseDto responseDto = new ResponseDto();
        responseDto.status = true;
        responseDto.message = "movie añadida a la lista correctamente";
        responseDto.id = newFavorite.getId();
        return responseDto;

    }

    public FavoriteDto get(String idclient, String idMovie) {
        FavoriteDto reporte = new FavoriteDto();
        Iterable<Favorite> favoritee = repository.getAll();
        for (Favorite favorite : favoritee) {
            if ( favorite.getClient().equals(idclient)) {
                Optional<Movie> movie = movieRepository.findById(idMovie);
                reporte.movieId = movie.get().getId();
                reporte.movieName = movie.get().getName();
                reporte.movieDescipcion = movie.get().getDescription();
                reporte.movieImagen = movie.get().getImageLink();
                reporte.movieTrailer = movie.get().getTrailerLink();
                reporte.movieCategory = movie.get().getCategories();
                reporte.moviestaffList = movie.get().getStaffList();

            }
        }
        return reporte;
    }
    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}

 /*

    public Iterable<Favorite> gettt(String idClient) {
        Iterable<Favorite> response;

        Iterable<Favorite> favoritee = repository.getAll();
        List<Movie> movie = new ArrayList<>();
        List<Favorite> fav = new ArrayList();
        for (Favorite favorite : favoritee) {
            if (favorite.getClient() != null && favorite.getMovie() != null ) {
                for ( Movie cat : favorite.getMovie()) {

                        Movie mov = new Movie();
                        mov.setId(movie.getId());
                        mov.setName(movie.getName());
                        mov.setTrailerLink(movie.getTrailerLink());
                        mov.setImageLink(movie.getImageLink());
                        mov.setDescription(movie.getDescription());
                        mov.setCategories(movie.getCategories());
                        mov.setStaffList(movie.getStaffList());
                        Moviesfavorites.add(mov);;
                    }
                }
            }
            return Moviesfavorites;
        }

    }



    public ResponseDto createFront (FavoriteDto request, String authorization) {
        ResponseDto response = new ResponseDto();
        response.status=false;

                Favorite MyFavorites = new Favorite();
                Optional<Movie> movie = movieRepository.findById(request.movieId);
                Optional<Client> client = clientService.getByCredential(authorization);
                if(movie.isPresent() && client.isPresent()){
                    MyFavorites.setState("activo");
                    MyFavorites.setFavorite(request.Favorite);
                    MyFavorites.setMovie(movie.get());
                    MyFavorites.setClient(client.get());
                    repository.save(MyFavorites);
                    response.status=true;
                    response.message="Calificación guardada correctamente";
                    response.id= MyFavorites.getId();
                }

            return response;
        }




}
*/