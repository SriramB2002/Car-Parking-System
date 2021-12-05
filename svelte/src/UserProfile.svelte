<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
    import { navigate } from "svelte-navigator";
    import { Navbar, NavbarBrand, Nav, NavItem, TabContent, TabPane, Table, Button } from "sveltestrap/src";
    import { login } from "./stores";

    const signOut = () => {
        const auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(() => {
            console.log("SIGNED OUT");
            login.set({
                id: -1,
                first_name: "",
                last_name: "",
                username: "",
                password: "",
                address: "",
                car_reg: "",
                mobile: "",
                email: "",
                car_model: ""
            });
            navigate("UserLogin");
        });
    }

    let bool = 0;
    const check = () => {
        bool = 1;
    }

    let edit = false;
    const editProfile = () => {
        edit = true;
    }

    async function updateProfile(){
        edit = false;
        const res = await fetch("http://localhost:8080/updateUser", {
            method: 'POST',
            headers: {'content-type': 'application/json'},
            body: JSON.stringify($login)
         });
    }
    let amt = 0;
</script>

<main>
    {#if $login.first_name != ""}
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
                <Table bordered striped>
                    <tr>
                        <td>Name:</td>
                        <td>{$login.first_name + " " + $login.last_name}</td>
                    </tr>
                    <tr>
                        <td>Residential Address:</td>
                        <td>{$login.address}</td>
                    </tr>
                    <tr>
                        <td>Email ID:</td>
                        <td>{$login.email}</td>
                    </tr>
                    <tr>
                        <td>Mobile Number:</td>
                        <td>{$login.mobile}</td>
                    </tr>
                    <tr>
                        <td>Car Model</td>
                        <td>{$login.car_model}</td>
                    </tr>
                    <tr>
                        <td>Car Registration Number:</td>
                        <td>{$login.car_reg}</td>
                    </tr>
                </Table>
                <Button color="info" on:click={editProfile}>Edit Details</Button>
                {#if edit}
                    <p></p>
                    <p>First Name: <input type="text" bind:value={$login.first_name}></p>
                    <p>Last Name: <input type="text" bind:value={$login.last_name}></p>
                    <p>Residential Address: <input type="text" bind:value={$login.address}></p>
                    <p>Email ID <input type="text" bind:value={$login.email}></p>
                    <p>Mobile Number: <input type="text" bind:value={$login.mobile}></p>
                    <p>Car Model: 
                        <select bind:value={$login.car_model}>
                            <option></option>
                            <option>Sedan</option>
                            <option>SUV</option>
                            <option>Hatchback</option>
                        </select>
                    </p>
                    <p>Car Registration Number: <input type="text" bind:value={$login.car_reg}></p>
                    <Button color="success" on:click={updateProfile}>Update Profile</Button>
                {/if}
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
    {:else}
        <p>You are not logged in! Kindly <a href="/UserLogin">login</a></p>
    {/if}
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
</style>