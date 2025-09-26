import {createRouter, createWebHashHistory} from "vue-router";
import Welcome from '@/components/WelcomeComponent.vue';
import ScootersOverview31 from "@/components/scooters/ScootersOverview31";
import ScootersOverview32 from "@/components/scooters/ScootersOverview32";
import UnknownRoute from "@/components/UnknownRoute.vue";
import ScootersOverview33 from "@/components/scooters/ScootersOverview33";
import ScootersDetail32 from "@/components/scooters/ScootersDetail32";
const routes = [{
    path: '/', component: Welcome},
    {path: '/scooters-list', component: ScootersOverview31},
    {path: '/scooters-detail', component: ScootersOverview32},
    {path: '/:pathMatch(.*)*', component: UnknownRoute},
    {path: '/scooters-overview', component: ScootersOverview33,
        children: [ { path: ':id', component: ScootersDetail32 }]},


];

export const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router