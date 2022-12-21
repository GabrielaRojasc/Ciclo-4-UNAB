package com.app.movie.service;

import com.app.movie.dto.FavoriteDto;
import com.app.movie.dto.ResponseDto;
import com.app.movie.entities.Client;
import com.app.movie.entities.Favorite;
import com.app.movie.entities.Movie;
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

    private final String MyFavorites_REGISTERED="Movie no guardada";
    private final String MyFavorites_SUCCESS="Movie guardada correctamente";


    @Autowired
    FavoriteRepository repository;
    @Autowired
    ClientService clientService;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ClientRepository clientRepository;

    public Iterable<Favorite> get() {
        Iterable<Favorite> response = repository.getAll();
        return response;
    }
    public Favorite check(String movieId,String authorization) {

        Favorite MyFavorites = new Favorite();
        Optional<Movie> movie = movieRepository.findById(movieId);
        Optional<Client> client = clientService.getByCredential(authorization);
        if(movie.isPresent() && client.isPresent()){
            List<Favorite> MyFavoritess = repository.findByMovieAndClient(movie.get().getId(),client.get().getId());
            if(MyFavoritess.size()>0){
                MyFavorites = MyFavoritess.get(MyFavoritess.size()-1);
            }
        }

        return MyFavorites;
    }


    public ResponseDto created (Favorite request) {
        ResponseDto response = new ResponseDto();
        List<Favorite> MyFavoritessClientandMovie = repository.getByMoviesAndClient(request.getMovie().getName(), request.getClient().getEmail());

        if (MyFavoritessClientandMovie != null) {
            response.status = false;
            response.message = MyFavorites_REGISTERED;

        } else {
            repository.save(request);
            response.status = true;
            response.message = MyFavorites_SUCCESS;
            response.id = request.getId();
        }
    }

        public Iterable<Movie> getMyFavoritesByName () {
            Iterable<Movie> responseMovies;

            Iterable<Movie> movies = movieRepository.getAll();
            List<Favorite> favorites = new ArrayList<>();
            List<Movie> MovieFavorites = new ArrayList<>();
            for (Movie movie : movies) {
                if (movie.getName() != null) {
                    for (Favorite favo : favorite ) {
                        Movie mov = new Movie();
                        mov.setId(movie.getId());
                        mov.setName(movie.getName());
                        mov.setTrailerLink(movie.getTrailerLink());
                        mov.setImageLink(movie.getImageLink());
                        mov.setDescription(movie.getDescription());
                        mov.setCategories(movie.getCategories());
                        mov.setStaffList(movie.getStaffList());
                        MovieFavorites.add(mov);

                    }
                }
            }
            return MovieFavorites;
        }
    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }

        public ResponseDto create(FavoriteDto request, String authorization) {
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

    /*
        public ResponseDto update(MyFavorites myFavorites, String MyFavoritesId) {

            ResponseDto response = new ResponseDto();
            Optional<MyFavorites> currentMyFavorites = repository.findById(MyFavoritesId);
            if (!currentMyFavorites.isEmpty()) {
                MyFavorites MyFavoritesToUpdate = new MyFavorites();
                MyFavoritesToUpdate = currentMyFavorites.get();
                MyFavoritesToUpdate.setMyFavorites(MyFavorites.getMyFavorites());
                repository.save(MyFavoritesToUpdate);
                response.status=true;
                response.message="Se actualizó correctamente";
                response.id=MyFavoritesId;
            }else{
                response.status=false;
                response.message="No se logró la actualización";
            }
            return response;
        }

    */

    // if (MyFavoritessClientandMovie.size()>0){
//         response.status=false;
//         response.message=MyFavorites_ERROR;

    //      return response;
    // }



}
