package com.klm.demo.controler;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klm.demo.artificats.Fare;
import com.klm.demo.artificats.Location;
import com.klm.demo.connector.MockConnector;

@RestController
public class ClientExerciseController {

	@Autowired
	private MockConnector mockConnector;

	@RequestMapping(value = "/getLocation", method = GET)
	public Location getLocation(@RequestParam String term) {
		Location location = mockConnector.getLocations(term);
		return location;
	}
	
	@RequestMapping(value = "/getFare", method = GET)
	public Fare getFare(@RequestParam String origin,@RequestParam String destination ) {
		Fare fare = mockConnector.getFares(origin , destination);
		return fare;
	}
}
