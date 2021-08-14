/* COSTAISA, S.A. */
package com.onebox.shopping.rest.mapper;

import java.util.List;

import org.mapstruct.DecoratedWith;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.onebox.shopping.domain.model.ProductDb;
import com.onebox.shopping.rest.mapper.decorator.ProductDecorator;
import com.onebox.shopping.rest.model.Product;

/**
 * Mapper between model (business objects) and api rest layer (DTO's)
 */
@Mapper(componentModel = "spring", uses = { })
@DecoratedWith(ProductDecorator.class)
public interface ProductMapper {

	@Mapping(target = "id", source = "id")
	@Mapping(target = "description", source = "description")
	@Mapping(target = "amount", source = "amount")
	Product mapEntity(ProductDb product);

	@InheritInverseConfiguration
	ProductDb mapDb(Product product);

	List<Product> mapList(List<ProductDb> productsDb);
	
}
