package com.bikKorova.springjwt.controllers;

import com.bikKorova.springjwt.models.User;
import com.bikKorova.springjwt.repository.UserRepository;
import com.bikKorova.springjwt.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/game")
public class GameController {
  @Autowired
  JwtUtils jwtUtils;
  @Autowired
  UserRepository userRepository;

  User user;
  String serverDigit;
  @GetMapping("/statistic")
  public ResponseEntity<List> getStatistic() {
    return ResponseEntity.ok(userRepository.getAllBy());
  }

  @GetMapping("/equals/{clientDigit}")
  public String equalsResult(@PathVariable String clientDigit,@RequestHeader("Authorization") String authHeader) {

    if(serverDigit.equals(clientDigit))
    {
      user.riseSuccesAttempCount();
      userRepository.save(user);
      return "Вы угадали число!";
    }
    user.riseAttempCount();
    userRepository.save(user);

    char[] arrDigitClient=clientDigit.toCharArray();
    char[] arrDigitServer=serverDigit.toCharArray();
    int countKorova=0;
    int countBik=0;

    //сколько цифр угадано без учета позиции (корова)
    for (char digit:arrDigitClient)
      if(serverDigit.contains(digit+""))
        countKorova++;

    //сколько цифр угадано c учетом позиции (бык)
    for(int i=0;i<arrDigitClient.length;i++)
      if(arrDigitClient[i]==arrDigitServer[i])
        countBik++;

    return countBik+"Б"+countKorova+"К";
  }

  @GetMapping("/generateDigit")
  @PreAuthorize("hasRole('USER')")
  public String generateDigit(@RequestHeader("Authorization") String authHeader) {
    user = userRepository.findUserByUsername(jwtUtils.getUserNameFromJwtToken(authHeader.substring(7, authHeader.length())));
    serverDigit="";
    ArrayList<Integer> arrDigit = new ArrayList<>();
    for ( int i = 0; i < 10; i++ )
      arrDigit.add(i);

    Collections.shuffle(arrDigit);

    int rnd =(int)Math.random()*7;
    for(int i = rnd;i<rnd+4;i++)
      serverDigit+=arrDigit.get(i);

    System.out.println(serverDigit);
    return "Угaдай число";
  }

  /*@GetMapping("/user")
  @PreAuthorize("hasRole('USER')")
  public String userAccess(@RequestHeader("Authorization") String authHeader) {

    System.out.println(jwtUtils.getUserNameFromJwtToken(authHeader.substring(7, authHeader.length())));
    user = userRepository.findUserByUsername(jwtUtils.getUserNameFromJwtToken(authHeader.substring(7, authHeader.length())));
    serverDigit="";
    ArrayList<Integer> arrDigit = new ArrayList<>();
    for ( int i = 0; i < 10; i++ )
      arrDigit.add(i);

    Collections.shuffle(arrDigit);
    int rnd =(int)Math.random()*7;
    for(int i = rnd;i<rnd+4;i++)
      serverDigit+=arrDigit.get(i);
    System.out.println(serverDigit);
    return "Угaдай число";
  }*/
}
