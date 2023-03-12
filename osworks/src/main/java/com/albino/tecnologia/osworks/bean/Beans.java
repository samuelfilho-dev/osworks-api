package com.albino.tecnologia.osworks.bean;

import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.model.Empresa;
import com.albino.tecnologia.osworks.model.Endereco;
import com.albino.tecnologia.osworks.model.Responsavel;
import com.albino.tecnologia.osworks.model.OS;
import com.albino.tecnologia.osworks.model.Projeto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    Empresa empresa() {
        return new Empresa();
    }

    Contrato contrato() {
        return new Contrato();
    }

    Responsavel responsavel() {
        return new Responsavel();
    }

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

}
