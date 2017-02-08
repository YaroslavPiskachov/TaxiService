package ru.yaroslav.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.yaroslav.Entities.CarEntity;
import ru.yaroslav.Services.interfaces.*;

/**
 * Created by Yaroslav on 11.01.2017.
 */
@Controller
public class DeleteEntityController {

    @Autowired
    DispatcherService dispatcherService;

    @Autowired
    DriverService driverService;

    @Autowired
    ShiftsService shiftsService;
    @Autowired
    CarService carService;
    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/deleteDriver", method = RequestMethod.GET)
    public String deleteDriver(Model model, @ModelAttribute("Driver") int driverEntity) {
        driverService.deleteDriver(driverService.getById(driverEntity));
        return "redirect:/drivers";
    }

    @RequestMapping(value = "/deleteCar", method = RequestMethod.GET)
    public String deleteCar(Model model, @ModelAttribute("Car") int carEntity) {
        carService.deleteCar(carService.getById(carEntity));
        return "redirect:/cars";
    }



    @RequestMapping(value = "/deleteDispatcher", method = RequestMethod.GET)
    public String deleteDriver(Model model, @ModelAttribute("Dispatcher") String code) {
        dispatcherService.deleteDispatcher(dispatcherService.getByPersonalCode(code).get(0));
        return "redirect:/dispatchers";
    }

    @RequestMapping(value = "/deleteShift", method = RequestMethod.GET)
    public String deleteShift(Model model, @ModelAttribute("Shift") int idShift) {
        shiftsService.deleteShift(shiftsService.getById(idShift));
        return "redirect:/shifts";
    }

}
