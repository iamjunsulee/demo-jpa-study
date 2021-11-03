<template>
  <div class = "submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" required v-model="member.name" name="name"/>
      </div>
      <div class="form-group">
        <label for="city">City</label>
        <input type="text" class="form-control" id="city" required v-model="member.city" name="city"/>
      </div>
      <div class="form-group">
        <label for="street">Street</label>
        <input type="text" class="form-control" id="street" required v-model="member.street" name="street"/>
      </div>
      <div class="form-group">
        <label for="zipcode">Zipcode</label>
        <input type="text" class="form-control" id="zipcode" required v-model="member.zipcode" name="zipcode"/>
      </div>

      <button class="btn btn-success" @click="saveMember">Submit</button>
    </div>
    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newMember">Add</button>
    </div>
  </div>
</template>

<script>
import MemberDataService from "../services/MemberDataService";

export default {
  name: "add-member",
  data() {
    return {
      member: {
        id: null
      },
      submitted: false
    };
  },
  methods: {
    saveMember() {
      let data = {
        name: this.member.name,
        address: {
          city: this.member.city,
          street: this.member.street,
          zipcode: this.member.zipcode
        }
      };

      MemberDataService.join(data)
      .then(response => {
        this.member.id = response.data.id;
        console.log(response.data);
        this.submitted = true;
      })
      .catch(e => {
        console.log(e);
      })
    },
    newMember() {
      this.member = {};
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