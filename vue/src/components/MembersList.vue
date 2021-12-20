<template>
  <v-card>
    <v-card-title>
      Members
      <v-spacer></v-spacer>
      <v-text-field
          v-model="searchName"
          append-icon="mdi-magnify"
          label="search"
          single-line
          hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="members"
        :search="searchName"
        disable-pagination
        :hide-default-footer="true"
    ></v-data-table>
  </v-card>
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
        { text: "Zipcode", value: "address.zipcode", sortable: false }
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
      MemberDataService.findByName(this.name)
          .then(response => {
            this.members = response.data.data;
            this.setActiveMember(null, -1);
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          })
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