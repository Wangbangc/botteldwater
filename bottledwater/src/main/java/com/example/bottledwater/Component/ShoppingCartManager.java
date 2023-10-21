package com.example.bottledwater.Component;

import com.example.bottledwater.DTO.OrderDetailDTO;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ShoppingCartManager {
    private static final String SHOPPING_CART_KEY_PREFIX = "shopping_cart:";

  @Resource
    private RedisTemplate<String, Object> redisTemplate;
//    如果你非得使用其他类型的 比如 RedisTemplate<String,Objec> 那么请使用 @Resource 注解
//    @Autowired 按类型装配 byType
//    @Resource 按bean名字装配 byName
    // 添加商品到购物车
    public void addToCart(int userId, OrderDetailDTO orderDetailDTO) {
        String cartKey = getCartKey(userId);

        // 构建购物车中的商品项
        OrderDetailDTO cartItem = new OrderDetailDTO();
        cartItem.setBottledWaterCategory(orderDetailDTO.getBottledWaterCategory());
        cartItem.setQuantity(orderDetailDTO.getQuantity());
        cartItem.setUnitPrice(orderDetailDTO.getUnitPrice());

        // 将购物车项添加到Redis中
        redisTemplate.opsForHash().put(cartKey, orderDetailDTO.getBottledWaterCategory(), cartItem);
    }

    // 从购物车中移除商品
    public void removeFromCart(int userId, String bottledWaterCategory) {
        String cartKey = getCartKey(userId);

        // 从Redis中移除购物车项
        redisTemplate.opsForHash().delete(cartKey, bottledWaterCategory);
    }

    // 获取用户的购物车
    public Map<Object, Object> getCart(int userId) {
        String cartKey = getCartKey(userId);
        return redisTemplate.opsForHash().entries(cartKey);
    }


    // 清空用户的购物车
    public void clearCart(int userId) {
        String cartKey = getCartKey(userId);

        // 删除用户的购物车数据
        redisTemplate.delete(cartKey);
    }

    private String getCartKey(int userId) {
        return SHOPPING_CART_KEY_PREFIX + userId;
    }
}
