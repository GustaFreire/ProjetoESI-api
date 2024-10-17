package br.usp.esi.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discente")
public class DiscenteController {

    @GetMapping
    public void teste() {
        System.out.println("usuario possui acesso pra chamar o discente!");
    }

    @PostMapping("/teste")
    public void teste2() {
        System.out.println("usuario possui acesso pra chamar o discente2!");
    }
}