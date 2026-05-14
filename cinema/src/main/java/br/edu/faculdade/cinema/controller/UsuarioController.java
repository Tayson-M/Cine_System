package br.edu.faculdade.cinema.controller;

import br.edu.faculdade.cinema.dto.UsuarioRequestDTO;
import br.edu.faculdade.cinema.model.Cargo;
import br.edu.faculdade.cinema.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("usuario", new UsuarioRequestDTO());
        model.addAttribute("cargos", Cargo.values());
        return "usuarios/form";
    }

    @PostMapping
    public String cadastrar(
            @Valid @ModelAttribute("usuario") UsuarioRequestDTO dto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("cargos", Cargo.values());
            return "usuarios/form";
        }

        String senhaCodificada = passwordEncoder.encode(dto.getSenha());
        usuarioService.cadastrar(dto, senhaCodificada);
        redirectAttributes.addFlashAttribute("sucesso", "Usuário '" + dto.getLogin() + "' cadastrado com sucesso!");
        return "redirect:/usuarios";
    }

    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        usuarioService.deletar(id);
        redirectAttributes.addFlashAttribute("sucesso", "Usuário removido com sucesso!");
        return "redirect:/usuarios";
    }
}