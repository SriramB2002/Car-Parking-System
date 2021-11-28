<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
    import { Button, Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink, Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from "sveltestrap/src";
    import Data from "./Data.svelte";
    import Employees from "./Employees.svelte";
    import Spaces from "./Spaces.svelte";
    let navv = 0;
    const nav1 = () => {
        navv = 1;
    }
    const nav2 = () => {
        navv = 2;
    }
    const nav3 = () => {
        navv = 3;
    }
</script>

<main>
    <Navbar color="dark" dark expand="md">
        <NavbarBrand>Car Parking System</NavbarBrand>
        <Nav class="ms-auto" navbar>
            <NavItem>
                <button class="primary" on:click={nav1}>Data</button>
            </NavItem>
            <NavItem>
                <button class="warning" on:click={nav2}>Employees</button>
            </NavItem>
            <NavItem>
                <button class="success" on:click={nav3}>Spaces</button>
            </NavItem>
        </Nav>
    </Navbar>
    {#if navv == 1}
        <Data/>
    {:else if navv == 2}
        <Employees/>
    {:else if navv == 3}
        <Spaces/>
    {/if}
</main>

<style>
	main {
		text-align: center;
		padding: 1em;
		margin: 0 auto;
	}

    button {
        border-radius: 5px;
        margin: 5px;
    }

    .primary {
        background-color: #61a0fe;
        color: white;
    }

    .warning {
        background-color: #ffd65d;
    }

    .success {
        background-color: #68b08f;
        color: white;
    }
</style>