package br.com.agendaDois.agendaDois.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.agendaDois.agendaDois.repository.CadastroRepository;

@Controller
public class listaAtualizarController {
	
	@Autowired 
	private CadastroRepository cadastroRepository;
	
	@GetMapping("/listaAtualizar")
    public String deletar(Model model) {
        model.addAttribute("cadastro", cadastroRepository.findAll());
        return "listaAtualizar";
    }
}
