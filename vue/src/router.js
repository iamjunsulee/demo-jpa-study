import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "/members",
            name: "members",
            component: () => import("./components/MembersList")
        },
        {
            path: "/add",
            name: "add",
            component: () => import("./components/MemberJoin")
        },
        {
            path: "/addItem",
            name: "addItem",
            component: () => import("./components/AddItem")
        }
    ]
});