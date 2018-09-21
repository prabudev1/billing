package com.gpware.billing.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.gpware.billing.dto.ProductDTO;
import com.gpware.billing.model.Product;

public class ProductHelper implements Serializable {

	private static final long serialVersionUID = -7451018221768265819L;

	public static ProductDTO copyProduct(Product prd) {
		ProductDTO dtoCust = new ProductDTO();
		BeanUtils.copyProperties(prd, dtoCust);
		return dtoCust;
	}

	public static List<ProductDTO> copyProductList(List<Product> prdList) {
		List<ProductDTO> dtoCustList = new ArrayList<ProductDTO>();
		for (Product prd : prdList) {
			ProductDTO dtoCust = copyProduct(prd);
			dtoCustList.add(dtoCust);
		}
		return dtoCustList;
	}
}