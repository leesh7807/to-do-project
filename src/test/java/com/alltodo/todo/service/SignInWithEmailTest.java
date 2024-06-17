package com.alltodo.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;

@SpringBootTest
public class SignInWithEmailTest {
    @Autowired
    private AuthenticationManager authenticationManager;
    public void test() {

    }
}