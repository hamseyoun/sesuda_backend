package sesuda.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sesuda.dto.AdminDTO;
import sesuda.util.Message;
import javax.validation.Valid;

@RestController
public class AdminController {

    @PostMapping(value = "/admin")
    public String admin(@Valid AdminDTO dto, BindingResult bindingResult) {

        String result = "";

        if (bindingResult.hasErrors()) {
            result = "not admin!";
        } else{
            result = "hello admin!";
        }
        System.out.println("bindingResult = " + bindingResult);
        System.out.println("dto = " + dto.getAuth());

        return result;
    }
}
