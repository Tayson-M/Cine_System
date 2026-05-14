package br.edu.faculdade.cinema.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNotFoundException.class)
    public String handleNotFound(UsuarioNotFoundException ex, Model model) {
        model.addAttribute("erro", ex.getMessage());
        return "erro";
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public String handleRegra(RegraDeNegocioException ex, Model model) {
        model.addAttribute("erro", ex.getMessage());
        return "erro";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneric(Exception ex, Model model) {
        model.addAttribute("erro", "Ocorreu um erro inesperado: " + ex.getMessage());
        return "erro";
    }
}
