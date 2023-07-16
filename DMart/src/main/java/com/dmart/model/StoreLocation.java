package com.dmart.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class StoreLocation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "storeLocation", cascade = CascadeType.ALL)
	private List<StockItem> stockItems = new ArrayList<>();

	@Override
	public String toString() {
		return "StoreLocation [id=" + id + ", name=" + name + "]";
	}

	// getters and setters
}