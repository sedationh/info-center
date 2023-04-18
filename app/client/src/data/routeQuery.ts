import { useRouter } from 'vue-router';
const router = useRouter();
export const routerQuery = ()=> {
    return router.currentRoute.value.query
}