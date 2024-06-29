package assignment2.com.assignment2.controllers;

import assignment2.com.assignment2.models.Rectangles;
import assignment2.com.assignment2.models.RectanglesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rectangles")
public class RectanglesController {  
    
    @Autowired
    private RectanglesRepository rectangleRepo;

    @GetMapping
    public String listRectangles(Model model) {
        model.addAttribute("rectangles", rectangleRepo.findAll());
        return "rectangles";
    }

    @GetMapping("/{pid}")
    public String getRectangle(@PathVariable("pid") int pid, Model model) {
        Rectangles rectangle = rectangleRepo.findById(pid)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rectangle Id:" + pid));
        model.addAttribute("rectangle", rectangle);
        return "rectangle-details";
    }

    @PostMapping("/update")
    public String updateRectangle(@ModelAttribute Rectangles rectangle, Model model) {
        if (!isValidHexColor(rectangle.getColor())) {
            model.addAttribute("error", "Invalid colour hex code. Please enter a valid 3 or 6 character hex code.");
            model.addAttribute("rectangle", rectangle);
            return "rectangle-details";
        }

        Rectangles existingRectangle = rectangleRepo.findById(rectangle.getPid())
                .orElseThrow(() -> new IllegalArgumentException("Invalid rectangle Id:" + rectangle.getPid()));

        existingRectangle.setName(rectangle.getName());
        existingRectangle.setWidth(rectangle.getWidth());
        existingRectangle.setHeight(rectangle.getHeight());
        existingRectangle.setColor(rectangle.getColor());

        rectangleRepo.save(existingRectangle);
        return "redirect:/rectangles";
    }

    @PostMapping
    public String addRectangle(@ModelAttribute Rectangles rectangle, Model model) {
        if (!isValidHexColor(rectangle.getColor())) {
            model.addAttribute("error", "Invalid colour hex code. Please enter a valid 3 or 6 character hex code.");
            model.addAttribute("rectangles", rectangleRepo.findAll());
            return "rectangles";
        }
        rectangleRepo.save(rectangle);
        return "redirect:/rectangles";
    }

    @GetMapping("/delete/{pid}")
    public String deleteRectangle(@PathVariable("pid") int pid) {
        rectangleRepo.deleteById(pid);
        return "redirect:/rectangles";
    }

    private boolean isValidHexColor(String color) {
        return color.matches("^([A-Fa-f0-9]{3}|[A-Fa-f0-9]{6})$");
    }
}
