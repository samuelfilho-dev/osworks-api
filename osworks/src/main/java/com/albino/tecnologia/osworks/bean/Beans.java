package com.albino.tecnologia.osworks.bean;

import com.albino.tecnologia.osworks.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    Empresa empresa() {
        return new Empresa();
    }

    @Bean
    Contrato contrato() {
        return new Contrato();
    }

    @Bean
    Responsavel responsavel() {
        return new Responsavel();
    }

    @Bean
    Endereco endereco() {
        return new Endereco();
    }

    @Bean
    Projeto projeto() {
        return new Projeto();
    }

    @Bean
    OS os() {
        return new OS();
    }

    @Bean
    Role role(){
        return new Role();
    }

    @Bean
    Usuario usuario(){
        return new Usuario();
    }


}
