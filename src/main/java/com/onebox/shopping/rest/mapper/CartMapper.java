/* COSTAISA, S.A. */
package com.onebox.shopping.rest.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.onebox.shopping.domain.model.CartDb;
import com.onebox.shopping.rest.model.Cart;

/**
 * Mapper between model (business objects) and api rest layer (DTO's)
 */
@Mapper(componentModel = "spring", uses = { ProductMapper.class })
public interface CartMapper {

	@Mapping(target = "id", source = "id")
	@Mapping(target = "username", source = "username")
	@Mapping(target = "products", source = "products")
	Cart mapEntity(CartDb cart);

	@InheritInverseConfiguration
	CartDb mapDb(Cart cart);

}
