<template>
  <v-app>
    <v-app-bar app dark>
      <v-btn to="/join" text>Join</v-btn>
      <v-btn to="/members" text>Members</v-btn>
      <v-btn to="/addItem" text>Register</v-btn>
      <v-btn to="/items" text>Items</v-btn>
      <v-btn to="/order" text>Order</v-btn>
    </v-app-bar>
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'App'
};
</script>
