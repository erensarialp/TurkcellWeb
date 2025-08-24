package com.turkcell.intro.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //Annotation -> Ozellik kazandirma.
public class Application {

	public static void main(String[] args)
    {
        //Spring bilesenleri konsol acildiginda baslatilsin.
		SpringApplication.run(Application.class, args);
	}


}
