package RobustifyBackend.Controllers;

import RobustifyBackend.Repositories.InventoryRepository;
import RobustifyBackend.model.Inventory.Inventory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class InventoryController {

@Autowired
private InventoryRepository inventoryRepository;


    @PostMapping("/inventory")
    public ResponseEntity<?> createInventory(@Valid @RequestBody Inventory inventoryDTO) {
        Inventory inventory = new Inventory();
        inventory.setItems(inventoryDTO.getItems());
        switch (inventoryDTO.getItems()) {
            case PPW_White:
            case PE_White:
            case PPW_Silver:
            case PE_Silver:
            case PPW_CLear:
            case Clear_Clear:
            case Laiser:
            case Sachet_Aluminum:
                inventory.setSize(2200);
                break;
            case Black_Ink:
            case White_Ink:
            case Cayan_Ink:
            case Magenta_Ink:
            case Yellow_Ink:
                inventory.setSize(1200);
                break;
            case Varnish:
                inventory.setSize(3000);
                break;
            default:
                return new ResponseEntity<>("Invalid item type", HttpStatus.BAD_REQUEST);
        }

        inventory.setQuantity(inventoryDTO.getQuantity());
        inventory.setLocation(inventoryDTO.getLocation());

        Inventory savedInventory = inventoryRepository.save(inventory);

        // Return the saved inventory item
        return new ResponseEntity<>(savedInventory, HttpStatus.CREATED);
    }

}
