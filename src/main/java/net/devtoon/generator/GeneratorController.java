package net.devtoon.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @RequestMapping(name = "/", method = RequestMethod.GET)
    @ResponseBody String generate() {
        StringBuilder responseBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            responseBuilder.append(generatorService.generateWork());
            if (i < 9) {
                responseBuilder.append(System.lineSeparator());
            }
        }
        return responseBuilder.toString();
    }
}
