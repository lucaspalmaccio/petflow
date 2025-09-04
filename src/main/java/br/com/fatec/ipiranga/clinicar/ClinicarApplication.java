package br.com.fatec.ipiranga.clinicar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// Adiciona esta linha para garantir que os repositórios no pacote 'repository' sejam encontrados.
@EnableJpaRepositories(basePackages = "br.com.fatec.ipiranga.clinicar.repository")
public class ClinicarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicarApplication.class, args);
    }

}

