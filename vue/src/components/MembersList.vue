<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" id="name" class="form-control" placeholder="검색할 이름을 입력하세요" v-model="name">
        <div class="input-group-prepend">
          <button type="button" class="btn btn-outline-secondary" @click="searchByName">검색</button>
        </div>
      </div>
    </div>

    <div class="col-md-6">
      <h4>회원 목록</h4>
      <ul class="list-group">
        <li class="list-group-item"
            :class="{ active: index === currentIndex }"
            v-for="(member, index) in members"
            :key="index"
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
      currentIndex: -1,
      name: ""
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