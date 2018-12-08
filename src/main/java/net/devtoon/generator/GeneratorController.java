package net.devtoon.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @GetMapping("/") String generate() {
        for (int i = 0; i < 10; i++) {
            generatorService.generateWork();
        }
        return "Success";
    }
}
