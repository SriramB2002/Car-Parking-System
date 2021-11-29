<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
import { navigate } from "svelte-navigator";
import { Button, Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink, Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from "sveltestrap/src";
    import { login } from "./stores";

    const signOut = () => {
        const auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(() => {
            console.log("SIGNED OUT");
            login.set("");
            navigate("UserLogin");
        });
    }
    
</script>

<main>
    <Navbar color="dark" dark expand="md">
        <NavbarBrand href="/UserDashboard">Car Parking System</NavbarBrand>
        <button class="green" on:click={signOut}>Sign Out</button>
    </Navbar>
    <p>{$login}</p>
    <h1>Book your slot here</h1>
    <h6>Location</h6>
    <Dropdown>
        <DropdownToggle caret>Location</DropdownToggle>
        <DropdownMenu>
            <DropdownItem>Delhi</DropdownItem>
            <DropdownItem>Mumbai</DropdownItem>
            <DropdownItem>Chennai</DropdownItem>
            <DropdownItem>Hyderabad</DropdownItem>
        </DropdownMenu>
    </Dropdown>
    <br>
    <h6>Date</h6>
    <input type="date"><br><br>
    <h6>Checkin</h6>
    <input type="time"><br><br>
    <h6>Checkout</h6>
    <input type="time"><br><br>
</main>

<style>
    main {
		text-align: center;
		padding: 1em;
		margin: 0 auto;
	}

    h1 {
		color: #ff3e00;
		text-transform: uppercase;
		font-size: 2em;
		font-weight: 100;
	}

    button {
        border-radius: 5px;
    }

    .green {
        background-color: chartreuse;
    }
</style>