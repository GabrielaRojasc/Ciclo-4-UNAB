package com.app.movie.controller;

import com.app.movie.dto.FavoriteDto;
import com.app.movie.dto.ResponseDto;
import com.app.movie.entities.Favorite;
import com.app.movie.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/favorite")
@CrossOrigin(origins = "*")
public class FavoriteController {

    @Autowired
    FavoriteService service;

    @GetMapping("")
    public Iterable<Favorite> getAll() {
        return service.getAll();
    }
    @GetMapping("/{idclient}/{idMovie}")
    public FavoriteDto get(@PathVariable("idclient") String idclient , @PathVariable("idMovie") String idMovie ) {
        return service.get(idclient,idMovie);
    }

    @GetMapping("/check/{movieId}")
    public Favorite check(@PathVariable("movieId") String movieId,@RequestHeader(value="authorization") String authorization) {
        return service.check(movieId,authorization);
    }

//    @PostMapping("")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseDto create(@RequestBody Score request) {
//       return service.create(request);
//    }
/*
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto create(@RequestBody FavoriteDto request, @RequestHeader(value="authorization") String authorization) {
        return service.create(request,authorization);
   }

 */
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto create(@RequestBody Favorite request) {
        return service.create(request);
    }

   // @PostMapping("")
 //   @ResponseStatus(HttpStatus.CREATED)
 //   public ResponseEntity<ResponseDto> create(@RequestBody Score request) {
 //       ResponseDto responseDto = service.create(request);
  //      ResponseEntity<ResponseDto> responseS = new ResponseEntity<>(responseDto,HttpStatus.CONFLICT);

  //      if(responseDto.status.booleanValue()==true){
   //         responseS = new ResponseEntity<>(responseDto,HttpStatus.CREATED);
  //      }
  //      return responseS;
  //  }

/*
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseDto update(@PathVariable("id") String id,@RequestBody Score request) {
        return service.update(request,id);
    }
*/
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

}

