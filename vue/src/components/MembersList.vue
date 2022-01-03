<template>
  <v-row align="center" class="list px-3 mx-auto">
    <v-col cols="12" sm="8">
      <v-text-field v-model="searchName" label="Search by Name"></v-text-field>
    </v-col>
    <v-col cols="12" sm="4">
      <v-btn @click="searchByName();">
        Search
      </v-btn>
    </v-col>
    <v-col cols="12" sm="12">
      <v-card>
        <v-card-title>Members</v-card-title>
        <v-data-table
            :headers="headers"
            :items="members"
            :loading="loading"
            disable-pagination
            :hide-default-footer="true"
        >
          <template v-slot:[`item.actions`]="{ item }">
            <v-icon small class="mx-2" @click="updateMember(item.id)">mdi-pencil</v-icon>
            <v-icon small @click="deleteMember(item.id)">mdi-delete</v-icon>
          </template>
        </v-data-table>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import MemberDataService from "../services/MemberDataService";

export default {
  name: "members-list",
  data() {
    return {
      members: [],
      currentMember: null,
      currentIndex: -1,
      searchName: "",
      headers: [
        { text: "Name", align: "start", sortable: false, value: "name" },
        { text: "City", value: "address.city", sortable: false },
        { text: "Street", value: "address.street", sortable: false },
        { text: "Zipcode", value: "address.zipcode", sortable: false },
        { text: "Actions", value: "actions", sortable: false }
      ],
      loading: false
    };
  },
  methods: {
    retrieveMembers() {
      if(this.loading) return;
      this.loading = true;
      MemberDataService.getAll()
          .then(response => {
            this.members = response.data.data;
            this.loading = false;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
            this.loading = false;
          });
    },
    refreshList() {
      this.retrieveMembers();
    },
    searchByName() {
      MemberDataService.findByName(this.searchName)
          .then(response => {
            this.members = response.data.data;
            console.log(response.data.data);
          })
          .catch(e => {
            console.log(e);
          })
    },
    updateMember(id) {
      this.$router.push({ name: "member-details", params: { id: id } });
    },
    deleteMember(id) {
      MemberDataService.deleteMember(id)
          .then(() => {
            this.refreshList();
          })
          .catch(e => {
            console.log(e);
          });
    }
  },
  mounted() {
    this.retrieveMembers();
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