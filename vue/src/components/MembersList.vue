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
            disable-pagination
            :hide-default-footer="true"
        >
          <template v-slot:[`item.actions`]="{ item }">
            <v-icon small @click="deleteMember(item.id)">
              mdi-delete
            </v-icon>
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
        { text: "Actions", value: "actions", sortable: false },
      ]
    };
  },
  methods: {
    retrieveMembers() {
      MemberDataService.getAll()
          .then(response => {
            this.members = response.data.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },
    setActiveMember(member, index) {
      this.currentMember = member;
      this.currentIndex = index;
    },
    searchByName() {
      MemberDataService.findByName(this.searchName)
          .then(response => {
            this.members = response.data.data;
            this.setActiveMember(null, -1);
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          })
    },
    deleteMember(id) {
      MemberDataService.deleteMember(id)
          .then(response => {
            this.members = response.data.data;
            console.log(response.data);
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