package com.example.demo.controller;

import com.example.demo.entity.Bag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String helloWorld(){
        return "hello";
    }

    @RequestMapping("/thymeleaf")
    public String thyme(){
        return "thyme";
    }

    @RequestMapping("/thymeleaf2")
    public String thyme2(){
        return "thyme2";
    } //뷰페이지를 리턴할때 변수에 값 전달 data1


    @RequestMapping("/thymeleaf3")
    public String thyme3(Model model){

        //뷰페이지단으로 model을 같이 전달
        model.addAttribute("data1","홍길동 model로 전달");
        return "thyme3";
    }

    @RequestMapping("/thymeleaf4")
    public ModelAndView thyme4(ModelAndView mav){

        //뷰페이지단으로 ModelAndView를 이용, 데이터를 전달
        // 1) 데이터를 지정
        // 2) 뷰페이지를 지정
        // 3) 리턴

        mav.addObject("name", "이순신");
        mav.addObject("age", 33);
        mav.setViewName("thyme4");
        return mav;
    }

    //단순 요청 + 뷰페이지로 데이터(Model) 전달 + @PathVariable 이용 파라미터 전달
    @RequestMapping("/thymeleaf5/{num}")
    public String thyme5(@PathVariable int num, Model model){

        //뷰페이지단으로 ModelAndView를 이용, 데이터를 전달
        // 1) 데이터를 지정
        // 2) 뷰페이지를 지정
        // 3) 리턴
        int result = 0;

        for (int i=1; i<=num; i++){
           result+=i;
        }
        System.out.println(num);

        //뷰페이지로 결과 전달
        model.addAttribute("result", result);
        model.addAttribute("num", num);
        return "thyme5";
    }

    @RequestMapping("/thymeleaf6/{num}")
    public String thyme6(@PathVariable int num, Model model){

        //뷰페이지단으로 ModelAndView를 이용, 데이터를 전달
        // 1) 데이터를 지정
        // 2) 뷰페이지를 지정
        // 3) 리턴
        String rst ="";
        int result = 0;

        for (int i=1; i<=9; i++){
            rst = rst + i + " x " + num + " = " + i*num + "<br>";
        }
        System.out.println(num);

        //뷰페이지로 결과 전달
        model.addAttribute("num", num);
        model.addAttribute("rst", rst);
        return "thyme6";
    }

    @RequestMapping("/thymeleaf7/{num}")
    public ModelAndView thyme7(@PathVariable int num,  ModelAndView mav){

        String rst ="";
        for (int i=1; i<=9; i++){
            rst = rst + i + " x " + num + " = " + i*num + "<br>";
        }
        mav.addObject("num", num);
        mav.addObject("rst", rst);
        mav.setViewName("thyme6");
        return mav;
    }


    //form 태그로 데이터 주고 받기 get & post
    @GetMapping(value = "/thymeleaf9")
    public ModelAndView thyme9(ModelAndView mav){

        mav.addObject("msg", "아래 폼 값을 입력하시고 전송 버튼을 눌러주세요");
        mav.setViewName("thyme9");

        return mav;
    }
    @PostMapping(value ="/thymeleaf9")
    public ModelAndView formSend( @RequestParam("data1") String data1, ModelAndView mav){

        mav.addObject("msg", "회원님이 입력하신 값은 "+ data1 + "입니다.");
        mav.addObject("data1", data1);
        mav.setViewName("thyme9");

       return mav;
    }

    @GetMapping(value = "/thymeleaf11")
    public ModelAndView formDTO( @ModelAttribute("formData") Bag bag, ModelAndView mav){


        mav.addObject("formData", bag);
        mav.addObject("form", "test");
        mav.setViewName("thyme11");

        return mav;
    }
    @PostMapping(value = "/thymeleaf11")
    public ModelAndView formSendDTO(@ModelAttribute("formData") Bag bag, ModelAndView mav){


        mav.addObject("formData", bag);
        mav.setViewName("thyme11");

        return mav;
    }

    @GetMapping(value ="/thymeleaf12")
    public ModelAndView formPageUtil(ModelAndView mav){

        mav.addObject("msg", "helloWorld");
        mav.setViewName("thyme12");

        return mav;

    }

    @GetMapping(value ="/thymeleaf13")
    public ModelAndView checkBox(ModelAndView mav){
        mav.addObject("msg", "어떤 메일을 사용하시나요?");
        mav.setViewName("thyme13");

        return mav;
    }

    @PostMapping(value ="/thymeleaf13")
    public ModelAndView getCheckBoxList(@RequestParam(value="email")List<String> emailItem, ModelAndView mav){

        List<String> emailList = emailItem;

        mav.addObject("emailList", emailList);
        mav.setViewName("thyme13");

        return mav;

    }



}
