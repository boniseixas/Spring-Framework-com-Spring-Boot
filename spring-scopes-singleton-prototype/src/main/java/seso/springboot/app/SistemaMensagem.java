package seso.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SistemaMensagem {
    @Autowired
    private Remetente seso;
    @Autowired
    private Remetente techTeam;

    public void  enviarConfirmacaoCadastro() {
        System.out.println(seso);
        System.out.println("Seu cadastro foi aprovado");
    }

    public void enviarMensagemBoasVindas() {
        techTeam.setEmail("tech@seso.como.br");
        System.out.println(techTeam);
        System.out.println("Bem vindo à SeSo Tech");
    }
}
