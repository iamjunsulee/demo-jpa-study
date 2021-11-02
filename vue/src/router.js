import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "/api/v2/members",
            name: "members",
            component: () => import("./components/MembersList")
        }
    ]
});