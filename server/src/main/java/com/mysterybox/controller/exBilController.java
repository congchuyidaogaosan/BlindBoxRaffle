package com.mysterybox.controller;

import com.mysterybox.common.Result;
import com.mysterybox.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("ex")
@RestController
public class exBilController {

    @Autowired
    private OrderService orderService;



    @GetMapping("mother")
    public Result mother(){



        return  orderService.list();

    }

}
