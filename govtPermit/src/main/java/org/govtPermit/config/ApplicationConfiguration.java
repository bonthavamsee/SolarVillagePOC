package org.govtPermit.config;

import org.govtPermit.controller.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.govtPermit.controller")
public class ApplicationConfiguration {
}