package pl.edu.wszib.petShop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.petShop.Services.IProductService;
import pl.edu.wszib.petShop.Session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CommonController {

    @Autowired
    IProductService productService;

    @Resource
    SessionObject sessionObject;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("products", this.productService.getAllProducts());
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "main";
    }
}
