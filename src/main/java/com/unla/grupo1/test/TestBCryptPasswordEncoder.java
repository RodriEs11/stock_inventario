package com.unla.grupo1.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCryptPasswordEncoder {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
		
		System.out.println(pass.encode("1234"));

	}

}
