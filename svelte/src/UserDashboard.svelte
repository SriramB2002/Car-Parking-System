<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
import { navigate } from "svelte-navigator";
import { Navbar, NavbarBrand, Nav, NavItem } from "sveltestrap/src";
    import { login } from "./stores";

    const signOut = () => {
        const auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(() => {
            console.log("SIGNED OUT");
            login.set("");
            navigate("UserLogin");
        });
    }
    
    const nav = () => {
        navigate("UserProfile");
    }
</script>

<main>
    <Navbar color="dark" dark expand="md">
        <NavbarBrand href="/UserDashboard">Car Parking System</NavbarBrand>
        <Nav class="ms-auto" navbar>
            <NavItem>
                <button class="orange" on:click={nav}>View Profile</button>
            </NavItem>
            <NavItem>
                <button class="green" on:click={signOut}>Sign Out</button>
            </NavItem>
        </Nav>
    </Navbar>
    <p>{$login}</p>
    <h1>Book your slot here</h1>
    <h6>Location</h6>
    <select>
        <option>Delhi</option>
        <option>Mumbai</option>
        <option>Chennai</option>
        <option>Hyderabad</option>
    </select>
    <br><br>
    <h6>Date</h6>
    <input type="date"><br><br>
    <h6>Checkin</h6>
    <input type="time"><br><br>
    <h6>Checkout</h6>
    <input type="time"><br><br>
    <button class="blue">Book Slot</button>
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

    .orange {
        background-color: rgb(255, 112, 60);
    }

    .green {
        background-color: chartreuse;
    }

    .blue {
        background-color: royalblue;
        color: white;
        border: 2px solid black;
    }
</style>