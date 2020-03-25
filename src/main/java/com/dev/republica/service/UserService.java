package com.dev.republica.service;

import java.util.Optional;

import com.dev.republica.model.Usuario;

public interface UserService {

	Usuario save(Usuario usuario);

	Optional<Usuario> findUsuarioByUsername(String username);

}
