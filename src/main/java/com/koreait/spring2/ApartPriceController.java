package com.koreait.spring2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/apart")
public class ApartPriceController {
    @Autowired
    private ApartPriceService service;

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("locationList",service.selLocationCodeList());
        return "apart/main";
    }

    @GetMapping("/result")
    public String result() {
        return "result";
    }

    @PostMapping("/result")
    public String result(SearchDTO param) {
        System.out.println("param  : " + param);
        service.saveData(param);
        return "redirect:/result";
    }
}
