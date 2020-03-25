package com.dev.republica.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.republica.model.Usuario;
import com.dev.republica.repository.UsuarioRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> findUsuarioByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}


}
