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
        <select>
            <option></option>
            <option>Dry Cleaning</option>
            <option>Car Washing</option>
            <option>Repair & Maintenance</option>
        </select>
    </p>
    <button>Submit</button>
</main>

<style>
	h1 {
		color: #ff3e00;
		text-transform: uppercase;
		font-size: 2em;
		font-weight: 100;
	}

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