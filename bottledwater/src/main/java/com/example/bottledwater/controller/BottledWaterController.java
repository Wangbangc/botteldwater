package com.example.bottledwater.controller;

import com.example.bottledwater.entity.BottledWater;
import com.example.bottledwater.service.BottledWaterInterface;
import com.example.bottledwater.utils.APIResponse;
import com.example.bottledwater.utils.ImageStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/******
 * 创建日期：2023/10/23
 * 编写人：wangbangc
 * 功能描述：管理商品信息
 * 版本：1.0
 * 方法列表：
 * 1.创建商品信息：createProduct
 * 2.获取所有商品信息：getAllProducts
 * 3.根据id查询商品信息：getProductById
 * 4.更新商品信息：updateProduct
 * 5.模糊查询商品信息：selProduct
 * 6.根据id删除商品：delProduct
 * **/
@RestController
@RequestMapping("/bottledWaterManage")
public class BottledWaterController {

    @Autowired
    private BottledWaterInterface bottledWaterService;

    @PostMapping("/createProduct")
    public APIResponse<String> createProduct(@RequestParam("imageData") MultipartFile file,
                                             @RequestParam("brand") String brand,
                                             @RequestParam("categoryName") String categoryName,
                                             @RequestParam("price") Integer price,
                                             @RequestParam("description") String description) {
        BottledWater productInfo = new BottledWater();
        productInfo.setBrand(brand);
        productInfo.setCategoryName(categoryName);
        productInfo.setPrice(BigDecimal.valueOf(price));
        productInfo.setDescription(description);
        if (bottledWaterService.selectIdBottledWater(productInfo)) {
            return APIResponse.errorResponse(2, "商品类别已存在");
        }
        try {
            System.out.println(productInfo.getCategoryName());
            byte[] imageData = file.getBytes();
            String filename = productInfo.getCategoryName() + ".jpg";
            ImageStorageUtil.storeAndResizeImage(imageData, filename, 400, 300);

            // 设置图片 URL
            productInfo.setImageUrl("/images/" + filename);

            if (bottledWaterService.insert(productInfo)) {
                return APIResponse.successResponse1();
            } else {
                return APIResponse.errorResponse(2, "Failed to create product");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<APIResponse<List<Map<String, Object>>>> getAllProducts() {
        try {
            List<BottledWater> products = bottledWaterService.getAllBottledWater();

            List<Map<String, Object>> responseList = new ArrayList<>();
            for (BottledWater product : products) {
                Map<String, Object> productMap = new HashMap<>();
                productMap.put("id", product.getId());
                productMap.put("brand", product.getBrand());
                productMap.put("categoryName", product.getCategoryName());
                productMap.put("price", product.getPrice());
                productMap.put("description", product.getDescription());

                // 加载图片并将其转换为Base64
                byte[] imageBytes = ImageStorageUtil.loadImage(product.getImageUrl().substring(8)); // Assuming the URL is "/images/filename.jpg"

                String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
                productMap.put("image", imageBase64);

                responseList.add(productMap);
            }

            return ResponseEntity.ok(APIResponse.successResponse(responseList));
        } catch (Exception e) {
            return ResponseEntity.ok(APIResponse.errorResponse(1, e.getMessage()));
        }
    }

    //根据id查询
    @GetMapping("/getProductById/{id}")
    public ResponseEntity<APIResponse<Map<String, Object>>> getProductById(@PathVariable Integer id) {
        try {
            BottledWater product = bottledWaterService.selectByPrimaryKey(id);
            if (product == null) {
                return ResponseEntity.ok(APIResponse.errorResponse(404, "查找不存在"));
            }
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("id", product.getId());
            productMap.put("brand", product.getBrand());
            productMap.put("categoryName", product.getCategoryName());
            productMap.put("price", product.getPrice());
            productMap.put("description", product.getDescription());
            byte[] imageBytes = ImageStorageUtil.loadImage(product.getImageUrl().substring(8)); // Assuming the URL is "/images/filename.jpg"
            String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
            productMap.put("image", imageBase64);
            return ResponseEntity.ok(APIResponse.successResponse(productMap));
        } catch (Exception e) {
            return ResponseEntity.ok(APIResponse.errorResponse(500, e.getMessage()));
        }
    }


    //更新商品信息
    @PutMapping("/updateProduct")
    public APIResponse<String> updateProduct(@RequestParam("imageData") MultipartFile file,
                                             @RequestParam("id") Integer id,
                                             @RequestParam("brand") String brand,
                                             @RequestParam("categoryName") String categoryName,
                                             @RequestParam("price") Integer price,
                                             @RequestParam("description") String description) {
        BottledWater productInfo = new BottledWater();
        productInfo.setId(id);
        productInfo.setBrand(brand);
        productInfo.setCategoryName(categoryName);
        productInfo.setPrice(BigDecimal.valueOf(price));
        productInfo.setDescription(description);
        try {
            byte[] imageData = file.getBytes();
            String filename = productInfo.getCategoryName() + ".jpg";
            ImageStorageUtil.storeAndResizeImage(imageData, filename, 400, 300);

            // 设置图片 URL
            productInfo.setImageUrl("/images/" + filename);

            if (bottledWaterService.updateByPrimaryKey(productInfo)) {
                return APIResponse.successResponse1();
            } else {
                return APIResponse.errorResponse(2, "更新商品失败异常");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }

    //模糊查询商品信息
    @GetMapping("/selProduct")
    @CrossOrigin
    public ResponseEntity<APIResponse<List<Map<String, Object>>>> selProduct(String sel) {
        try {
            List<BottledWater> products = bottledWaterService.selProduct(sel);

            List<Map<String, Object>> responseList = new ArrayList<>();
            for (BottledWater product : products) {
                Map<String, Object> productMap = new HashMap<>();
                productMap.put("id", product.getId());
                productMap.put("brand", product.getBrand());
                productMap.put("categoryName", product.getCategoryName());
                productMap.put("price", product.getPrice());
                productMap.put("description", product.getDescription());

                // 加载图片并将其转换为Base64
                byte[] imageBytes = ImageStorageUtil.loadImage(product.getImageUrl().substring(8)); // Assuming the URL is "/images/filename.jpg"

                String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
                productMap.put("image", imageBase64);

                responseList.add(productMap);
            }

            return ResponseEntity.ok(APIResponse.successResponse(responseList));
        } catch (Exception e) {
            return ResponseEntity.ok(APIResponse.errorResponse(1, e.getMessage()));
        }
    }
    //6.根据id删除商品：delProduct
    @DeleteMapping("/delProduct/{id}")
    @CrossOrigin
    public APIResponse<String> delProduct(@PathVariable Integer id) {
        try {
            if (bottledWaterService.deleteByPrimaryKey(id)) {
                return APIResponse.successResponse1();
            } else {
                return APIResponse.errorResponse(2, "删除商品异常");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }

}
