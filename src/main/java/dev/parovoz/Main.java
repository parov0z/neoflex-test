package dev.parovoz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.Date;
import java.time.LocalDate;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

@Controller
class Calc {
    @GetMapping("calculate")
    @ResponseBody double calculateByCount (
        @RequestParam("salary") int salary,
        @RequestParam("days") int days
    ) { return salary/12/29.4*days; }

    @GetMapping("calculate-dates")
    @ResponseBody double calculateByDates (
        @RequestParam("salary") double salary,
        @RequestParam("dates") Collection<LocalDate> dates
    ) {
        return salary/12/29.4 * dates.stream()
            .filter(date -> date.getDayOfWeek().ordinal() < DayOfWeek.SATURDAY.ordinal() )
            .count();
    }


}