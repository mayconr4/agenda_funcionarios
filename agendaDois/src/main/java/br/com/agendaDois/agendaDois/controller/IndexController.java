package br.com.agendaDois.agendaDois.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.agendaDois.agendaDois.repository.CadastroRepository;

@Controller
public class IndexController {
	
	/*@GetMapping("/")
	public String index() {
		return "index"; // caso não fosse listar os dados somente á pagina sem listar
	}*/
	
	@Autowired
	public CadastroRepository cadastroRepository;
	
	@GetMapping("/")
	public String index(Model model) {
	    model.addAttribute("cadastros", cadastroRepository.findAll());
	    return "index"; // exibe á pagina e ao mesmo tempo lista os dados
	}

}
