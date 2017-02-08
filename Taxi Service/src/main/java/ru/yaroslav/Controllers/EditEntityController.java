package ru.yaroslav.Controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.yaroslav.Entities.CarEntity;
import ru.yaroslav.Entities.DispatcherEntity;
import ru.yaroslav.Entities.DriverEntity;
import ru.yaroslav.Entities.ShiftsEntity;
import ru.yaroslav.Services.interfaces.CarService;
import ru.yaroslav.Services.interfaces.DispatcherService;
import ru.yaroslav.Services.interfaces.DriverService;
import ru.yaroslav.Services.interfaces.ShiftsService;

import java.sql.Timestamp;

/**
 * Created by Yaroslav on 11.01.2017.
 */
@Controller
public class EditEntityController {

    @Autowired
    private DriverService driverService;
    @Autowired
    private DispatcherService dispatcherService;
    @Autowired
    private ShiftsService shiftsService;
    @Autowired
    private CarService carService;

    Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/editDriver", method = RequestMethod.GET)
    public String editDriver(Model model, @ModelAttribute("Driver") int idWorker) {
        logger.info("Editing worker with id: " + idWorker);
        model.addAttribute("Driver", driverService.getById(idWorker));
        return "editDriver";
    }

    @RequestMapping(value = "/editCar", method = RequestMethod.GET)
    public String editCar(Model model, @ModelAttribute("Car") int id_car) {
        logger.info("Editing car with id: " + id_car);
        model.addAttribute("Car", carService.getById(id_car));
        return "editCar";
    }



    @RequestMapping(value = "/editDispatcher", method = RequestMethod.GET)
    public String editDispatcher(Model model, @ModelAttribute("Dispatcher") String code) {
        logger.info("Editing dispatcher with code: " + code);
        model.addAttribute("Dispatcher", dispatcherService.getByPersonalCode(code).get(0));
        return "editDispatcher";
    }

    @RequestMapping(value = "/editShift", method = RequestMethod.GET)
    public String editShift(Model model, @ModelAttribute("Shift") String ShiftId) {
        logger.info("Editing sift with code: " + ShiftId);
        model.addAttribute("Shift", shiftsService.getById(Integer.valueOf(ShiftId)));
        return "editTimeLimitsToShift";
    }

    @RequestMapping(value = "/editDriverToShift", method = RequestMethod.POST)
    public String editDriverToShift(Model model, @ModelAttribute("Shift") ShiftsEntity shiftsEntity) {
        logger.info("Editing sift with code: " + shiftsEntity.getIdShift());
        shiftsEntity.setDriverEntity(shiftsService.getById(shiftsEntity.getIdShift()).getDriverEntity());
        if(!shiftsService.isEditingDriverNeeded(shiftsEntity))
            model.addAttribute("keepDriver","Оставить прежнего водителя для этой смены");

        model.addAttribute("Shift",shiftsEntity);
        model.addAttribute("Drivers",driverService.getFreeDriversByTimeLimits(shiftsEntity.getTimeIn(),shiftsEntity.getTimeOut()));


        return "editDriverToShift";
    }


    @RequestMapping(value = "/editCarToShift", method = RequestMethod.GET)
    public String editCarToShift(Model model, @ModelAttribute("Driver") int driverId,
                                 @ModelAttribute("ShiftId") int shiftId,
                                 @ModelAttribute("ShiftTimeIn") String timeIn,
                                 @ModelAttribute("ShiftTimeOut") String timeOut) {
        if(!shiftsService.isEditingCarNeeded(shiftsService.getById(shiftId)))
            model.addAttribute("keepCar","Оставить прежнее авто для этой смены");
        model.addAttribute("Car",carService.getByIdShift(shiftId).getId_car());
        model.addAttribute("Driver",driverId);
        model.addAttribute("ShiftId",shiftId);
        model.addAttribute("ShiftTimeIn",timeIn);
        model.addAttribute("ShiftTimeOut",timeOut);
        model.addAttribute("Cars",carService.getFreeCarsByTimeLimits(Timestamp.valueOf(timeIn),Timestamp.valueOf(timeOut)));
        return "editCarToShift";
    }




    @RequestMapping(value = "/updateDriver", method = RequestMethod.POST)
    public String updateDriver(Model model, @ModelAttribute("Driver") DriverEntity driverEntity) {
        driverService.updateDriver(driverEntity);
        return "redirect:/drivers";
    }

    @RequestMapping(value = "/updateCar", method = RequestMethod.POST)
    public String updateCar(Model model, @ModelAttribute("Car")CarEntity carEntity) {
        carService.updateCar(carEntity);
        return "redirect:/cars";
    }

    @RequestMapping(value = "/updateShift", method = RequestMethod.GET)
    public String updateShift(Model model, @ModelAttribute("Driver") int driverId,
                              @ModelAttribute("ShiftId") int shiftId,
                              @ModelAttribute("ShiftTimeIn") String timeIn,
                              @ModelAttribute("ShiftTimeOut") String timeOut,
                              @ModelAttribute("Car")int carId) {
        ShiftsEntity shiftsEntity=new ShiftsEntity();
        shiftsEntity.setTimeIn(Timestamp.valueOf(timeIn));
        shiftsEntity.setTimeOut(Timestamp.valueOf(timeOut));
        shiftsEntity.setDriverEntity(driverService.getById(driverId));
        shiftsEntity.setCarEntity(carService.getById(carId));
        shiftsEntity.setIdShift(shiftId);
        shiftsService.update(shiftsEntity);
        return "redirect:/shifts";
    }

    @RequestMapping(value = "/updateDispatcher", method = RequestMethod.POST)
    public String updateDispatcher(Model model, @ModelAttribute("Dispatcher") DispatcherEntity dispatcherEntity) {
        dispatcherService.updateDispatcher(dispatcherEntity);
        return "redirect:/dispatchers";
    }
}
