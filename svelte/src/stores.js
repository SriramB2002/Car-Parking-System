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
    email: "",
    car_model: "",
    balance: 0
})

export const worker = writable({
    id: -1,
    name: "",
    pass: "",
    rating: 0,
    service: "",
    slotID: -1
})
export const admin = writable(0)

export const m = writable("")