package com.boostIT.controllers;




import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boostIT.configuration.GeneralBeanFactory;
import com.boostIT.domain.BitstampTickerDemoImpl;
import com.boostIT.domain.MarketScannerFactory;
import com.boostIT.entities.ExchangeTransaction;
import com.boostIT.service.StoreManagementService;


@Controller
public class AdvancedController extends BaseController{

	@Autowired
    StoreManagementService storeService;

    
	@RequestMapping(value = "/loadstore", method = RequestMethod.GET)
    public String storeLoad(Model model) throws IOException, InterruptedException {
        model.addAttribute("store", new ExchangeTransaction());    
        ApplicationContext context = new AnnotationConfigApplicationContext(GeneralBeanFactory.class);
        MarketScannerFactory msf = context.getBean(MarketScannerFactory.class);
        msf.start();
        context=null;
        return "store";

    }

    @RequestMapping(value = "/getallstores", method = RequestMethod.GET)

    public String getAllStores(Model model) {

        model.addAttribute("stores", storeService.getAllStores());

        return "storelist";

    }

     

    @RequestMapping(value = "/addstore", method = RequestMethod.POST)

    public String storeAdd(@ModelAttribute ExchangeTransaction store, Model model) {

        ExchangeTransaction addedStore = storeService.addStore(store);

        model.addAttribute("stores", storeService.getAllStores());

        return "storelist";

    }

     

    @RequestMapping(value = "/deletestore/{id}", method = RequestMethod.GET)

    public String storeDelete(@PathVariable Long id, Model model) {

 

        storeService.deleteStore(id);

        model.addAttribute("stores", storeService.getAllStores());

        return "storelist";

    }

     

    @RequestMapping(value = "/updatestore", method = RequestMethod.POST)

    public String storeUpdate(@ModelAttribute ExchangeTransaction store, Model model) {

        storeService.updateStore(store);

        model.addAttribute("stores", storeService.getAllStores());

        return "storelist";

    }

     

    @RequestMapping(value = "/editstore/{id}", method = RequestMethod.GET)

    public String storeEdit(@PathVariable Long id, Model model) {

        model.addAttribute("store", storeService.getStore(id));

        return "editstore";

    }
    @RequestMapping("/")
	public String index() {
		return "index.html";
	}

	@RequestMapping(value = "downloadFiles", method = RequestMethod.POST)
	public String downloadFiles(@RequestParam(value = "fileTypePickCheckbox", required = false) String[] checkboxValue)
	 {
		return "redirect:noFileTypeSelected.html";

	}

}
