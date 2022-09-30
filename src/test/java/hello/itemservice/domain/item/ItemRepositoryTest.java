package hello.itemservice.domain.item;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA",10000,10);
        //when
        Item savedItem= itemRepository.save(item);
        //then
        Item findItem= itemRepository.findById(item.getId());
        assertThat(savedItem).isEqualTo(findItem);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("item1",2000,5);
        Item item2 = new Item("item2",5000,1);
        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        Assertions.assertThat(result).hasSize(2);
        assertThat(result).contains(item1,item2);
    }

    @Test
    void update() {
        //given
        Item item = new Item("item1",10000,2);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();
        //when
        Item updateParam = new Item("item2", 4000, 1);
        itemRepository.updateItem(itemId,updateParam);

        //then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());

    }

    @Test
    void clearStore() {
    }
}