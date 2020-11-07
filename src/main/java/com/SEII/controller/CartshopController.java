package com.SEII.controller;

import com.SEII.models.Cartshop;
import com.SEII.models.Cartshop_item;
import com.SEII.models.Category;
import com.SEII.models.PersonDTO;
import com.SEII.pojo.MyCartshopItemPOJO;
import com.SEII.services.CartshopItemService;
import com.SEII.services.CartshopService;
import com.SEII.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/shopping-cart")
public class CartshopController {

  private PersonService personService;

  private CartshopService cartshopService;

  private CartshopItemService cartshopItemService;


  @Autowired
  public CartshopController(PersonService personService, CartshopService cartshopService, CartshopItemService cartshopItemService){
    this.personService = personService;
    this.cartshopService = cartshopService;
    this.cartshopItemService = cartshopItemService;
  }


  @GetMapping(value = {"/items"})
  public List<MyCartshopItemPOJO>  getItems(){
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    PersonDTO person2 = personService.findByUsername(username);
    //Esta parte no require Pojos ya que no es información que entra o sale del back
    Cartshop cartshop = cartshopService.findByPersonId(person2.getId()); /*Falta controlar el caso en el que un usuario no tenga carrito
    O hacer que siempre que se crean tengan carrito (seria lo ideal) entonces mas que nada hay que controlar cuando tengan 0 items en el carrito*/
    MyCartshopItemPOJO myCartShopItems = new MyCartshopItemPOJO();
    //Creo este objeto sencillamente para usar el metodo que luego me retorna la lista.
    

    //List<Cartshop_item> items =
    return myCartShopItems.MyCartshopItemPOJO(cartshopItemService.findByCartshop(cartshop.getId()));
  }

  @DeleteMapping({"/delete-item"})
  public String deleteCSItem(@RequestBody Integer id) {


    if(id > 0) {
      if(cartshopItemService.delete(id)) {
        return "Deleted the Item.";
      } else {
        return "Cannot delete the Item.";
      }
    }
    return "The id given is invalid.";
  }

  @PutMapping("/update")
  public String updateItem(@RequestParam("id") Integer ID,@RequestParam("quantity") Integer quantity) {

      Cartshop_item cartshopItem = cartshopItemService.getByID(ID);
      cartshopItem.setQuantity(quantity);

    if(cartshopItem != null) {
      cartshopItemService.update(cartshopItem);
      return "Updated Item.";
    } else {
      return "Request does not contain a body";
    }
  }


}