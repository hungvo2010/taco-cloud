package com.starter.springboot.controller;

import com.starter.springboot.model.Ingredient;
import com.starter.springboot.model.Ingredient.Type;
import com.starter.springboot.model.Taco;
import com.starter.springboot.model.TacoOrder;
import com.starter.springboot.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;
    @Autowired
    public DesignTacoController (IngredientRepository repo){
        this.ingredientRepository = repo;
    }
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        log.info("tacoOrder in ModelAttribute");
        TacoOrder sessionAtt = new TacoOrder();
        sessionAtt.setDeliveryCity("Quang Ngai");
        return sessionAtt;
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        log.info("taco in ModelAttribute");
        return new Taco();
    }

    @ModelAttribute
    public void addIngredientsToModel(@ModelAttribute("tacoOrder") TacoOrder tacoOrder, Model model) {
//        Logger.getLogger(DesignTacoController.class.getSimpleName()).log(Level.INFO, "hello");
        log.info("addIngredientsToModel");
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//        );
        List<Ingredient> ingredients = ingredientRepository.findAll();
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(HttpServletRequest req) {
//        log.info(((TacoOrder)req.getSession().getAttribute("tacoOrder")).getDeliveryCity());
        log.info(req.getServletPath());
//        TacoOrder tacoOrder = (TacoOrder) req.getSession().getAttribute("tacoOrder");
        Enumeration<String> sessionNames = req.getSession().getAttributeNames();
        while (sessionNames.hasMoreElements()){
            log.info(sessionNames.nextElement());
        }
//        log.info(tacoOrder.getDeliveryCity());
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()){
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
