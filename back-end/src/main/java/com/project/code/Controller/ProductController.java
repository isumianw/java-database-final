package com.project.code.Controller;

import com.project.code.Repo.InventoryRepository;
import com.project.code.Repo.OrderItemRepository;
import com.project.code.Service.ServiceClass;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ServiceClass serviceClass;

    @Autowired
    private InventoryRepository inventoryRepository;

// 3. Define the `addProduct` Method:
//    - Annotate with `@PostMapping` to handle POST requests for adding a new product.
//    - Accept `Product` object in the request body.
//    - Validate product existence using `validateProduct()` in `ServiceClass`.
//    - Save the valid product using `save()` method of `ProductRepository`.
//    - Catch exceptions (e.g., `DataIntegrityViolationException`) and return appropriate error message.
    @PostMapping
    public Map<String, String> addProduct(@RequestBody Product product) {
        Map<String, String> map =  new HashMap<>();
        if (!serviceClass.validateProduct(product.getId())) {
            map.put("message", "Product already present in the database");
            return map;
        } 
        try {
            productRepository.save(product);
            map.put("message", "Product added successfully");
        } catch (DataIntegrityViolationException e) {
            map.put("message", "SKU should be unique");
        }
        return map;
    }

// 4. Define the `getProductbyId` Method:
//    - Annotate with `@GetMapping("/product/{id}")` to handle GET requests for retrieving a product by ID.
//    - Accept product ID via `@PathVariable`.
//    - Use `findById(id)` method from `ProductRepository` to fetch the product.
//    - Return the product in a `Map<String, Object>` with key `products`.
    @GetMapping("/product/{id}")
    public Map<String, Object> getProductbyId(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        Product result = productRepository.findById(id);
        System.out.println("result: " + result);
        map.put("products", result);
        return map;
    }

 // 5. Define the `updateProduct` Method:
//    - Annotate with `@PutMapping` to handle PUT requests for updating an existing product.
//    - Accept updated `Product` object in the request body.
//    - Use `save()` method from `ProductRepository` to update the product.
//    - Return a success message with key `message` after updating the product.
    @PutMapping
    public Map<String, String> updateProduct(@RequestBody Product product) {
        Map<String, String> map = new HashMap<>();
        try {
            productRepository.save(product);
            map.put("message", "Data updated successfully");
        } catch (Error e) {
            map.put("message", "Error occured");
        }
        return map;
    }

// 6. Define the `filterbyCategoryProduct` Method:
//    - Annotate with `@GetMapping("/category/{name}/{category}")` to handle GET requests for filtering products by `name` and `category`.
//    - Use conditional filtering logic if `name` or `category` is `"null"`.
//    - Fetch products based on category using methods like `findByCategory()` or `findProductBySubNameAndCategory()`.
//    - Return filtered products in a `Map<String, Object>` with key `products`.
    @GetMapping("/category/{name}/{category}")
    public Map<String, Object> filterbyCategoryProduct(@PathVariable String name, @PathVariable String category) {
        Map<String, Object> map = new HashMap<>();
        if (name.equals("null")) {
            map.put(productRepository.findByCategory(category));
            return map;
        } else if (category.equals("null")) {
            map.put(productRepository.findProductBySubName(name));
            return map;
        }
        map.put(productRepository.findProductBySubNameAndCategory(name, category));
        return map;
    }

 // 7. Define the `listProduct` Method:
//    - Annotate with `@GetMapping` to handle GET requests to fetch all products.
//    - Fetch all products using `findAll()` method from `ProductRepository`.
//    - Return all products in a `Map<String, Object>` with key `products`.
    @GetMapping
    public Map<String, Object> listProduct() {
        Map<String, Object> map = new HashMap<>();
        map.put("products", productRepository.findAll());
        return map;
    }
// 8. Define the `getProductbyCategoryAndStoreId` Method:
//    - Annotate with `@GetMapping("filter/{category}/{storeid}")` to filter products by `category` and `storeId`.
//    - Use `findProductByCategory()` method from `ProductRepository` to retrieve products.
//    - Return filtered products in a `Map<String, Object>` with key `product`.
    @GetMapping("filter/{category}/{storeId}")
    public  Map<String, Object> getProductbyCategoryAndStoreId(@PathVariable String category, @PathVariable Long storeId) {
        Map<String, Object> map = new HashMap<>();
        List<Product> result = productRepository.findByCategoryAndStoreId(category, storeId);

        map.put("product", result);
        return map;

    }

// 9. Define the `deleteProduct` Method:
//    - Annotate with `@DeleteMapping("/{id}")` to handle DELETE requests for removing a product by its ID.
//    - Validate product existence using `ValidateProductId()` in `ServiceClass`.
//    - Remove product from `Inventory` first using `deleteByProductId(id)` in `InventoryRepository`.
//    - Remove product from `Product` using `deleteById(id)` in `ProductRepository`.
//    - Return a success message with key `message` indicating product deletion.
    @DeleteMapping("/{id}")
    public Map<String, String> deleteProduct(@PathVariable Long id) {
        Map<String, String> map = new HashMap<>();

        if (!serviceClass.validateProductId(id)) {
            map.put("message", "Id " + id + " not present in database");
            return map;
        }
        inventoryRepository.deleteByProductId(id);
        orderItemRepository.deleteByProductId(id);
        productRepository.deleteByProductId(id);

        map.put("messgae", "Deleted product successfully with id");
        return map;
    }

 // 10. Define the `searchProduct` Method:
//    - Annotate with `@GetMapping("/searchProduct/{name}")` to search for products by `name`.
//    - Use `findProductBySubName()` method from `ProductRepository` to search products by name.
//    - Return search results in a `Map<String, Object>` with key `products`.
    @GetMapping("/searchProduct/{name}")
    public Map<String, Object> searchProduct(@PathVariable String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("products", productRepository.findProductBySubName(name));
        return map;
    }

  
    
}
