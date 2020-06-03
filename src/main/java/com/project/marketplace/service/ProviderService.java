package com.project.marketplace.service;

import com.project.marketplace.entity.Image;
import com.project.marketplace.entity.Product;
import com.project.marketplace.entity.Provider;
import com.project.marketplace.entity.Speciality;
import com.project.marketplace.repository.ProductRepository;
import com.project.marketplace.repository.ProviderRepository;
import com.project.marketplace.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final SpecialityRepository specialityRepository;
    private final ProductRepository productRepository;

    public ProviderService(ProviderRepository providerRepository, SpecialityRepository specialityRepository, ProductRepository productRepository) {
        this.providerRepository = providerRepository;
        this.specialityRepository = specialityRepository;
        this.productRepository = productRepository;
//        this.initDB();
//        this.updateDB();
//        System.out.println(this.afficher(this.getAdmin()));
    }

    private void initDB() {
        for(int i=0;i<10;i++) {
            Product product = new Product();
            product.setName("Product " + (i + 1) + " Name ");
            product.setDescription("Product " + (i + 1) + " Description ");
            product.setCatalogue("Product " + (i + 1) + " Catalogue ");
            product.setMarque("Product " + (i + 1) + " Marque ");
            this.addProduct(product);
        }
    }

    public Provider getProvider(long id){
        return this.providerRepository.findById(id).orElseThrow();
    }

    public Provider getProviderByEmail(String mail){
        return this.providerRepository.findFirstByEmailEquals(mail);
    }

    public Provider getAdmin(){
        return this.providerRepository.findFirstByAdminEquals(true);
    }

    private void updateDB() {
        for(int i=0;i<10;i++){
            Provider provider = this.providerRepository.findById((long)(i+1+10)).orElseThrow();
            Product product = this.productRepository.findById((long)(i+1)).orElseThrow();
            product.setProvider(provider);
            this.updateProduct(product.getId(),product);
        }
    }


//    public Provider getAdmin() {
//        return this.providerRepository.findFirstByAdminEquals(true);
//    }

    public boolean updateProfil(long id, Provider provider) {
        Provider provider1 = this.providerRepository.findById(provider.getId()).orElseThrow();
        if (!provider1.equals(null)){
            provider.setId(id);
            this.providerRepository.save(provider);
            return true;
        }
        return false;
    }


    /*Start product */
    public Product addProduct(Product product) {
        return this.productRepository.save(product);
    }

    public long getLastProductId(){
        List<Product> produits = this.productRepository.findAll();
        long id=-1;
        if(produits.size()==0) {
            id=1;
        }else{
            for (Product product1: produits) {
                if(product1.getId()>id)
                    id=product1.getId();
            }
        }
        return id;
    }

    public Product updateProduct(long idProduct, Product newProduct) {
        Product oldProduct = getProduct(idProduct);
        if(oldProduct!=null){
            newProduct.setId(idProduct);
//            System.out.println("success");
            return this.productRepository.save(newProduct);
        }
//        System.out.println("failed ");
        return null;
    }

    public boolean deleteProduct(long id) {
        this.productRepository.deleteById(id);
        return true;
    }
//    public String afficher( Provider Admin){
//        return "Admin ["+ Admin.getFirstname()+"; "+ Admin.getLastname()+"; "+ Admin.getEmail()+"; "
//                + Admin.getPassword()+"; "+ Admin.getTel()+"; "+ Admin.getStatus()+"] ";
//    }

    public Product getProduct(long id) {
        return this.productRepository.findById(id).orElseThrow();
    }

    public List<Product> getOwnedProducts(long id) {
        List<Product> products =null;
        products =this.productRepository.findAllByProviderEquals(this.getProvider(id));
        return products;
    }

    public Product[] getOwnedProducts() {
        throw new UnsupportedOperationException();
    }
    /*End product */

    /*Start speciality */
//    public boolean addSpeciality(Speciality speciality) {
//        this.specialityRepository.save(speciality);
//        return true;
//    }

    public boolean updateSubscription(int subscription) {
        throw new UnsupportedOperationException();
    }

    public List<Speciality> getAllSpecialitys() {
        return this.specialityRepository.findAll();
    }

//    public boolean deleteSpeciality() {
//        throw new UnsupportedOperationException();
//    }

    /*End speciality */
}
