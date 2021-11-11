<template>
  <div class="list row">
    <div class="col-md-6">
      <h4>상품 목록</h4>
      <ul class="list-group">
        <li class="list-group-item" :class="{active: index == currentIndex}"
        v-for="(item, index) in items" :key="item.itemId"
        @click="setActiveItem(item, index)">
          {{ item.itemName }}
        </li>
      </ul>
    </div>
    <div class="col-md-6">
      <div v-if="currentItem">
        <div>
          <label><strong>상품명 : </strong></label>{{ currentItem.itemName }}<br>
          <label><strong>재고량 : </strong></label>{{ currentItem.stockQuantity }}<br>
          <label><strong>가격 : </strong></label>{{ currentItem.itemPrice }}
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import ItemDataService from "@/services/ItemDataService";

export default {
  name: "item-list",
  data() {
    return {
      items: [],
      currentItem: null,
      currentIndex: -1
    };
  },
  methods: {
    findAllItems() {
      ItemDataService.findAllItems()
      .then(response => {
        this.items = response.data.data;
      })
      .catch(e => {
        console.log(e);
      })
    },

    setActiveItem(item, index) {
      this.currentItem = item;
      this.currentIndex = index;
    }
  },
  mounted() {
    this.findAllItems();
  }
};
</script>
<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>