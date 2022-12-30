package com.bikKorova.springjwt.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UserStatisticView {
   // Long getId();
    String getUsername();
    @Value("#{target.getStatistic()}")
    int getStatistic();
}
