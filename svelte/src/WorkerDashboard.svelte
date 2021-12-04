<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
    import { worker } from "./stores";
    import { Navbar, NavbarBrand, Nav, NavItem } from "sveltestrap/src"
</script>

<main>
    <Navbar color="dark" dark expand="md">
        <NavbarBrand href="/WorkerDashboard">Car Parking System</NavbarBrand>
        <Nav class="ms-auto" navbar>
            <NavItem>
                <button class="green">Sign Out</button>
            </NavItem>
        </Nav>
    </Navbar>
    <br>
    <h1>{$worker}</h1>
    <p class="rating">Rating: </p>
    <h3>Parking Space: </h3>
    <h3>Slot: </h3>
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