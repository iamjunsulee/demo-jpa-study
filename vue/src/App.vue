<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/members" class="nav-link">회원목록</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/add" class="nav-link">회원등록</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/addItem" class="nav-link">상품등록</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/items" class="nav-link">상품목록</router-link>
        </li>
      </div>
    </nav>

    <div class="container mt-3">
      <router-view />
    </div>
  </div>
</template>

<script>

export default {
  name: 'App'
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
