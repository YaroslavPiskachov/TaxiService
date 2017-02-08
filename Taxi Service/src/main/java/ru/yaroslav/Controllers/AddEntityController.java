package ru.yaroslav.Controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.yaroslav.Entities.*;
import ru.yaroslav.Services.interfaces.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Yaroslav on 11.01.2017.
 */
@Controller
public class AddEntityController {

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
    @Autowired
    private TaxiService_Service taxiService_service;

    Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/addDriver", method = RequestMethod.POST)
    public String addDriver(@ModelAttribute("Driver") DriverEntity driverEntity) {
        if (driverEntity == null) {
            logger.info("driver is null");
            return "redirect:/drivers";
        }
        driverService.addDriver(driverEntity);
        return "redirect:/drivers";
    }

    @RequestMapping(value = "/addCar", method = RequestMethod.POST)
    public String addCar(@ModelAttribute("Car") CarEntity carEntity) {
        if (carEntity == null) {
            logger.info("car is null");
            return "redirect:/cars";
        }
        carService.addCar(carEntity);
        return "redirect:/cars";
    }



    @RequestMapping(value = "/addDispatcher", method = RequestMethod.POST)
    public String addDispatcher(@ModelAttribute("Dispatcher") DispatcherEntity dispatcherEntity) {
        if (dispatcherEntity == null) {
            logger.info("dispatcher is null");
            return "redirect:/dispatchers";
        }
        logger.info("im here");
        dispatcherService.addDispatcher(dispatcherEntity);
        return "redirect:/dispatchers";
    }


    @RequestMapping(value = "/addDriverToShift", method = RequestMethod.POST)
    public String addDriverToShift(Model model, @ModelAttribute("Shift") ShiftsEntity shiftsEntity) {
        if (shiftsEntity == null) {
            logger.info("shift is null");
        }
        model.addAttribute("Shift",shiftsEntity);
        model.addAttribute("Drivers",driverService.getFreeDriversByTimeLimits(shiftsEntity.getTimeIn(),shiftsEntity.getTimeOut()));
        return "addDriverToShift";
    }

    @RequestMapping(value = "/addDriverToOrder", method = RequestMethod.POST)
    public String addDriverToOrder(Model model, @ModelAttribute("Booking") BookingEntity bookingEntity) {
        if (bookingEntity == null) {
            logger.info("shift is null");
        }

        Timestamp currentTime=new Timestamp(System.currentTimeMillis());
        model.addAttribute("Booking",bookingEntity);
        model.addAttribute("Shifts",shiftsService.getShiftByTime(currentTime));
        return "addShiftToOrder";
    }



    @RequestMapping(value = "/addCarToShift", method = RequestMethod.GET)
    public String addCarToShift(Model model, @ModelAttribute("Driver") int idWorker,
                           @ModelAttribute("ShiftTimeIn")Timestamp shiftTimeIn,
                           @ModelAttribute("ShiftTimeOut")Timestamp shiftTimeOut) {

        model.addAttribute("Driver",idWorker);
        model.addAttribute("ShiftTimeIn", shiftTimeIn);
        model.addAttribute("ShiftTimeOut",shiftTimeOut);
        model.addAttribute("Cars",carService.getFreeCarsByTimeLimits(shiftTimeIn,shiftTimeOut));
        return "addCarToShift";
    }

    @RequestMapping(value = "/addShift", method = RequestMethod.GET)
    public String addShift(Model model, @ModelAttribute("Driver") int idWorker,
                           @ModelAttribute("ShiftTimeIn")Timestamp shiftTimeIn,
                           @ModelAttribute("ShiftTimeOut")Timestamp shiftTimeOut,
                           @ModelAttribute("Car")int idCar) {
        ShiftsEntity shiftsEntity=new ShiftsEntity();
        shiftsEntity.setCarEntity(carService.getById(idCar));
        shiftsEntity.setDriverEntity(driverService.getById(idWorker));
        shiftsEntity.setTimeIn(shiftTimeIn);
        shiftsEntity.setTimeOut(shiftTimeOut);
        shiftsService.addShift(shiftsEntity);
        return "redirect:/shifts";
    }


    @RequestMapping(value = "/addOrder", method = RequestMethod.GET)
    public String addOrder(Model model, @ModelAttribute("Shift1")int idShift,
                           @ModelAttribute("addressDeparture")String addressDeparture,
                           @ModelAttribute("addressArrive")String addressArrive,
                           @ModelAttribute("distance")double distance,
                           @ModelAttribute("countPassangers")int countPassengers) {
        logger.info("im here!");
        BookingEntity bookingEntity=new BookingEntity();
        bookingEntity.setTime(new Timestamp(System.currentTimeMillis()));
        bookingEntity.setShiftsEntity(shiftsService.getById(idShift));
        bookingEntity.setDistance(distance);
        bookingEntity.setCountPassangers(countPassengers);
        bookingEntity.setAddressArrive(addressArrive);
        bookingEntity.setAddressDeparture(addressDeparture);
        bookingService.add(bookingEntity);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/addService", method = RequestMethod.POST)
    public String add(@ModelAttribute("Service")TaxiServiseEntity taxiServiseEntity){
        if(taxiServiseEntity==null) return "serviceRegestration";
        taxiService_service.addService(taxiServiseEntity);
        DispatcherEntity dispatcherEntity=new DispatcherEntity();
        List<TaxiServiseEntity> services= taxiService_service.getAll();
        TaxiServiseEntity maxId=services.get(0);
        for (int i = 0; i < services.size(); i++) {
            if(services.get(i).getIdService()>maxId.getIdService())maxId=services.get(i);
        }
        dispatcherEntity.setTaxiServiseEntity(maxId);
        logger.info(dispatcherEntity.getTaxiServiseEntity().getIdService());
        return "addDispatcher";
    }



    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public String Car(Model model) {
        CarEntity carEntity=new CarEntity();
        model.addAttribute("Car", carEntity);
        return "addCar";
    }


    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String Order(Model model) {
        BookingEntity bookingEntity=new BookingEntity();
        model.addAttribute("Booking", bookingEntity);
        return "addOrder";
    }



    @RequestMapping(value = "/driver", method = RequestMethod.GET)
    public String Driver(Model model) {
        DriverEntity driverEntity = new DriverEntity();
        model.addAttribute("Driver", driverEntity);
        return "addDriver";
    }

    @RequestMapping(value = "/dispatcher", method = RequestMethod.GET)
    public String Dispatcher(Model model) {
        DispatcherEntity dispatcherEntity= new DispatcherEntity();
        model.addAttribute("Dispatcher", dispatcherEntity);
        return "addDispatcher";
    }

    @RequestMapping(value = "/shift", method = RequestMethod.GET)
    public String Shift(Model model) {
        ShiftsEntity shiftsEntity=new ShiftsEntity();
        model.addAttribute("Shift", shiftsEntity);
        return "addTimeLimitsToShift";
    }

}
