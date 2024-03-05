package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        Integer cnt = jdbcTemplate.queryForObject("select count(*) from countries", Integer.class);

        model.addAttribute("name", name);
        model.addAttribute("cCnt", cnt);
        System.out.println("Greeting requested: " + name);
        System.out.println(cnt + " countries found");
        return "greeting";
    }

    @GetMapping("/")
    public String index(Model model) {
        Runtime rt = Runtime.getRuntime();

        model.addAttribute("cpuCnt", rt.availableProcessors());
        model.addAttribute("totalMem", rt.totalMemory());
        model.addAttribute("freeMem", rt.freeMemory());
        model.addAttribute("maxMem", rt.maxMemory());
        return "index";
    }

}
