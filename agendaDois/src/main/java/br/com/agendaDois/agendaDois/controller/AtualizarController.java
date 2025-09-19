package br.com.agendaDois.agendaDois.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agendaDois.agendaDois.model.Cadastro;
import br.com.agendaDois.agendaDois.repository.CadastroRepository;

@Controller
public class AtualizarController {

	@Autowired
	public CadastroRepository cadastroRepository ;
	
	@GetMapping("/atualizar")
	public String atualizar(Model model) {
		model.addAttribute("cadastros",cadastroRepository.findAll()); 	
		
		return "atualizar";
	}
	
	// MÉTODO PARA EDITAR SOLICITAÇÃO (GET) 	
	@GetMapping("/editarAtualizar/{id}")
	public String editarAtualizar(@PathVariable("id") long id, Model model) {
	    Cadastro cadastro = cadastroRepository.findById(id).orElse(null);
	    if (cadastro != null) {
	        model.addAttribute("cadastro", cadastro);
	        return "atualizar"; // nome correto do arquivo HTML
	    }
	    return "redirect:/";         
	}

	
	//MÉTODO POST COMO FALLBACK PARA ATUALIZAÇÃO
	@PostMapping("/atualizarAtualizar/{id}")
	public String atualizarAtualizarPost(@PathVariable("id") long id, Cadastro cadastroAtualizado) {
		return atualizarAtualizar(id, cadastroAtualizado);
	}
	
	// MÉTODO PUT PARA ATUALIZAÇÃO
	@PutMapping("/atualizarAtualizar/{id}")
	public String atualizarAtualizar(@PathVariable("id") long id, Cadastro cadastroAtualizado) {
		Cadastro cadastroExistente = cadastroRepository.findById(id).orElse(null);
		
		if (cadastroExistente != null) {
			cadastroExistente.setId(cadastroAtualizado.getId());
			cadastroExistente.setNome(cadastroAtualizado.getNome());
			cadastroExistente.setEmail(cadastroAtualizado.getEmail());
			cadastroExistente.setTelefone(cadastroAtualizado.getTelefone());
			cadastroExistente.setCargo(cadastroAtualizado.getCargo());
			
			cadastroRepository.save(cadastroExistente);
			
		}
		return "redirect:/";
	}
	
	
	
}
