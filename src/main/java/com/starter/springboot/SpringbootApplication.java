package com.starter.springboot;

import com.starter.springboot.model.Ingredient;
import com.starter.springboot.model.Ingredient.Type;
import com.starter.springboot.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"com.starter.springboot.*"})
@Slf4j
public class SpringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	public ApplicationRunner dataLoader(IngredientRepository repo){
		return args -> {
			List<String> sample = args.getOptionValues("Dzappname");
			sample.forEach(System.out::println);
			if (sample == null) log.info("NULL test");
			log.info(args.getOptionNames().toString());
//			repo.save (new Ingredient("CAND", "Cong An Nhan Dan", Ingredient.Type.PROTEIN));
			repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
			repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
			repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
			repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
			repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
			repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
			repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
			repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
			repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
			repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		};
	}
}
