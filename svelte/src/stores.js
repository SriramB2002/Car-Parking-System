import { writable } from 'svelte/store'

export const login = writable({
    id: -1,
    first_name: "",
    last_name: "",
    username: "",
    password: "",
    address: "",
    car_reg: "",
    mobile: "",
    email: ""
})
export const worker = writable({})
export const admin = writable(0)