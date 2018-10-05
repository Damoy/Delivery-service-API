package com.lama.dsa.model.order;

import java.util.List;

import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.food.Menu;

public class OrderContainer {

	private List<Food> foods;
	private List<Menu> menus;
	
	public OrderContainer(List<Food> foods, List<Menu> menus) {
		this.foods = foods;
		this.menus = menus;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foodIds) {
		this.foods = foodIds;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
}