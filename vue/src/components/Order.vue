<template>
  <div class = "submit-form mt-3 mx-auto">
    <p class="headline">상품주문</p>
    <v-form ref="form" lazy-validation>
      <v-combobox
          dense
          solo
          :items="items"
          item-text="itemName"
          item-value="id"
          label="상품을 선택하세요"
          v-model="selected"
          @change="setOrderPrice"
      >
      </v-combobox>
      <v-text-field
        v-model.number="orderItem.orderQuantity"
        :rules="[v => !!v || '주문수량은 필수입니다.', v => (v > 0) || '주문수량은 0보다 커야합니다.']"
        label="주문수량"
        required
      >
        <v-icon
            slot="append-outer"
            color="blue"
            @click="increment"
        >
          mdi-plus
        </v-icon>
        <v-icon
            slot="prepend"
            color="red"
            @click="decrement"
        >
          mdi-minus
        </v-icon>
      </v-text-field>
      <v-text-field
          v-model="orderItem.orderPrice"
          :rules="[v => !!v || '주문가격은 필수입니다.']"
          label="주문가격"
          required
          disabled
      ></v-text-field>
    </v-form>
    <v-btn color="primary" class="mt-3" @click="createOrder">주문</v-btn>
  </div>
</template>

<script>
import ItemDataService from "@/services/ItemDataService";
import OrderDataService from "@/services/OrderDataService";

export default {
  name: "order",
  data() {
    return {
      selected: null,
      items: [],
      orderItem: {
        itemId: "",
        orderQuantity: 0,
        orderPrice: 0
      },
      order: {
        id: null
      }
    }
  },
  methods: {
    getAllItems() {
      ItemDataService.findAllItems()
      .then(response => {
        this.items = response.data.data;
        console.log(response.data.data);
      })
      .catch(e => {
        console.log(e);
      })
    },
    setOrderPrice() {
      this.orderItem.orderPrice = this.selected.itemPrice;
      this.orderItem.itemId = this.selected.id;
      this.orderItem.orderQuantity = 0;
    },
    increment() {
      this.orderItem.orderQuantity = parseInt(this.orderItem.orderQuantity, 10) + 1;
    },
    decrement() {
      this.orderItem.orderQuantity = parseInt(this.orderItem.orderQuantity, 10) - 1;
    },
    createOrder() {
      OrderDataService.createOrder(this.orderItem)
      .then(response => {
        this.order.id = response.data.id;
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      })
    }
  },
  mounted() {
    this.getAllItems();
  }
}
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>