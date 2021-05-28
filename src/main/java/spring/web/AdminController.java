package spring.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.service.CardService;
import spring.web.dto.CardDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final CardService cardService;

    @GetMapping("/adminpage")
    public String adminpage() {
        return "adminpage";
    }

    @GetMapping("/admincardlist")
    public String admincardlist(Model model) {

        List<CardDto> cardDtos = cardService.cardDtolist();

        model.addAttribute("cardDtos", cardDtos);

        return  "admincardlist";

    }

}
