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
import ru.yaroslav.Entities.BookingEntity;
import ru.yaroslav.Entities.DispatcherEntity;
import ru.yaroslav.Entities.ShiftsEntity;
import ru.yaroslav.Entities.TaxiServiseEntity;
import ru.yaroslav.Extends.ShiftsWithBenefit;
import ru.yaroslav.Services.interfaces.BookingService;
import ru.yaroslav.Services.interfaces.DispatcherService;
import ru.yaroslav.Services.interfaces.ShiftsService;
import ru.yaroslav.Services.interfaces.TaxiService_Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaroslav on 28.12.2016.
 */
@Controller
public class LoginController {
    Log logger = LogFactory.getLog(getClass());
    @Autowired
    private TaxiService_Service taxiService_service;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private DispatcherService dispatcherService;
    @Autowired
    private ShiftsService shiftsService;



    @RequestMapping(value="/", method = RequestMethod.GET)
    public String HelloWorld(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DispatcherEntity dispatcherEntity=dispatcherService.getDispatcherByFIO(auth.getName());
        int idCurrentService=dispatcherEntity.getTaxiServiseEntity().getIdService();
        List<BookingEntity> bookings=bookingService.getAllByServiceId(idCurrentService);

        List<ShiftsEntity> shiftsEntities= shiftsService.getShiftsByServiceId(idCurrentService);
        if(shiftsEntities.size()==0){
            return "main";
        }

        if(shiftsEntities.get(0).getTimeIn()!=null)
            shiftsEntities=shiftsService.getShiftsByDay(shiftsEntities.get(0).getTimeIn());
        ArrayList<ShiftsWithBenefit> shiftsWithBenefits=new ArrayList<ShiftsWithBenefit>();
        int wholeSum=0;
        double wholeGas=0;
        int[] MoneySumByShift=new int[shiftsEntities.size()];
        double[] GasolineSumByShift=new double[shiftsEntities.size()];
        for (int i = 0; i < shiftsEntities.size(); i++) {
                List<BookingEntity> bookingsByShift=bookingService.getAllByShiftId(shiftsEntities.get(i).getIdShift());
                MoneySumByShift[i]=0;
                GasolineSumByShift[i]=0;
                for (int j = 0; j < bookingsByShift.size(); j++) {
                MoneySumByShift[i]+=bookingsByShift.get(j).getPrice();
                GasolineSumByShift[i]+=(shiftsEntities.get(i).getCarEntity().getGasolineRate()/100)*bookingsByShift.get(j).getDistance();
                logger.info(shiftsEntities.get(i).getCarEntity().getGasolineRate());
            }
            shiftsWithBenefits.add(new ShiftsWithBenefit(shiftsEntities.get(i).getTimeIn(),
                    shiftsEntities.get(i).getTimeOut(),MoneySumByShift[i],
                    shiftsEntities.get(i).getDriverEntity().getFullName(), shiftsEntities.get(i).getCarEntity().getModel(), GasolineSumByShift[i]));
            wholeSum+=MoneySumByShift[i];
            wholeGas+=GasolineSumByShift[i];
        }

        model.addAttribute("Shifts",shiftsWithBenefits);
        model.addAttribute("Sum",wholeSum);
        model.addAttribute("Gas",wholeGas);

        return "main";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model){
        return "/login?logout";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model,
    @RequestParam(value = "error", required = false) String error,
     @RequestParam(value = "logout", required = false) String logout){
        if (error != null) {
            model.addAttribute("error", "Неправильное ФИО или личный код");
        }
        if (logout != null) {
            model.addAttribute("msg", "Выход из аккаунта успешен");
        }
        return "index";
    }

    @RequestMapping(value="/regestrationService", method = RequestMethod.GET)
    public String regesrtationService(Model model){
        model.addAttribute("Service", new TaxiServiseEntity());
        return "serviceRegestration";
    }







//    @RequestMapping(value="/", method = RequestMethod.GET)
//    public ModelAndView ServicesTest(){
//
//            List<TaxiServiseEntity> services = taxiService_service.getAll();
//            ModelAndView modelAndView=new ModelAndView();
//
//            modelAndView.addObject("services",services);
//            modelAndView.setViewName("servicesTest");
//        return modelAndView;
//    }
}
