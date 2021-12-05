<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
    import { worker } from "./stores";
    import { Navbar, NavbarBrand, Nav, NavItem } from "sveltestrap/src"
import { navigate } from "svelte-navigator";

    let promise = getSlot();

    async function getSlot() {
        const res = await fetch("http://localhost:8080/getSlot?id=" + $worker.slotID, {
            headers: {'content-type': 'application/json'}
        });
        const resp = await res.json();
        console.log(resp);
        return resp;
    }

    function logOut() {
        worker.set({
            id: -1,
            name: "",
            pass: "",
            rating: 0,
            service: "",
            slotID: -1
        })

        navigate("WorkerLogin");
    }

    let service = "";

    if($worker.service != null) service = $worker.service;

    async function updateService() {
        const res = await fetch("http://localhost:8080/update?service=" + service + "&id=" + $worker.id, {
            method: 'POST',
            headers: {'content-type': 'application/json'}
        });

        const resp = await res.text();
    }
</script> 

<main>
    <Navbar color="dark" dark expand="md">
        <NavbarBrand href="/WorkerDashboard">Car Parking System</NavbarBrand>
        <Nav class="ms-auto" navbar>
            <NavItem>
                <button class="green" on:click={logOut}>Sign Out</button>
            </NavItem>
        </Nav>
    </Navbar>
    <br>
    <p class="rating">Rating: {$worker.rating}</p>

    {#await promise}
        <p>Loading slot info...</p>
    {:then slot}
    <h3>Parking Space: {slot.location}</h3>
    <h3>Slot: {slot.id}</h3>
    {/await}
    <br>
    <p>Choose your service: 
        <select bind:value={service}>
            <option></option>
            <option>Dry Cleaning</option>
            <option>Car Washing</option>
            <option>Repair Work</option>
        </select>
    </p>
    <button on:click={updateService}>Submit</button>
</main>

<style>
    button {
        border-radius: 5px;
        margin: 5px;
    }

    .rating {
        font-size: 1.5em;
        color: green;
    }

    .green {
        background-color: chartreuse;
    }
</style>