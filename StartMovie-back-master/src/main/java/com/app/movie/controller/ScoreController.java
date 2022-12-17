package com.app.movie.controller;

import com.app.movie.dto.ResponseDto;
import com.app.movie.dto.ScoreDto;
import com.app.movie.entities.Score;
import com.app.movie.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/score")
@CrossOrigin(origins = "*")
public class ScoreController {

    @Autowired
    ScoreService service;

    @GetMapping("")
    public Iterable<Score> get() {
        return service.get();
    }
    @GetMapping("/check/{movieId}")
    public Score check(@PathVariable("movieId") String movieId,@RequestHeader(value="authorization") String authorization) {
        return service.check(movieId,authorization);
    }

//    @PostMapping("")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseDto create(@RequestBody Score request) {
//       return service.create(request);
//    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto create(@RequestBody ScoreDto request, @RequestHeader(value="authorization") String authorization) {
        return service.create(request,authorization);
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


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseDto update(@PathVariable("id") String id,@RequestBody Score request) {
        return service.update(request,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

}

