package com.project.marketplace.rest;

import com.project.marketplace.entity.Product;
<<<<<<< HEAD
import com.project.marketplace.entity.Provider;
import com.project.marketplace.entity.Speciality;
import com.project.marketplace.service.ProviderService;
import com.project.marketplace.service.ProxyAdmin;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/provider")
public class ProviderController {
    private final ProviderService providerService;
    private final ProxyAdmin proxyAdmin;

    public ProviderController(ProviderService providerService, ProxyAdmin proxyAdmin) {
        this.providerService = providerService;
        this.proxyAdmin = proxyAdmin;
    }

    @PutMapping("/updateProfil")
    public boolean updateProfil(@PathVariable long id, @Valid @RequestBody Provider provider) {
        return this.providerService.updateProfil(id,provider);
    }

    @PostMapping("/addproduct")
    public Product addProduct(@Valid @RequestBody Product product) {
        return this.providerService.addProduct(product);
    }

    @PutMapping("/updateproduct")
    public Product updateProduct(@PathVariable long idProduct, @Valid @RequestBody Product newProduct) {
        return this.providerService.updateProduct(idProduct,newProduct);
    }

    @DeleteMapping("/deleteproduct")
    public boolean deleteProduct(@PathVariable long id) {
        return  this.providerService.deleteProduct(id);
    }

    @GetMapping("/getproduct/{id}")
    public Product getProduct(@PathVariable long id) {
        return this.providerService.getProduct(id);
    }

    @GetMapping("/specialities")
    public List<Speciality> getAllSpecialitys() {
        return this.providerService.getAllSpecialitys();
    }

=======
import com.project.marketplace.service.ProviderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/Provider")
public class ProviderController {
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping("/add")
    public Product addAllProduct(@Valid @RequestBody Product product) {
        return this.providerService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProductById(@PathVariable long id,@RequestBody Product product){
        return this.providerService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProductById(@PathVariable long id) {
        this.providerService.deleteProduct(id);
        return true;
    }

    @GetMapping("/{id}")
    public Product getProductById(long id) {
        return this.providerService.getProduct(id);
    }
>>>>>>> remotes/origin/master
}