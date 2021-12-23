<template>
  <v-row align="center" class="list px-3 mx-auto">
    <v-col cols="12" sm="12">
      <v-card>
        <v-card-title>Items</v-card-title>
        <v-data-table
            :headers="headers"
            :items="items"
            :loading="loading"
            disable-pagination
            :hide-default-footer="true"
        >
          <template v-slot:[`item.actions`]="{ item }">
            <v-icon small class="mx-2" @click="updateItem(item.id)">mdi-pencil</v-icon>
            <v-icon small @click="deleteItem(item.id)">mdi-delete</v-icon>
          </template>
        </v-data-table>
      </v-card>
    </v-col>
  </v-row>
</template>
<script>
import ItemDataService from "@/services/ItemDataService";

export default {
  name: "item-list",
  data() {
    return {
      items: [],
      currentItem: null,
      currentIndex: -1,
      headers: [
        { text: "Name", align: "start", sortable: false, value: "itemName" },
        { text: "StockQuantity", value: "stockQuantity", sortable: false },
        { text: "Price", value: "itemPrice", sortable: false },
        { text: "Actions", value: "actions", sortable: false }
      ],
      loading: false
    };
  },
  methods: {
    findAllItems() {
      if(this.loading) return;
      this.loading = true;
      ItemDataService.findAllItems()
      .then(response => {
        this.items = response.data.data;
        this.loading = false;
      })
      .catch(e => {
        console.log(e);
        this.loading = false;
      })
    },
    refreshList() {
      this.findAllItems();
    },
    deleteItem(id) {
      ItemDataService.deleteItem(id)
      .then(() => {
        this.refreshList();
      })
      .catch(e => {
        console.log(e);
      })
    },
    updateItem(id) {
      this.$router.push({name:"item-details", params: { id: id }})
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