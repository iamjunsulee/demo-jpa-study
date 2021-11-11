<template>
  <div class="list row">
    <div class="col-md-6">
      <h4>회원 목록</h4>
      <ul class="list-group">
        <li class="list-group-item"
            :class="{ active: index == currentIndex }"
            v-for="(member, index) in members"
            :key="member.id"
            @click="setActiveMember(member, index)"
        >
          {{ member.name }}
        </li>
      </ul>
    </div>
    <div class="col-md-6">
      <div v-if="currentMember">
        <h4>Members</h4>
        <div>
          <label><strong>Name:</strong></label> {{ currentMember.name }}<br>
          <label><strong>Address:</strong></label> {{ currentMember.address.city }} {{ currentMember.address.street }} {{ currentMember.address.zipcode }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MemberDataService from "../services/MemberDataService";

export default {
  name: "members-list",
  data() {
    return {
      members: [],
      currentMember: null,
      currentIndex: -1
    };
  },
  methods: {
    retrieveTutorials() {
      MemberDataService.getAll()
          .then(response => {
            this.members = response.data.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    refreshList() {
      this.retrieveTutorials();
      this.currentMember = null;
      this.currentIndex = -1;
    },

    setActiveMember(member, index) {
      this.currentMember = member;
      this.currentIndex = index;
    }
  },
  mounted() {
    this.retrieveTutorials();
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