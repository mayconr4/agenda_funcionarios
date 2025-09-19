package br.com.agendaDois.agendaDois.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agendaDois.agendaDois.model.Cadastro;
import br.com.agendaDois.agendaDois.repository.CadastroRepository;

@Controller
public class DeletarController {

    @Autowired
    private CadastroRepository cadastroRepository;

    @GetMapping("/deletar")
    public String deletar(Model model) {
        model.addAttribute("cadastro", cadastroRepository.findAll());
        return "deletar";
    }

    @PostMapping("/excluirCadastro/{id}")
    public String excluirCadastro(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        try {
            Cadastro cadastro = cadastroRepository.findById(id).orElse(null);
            if (cadastro != null) {
                cadastroRepository.delete(cadastro);
                redirectAttributes.addFlashAttribute("mensagem", "Funcionário excluído com sucesso!");
            } else {
                redirectAttributes.addFlashAttribute("erro", "Funcionário não encontrado!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir funcionário: " + e.getMessage());
        }
        return "redirect:/";
    }
}
