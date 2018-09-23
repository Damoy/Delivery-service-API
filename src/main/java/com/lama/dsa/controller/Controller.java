package com.lama.dsa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.order.Order;
import com.lama.dsa.service.IFoodService;
import com.lama.dsa.service.IOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/DSA/")
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
@Api(value = "dsa", description = "Resources pertaining to Fresh Food Delivery Information")
public class Controller {

	@Autowired
	private IControllerHelper helper;
	
	@Autowired
	private IFoodService foodService;

	@Autowired
	private IOrderService orderService;

	/**
	 * Get all the foods available in the database.
	 */
	@RequestMapping(value = "/FOOD", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View the whole food catalogue", response = Food.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food catalogue."),
			@ApiResponse(code = 404, message = "No food was found.") })
	public ResponseEntity getAllFoods() {
		List<Food> foods = foodService.getAll();
		return new ResponseEntity(foods, (foods == null || foods.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}

	/**
	 * Get food by given food name.
	 */
	@RequestMapping(value = "FOOD/{name}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View food given a food name", response = Food.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food."),
			@ApiResponse(code = 404, message = "No food was found.") })
	public ResponseEntity getFoodByName(@PathVariable String name) {
		List<Food> foods = foodService.getFoodByName(name);
		return new ResponseEntity(foods, (foods == null || foods.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}

	/**
	 * Get commands of a restaurant (get by name).
	 */
	@RequestMapping(value = "RESTAURANT/{restaurantName}/COMMANDS", method = RequestMethod.GET, produces = "application/xml")
	@ApiOperation(value = "View a restaurant orders", response = Order.class, responseContainer = "List")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved orders"),
			@ApiResponse(code = 404, message = "No order was found")})
	public ResponseEntity getOrdersByRestaurantName(@PathVariable String restaurantName) {
		List<Order> orders = orderService.getOrdersByRestaurantIds(helper.getRestaurantIdsFromName(restaurantName));
		return new ResponseEntity(orders, (orders == null || orders.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}

	/**
	 * Get commands of a coursier (get by name).
	 */
	@RequestMapping(value = "COURSIER/{coursierName}/COMMANDS", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View a coursier orders", response = Order.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved orders"),
			@ApiResponse(code = 404, message = "No order was found") })
	public ResponseEntity getOrdersByCoursierName(@PathVariable String coursierName) {
		List<Order> orders = orderService.getOrdersByCoursierIds(helper.getCoursierIdsFromName(coursierName));
		return new ResponseEntity(orders, (orders == null || orders.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
}
