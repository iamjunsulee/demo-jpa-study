package me.junsu.demojpastudy.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.junsu.demojpastudy.domain.Book;
import me.junsu.demojpastudy.domain.Item;
import me.junsu.demojpastudy.service.ItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemApiController {
    private final ItemService itemService;

    @PostMapping("/api/addBook")
    public CreateItemResponse registerItem(@RequestBody CreateItemRequest itemRequest) {
        //상품 카테고리가 필요해보임. 상품 타입이 뭔줄 알아야 해당 객체를 생성할 수 있지 않을까..?
        Book book = new Book(itemRequest.getItemName(), itemRequest.getStockQuantity(), itemRequest.getItemPrice(), itemRequest.getAuthor());
        Long saveItemId = itemService.saveItem(book);
        return new CreateItemResponse(saveItemId);
    }

    @Data
    @AllArgsConstructor
    static class CreateItemResponse {
        private Long id;
    }
    @Data
    static class CreateItemRequest {
        private String itemName;
        private int itemPrice;
        private int stockQuantity;
        private String author;
    }
}
