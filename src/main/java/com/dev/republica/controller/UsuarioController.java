package com.dev.republica.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dev.republica.model.Usuario;
import com.dev.republica.repository.UsuarioRepository;
import com.dev.republica.service.UserService;

@RestController
//@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
	}
	
    @GetMapping(value="/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        Usuario user = new Usuario();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value="/registration")
    public ModelAndView createNewUser(@Valid Usuario user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
      /*  Optional<Usuario> userExists = userService.findUsuarioByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {*/
            userService.save(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new Usuario());
            modelAndView.setViewName("registration");

        
        return modelAndView;
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> detalhar(@PathVariable Long id) {
		Optional<Usuario> topico = usuarioRepository.findById(id);
		
		if(topico.isPresent()) {
			return ResponseEntity.ok(topico.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}
