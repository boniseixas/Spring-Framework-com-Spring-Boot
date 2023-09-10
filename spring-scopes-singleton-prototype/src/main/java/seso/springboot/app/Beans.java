package seso.springboot.app;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Beans {
    @Bean
    @Scope("prototype")
    public Remetente remetente() {
        System.out.println("Criando um objeto remetente");
        Remetente remetente = new Remetente();
        remetente.setEmail("seso@seso.com.br");
        remetente.setNome("Boni SeSo");
        return remetente;
    }
}
