/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.oauth2.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aldwin Delgado
 */
@SpringBootApplication
@EnableResourceServer
@EnableWebSecurity
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Application extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("{\"message\":\"No scope\"}", HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('write')")
    @RequestMapping(
            value = "/write",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> write() {
        return new ResponseEntity<>("{\"message\":\"Write only scope\"}", HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('bogus')")
    @RequestMapping(
            value = "/bogus",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> bogus() {
        return new ResponseEntity<>("{\"message\":\"Bogus only scope\"}", HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(
            value = "/read",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> read() {
        return new ResponseEntity<>("{\"message\":\"Read only scope\"}", HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('read') OR #oauth2.hasScope('bogus')")
    @RequestMapping(
            value = "/read-and-bogus",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> readAndBogus() {
        return new ResponseEntity<>("{\"message\":\"Read and Bogus scopes\"}", HttpStatus.OK);
    }
}
