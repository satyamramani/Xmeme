package com.satyam.xmeme;

import com.satyam.xmeme.controller.XmemeController;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
public class XmemeApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmemeApplication.class, args);
	}

	@Bean
	@Scope("prototype")
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
