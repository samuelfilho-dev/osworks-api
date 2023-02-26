package com.albino.tecnologia.osworks.bean;

import com.albino.tecnologia.osworks.model.Empresa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    Empresa empresa(){
        return new Empresa();
    }

}
