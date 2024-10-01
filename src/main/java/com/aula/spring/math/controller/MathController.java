package com.aula.spring.math.controller;

import com.aula.spring.math.tools.NumberConverter;
import com.aula.spring.math.tools.SimpleMath;
import com.aula.spring.exception.UnsuportedException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/math")
public class MathController {

    private final AtomicLong counter = new AtomicLong();

    private SimpleMath math = new SimpleMath();
    @RequestMapping(value = "/sum/{nOne}/{nTwo}",
            method = RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "nOne") String nOne,
            @PathVariable(value = "nTwo") String nTwo)
            throws Exception{
        if(!NumberConverter.isNumeric(nOne) || !NumberConverter.isNumeric(nTwo)){
            throw new UnsuportedException("Please set a numeric value!");
        }
        return math.sum(NumberConverter.convertToDouble(nOne), NumberConverter.convertToDouble(nTwo));
    }

    @RequestMapping(value = "/sub/{nOne}/{nTwo}",
            method = RequestMethod.GET)
    public Double sub(
            @PathVariable(value = "nOne") String nOne,
            @PathVariable(value = "nTwo") String nTwo)
            throws Exception{
        if(!NumberConverter.isNumeric(nOne) || !NumberConverter.isNumeric(nTwo)){
            throw new UnsuportedException("Please set a numeric value!");
        }
        return math.sub(NumberConverter.convertToDouble(nOne), NumberConverter.convertToDouble(nTwo));
    }

    @RequestMapping(value = "/times/{nOne}/{nTwo}",
            method = RequestMethod.GET)
    public Double times(
            @PathVariable(value = "nOne") String nOne,
            @PathVariable(value = "nTwo") String nTwo)
            throws Exception{
        if(!NumberConverter.isNumeric(nOne) || !NumberConverter.isNumeric(nTwo)){
            throw new UnsuportedException("Please set a numeric value!");
        }
        return math.times(NumberConverter.convertToDouble(nOne), NumberConverter.convertToDouble(nTwo));
    }

    @RequestMapping(value = "/div/{nOne}/{nTwo}",
            method = RequestMethod.GET)
    public Double div(
            @PathVariable(value = "nOne") String nOne,
            @PathVariable(value = "nTwo") String nTwo)
            throws Exception{
        if(!NumberConverter.isNumeric(nOne) || !NumberConverter.isNumeric(nTwo)){
            throw new UnsuportedException("Please set a numeric value!");
        }
        return math.div(NumberConverter.convertToDouble(nOne), NumberConverter.convertToDouble(nTwo));
    }

    @RequestMapping(value = "/avg/{nOne}/{nTwo}",
            method = RequestMethod.GET)
    public Double avg(
            @PathVariable(value = "nOne") String nOne,
            @PathVariable(value = "nTwo") String nTwo)
            throws Exception{
        if(!NumberConverter.isNumeric(nOne) || !NumberConverter.isNumeric(nTwo)){
            throw new UnsuportedException("Please set a numeric value!");
        }
        return math.avg(NumberConverter.convertToDouble(nOne), NumberConverter.convertToDouble(nTwo));
    }

    @RequestMapping(value = "/square/{nOne}",
            method = RequestMethod.GET)
    public Double square(
            @PathVariable(value = "nOne") String nOne)
            throws Exception{
        if(!NumberConverter.isNumeric(nOne)){
            throw new UnsuportedException("Please set a numeric value!");
        }
        return math.square(NumberConverter.convertToDouble(nOne));
    }


}

