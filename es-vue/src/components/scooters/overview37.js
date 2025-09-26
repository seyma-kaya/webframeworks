import {createRouter, createWebHashHistory} from "vue-router";
import Welcome from '@/components/WelcomeComponent.vue';
import ScootersOverview31 from "@/components/scooters/ScootersOverview31";
import ScootersOverview32 from "@/components/scooters/ScootersOverview32";
import UnknownRoute from "@/components/UnknownRoute.vue";
import ScootersOverview33 from "@/components/scooters/ScootersOverview33";
import ScootersDetail34 from "@/components/scooters/ScootersDetail34";
import ScootersOverview37 from "@/components/scooters/ScootersOverview37";
import ScootersDetail37 from "@/components/scooters/ScootersDetail37";
import SignIn from "@/components/SignIn";
const routes = [{
    path: '/', component: Welcome},
    {path: '/scooters-list', component: ScootersOverview31},
    {path: '/scooters-detail', component: ScootersOverview32},
    {path: '/:pathMatch(.*)*', component: UnknownRoute},
    {path: '/scooters-overview', component: ScootersOverview33,
        children: [{path: ':id', component: ScootersDetail34}]},
    {path: '/scooters-overview37', component: ScootersOverview37,
        children: [{path: ':id', component: ScootersDetail37}]},
    {path: '/sign-in', component: SignIn, props: true},
    {path: '/sign-out', component: SignIn}
];

export const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router