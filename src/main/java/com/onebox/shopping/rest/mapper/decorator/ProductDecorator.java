package com.onebox.shopping.rest.mapper.decorator;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.onebox.shopping.domain.model.ProductDb;
import com.onebox.shopping.rest.mapper.ProductMapper;
import com.onebox.shopping.rest.model.Product;

@Mapper(componentModel = "spring")
public class ProductDecorator implements ProductMapper {

	@Autowired
	@Qualifier("delegate")
	private ProductMapper delegate;

	@Override
	public Product mapEntity(ProductDb entity) {
		return this.delegate.mapEntity(entity);
	}

	@Override
	public ProductDb mapDb(Product entity) {
		return this.delegate.mapDb(entity);
	}

	@Override
	public List<Product> mapList(List<ProductDb> entities) {
		List<Product> result = new ArrayList<>();
		for(ProductDb productDb : entities) {
			Product entity = this.mapEntity(productDb);
			result.add(entity);
		}
		return result;
	}

}
