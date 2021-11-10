<template>
  <div class = "submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">상품명</label>
        <input type="text" class="form-control" id="name" required v-model="book.name" name="name"/>
      </div>
      <div class="form-group">
        <label for="stockQuantity">재고량</label>
        <input type="text" class="form-control" id="stockQuantity" required v-model="book.stockQuantity" name="stockQuantity"/>
      </div>
      <div class="form-group">
        <label for="price">가격</label>
        <input type="text" class="form-control" id="price" required v-model="book.price" name="price"/>
      </div>
      <div class="form-group">
        <label for="author">작가</label>
        <input type="text" class="form-control" id="author" required v-model="book.author" name="author"/>
      </div>

      <button class="btn btn-success" @click="saveBook">Submit</button>
    </div>
    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newBook">Add</button>
    </div>
  </div>
</template>
<script>
import ItemDataService from "../services/ItemDataService"

export default {
  name: "add-book",
  data() {
    return {
      book: {
        id: null
      },
      submitted: false
    }
  },
  methods: {
    saveBook() {
      let data = {
        itemName: this.book.name,
        stockQuantity: this.book.stockQuantity,
        itemPrice: this.book.price,
        author: this.book.author
      }

      ItemDataService.addBook(data)
      .then(response => {
        this.book.id = response.data.id;
        this.submitted = true;
      })
      .catch(e => {
        console.log(e);
      })
    },
    newBook() {
      this.book = {};
      this.submitted = false;
    }
  }
};
</script>
<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>