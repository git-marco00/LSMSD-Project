package it.unipi.BGnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BGnetApplication
{
	public String label;
	private static final BGnetApplication app = new BGnetApplication();

	public static BGnetApplication getInstance() {
		return app;
	}

	public static void main(String[] args)
	{
		SpringApplication.run(BGnetApplication.class, args);
	}

}
