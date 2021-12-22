<template>
  <div v-if="currentItem" class="edit-form py-3">
    <p class="headline">상품수정</p>
    <v-form ref="form" lazy-validation>
      <v-text-field
          v-model="currentItem.itemName"
          :rules="[(v) => !!v || '상품명은 필수입니다.']"
          label="상품명"
          required></v-text-field>
      <v-text-field
          v-model="currentItem.stockQuantity"
          :rules="[(v) => !!v || '재고량은 필수입니다.']"
          label="재고량"
          required></v-text-field>
      <v-text-field
          v-model="currentItem.itemPrice"
          :rules="[(v) => !!v || '상품가격은 필수입니다.']"
          label="상품가격"
          required></v-text-field>

      <v-btn color="error" small class="mr-2" @click="deleteItem">
        Delete
      </v-btn>

      <v-btn small color="primary" @click="updateItem">
        Edit
      </v-btn>
    </v-form>
  </div>
  <div v-else>
    <p>상품을 먼저 조회한 후, 수정할 상품을 선택하세요.</p>
  </div>
</template>
<script>
import ItemDataService from "@/services/ItemDataService";

export default {
  name: "item",
  data() {
    return {
      currentItem: null
    };
  },
  methods: {
    findItem(id) {
      ItemDataService.findById(id)
          .then(response => {
            this.currentItem = response.data.data;
            console.log(response.data.data);
          })
          .catch(e => {
            console.log(e);
          })
    },
    updateItem() {
      ItemDataService.updateItem(this.currentItem.id, this.currentItem)
          .then(() => {
            this.$router.push({name: "items"});
          })
          .catch(e => {
            console.log(e);
          })
    },
    deleteItem() {
      ItemDataService.deleteItem(this.currentMember.id)
          .then(() => {
            this.$router.push({name: "items"});
          })
          .catch(e => {
            console.log(e);
          })
    }
  },
  mounted() {
    this.findItem(this.$route.params.id);
  }
}
</script>
<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>