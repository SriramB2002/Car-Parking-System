<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
    import { navigate } from "svelte-navigator";
    import { Navbar, NavbarBrand, Nav, NavItem, TabContent, TabPane, Table, Button } from "sveltestrap/src";
import Spaces from "./Spaces.svelte";
    import { login } from "./stores";
    import { m } from "./stores";

    m.set("");

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
    const check = async () => {
        bool = 1;
        $login.balance += add;
        updateProfile();
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

    async function getBookings() {
        const res = await fetch("http://localhost:8080/getBookings?id=" + $login.id);
        const resp = await res.json();
        return resp;
    }

    let promise = getBookings();
    let add = 0;
    const ok = () => {
        bool = 0;
        add = 0;
    }

    function refresh() {
        promise = getBookings();
    }

    async function remove(booking) {
        const res = await fetch("http://localhost:8080/deleteBooking?id=" + booking.bookingID, {
            method: 'DELETE',
            headers: {'content-type': 'application/json'}
         });

         refresh();
    }

    function modify(booking) {
        m.set(booking.bookingID);
        navigate("UserDashboard");
    }

</script>

<main>
    {#if $login.id != -1}
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
            <TabPane tabId="2" tab="My Bookings">
                <Table bordered>
                    <tr>
                        <th>Booking ID</th>
                        <th>Checkin</th>
                        <th>Checkout</th>
                        <th>Slot</th>
                        <th>Cleaning Worker</th>
                        <th>Repair Worker</th>
                        <th>Washing Worker</th>
                        <th></th>
                    </tr>
                        {#await promise}
                            <p>Waiting...</p>
                        {:then bookings}
                        {#each bookings as book}
                        <tr>
                            <td>{book.bookingID}</td>
                            <td>{new Date(book.checkIn)}</td>
                            <td>{new Date(book.checkOut)}</td>
                            <td>{book.slotID}</td>
                            <td>{book.cleaningWorker}</td>
                            <td>{book.repairWorker}</td>
                            <td>{book.washingWorker}</td>
                            <td>
                                <div>
                                    <Button color="primary" on:click={modify(book)}>Modify</Button>
                                    <Button color="danger" on:click={remove(book)}>Remove</Button>
                                </div>
                            </td>
                        </tr>
                        {/each} 
                            
                        {/await}
                </Table>
            </TabPane>
            <TabPane tabId="3" tab="My Finances">
                <p>Balance in Account: {$login.balance}</p>
                <input type="number" bind:value={add}>
                <button on:click={check}>Add Money</button>
                {#if bool}
                    <p class="green-text">Rs. {add} added to account. <button on:click={ok}>OK</button></p>
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