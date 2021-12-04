<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
    import { navigate } from "svelte-navigator";
    import { Navbar, NavbarBrand, Nav, NavItem, TabContent, TabPane } from "sveltestrap/src";

    const signOut = () => {
        const auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(() => {
            console.log("SIGNED OUT");
            login.set("");
            navigate("UserLogin");
        });
    }

    let bool = 0;
    const check = () => {
        bool = 1;
    }

    let amt = 0;
</script>

<main>
    <Navbar color="dark" dark expand="md">
        <NavbarBrand href="/UserDashboard">Car Parking System</NavbarBrand>
        <Nav class="ms-auto" navbar>
            <NavItem>
                <button class="green" on:click={signOut}>Sign Out</button>
            </NavItem>
        </Nav>
    </Navbar>
    <br>
    <TabContent vertical pills>
        <TabPane tabId="1" tab="My Profile" active>
            <ul class="list">
                <li>Name: </li>
                <li>Username: </li>
                <li>Password: </li>
                <li>Residential Address: </li>
                <li>Email ID: </li>
                <li>Mobile Number: </li>
                <li>Car Model</li>
                <li>Car Registration Number: </li>
            </ul>
        </TabPane>
        <TabPane tabId="2" tab="My Bookings"></TabPane>
        <TabPane tabId="3" tab="My Finances">
            <p>Balance in Account: </p>
            <input type="number" bind:value={amt}>
            <button on:click={check}>Add Money</button>
            {#if bool}
                <p class="green-text">Rs. {amt} added to account successfully!</p>
            {/if}
        </TabPane>
    </TabContent>
</main>

<style>
    button {
        border-radius: 5px;
        margin: 5px;
    }

    .green {
        background-color: chartreuse;
    }

    .green-text {
        color: green;
    }

    .list {
        list-style-type: none;
    }
</style>