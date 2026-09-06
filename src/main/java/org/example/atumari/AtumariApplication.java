package org.example.atumari;

import org.example.atumari.festival.service.FestivalApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class AtumariApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtumariApplication.class, args);


        //TODO 나중에 서비스로빼서 스케쥴로바꿔야함
        FestivalApiService festivalApiService = new FestivalApiService();
        festivalApiService.fetchAndSaveFestivals();


    }


}
