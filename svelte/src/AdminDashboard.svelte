<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
import { navigate } from "svelte-navigator";

    import { Navbar, NavbarBrand, Nav, NavItem, TabContent, TabPane } from "sveltestrap/src";
    import Data from "./Data.svelte";
    import Employees from "./Employees.svelte";
    import Spaces from "./Spaces.svelte";
import { admin } from "./stores";

    function signOut() {
        admin.set(0);
        navigate("AdminLogin");
    }
</script>

<main>
    <Navbar color="dark" dark expand="md">
        <NavbarBrand href="/AdminDashboard">Car Parking System</NavbarBrand>
        <Nav class="ms-auto" navbar>
            <NavItem>
                <button class="green" on:click={signOut}>Sign Out</button>
            </NavItem>
        </Nav>
    </Navbar>
    <br>
    <TabContent pills>
        <TabPane tabId="1" tab="User Data" active><Data/></TabPane>
        <TabPane tabId="2" tab="Employees"><Employees/></TabPane>
        <TabPane tabId="3" tab="Spaces"><Spaces/></TabPane>
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
</style>