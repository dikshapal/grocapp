package com.example.demo.resource;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductResource {
    @Autowired
    private ProductService productService;

    @RequestMapping (value="/add", method=RequestMethod.POST)
    public Product addProductPost(@RequestBody Product product) {
        return productService.save(product);
    }

    @RequestMapping(value="/add/image", method=RequestMethod.POST)
    public ResponseEntity upload(
            @RequestParam("id") Long id,
            HttpServletResponse response, HttpServletRequest request
    ){
        try {
            Product product = productService.findOne(id);
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> it = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(it.next());
            String fileName = id+".png";


            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/product/"+fileName)));
            stream.write(bytes);
            stream.close();

            return new ResponseEntity("Upload Success!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
        }
    }



    @RequestMapping(value="/update/image", method=RequestMethod.POST)
    public ResponseEntity updateImagePost(
            @RequestParam("id") Long id,
            HttpServletResponse response, HttpServletRequest request
    ){
        try {
            Product product = productService.findOne(id);
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> it = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(it.next());
            String fileName = id+".png";

            Files.deleteIfExists(Paths.get("src/main/resources/static/image/product/"+fileName));

            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/product/"+fileName)));
            stream.write(bytes);
            stream.close();

            return new ResponseEntity("Upload Success!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value="/remove", method=RequestMethod.POST)
    public ResponseEntity remove(
            @RequestBody String id
    ) throws IOException {
        productService.removeOne(Long.parseLong(id));
        String fileName = id+".png";

        Files.delete(Paths.get("src/main/resources/static/image/product/"+fileName));

        return new ResponseEntity("Remove Success!", HttpStatus.OK);
    }


    @RequestMapping("/productList")
    public List<Product> getBookList() {
        return productService.findAll();
    }


    @RequestMapping(value="/update", method=RequestMethod.POST)
    public Product updateBookPost(@RequestBody Product product) {
        return productService.save(product);
    }

    @RequestMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id){
        Product product = productService.findOne(id);
        return product;
    }

    @RequestMapping(value="/searchProduct", method=RequestMethod.POST)
    public List<Product> searchProduct (@RequestBody String keyword) {
        List<Product> productList = productService.blurrySearch(keyword);

        return productList;
    }

}