package ru.yaroslav.Controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yaroslav.Entities.DispatcherEntity;
import ru.yaroslav.Entities.DriverEntity;
import ru.yaroslav.Services.interfaces.*;

/**
 * Created by Yaroslav on 08.01.2017.
 */
@Controller
public class MainPagesController {
    @Autowired
    private DriverService driverService;
    @Autowired
    private DispatcherService dispatcherService;
    @Autowired
    private ShiftsService shiftsService;
    @Autowired
    private CarService carService;
    @Autowired
    private BookingService bookingService;

    Log logger = LogFactory.getLog(getClass());







    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String Cars(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DispatcherEntity dispatcherEntity = dispatcherService.getDispatcherByFIO(auth.getName());
        model.addAttribute("Cars", carService.getByIdService(dispatcherEntity.getTaxiServiseEntity().getIdService()));
        return "cars";
    }




    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public String Drivers(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DispatcherEntity dispatcherEntity = dispatcherService.getDispatcherByFIO(auth.getName());
        model.addAttribute("Drivers", driverService.getByIdService(dispatcherEntity.getTaxiServiseEntity().getIdService()));

        return "drivers";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String Orders(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DispatcherEntity dispatcherEntity = dispatcherService.getDispatcherByFIO(auth.getName());
        model.addAttribute("Booking", bookingService.getAllByServiceId(dispatcherEntity.getTaxiServiseEntity().getIdService()));

        return "orders";
    }


    @RequestMapping(value = "/shifts", method = RequestMethod.GET)
    public String Shifts(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DispatcherEntity dispatcherEntity = dispatcherService.getDispatcherByFIO(auth.getName());
        model.addAttribute("Shifts", shiftsService.getShiftsByServiceId(dispatcherEntity.getTaxiServiseEntity().getIdService()));
        return "shifts";
    }

    @RequestMapping(value = "/dispatchers", method = RequestMethod.GET)
    public String Dispatchers(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DispatcherEntity dispatcherEntity = dispatcherService.getDispatcherByFIO(auth.getName());
        model.addAttribute("Dispatchers", dispatcherService.getByIdService(dispatcherEntity.getTaxiServiseEntity().getIdService()));
        return "/dispatchers";
    }

}
