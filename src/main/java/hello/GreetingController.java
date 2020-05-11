package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private static final String question = "How are you, %s";
    private static final String youre = "You are %s";
    private final AtomicLong counter = new AtomicLong();
    private CountService countService;

    @Autowired
    public GreetingController(CountService countService) {
        this.countService = countService;
    }

//    @RequestMapping(path="/bye", method = GET) //1)zmieniam nazwe enpointa
//    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String
//                                     name) {
//        return new Greeting(counter.incrementAndGet(),String.format(template,
//                name)); //metoda countService.count(name) zwraca nie imie, tylko
//                                            // liczbe znakow? zmieniam zeby zwracalo imie

    //zeby ten endpoint nie przeszkadzal, komentuje go
//    }

    @RequestMapping(path = "/bye/mood", method = GET) //2)dodaje nowy endpoint
    public Greeting mood(@RequestParam(value = "name", defaultValue = "World") String
                                 name) {
        return new Greeting(counter.incrementAndGet(), String.format(question,
                name + "?"));
    }

//    @RequestMapping(path="/bye", method = GET) //3)zmieniam zwracana wartosc na wiek
//    public Greeting greeting(@RequestParam(value = "age", defaultValue = "1") int
//                                     age) {
//        return new Greeting(counter.incrementAndGet(),String.format(youre,
//                age));
//    }

    @RequestMapping(path = "/bye", method = GET) //4)dodaje zwracana wartosc - imie i wiek
    public Greeting greeting(@RequestParam(value = "age", defaultValue = "1") int age,
                             @RequestParam(value = "name", defaultValue = "User") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name) +
                String.format(youre, age));
    }

    @RequestMapping(path = "/defineBinaryGender", method = GET)
    public String gender(@RequestParam(value = "name", defaultValue = "World") String name) {
        String gender;
        if (name.endsWith("a")) {
            gender = "F";
        } else gender = "M";
        return "Name gender : " + gender;
    }
    // metoda zwraca plec na podstawie tego jaka litera konczy sie wpisane imie. uzyto metody
    //String.endsWith(String sufix)

    @RequestMapping(path = "/matchExample", method = GET)
    public String match(@RequestParam(value = "name", defaultValue = "World") String name) {
        String matches;
        if (name.equals("Sofiia")) {
            matches = "Yes";
        } else matches = "No";
        return "Name matches : " + matches;
    }
    // metoda sprawdza czy wpisane imie jest podobne do wzorca. uzyto metody String.equals

} 