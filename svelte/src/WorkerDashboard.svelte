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
    <p>{$worker}</p>
    <p>Rating: </p>
    <h3>Your Slot is: </h3>
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
    button {
        border-radius: 5px;
        margin: 5px;
    }

    .green {
        background-color: chartreuse;
    }
</style>