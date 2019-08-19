package com.example.demo.resource;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.domain.CartItem;
import com.example.demo.domain.Product;
import com.example.demo.domain.ShoppingCart;
import com.example.demo.domain.User;
import com.example.demo.service.CartItemService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ShoppingCartService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/cart")
public class ShoppingCartResource {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/add")
    public ResponseEntity addItem (
            @RequestBody HashMap<String, String> mapper,HttpSession session
    ){
    	Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	
    	String username="dikshapal";
		/*
		 * if(principal instanceof User) { username =((User)principal).getUsername(); }
		 * else { username= principal.toString(); }
		 */
        String productId = (String) mapper.get("productId");
        String qty = (String) mapper.get("qty");
        System.out.println("f"+productId);
        System.out.println("f"+qty);
        
        System.out.println("name : "+username);
        User user = userService.findByUsername(username);
        System.out.println("ffgd");
        Product product = productService.findOne(Long.parseLong(productId));
        System.out.println("hi");

        if(Integer.parseInt(qty) > product.getInStockNumber()) {
            return new ResponseEntity("Not Enough Stock!", HttpStatus.BAD_REQUEST);
        }

        CartItem cartItem = cartItemService.addProductToCartItem(product, user, Integer.parseInt(qty));

        return new ResponseEntity("Book Added Successfully!", HttpStatus.OK);
    }

    @RequestMapping("/getCartItemList")
    public List<CartItem> getCartItemList(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        shoppingCartService.updateShoppingCart(shoppingCart);

        return cartItemList;
    }

    @RequestMapping("/getShoppingCart")
    public ShoppingCart getShoppingCart(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();

        shoppingCartService.updateShoppingCart(shoppingCart);

        return shoppingCart;
    }

    @RequestMapping("/removeItem")
    public ResponseEntity removeItem(@RequestBody String id) {
        cartItemService.removeCartItem(cartItemService.findById(Long.parseLong(id)));

        return new ResponseEntity("Cart Item Removed Successfully!", HttpStatus.OK);
    }

    @RequestMapping("/updateCartItem")
    public ResponseEntity updateCartItem(
            @RequestBody HashMap<String, String> mapper
    ){
        String cartItemId = mapper.get("cartItemId");
        String qty = mapper.get("qty");

        CartItem cartItem = cartItemService.findById(Long.parseLong(cartItemId));
        cartItem.setQty(Integer.parseInt(qty));

        cartItemService.updateCartItem(cartItem);

        return new ResponseEntity("Cart Item Updated Successfully!", HttpStatus.OK);
    }

}
