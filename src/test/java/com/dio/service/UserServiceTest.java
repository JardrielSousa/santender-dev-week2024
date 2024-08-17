package com.dio.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.dio.model.Account;
import com.dio.model.Card;
import com.dio.model.User;
import com.dio.repository.UserRepository;
import com.dio.serviceImpl.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Test
	public void test01_CreateUserSucess() {
		
		
		User usuario = new User(1L,"jardriel",new Account(),new Card());

		when(userRepository.save(usuario)).thenReturn(usuario);
		
		var usuarioCriado = userRepository.save(usuario);
		
		assertEquals(usuarioCriado.getId(),1L);
		assertThat(usuarioCriado.getName()).isSameAs("jardriel");
		verify(userRepository).save(usuario);
	}
	
	@Test
	public void test02_FindUserSucess() {
		
		User usuarioInserido = new User(1L);

		when(userRepository.findById(usuarioInserido.getId())).thenReturn(Optional.of(usuarioInserido));
		
		var usuarioEncontrado = userRepository.findById(usuarioInserido.getId()).get();
		
		assertEquals(usuarioEncontrado.getId(),1L);
		verify(userRepository).findById(usuarioInserido.getId());
	}
	
	@Test
	public void test03_CreateUserErrorAccountNumberExists() {

		User usuario = new User(1L,"jardriel",new Account(),new Card());
        when(userRepository.existsByAccountNumber(usuario.getAccount().getNumber())).thenReturn(true);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			userServiceImpl.createUser(usuario);
	    });
	    assertTrue(exception.getMessage().contains("This account number already exists."));
	}
	
	@Test
	public void test04_FindUserNotFound() {
		
		User usuarioInserido = new User(2L);

		when(userRepository.findById(usuarioInserido.getId())).thenReturn(Optional.empty());
				
		Exception exception = assertThrows(NoSuchElementException.class, () -> {
			userServiceImpl.findById(usuarioInserido.getId());
	    });
	    assertEquals(exception.getClass(),NoSuchElementException.class);
	}
}
