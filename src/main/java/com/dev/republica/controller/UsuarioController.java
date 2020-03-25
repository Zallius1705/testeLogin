package com.dev.republica.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.republica.model.Usuario;
import com.dev.republica.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
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
